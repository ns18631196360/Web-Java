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
		//plantDao.newPlant("����", 12, 13, 11, 14,"����","ϲʪ");
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
		System.out.println(plantDao.queryByName("����").toString());
	}
	
	@Test
	public void testDelete() {
		System.out.println(plantDao.deletePlant(2));
	}
	
	@Test
	public void testUpdate() {
		plantDao.updatePlant(1, "����", 100, 100, 100, 100, "��ǿ���������ֹ�", "�����࣬Ҳ��ˮ�࣬������ϲʪ��ƽ��ÿ��ʩ��1�Σ���ʱ������Ҷ", 2);
	}

}
