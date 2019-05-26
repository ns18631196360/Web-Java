package org.zjn.myplant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zjn.myplant.service.MqttOutboundService;

@Service
@Configuration
@EnableIntegration
@IntegrationComponentScan
//@MessageEndpoint ²»±ØÒª
public class MqttOutboundServiceImpl implements MqttOutboundService {

	@Autowired
	private MyGateway gateway;

	public void sendMqtt(String string, int deviceId) {
		
		gateway.sendToMqtt(MessageBuilder.withPayload(string).setHeader(MqttHeaders.TOPIC, "MyPlant/Arduino/instruction/" + deviceId).build());
	}

	@Override
	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		// TODO Auto-generated method stub
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setServerURIs("tcp://47.92.246.163:1883");
		factory.setUserName("server");
		factory.setPassword("");
		return factory;
	}

	@Override
	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel")
	public MessageHandler mqttOutbound() {
		// TODO Auto-generated method stub
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("testClient", mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic("MyPlant/Arduino/instruction/");
		//messageHandler.setTopicExpressionString(topicExpression);
		//messageHandler.setTopicExpression(topicExpression);
		return messageHandler;
	}

	@Override
	@Bean
	public MessageChannel mqttOutboundChannel() {
		// TODO Auto-generated method stub
		return new DirectChannel();
	}
	
	@MessagingGateway
	public interface MyGateway {
		@Gateway(requestChannel = "mqttOutboundChannel")
		void sendToMqtt(Message<String> message);

	}
	

}
