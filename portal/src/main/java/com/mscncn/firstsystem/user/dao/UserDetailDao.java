package com.mscncn.firstsystem.user.dao;

import com.mscncn.firstsystem.user.model.UserDetail;

public interface UserDetailDao {

	void save(UserDetail detail);

	void update(UserDetail userDetail);

	void deleteUserDetail(String[] userId);
}
