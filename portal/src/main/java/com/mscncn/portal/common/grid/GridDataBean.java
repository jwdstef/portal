package com.mscncn.portal.common.grid;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 类名称: GridPageBean
 * 类描述: 封装适合easyui的分页实体,直接将该实体转换成json字符串传递到前台
 * @author King-Pan
 * @date   2015年3月17日
 * @version V1.0
 */
public class GridDataBean<T> implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8339161406655274655L;
	/**
	 * 数据总页数
	 */
	private Integer total;
	
	/**
	 * 分页数据
	 */
	private List<T> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "GridPageBean [total=" + total + ", rows=" + rows + "]";
	}
}
