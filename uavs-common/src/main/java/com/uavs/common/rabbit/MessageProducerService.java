package com.uavs.common.rabbit;

import java.io.Serializable;

public interface MessageProducerService {
	/**
	 * @description mq发送消息
	 * @param key 监听的key 渠道
	 * @param message json格式
	 * @author shiyang
	 * 2016年10月6日 
	 */
	public void sendMessage(String key,Serializable message);
}
