package com.mscncn.firstsystem.user.dao;

import org.apache.ibatis.annotations.Param;

import com.mscncn.firstsystem.user.model.UserDetail;

public interface UserDetailDao {

	void save(UserDetail detail);

	void update(UserDetail userDetail);

	void deleteUserDetail(@Param("userId") String userId);
}
