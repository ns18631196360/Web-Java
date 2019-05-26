package org.zjn.myplant.test;

import org.zjn.myplant.service.impl.MqttOutboundServiceImpl;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"})
public class MqttOutboundServiceTest {
	@Autowired
	MqttOutboundServiceImpl mqttOutboundServiceImpl;

	@Test
	public void testSendMqtt() {
		//mqttOutboundServiceImpl.sendMqtt("hello from server");
	}

	@Test
	public void testMqttClientFactory() {
		fail("Not yet implemented");
	}

	@Test
	public void testMqttOutbound() {
		fail("Not yet implemented");
	}

	@Test
	public void testMqttOutboundChannel() {
		fail("Not yet implemented");
	}

}
