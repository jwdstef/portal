package com.mscncn.firstsystem.menu.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mscncn.firstsystem.common.SpringTestBase;

public class MenuServiceImplTest extends SpringTestBase {
	
	@Autowired
	private MenuService menuService;
	@Test
	public void testFindAllTreeMenu() {
		Assert.assertNotNull(menuService.findAllTreeMenu());
		System.out.println(menuService.findAllTreeMenu());
	}

	@Test
	public void testFindAllGridMenu() {
		Assert.assertNotNull(menuService.findAllGridMenu());
		System.out.println(menuService.findAllGridMenu());
	}

}
