package com.uavs.common.rabbit;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 功能概要：消费接收
 * 
 */
public class MessageConsumerListener implements MessageListener {

	private Logger logger = LoggerFactory.getLogger(MessageConsumerListener.class);
	
	private MessageConsumerService messageConsumerService;
	@Override
	public void onMessage(Message message) {
		logger.info("receive message:{}", message);
		String msg=null;
		try {
			Method method = message.getClass().getDeclaredMethod("getBodyContentAsString");
			method.setAccessible(true); // 抑制Java的访问控制检查
			Object invoke = method.invoke(message);	
			msg = String.valueOf(invoke);
		} catch (Exception e) {
		}
		logger.info("处理后的 message:{}", msg);
		messageConsumerService.consumerMsg(msg);
	}
	public MessageConsumerService getMessageConsumerService() {
		return messageConsumerService;
	}
	public void setMessageConsumerService(MessageConsumerService messageConsumerService) {
		this.messageConsumerService = messageConsumerService;
	}
	

}
