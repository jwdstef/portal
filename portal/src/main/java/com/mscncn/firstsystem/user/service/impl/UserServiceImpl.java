package com.mscncn.firstsystem.user.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mscncn.firstsystem.common.PasswordHelper;
import com.mscncn.firstsystem.common.grid.GridDataBean;
import com.mscncn.firstsystem.common.mybatis.pagination.Pagination;
import com.mscncn.firstsystem.user.dao.UserDao;
import com.mscncn.firstsystem.user.dao.UserDetailDao;
import com.mscncn.firstsystem.user.model.User;
import com.mscncn.firstsystem.user.model.UserDetail;
import com.mscncn.firstsystem.user.service.UserService;

@Service
@Scope(value = "prototype")
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger
			.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserDetailDao userDetailDao;

	public UserDetail findUserByName(String userName) {
		return userDao.findUserByName(userName);
	}

	public void saveUser(User user) {
		user.setUserId(userDao.getId() + "");
		PasswordHelper.encryptPassword(user);
		userDao.save(user);
	}

	public void saveUserDetail(UserDetail detail) {
		saveUser(detail);
		userDetailDao.save(detail);
	}

	public Set<String> findRoles(String userName) {
		return userDao.findRolesByUserName(userName);
	}

	public Set<String> findPermissions(String userName) {
		return userDao.findResourcesByUserName(userName);
	}

	@Override
	public GridDataBean<UserDetail> pageList(Pagination<UserDetail> pagination) {
		GridDataBean<UserDetail> bean = null;
		List<UserDetail> rows = userDao.getList(pagination);
		if (CollectionUtils.isNotEmpty(rows)) {
			bean = new GridDataBean<UserDetail>();
			bean.setRows(rows);
			bean.setTotal(pagination.getTotalRecord());
		}
		return bean;
	}

	@Override
	public UserDetail getUserDetailById(Integer id) {
		if (null == id) {
			LOGGER.info("the param id is null");
			return null;
		}
		return userDao.getUserById(id);
	}

	@Override
	public void deleteUser(String userId) {
		userDao.deleteUser(userId);
		userDetailDao.deleteUserDetail(userId);
	}

	@Override
	public void reSetPassword(User user) {
		PasswordHelper.reSetPassword(user);
		userDao.reSetPassword(user);
	}

	@Override
	public void update(UserDetail userDetail) {
		if (!StringUtils.isEmpty(userDetail.getPassword())) {
			PasswordHelper.reSetPassword(userDetail);
		}
		userDao.update(userDetail);
		userDetailDao.update(userDetail);
	}

}
