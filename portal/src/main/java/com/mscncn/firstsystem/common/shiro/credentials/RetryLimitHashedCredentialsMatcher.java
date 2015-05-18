/**
 * 项目名：java博客论坛
 * 包名：com.mscncn.console.common.shiro.credentials
 * 文件名：RetryLimitHashedCredentialsMatcher.java
 * 版本信息：V1
 * 日期：2015年1月22日
 * Copyright 2013-2015 King-Pan-版权所有
 * 
 */

package com.mscncn.firstsystem.common.shiro.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * 
 * 类名称：RetryLimitHashedCredentialsMatcher 
 * 类描述：TODO 
 * 创建人：king-pan 
 * 修改时间：2015年1月22日
 * 
 * @version 1.0
 * 
 */

public class RetryLimitHashedCredentialsMatcher extends
		HashedCredentialsMatcher {
	private Cache<String, AtomicInteger> passwordRetryCache;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		
		UsernamePasswordToken tk=(UsernamePasswordToken)token;
		String username =tk.getUsername();
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			passwordRetryCache.remove(username);
		}
		return matches;
	}
}
