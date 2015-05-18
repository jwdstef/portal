package com.mscncn.firstsystem.user.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscncn.firstsystem.common.mybatis.pagination.Pagination;
import com.mscncn.firstsystem.user.model.User;
import com.mscncn.firstsystem.user.model.UserDetail;
import com.mscncn.firstsystem.user.service.UserService;

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
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(
					user.getUserName(), user.getPassword());
			currentUser.login(token);
			result.put("success", true);
		} catch (AuthenticationException e) {
			logger.error("用户账号密码错误", e);
			result.put("success", false);
			result.put("errorMsg", "用户账号密码错误");
		}
		return result;
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String userList() {
		return "user/user";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	@ResponseBody
	public Object userList(@RequestParam("page") int pageNo,
			@RequestParam("rows") int pageSize) {
		Pagination<UserDetail> pagination = new Pagination<UserDetail>();
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		return userService.pageList(pagination);
	}

	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	@ResponseBody
	public Object save(UserDetail userDetail) {
		userService.saveUserDetail(userDetail);
		return "success";
	}

	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public Object getUser(@PathVariable Integer id) {
		return userService.getUserDetailById(id);
	}

	@RequestMapping(value = { "/{id}" }, method = RequestMethod.POST)
	@ResponseBody
	public Object getUser(UserDetail userDetail) {
		return null;
	}

	@RequestMapping(value = { "/reSetPwd" }, method = RequestMethod.POST)
	@ResponseBody
	public Object reSetPwd(User user) {
		userService.reSetPassword(user);
		return "success";
	}
}
