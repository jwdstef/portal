package com.mscncn.firstsystem.user.service;

import java.util.Set;

import com.mscncn.firstsystem.common.grid.GridDataBean;
import com.mscncn.firstsystem.common.mybatis.pagination.Pagination;
import com.mscncn.firstsystem.user.model.User;
import com.mscncn.firstsystem.user.model.UserDetail;

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

	void saveUserDetail(UserDetail user);

	void deleteUser(String[] ids);

	void reSetPassword(User user);

	void update(UserDetail userDetail);

	Set<String> findRoles(String userName);

	Set<String> findPermissions(String userName);

	GridDataBean<UserDetail> pageList(Pagination<UserDetail> pagination);

	/**
	 * 通过用户id查找用户详细信息
	 * 
	 * @param id
	 *            用户id
	 * @return 用户详情
	 */
	UserDetail getUserDetailById(Integer id);
}
