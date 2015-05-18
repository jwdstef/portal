package com.mscncn.firstsystem.menu.service;

import java.util.List;

import com.mscncn.firstsystem.menu.model.GridMenu;
import com.mscncn.firstsystem.menu.model.Menu;

public interface MenuService {
	List<Menu> findAllTreeMenu();
	List<GridMenu> findAllGridMenu();
}
