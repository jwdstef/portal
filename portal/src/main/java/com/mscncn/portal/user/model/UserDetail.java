package com.mscncn.portal.user.model;

import java.sql.Date;

public class UserDetail extends User {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 4189451328288123745L;

	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;

	@Override
	public String toString() {
		return "UserDetail [realName=" + realName + ", email=" + email
				+ ", phone=" + phone + ", createTime=" + createTime
				+ ", lastLoginTime=" + lastLoginTime + ", userId=" + userId
				+ ", userName=" + userName + "]";
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}
