package com.mscncn.portal.user.model;

import java.io.Serializable;

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
 * 类名称: User 类描述: TODO
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
	 * 密码加密方式
	 */
	private String salt;

	private Boolean locked;

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
				+ locked + "]";
	}

}
