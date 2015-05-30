package com.mscncn.portal.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mscncn.portal.common.PasswordHelper;
import com.mscncn.portal.common.grid.GridDataBean;
import com.mscncn.portal.common.mybatis.pagination.Pagination;
import com.mscncn.portal.user.dao.UserDao;
import com.mscncn.portal.user.model.User;
import com.mscncn.portal.user.service.UserService;

@Service
@Scope(value = "prototype")
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger
			.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;


	public User findUserByName(String userName) {
		return userDao.findUserByName(userName);
	}

	public void saveUser(User user) {
		PasswordHelper.encryptPassword(user);
		userDao.save(user);
	}

	public void saveUserDetail(User detail) {
		saveUser(detail);
	}

	public Set<String> findRoles(String userName) {
		return userDao.findRolesByUserName(userName);
	}

	public Set<String> findPermissions(String userName) {
		return userDao.findResourcesByUserName(userName);
	}

	@Override
	public GridDataBean<User> pageList(Pagination<User> pagination) {
		GridDataBean<User> bean = null;
		List<User> rows = userDao.getList(pagination);
		if (CollectionUtils.isNotEmpty(rows)) {
			bean = new GridDataBean<User>();
			bean.setRows(rows);
			bean.setTotal(pagination.getTotalRecord());
		}
		return bean;
	}

	@Override
	public void deleteUser(String[] userIds) {
		userDao.deleteUser(userIds);
	}

	@Override
	public void reSetPassword(User user) {
		PasswordHelper.reSetPassword(user);
		userDao.reSetPassword(user);
	}

	@Override
	public void update(User userDetail) {
		if (!StringUtils.isEmpty(userDetail.getPassword())) {
			PasswordHelper.reSetPassword(userDetail);
		}
		userDao.update(userDetail);
	}

	@Override
	public Map<String, Object> login(User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(
					user.getUserName(), user.getPassword());
			currentUser.login(token);
			user = userDao.findUserByName(user.getUserName());
			result.put("success", true);
			userDao.updateLoginTime(user.getUserId());
		} catch (AuthenticationException e) {
			LOGGER.error("用户账号密码错误", e);
			result.put("success", false);
			result.put("errorMsg", "用户账号密码错误");
		}
		return result;
	}

}
