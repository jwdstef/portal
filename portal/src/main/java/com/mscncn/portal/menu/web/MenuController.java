package com.mscncn.portal.menu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscncn.portal.menu.service.MenuService;


@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	@RequestMapping(value={"/treeMenu"},method=RequestMethod.POST)
	@ResponseBody
	public Object getTreeMenu(){
		return menuService.findAllTreeMenu();
	}
}
