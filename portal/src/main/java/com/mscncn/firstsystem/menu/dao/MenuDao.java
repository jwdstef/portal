package com.mscncn.firstsystem.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mscncn.firstsystem.menu.model.GridMenu;
import com.mscncn.firstsystem.menu.model.Menu;

public interface MenuDao {
	Menu findRootMenu();
	List<Menu> findChirderById(@Param("parentId")Integer parentId);
	List<GridMenu> findAllGridMenu();
}
