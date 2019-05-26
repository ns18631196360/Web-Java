package org.zjn.myplant.test;

import static org.junit.Assert.*;

import org.zjn.myplant.service.MqttInboundService;
import org.zjn.myplant.service.impl.MqttInboundServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"})

public class MqttInboundServiceTest {
	@Autowired
	MqttInboundServiceImpl serviceimpl;

	@Test
	public void test() {
		serviceimpl.handler();
	}

}
