package com.mscncn.portal.menu.model;

import java.io.Serializable;

public class GridMenu implements Serializable {
	private static final long serialVersionUID = 7752596687961894775L;
	/**
	 * 主键字段，菜单id
	 */
	private Integer id;
	/**
	 * 菜单名称
	 */
	private String text;
	/**
	 * 菜单图标
	 */
	private String iconCls;
	/**
	 * 父菜单ID
	 */
	private Integer parentId;
	/**
	 * 排序字段
	 */
	private Integer orderValue;
	/**
	 * 属性url
	 */
	private String url;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "GridMenu [id=" + id + ", text=" + text + ", iconCls=" + iconCls
				+ ", parentId=" + parentId + ", orderValue=" + orderValue
				+ ", url=" + url + "]";
	}

}
