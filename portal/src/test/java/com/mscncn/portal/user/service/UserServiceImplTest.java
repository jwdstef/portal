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
	public void testSaveUserDetail() {
		UserDetail user = new UserDetail();
		user.setUserName("sysadmin");
		user.setPassword("222222");
		user.setEmail("pwpw12111@ww.cm");
		user.setLocked(false);
		userService.saveUserDetail(user);
		System.out.println(ByteSource.Util
				.bytes("sysadmin855e4bf810581f256b8a60b98f2aadb0"));
		String newPassword = new SimpleHash("md5", "222222",
				ByteSource.Util
						.bytes("sysadmin855e4bf810581f256b8a60b98f2aadb0"), 2)
				.toHex();
		System.out.println(newPassword);
	}

	@Test
	public void pageListTest() {
		Pagination<UserDetail> pagination = new Pagination<UserDetail>();
		pagination.setPageNo(1);
		pagination.setPageSize(5);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", 11);
		pagination.setParams(params);
		GridDataBean<UserDetail> bean = userService.pageList(pagination);
		System.out.println(bean);
		Assert.assertSame(bean.getRows().size(), 2);
	}

	@Test
	public void getUserDetailTest() {
		UserDetail detail = userService.getUserDetailById(1);
		System.out.println(detail);
		Assert.assertNotNull(detail);

	}

	@Test
	public void getUserDetailTestNull() {
		UserDetail detail = userService.getUserDetailById(null);
		System.out.println(detail);
		Assert.assertNull(detail);
	}

	@Test
	public void reSetPasswdTest() {
		User user = new User();
		user.setUserId(1 + "");
		user.setSalt("md5");
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
