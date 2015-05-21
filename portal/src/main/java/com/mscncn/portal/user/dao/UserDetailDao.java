package com.mscncn.portal.user.dao;

import com.mscncn.portal.user.model.UserDetail;

public interface UserDetailDao {

	void save(UserDetail detail);

	void update(UserDetail userDetail);

	void deleteUserDetail(String[] userId);
}
