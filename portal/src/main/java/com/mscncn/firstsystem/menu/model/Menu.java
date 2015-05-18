package com.mscncn.firstsystem.menu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable {

	private static final long serialVersionUID = 2372622424209821728L;
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
	
	private List<Menu> children=new ArrayList<Menu>();
	
	private Attribute attributes;
	
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
	
	public Attribute getAttributes() {
		return attributes;
	}
	public void setAttributes(Attribute attributes) {
		this.attributes = attributes;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", text=" + text + ", iconCls=" + iconCls
				+ ", parentId=" + parentId + ", orderValue=" + orderValue
				+ ", children=" + children + ", attributes=" + attributes + "]";
	}
}
