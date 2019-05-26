package org.zjn.myplant.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zjn.myplant.dao.WateringDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class WateringDaoTest {
	
	@Resource WateringDao wateringDao;

	@Test
	public void testNewWatering() {
		wateringDao.newWatering(1, 1, 1, new Date());
	}

	@Test
	public void testQueryByDeviceId() {
		System.out.println(wateringDao.queryByDeviceId(1).toString());
	}
	
	@Test
	public void testQueryLastTimeWatering() {
		System.out.println(wateringDao.queryLastTimeWater(1, 1).toString());
	}

}
