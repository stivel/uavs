package com.uavs.common.exception;

/**
 * 全局异常
 * 
 * @author zhangyinghua
 * @date 2016-10-18
 * @version v1.0.0
 */
public class BaseException extends Exception {
	/**
	 * 异常信息
	 */
	public String message;

	/**
	 * 构造方法
	 * 
	 * @param message
	 *            错误信息
	 */
	public BaseException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/****用户异常以E1001开始****/
	/**
	 * 账户不存在
	 */
	public static final String E10010001 = "账户不存在";
	/****订单异常以E2001开始****/
	
	/****支付异常以E3001开始****/
	
	/****产品异常以E5001开始****/
	
	/****  ?异常以E6001开始****/

}
