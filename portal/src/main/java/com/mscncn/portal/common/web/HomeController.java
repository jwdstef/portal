package com.mscncn.portal.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
	@RequestMapping(value={"/","/home","index"})
	public String home(){
		return "index";
	}
}
