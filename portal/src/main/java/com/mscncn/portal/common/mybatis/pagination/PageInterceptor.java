package com.mscncn.portal.common.mybatis.pagination;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.log4j.Logger;


@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
	
	private Logger logger=Logger.getLogger(PageInterceptor.class);
	/**
	 * 数据库策略
	 */
	private IStrategy iStrategy;

	public IStrategy getiStrategy() {
		return iStrategy;
	}

	public void setiStrategy(IStrategy iStrategy) {
		this.iStrategy = iStrategy;
	}

	/**
	 * 拦截器的执行方法
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		/**
		 * 对于StatementHandler其实只有两个实现类， 一个是RoutingStatementHandler，
		 * 另一个是抽象类BaseStatementHandler
		 */
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation
				.getTarget();
		/**
		 * 通过反射获取到当前RoutingStatementHandler对象的delegate属性
		 */
		StatementHandler delegate = (StatementHandler) ReflectUtil
				.getFieldValue(handler, "delegate");
		/**
		 * 获取到当前StatementHandler的 boundSql， 这里不管是调用handler.getBoundSql()还是直接调用
		 * delegate.getBoundSql()结果是一样的，因为之前已经说过了
		 * RoutingStatementHandler实现的所有StatementHandler
		 * 接口方法里面都是调用的delegate对应的方法。
		 */
		BoundSql boundSql = delegate.getBoundSql();
		/**
		 * 拿到当前绑定Sql的参数对象， 就是我们在调用对应的Mapper映射语句时所传入的参数对象
		 */
		Object obj = boundSql.getParameterObject();
		/**
		 * 虽然每次都执行了拦截方法，但是没有真正的进行数据库的分页逻辑 if 中实现了分页逻辑，那么我们的核心逻辑都是在if中
		 */
		if (obj instanceof Pagination<?>) {
			Pagination<?> page = (Pagination<?>) obj;
			/**
			 * 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
			 */
			MappedStatement mappedStatement = (MappedStatement) ReflectUtil
					.getFieldValue(delegate, "mappedStatement");
			/**
			 * 拦截到的prepare方法参数是一个Connection对象
			 */
			Connection connection = (Connection) invocation.getArgs()[0];
			/**
			 * 获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
			 */
			String sql = boundSql.getSql();
			// 给当前的page参数对象设置总记录数
			this.setTotalRecord(page, mappedStatement, connection);
			// 获取分页Sql语句
			String pageSql = iStrategy.getPageSql(page, sql);
			// 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
			ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
		}
		return invocation.proceed();
	}

	/**
	 * 给当前的参数对象page设置总记录数
	 * 
	 * @param page
	 *            Mapper映射语句对应的参数对象
	 * @param mappedStatement
	 *            Mapper映射语句 
	 * @param connection
	 *            当前的数据库连接
	 */
	private void setTotalRecord(Pagination<?> page, MappedStatement mappedStatement,
			Connection connection) {
		// 获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
		// delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
		BoundSql boundSql = mappedStatement.getBoundSql(page);
		// 获取到我们自己写在Mapper映射语句中对应的Sql语句
		String sql = boundSql.getSql();
		// 通过查询Sql语句获取到对应的计算总记录数的sql语句
		String countSql = this.getCountSql(sql);
		logger.info("[--<>分页插件<>--]:生成查询记录总数SQL:"+countSql);
		// 通过BoundSql获取对应的参数映射
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		// 利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
		BoundSql countBoundSql = new BoundSql(
				mappedStatement.getConfiguration(), countSql,
				parameterMappings, page);
		// 通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
		ParameterHandler parameterHandler = new DefaultParameterHandler(
				mappedStatement, page, countBoundSql);
		// 通过connection建立一个countSql对应的PreparedStatement对象。
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			// 通过parameterHandler给PreparedStatement对象设置参数
			parameterHandler.setParameters(pstmt);
			// 之后就是执行获取总记录数的Sql语句和获取结果了。
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int totalRecord = rs.getInt(1);
				// 给当前的参数page对象设置总记录数
				page.setTotalRecord(totalRecord);
				logger.info("[--<>分页插件<>--]:查询的总记录数为:"+totalRecord);
			}
		} catch (Exception e) {
			logger.error("执行sql出错,sql="+countSql, e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				logger.error("关闭数据库连接出错", e);
			}
		}
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 * 
	 * @param sql
	 * @return
	 */
	private String getCountSql(String sql) {
		int index = sql.indexOf("from");
		return "select count(*) " + sql.substring(index);
	}

	/**
	 * 拦截器对应的封装原始对象的方法
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 设置拦截器中数据库的类型
	 */
	public void setProperties(Properties properties) {
	}

	/**
	 * 利用反射进行操作的一个工具类
	 * 
	 */
	private static class ReflectUtil {
		/**
		 * 利用反射获取指定对象的指定属性
		 * 
		 * @param obj
		 *            目标对象
		 * @param fieldName
		 *            目标属性
		 * @return 目标属性的值
		 */
		public static Object getFieldValue(Object obj, String fieldName) {
			Object result = null;
			Field field = ReflectUtil.getField(obj, fieldName);
			if (field != null) {
				field.setAccessible(true);
				try {
					result = field.get(obj);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		/**
		 * 利用反射获取指定对象里面的指定属性
		 * 
		 * @param obj
		 *            目标对象
		 * @param fieldName
		 *            目标属性
		 * @return 目标字段
		 */
		private static Field getField(Object obj, String fieldName) {
			Field field = null;
			for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
					.getSuperclass()) {
				try {
					field = clazz.getDeclaredField(fieldName);
					break;
				} catch (NoSuchFieldException e) {
					// 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
				}
			}
			return field;
		}

		/**
		 * 利用反射设置指定对象的指定属性为指定的值
		 * 
		 * @param obj
		 *            目标对象
		 * @param fieldName
		 *            目标属性
		 * @param fieldValue
		 *            目标值
		 */
		public static void setFieldValue(Object obj, String fieldName,
				String fieldValue) {
			Field field = ReflectUtil.getField(obj, fieldName);
			if (field != null) {
				try {
					field.setAccessible(true);
					field.set(obj, fieldValue);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
