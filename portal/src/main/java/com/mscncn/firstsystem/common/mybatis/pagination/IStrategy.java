package com.mscncn.firstsystem.common.mybatis.pagination;

public interface IStrategy {
	String getPageSql(Pagination<?> page, String sql);
}
