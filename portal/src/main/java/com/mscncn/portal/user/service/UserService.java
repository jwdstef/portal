package com.mscncn.portal.user.service;

import java.util.Map;
import java.util.Set;

import com.mscncn.portal.common.grid.GridDataBean;
import com.mscncn.portal.common.mybatis.pagination.Pagination;
import com.mscncn.portal.user.model.User;

public interface UserService {
	/**
	 * 通过用户名称查找用户
	 * 
	 * @param userName
	 *            用户名
	 * @return User
	 * @exception
	 * @since 1.0.0
	 */
	User findUserByName(String userName);

	void saveUser(User user);

	void saveUserDetail(User user);

	void deleteUser(String[] ids);

	void reSetPassword(User user);

	void update(User userDetail);

	Set<String> findRoles(String userName);

	Set<String> findPermissions(String userName);

	GridDataBean<User> pageList(Pagination<User> pagination);

	Map<String, Object> login(User user);

}
