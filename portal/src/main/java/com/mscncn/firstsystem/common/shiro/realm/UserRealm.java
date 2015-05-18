/**
 * 项目名：java博客论坛
 * 包名：com.mscncn.console.common.shiro.realm
 * 文件名：UserRealm.java
 * 版本信息：V1
 * 日期：2015年1月22日
 * Copyright 2013-2015 King-Pan-版权所有
 * 
 */

package com.mscncn.firstsystem.common.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.mscncn.firstsystem.user.model.User;
import com.mscncn.firstsystem.user.service.UserService;

/**
 * 
 * 类名称：UserRealm 类描述：TODO 创建人：king-pan 修改时间：2015年1月22日
 * 
 * @version 1.0
 * 
 */

public class UserRealm extends AuthorizingRealm {
	/**
	 * 用户Service接口
	 */
	private UserService userService;

	/**
	 * 用户登录验证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		// 转换程UsernamePasswordToken获取需要验证的用户信息
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user= userService.findUserByName(token.getUsername());
		if (null == user) {
			throw new UnknownAccountException();// 没找到帐号
		}
		if (user.getLocked()) {
			throw new LockedAccountException(); // 帐号锁定
		}
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUserName(), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getSalt()),// salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}
	
	/**
	 * 查询登录用户的授权信息
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(username));
		authorizationInfo.setStringPermissions(userService
				.findPermissions(username));
		return authorizationInfo;
	}
	/**
	 * 返回Realm的名称
	 */
	public String getName() {
		return "simple.firstsystem.UserRealm";
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
