package com.mscncn.firstsystem.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mscncn.firstsystem.menu.dao.MenuDao;
import com.mscncn.firstsystem.menu.model.GridMenu;
import com.mscncn.firstsystem.menu.model.Menu;
import com.mscncn.firstsystem.menu.service.MenuService;

@Service
@Scope(value="prototype")
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public List<Menu> findAllTreeMenu() {
		List<Menu> list=new ArrayList<Menu>();
		Menu menu=menuDao.findRootMenu();
		menu.setChildren(getChildren(menu));
		list.add(menu);
		return list;
	}
	private List<Menu> getChildren(Menu menu){
		List<Menu> list=null;
		if(menu!=null){
		    list=menuDao.findChirderById(menu.getId());
			for(Menu me:list){
				me.setChildren(getChildren(me));
			}
		}
		return list;
	}
	@Override
	public List<GridMenu> findAllGridMenu() {
		return menuDao.findAllGridMenu();
	}

}
