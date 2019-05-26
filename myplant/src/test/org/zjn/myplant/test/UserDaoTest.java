package org.zjn.myplant.test;

import org.zjn.myplant.dao.UserDao;
import org.zjn.myplant.entity.User;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class UserDaoTest {
	
	@Autowired 
	private UserDao userDao;

	@Test
	public void testNewUser() {
		userDao.newUser("he", "he", "12312", 1);
		System.out.println("start.");
		System.out.println(Test.class.getResource("/").toString());
		String a = "dfsf", b = "cccc";
		//java没有保存形参的记录
		//userDao.newUser(a, b, "44444444444");
		System.out.println(userDao.queryByPhone("1883").getUserName());
	}

	@Test
	public void testQueryById() {
		System.out.println(userDao.queryById(1).toString());
		
	}
	@Test
	public void testQueryAll() {
		List<User> list = new ArrayList<User>();
		list = userDao.queryAll();
		for(User u : list) {
			System.out.println(u.toString());
		}
	}
	
	@Test
	public void testDelete() {
		userDao.deleteUser(10);
	}

}
