package com.mscncn.firstsystem.common.mybatis.pagination;

import org.apache.log4j.Logger;

public class MySQLStrategy implements IStrategy {

	private Logger logger = Logger.getLogger(MySQLStrategy.class);

	/**
	 * Mysql 分页语句
	 * 
	 * @param page
	 * @param sqlBuffer
	 * @return 分页sql
	 */
	@Override
	public String getPageSql(Pagination<?> page, String sql) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		int offset = (page.getPageNo() - 1) * page.getPageSize();
		sqlBuffer.append(" limit ").append(offset).append(",")
				.append(page.getPageSize());
		logger.info("[--<>分页插件<>--]:生成Mysql分页语句为:" + sqlBuffer);
		return sqlBuffer.toString();
	}

}
