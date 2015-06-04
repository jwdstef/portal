package com.mscncn.portal.system.role.service;

import com.mscncn.portal.common.grid.GridDataBean;
import com.mscncn.portal.common.grid.ParamBean;
import com.mscncn.portal.system.role.model.Role;

public interface RoleService {
	void add(Role role);
	void update(Role role);
	void delete(String roleId);
	GridDataBean<Role> getRoleList(ParamBean paramBean);
}
