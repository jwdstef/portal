package com.mscncn.portal.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mscncn.portal.menu.model.GridMenu;
import com.mscncn.portal.menu.model.Menu;

public interface MenuDao {
	Menu findRootMenu();
	List<Menu> findChirderById(@Param("parentId")Integer parentId);
	List<GridMenu> findAllGridMenu();
}
