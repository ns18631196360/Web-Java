package org.zjn.myplant.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zjn.myplant.dao.PlantDao;
import org.zjn.myplant.dao.UserDao;
import org.zjn.myplant.entity.Plant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class PlantDaoTest {
	
	@Resource PlantDao plantDao;

	@Test
	public void testNewPlant() {
		//plantDao.newPlant("玉兰", 12, 13, 11, 14,"爱情","喜湿");
		List<Plant> list = plantDao.queryAll();
		for(Plant p : list) {
			System.out.println(p.toString());
		}
	}

	@Test
	public void testQueryById() {
		System.out.println(plantDao.queryById(1).toString());
	}
	
	@Test
	public void testQueryByName() {
		System.out.println(plantDao.queryByName("绿萝").toString());
	}
	
	@Test
	public void testDelete() {
		System.out.println(plantDao.deletePlant(2));
	}
	
	@Test
	public void testUpdate() {
		plantDao.updatePlant(1, "绿萝", 100, 100, 100, 100, "坚强，善良，乐观", "可土培，也可水培，耐阴，喜湿，平均每月施肥1次，及时剪除枯叶", 2);
	}

}
