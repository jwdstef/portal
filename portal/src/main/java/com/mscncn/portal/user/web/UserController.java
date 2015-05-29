package com.mscncn.portal.user.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscncn.portal.common.mybatis.pagination.Pagination;
import com.mscncn.portal.user.model.User;
import com.mscncn.portal.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	@ResponseBody
	public Object login(User user) {
		return userService.login(user);
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String userList() {
		return "user/user";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	@ResponseBody
	public Object userList(@RequestParam("page") int pageNo,
			@RequestParam("rows") int pageSize) {
		Pagination<User> pagination = new Pagination<User>();
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		return userService.pageList(pagination);
	}

	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	@ResponseBody
	public Object save(User user) {
		userService.saveUserDetail(user);
		return "success";
	}

	@RequestMapping(value = { "/reSetPwd" }, method = RequestMethod.POST)
	@ResponseBody
	public Object reSetPwd(User user) {
		userService.reSetPassword(user);
		return "success";
	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	@ResponseBody
	public Object update(User user) {
		userService.update(user);
		return "success";
	}

	@RequestMapping(value = { "/delete" }, method = RequestMethod.POST)
	@ResponseBody
	public Object delete(String[] ids) {
		userService.deleteUser(ids);
		return "success";
	}
}
