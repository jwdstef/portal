package com.mscncn.portal.user.dao;

import java.util.List;

import com.mscncn.portal.system.role.model.Role;
import com.mscncn.portal.user.model.User;

public interface UserRoleDao {
	List<Role> findRoleByUserId(String userId);
	
	void updateUserRole(User user);
}
