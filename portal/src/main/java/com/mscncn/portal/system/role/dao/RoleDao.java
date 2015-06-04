package com.mscncn.portal.system.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mscncn.portal.common.mybatis.pagination.Pagination;
import com.mscncn.portal.system.role.model.Role;

public interface RoleDao {
  void	add(Role role);
  void delete(@Param("roleId")String roleId);
  void update(Role role);
  List<Role> getList(Pagination<Role> pagination);
}
