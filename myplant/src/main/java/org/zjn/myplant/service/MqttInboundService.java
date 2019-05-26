package org.zjn.myplant.service;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.core.MessageProducer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@IntegrationComponentScan
public interface MqttInboundService {
	
	/*
	 * ��վ��ϢҪ����input channel -> service activator -> message handler
	 */

	MessageChannel mqttInputChannel();
	
	MessageProducer inbound();
	
	MessageHandler handler();

}
