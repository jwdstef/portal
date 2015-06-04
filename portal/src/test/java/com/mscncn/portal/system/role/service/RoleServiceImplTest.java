package com.mscncn.portal.system.role.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mscncn.portal.common.SpringTestBase;
import com.mscncn.portal.common.grid.GridDataBean;
import com.mscncn.portal.common.grid.ParamBean;
import com.mscncn.portal.common.mybatis.pagination.Pagination;
import com.mscncn.portal.system.role.model.Role;

public class RoleServiceImplTest extends SpringTestBase {
	
	@Autowired
	private RoleService roleService;
	
	@Test
	public void testAdd() {
		Role role=new Role();
		role.setRoleName("超级管理员");
		role.setRemark("系统管理员");
		roleService.add(role);
	}

	@Test
	public void testUpdate() {
		Role role=new Role();
		role.setRoleId("2");
		role.setRoleName("超级管理员_update");
		role.setRemark("系统管理员_update");
		roleService.update(role);
	}

	@Test
	public void testDelete() {
		roleService.delete("2");
	}

	@Test
	public void testGetRoleList() {
		ParamBean paramBean=new ParamBean();
		paramBean.setPage(1);
		paramBean.setRows(5);
		Map<String, Object> map=paramBean.getQueryParams();
		map.put("roleName", "%管理员%");
		
		GridDataBean<Role> bean = roleService.getRoleList(paramBean);
		System.out.println(bean);
	}

}
