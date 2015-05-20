package com.mscncn.portal.common.mybatis.pagination;

public interface IStrategy {
	String getPageSql(Pagination<?> page, String sql);
}
