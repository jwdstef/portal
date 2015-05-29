package com.mscncn.portal.user.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.mscncn.portal.system.role.model.Role;

/**
 * 
 * 类名称：User
 * 类描述：用户实体
 * 创建人：king-pan
 * 修改时间：2014年11月8日
 * @version 1.0
 * 
 */
/**
 * 
 * 类名称: User 类描述: 用户信息表
 * 
 * @author King-Pan
 * @date 2015年3月4日
 * @version V1.0
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	protected String userId;
	/**
	 * 用户名称，唯一
	 */
	protected String userName;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 密码加密盐
	 */
	private String salt;

	private Boolean locked;
	
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

	private Set<Role> roles=new HashSet<Role>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", salt=" + salt + ", locked="
				+ locked + ", realName=" + realName + ", email=" + email
				+ ", phone=" + phone + ", createTime=" + createTime
				+ ", lastLoginTime=" + lastLoginTime + ", roles=" + roles + "]";
	}	

}
