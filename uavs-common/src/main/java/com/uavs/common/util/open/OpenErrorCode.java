package com.uavs.common.util.open;

/**
 * 开放接口常量
 * @author shiyang
 *
 */
public class OpenErrorCode {
	/**
	 * 签名错误
	 */
	public static String SYS_SIGN_ERROR ="100000";
	public static String SYS_SIGN_ERROR_MSG ="签名错误";
	/**
	 * 服务器错误
	 */
	public static String SYS_ERROR ="500";
	public static String SYS_ERROR_MSG ="服务器内部忙";
	
	public static String SUCCESS ="200";
	
	public static String SYS_PARAMS_ERROR ="100001";
	public static String SYS_PARAMS_ERROR_MSG ="参数错误";
	
	public static class User{
		public static String USER_NULL_ERROR ="110000";
		public static String USER_NULL_ERROR_MSG ="用户不存在";
	}
}
