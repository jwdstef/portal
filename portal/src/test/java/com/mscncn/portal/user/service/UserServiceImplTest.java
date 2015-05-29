package com.mscncn.portal.user.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mscncn.portal.common.SpringTestBase;
import com.mscncn.portal.common.grid.GridDataBean;
import com.mscncn.portal.common.mybatis.pagination.Pagination;
import com.mscncn.portal.user.model.User;
import com.mscncn.portal.user.model.UserDetail;

public class UserServiceImplTest extends SpringTestBase {
	@Autowired
	private UserService userService;

	@Test
	public void test() {
		System.out.println(userService.findUserByName("11") + "----------");
	}

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUserName("sysadmin");
		user.setPassword("222222");
		user.setEmail("pwpw12111@ww.cm");
		user.setLocked(false);
		userService.saveUserDetail(user);
		System.out.println(ByteSource.Util
				.bytes("855e4bf810581f256b8a60b98f2aadb0"));
		String newPassword = new SimpleHash("md5", "222222",
				ByteSource.Util
						.bytes("855e4bf810581f256b8a60b98f2aadb0"), 2)
				.toHex();
		System.out.println(newPassword);
	}

	@Test
	public void pageListTest() {
		Pagination<User> pagination = new Pagination<User>();
		pagination.setPageNo(1);
		pagination.setPageSize(5);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", 11);
		pagination.setParams(params);
		GridDataBean<User> bean = userService.pageList(pagination);
		System.out.println(bean);
		Assert.assertSame(bean.getRows().size(), 2);
	}


	@Test
	public void reSetPasswdTest() {
		User user = new User();
		user.setUserId(4 + "");
		user.setSalt("test_000002b58f17297650cd0a303ebf788dde5c61");
		userService.reSetPassword(user);
	}
	@Test
	public void updateTest(){
		UserDetail user=new UserDetail();
		user.setUserId(4+"");
		user.setUserName("test_000002");
		user.setRealName("test_000002");
		user.setEmail("11111@qq.cm");
		//user.setSalt("zssss");
		//user.setPassword("222");
		user.setPhone("test_000002");
		user.setLocked(false);
		userService.update(user);
		
	}
	@Test
	public void deleteTest(){
		String[] ids={"3","4","5"};
		userService.deleteUser(ids);
	}
}
