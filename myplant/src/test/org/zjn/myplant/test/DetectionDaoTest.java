package org.zjn.myplant.test;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zjn.myplant.dao.DetectionDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class DetectionDaoTest {

	@Resource DetectionDao detectionDao;
	
	@Test
	public void testNewDetection() {
		Date date = new Date();
		System.out.println(date);
		detectionDao.newDetection(1, 1, 1, date);
	}

	@Test
	public void testQueryByDeviceId() {
		System.out.println(detectionDao.queryLastOneByDeviceId(1).toString());
	}
	@Test
	public void testQueryLastDay() {
		System.out.println(detectionDao.queryLastDayTemperature(1).get(0));
		System.out.println(detectionDao.queryLastDayMoisture(1).get(0));
	}

}
