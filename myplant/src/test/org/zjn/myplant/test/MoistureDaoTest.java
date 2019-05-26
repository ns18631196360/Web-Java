package org.zjn.myplant.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zjn.myplant.dao.MoistureDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class MoistureDaoTest {
	@Resource MoistureDao moistureDao;
	
	@Test
	public void testQueryLastDayMoisture() {
		System.out.println(moistureDao.queryLastDayMoisture(1, 1));
		System.out.println(moistureDao.insertMoisture(1, 1, 23, new Date()));
	}

}
