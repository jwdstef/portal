package com.mscncn.firstsystem.user.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.mscncn.firstsystem.common.mybatis.pagination.Pagination;
import com.mscncn.firstsystem.user.model.User;
import com.mscncn.firstsystem.user.model.UserDetail;

public interface UserDao {

	void save(User user);

	void deleteUser(String[] userIds);

	Integer getId();

	void reSetPassword(User user);

	void update(UserDetail userDetail);

	UserDetail findUserByName(@Param("userName") String userName);

	Set<String> findRolesByUserName(@Param("userName") String userName);

	Set<String> findResourcesByUserName(@Param("userName") String userName);

	List<UserDetail> getList(Pagination<UserDetail> pagination);

	UserDetail getUserById(@Param("id") Integer id);

}
