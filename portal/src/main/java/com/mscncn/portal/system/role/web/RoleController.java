package com.mscncn.portal.system.role.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscncn.portal.common.grid.ParamBean;
import com.mscncn.portal.system.role.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@RequestMapping("/")
	public String roleHome(){
		return "role/role";
	}
	@RequestMapping(value={"/"},method=RequestMethod.POST)
	@ResponseBody
	public Object getRoleList(@ModelAttribute("paramBean")ParamBean paramBean){
		System.out.println(paramBean);
		return roleService.getRoleList(paramBean);
	}
}
