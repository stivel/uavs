package com.uavs.common.rabbit;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import com.alibaba.fastjson.JSON;

/**
 * 功能概要：消息产生,提交到队列中去
 * 
 */
/**
 * @author hzcf
 *
 */
public class MessageProducerServiceImpl implements MessageProducerService{

	private Logger logger = LoggerFactory.getLogger(MessageProducerServiceImpl.class);

	private AmqpTemplate amqpTemplate;
	

	public AmqpTemplate getAmqpTemplate() {
		return amqpTemplate;
	}

	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	 
	
	/**
	 * @description
	 * @param key
	 * @param message
	 * @author shiyang
	 * 2016年10月6日 
	 */
	@Override
	public void sendMessage(String key, Serializable message) {
		logger.info("to send "+key, JSON.toJSON(message));
		amqpTemplate.convertAndSend(key, message);
		
	}
	
}
