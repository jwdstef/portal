package com.mscncn.portal.common;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.mscncn.portal.user.model.User;

/**
 * <p>
 * User: King-Pan
 * <p>
 * Date: 15-2-1
 * <p>
 * Version: 1.0
 */
public class PasswordHelper {

	private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static final String algorithmName = "md5";
	private static final int hashIterations = 2;
	private static String DEFAULT_PWD = "88888888";

	public static void encryptPassword(User user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), hashIterations).toHex();
		user.setPassword(newPassword);
	}

	public static void reSetPassword(User user) {
		user.setPassword(new SimpleHash(algorithmName, DEFAULT_PWD,
				ByteSource.Util.bytes(user.getSalt()), hashIterations).toHex());
	}
}
