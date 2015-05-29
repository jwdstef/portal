package com.mscncn.portal.system.role.model;

import java.io.Serializable;
import java.util.Arrays;

public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8943093626511265326L;
	
	private String roleId;
	
	private String roleName;
	
	private String remark;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String[] resourceIds;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String[] getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String[] resourceIds) {
		this.resourceIds = resourceIds;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", remark=" + remark + ", resourceIds="
				+ Arrays.toString(resourceIds) + "]";
	}

	
}
