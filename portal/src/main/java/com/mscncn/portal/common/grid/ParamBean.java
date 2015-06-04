package com.mscncn.portal.common.grid;

import java.util.HashMap;
import java.util.Map;

public class ParamBean {
	private Integer rows;
	private Integer page;
	private Map<String, Object> queryParams = new HashMap<String, Object>();
	private Map<String, Object> params = new HashMap<String, Object>();

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "ParamBean [rows=" + rows + ", page=" + page + ", queryParams="
				+ queryParams + ", params=" + params + "]";
	}
}
