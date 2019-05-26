package org.zjn.myplant.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zjn.myplant.service.impl.AlgorithmServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"})

public class testPredictMoistureService {
	
	@Autowired
	AlgorithmServiceImpl impl;
	
	@Test
	public void testPredictMoisture() {
		impl.predictMoisture();
	}

}
