package org.zjn.myplant.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zjn.myplant.dao.CultivationDao;
import org.zjn.myplant.entity.Cultivation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class CultivationDaoTest {
	@Resource CultivationDao cultivationDao;
	
	@Test
	public void testQueryBySerial() {
		Cultivation cultivation = cultivationDao.queryBySerial(1, 2);
		System.out.println(cultivation.toString());
		List<Cultivation> list = cultivationDao.queryByUserId(1);
		for(Cultivation c : list) {
			System.out.println(c.toString());
		}
		//System.out.println(cultivationDao.updateCultivation(3, 2, 1, 777, 777, 777, "wsffergergergegrgtge", 1)
//);
	}
	
	@Test
	public void testQueryAll() {
		List<Cultivation> list = cultivationDao.queryAll();
		for(Cultivation c : list) {
			System.out.println(c.toString());
		}
	}
	
	@Test
	public void testDelete() {
		cultivationDao.deleteCultivation(8, 8);
	}
	
	@Test
	public void testNew() {
		cultivationDao.newCultivation(1, 5, 1, 1, 14, 14, 1, "fsdf", 20, 1);
		
	}

}
