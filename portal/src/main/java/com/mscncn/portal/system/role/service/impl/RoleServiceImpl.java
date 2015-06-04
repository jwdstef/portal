package com.mscncn.portal.system.role.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscncn.portal.common.grid.GridDataBean;
import com.mscncn.portal.common.grid.ParamBean;
import com.mscncn.portal.common.mybatis.pagination.Pagination;
import com.mscncn.portal.system.role.dao.RoleDao;
import com.mscncn.portal.system.role.model.Role;
import com.mscncn.portal.system.role.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleDao roleDao;

	@Override
	public void add(Role role) {
		if (null == role) {
			LOGGER.error("the role is null,nothing to add");
		} else {
			roleDao.add(role);
			LOGGER.debug("添加角色信息成功,信息为{}", role);
		}
	}

	@Override
	public void update(Role role) {
		if (null == role) {
			LOGGER.error("the role is null,nothing to update");
		} else {
			roleDao.update(role);
			LOGGER.debug("更新角色信息成功,信息为{}", role);
		}
	}

	@Override
	public void delete(String roleId) {
		if (StringUtils.isNotEmpty(roleId)) {
			roleDao.delete(roleId);
			LOGGER.debug("删除角色信息成功,roleId为{}", roleId);
		} else {
			LOGGER.error("the roleId is null,nothing to delete");
		}
	}

	@Override
	public GridDataBean<Role> getRoleList(ParamBean paramBean) {
		GridDataBean<Role> bean  = new GridDataBean<Role>();
		Pagination<Role> pagination = new Pagination<Role>();
		pagination.setPageNo(paramBean.getPage());
		pagination.setPageSize(paramBean.getRows());
		pagination.setParams(paramBean.getQueryParams());
		List<Role> rows = roleDao.getList(pagination);
		if (CollectionUtils.isNotEmpty(rows)) {
			bean.setRows(rows);
			bean.setTotal(pagination.getTotalRecord());
		}else{
			bean.setRows(null);
			bean.setTotal(0);
		}
		return bean;
	}

}
