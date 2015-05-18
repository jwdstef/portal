package com.mscncn.firstsystem.user.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;

import com.mscncn.firstsystem.common.SpringMVCTestBase;

public class UserControllerTest  extends SpringMVCTestBase{
	@Test
	public void homeTest(){
		try {
			mockMvc.perform(get("/user/home")).andDo(print());
			wac.getBean(UserController.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void pageListTest(){
		try {
			mockMvc.perform(get("/user/")).andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
