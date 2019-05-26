package org.zjn.myplant.test;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zjn.myplant.dao.CollectDataDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class CollectDataDaoTest {
	@Resource CollectDataDao collectDataDao;

	@Test
	public void testNewDetection() {
		Date date = new Date();
		System.out.println(date);
		collectDataDao.newCollection(1, 2, 3, 4, 5, 6, 7, 8, date);
	}

	@Test
	public void testQueryByDeviceId() {
		System.out.println(collectDataDao.queryByDeviceId(1).toString());
	}

}
