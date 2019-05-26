package org.zjn.myplant.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zjn.myplant.dao.DeviceDao;
import org.zjn.myplant.entity.Device;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class DeviceDaoTest {
	@Resource DeviceDao deviceDao;

	@Test
	public void testNewDevice() {
		System.out.println(deviceDao.newDevice(3, 1, 1));
		System.out.println(deviceDao.increaseDeviceNumber(2));
		System.out.println(deviceDao.decreaseDeviceNumber(3));
	}

	@Test
	public void testQueryById() {
		System.out.println(deviceDao.queryById(1).toString());
	}
	@Test
	public void testQueryAll() {
		List<Device> list= deviceDao.queryAll();
		for(Device i : list) {
			System.out.println(i.toString());
		}
		
	}
	@Test
	public void testUpdateUser() {
		deviceDao.updateUser(3);
	}

}
