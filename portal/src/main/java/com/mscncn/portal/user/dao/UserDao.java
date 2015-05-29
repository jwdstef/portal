package com.mscncn.portal.user.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.mscncn.portal.common.mybatis.pagination.Pagination;
import com.mscncn.portal.user.model.User;

public interface UserDao {

	void save(User user);

	void deleteUser(String[] userIds);

	void reSetPassword(User user);

	void update(User user);

	User findUserByName(@Param("userName") String userName);

	Set<String> findRolesByUserName(@Param("userName") String userName);

	Set<String> findResourcesByUserName(@Param("userName") String userName);

	List<User> getList(Pagination<User> pagination);

	User getUserById(@Param("id") String id);

	void updateLoginTime(@Param("id") String id);

}
