package com.mscncn.portal.common.mybatis.pagination;

import org.apache.log4j.Logger;

public class OracleStrategy implements IStrategy {
	private Logger logger = Logger.getLogger(OracleStrategy.class);

	/**
	 * Oracle 分页语句
	 * 
	 * @param page
	 * @param sqlBuffer
	 * @return 分页sql
	 */
	@Override
	public String getPageSql(Pagination<?> page, String sql) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		// 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
		int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
		sqlBuffer.insert(0, "select u.*, rownum r from (")
				.append(") u where rownum < ")
				.append(offset + page.getPageSize());
		sqlBuffer.insert(0, "select * from (").append(") where r >= ")
				.append(offset);
		// 上面的Sql语句拼接之后大概是这个样子：
		// select * from (select u.*, rownum r from (select * from t_user) u
		// where rownum < 31) where r >= 16
		logger.info("[--<>分页插件<>--]:生成Oracle分页语句为:" + sqlBuffer);
		return sqlBuffer.toString();
	}

}
