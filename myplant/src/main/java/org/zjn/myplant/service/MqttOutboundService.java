package org.zjn.myplant.service;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

//@IntegrationComponentScan
public interface MqttOutboundService {

	//void sendMqtt(String string);

	MqttPahoClientFactory mqttClientFactory();

	//@ServiceActivator(inputChannel = "mqttOutboundChannel")
	MessageHandler mqttOutbound();

	MessageChannel mqttOutboundChannel();

}
