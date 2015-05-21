package com.mscncn.portal.menu.service;

import java.util.List;

import com.mscncn.portal.menu.model.GridMenu;
import com.mscncn.portal.menu.model.Menu;

public interface MenuService {
	List<Menu> findAllTreeMenu();
	List<GridMenu> findAllGridMenu();
}
