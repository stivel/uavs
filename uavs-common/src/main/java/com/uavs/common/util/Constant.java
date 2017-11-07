package com.uavs.common.util;

/**
 * 系统常量类
 *
 * @author zhangyinghua
 * @version v1.0
 * @date 2016-10.16
 */
public class Constant {
	
	/**
	 * @description  pc session 
	 * @author shiyang
	 * 2016年10月26日 下午8:57:56
	 */
	public static final String SESSION_PC_KEY = "USER";
	
	/**
	 * @description 注册图形验证码
	 * @author shiyang
	 * 2016年10月26日 下午8:57:37
	 */
	public static final String USER_REGISTER_PC_PIC_CODE_KEY = "USER_REGISTER_PIC_CODE_KEY";
	
	/**
	 * @description 忘记密码
	 * @author shiyang
	 * 2016年10月31日 上午11:10:09
	 */
	public static final String USER_FORGOT_PC_PIC_CODE_KEY = "USER_FORGOT_PIC_CODE_KEY";
	
	/**
	 * @description 登陆图形验证码
	 * @author wuwenbin
	 */
	public static final String USER_LOGIN_PC_PIC_CODE_KEY = "USER_LOGIN_PIC_CODE_KEY";
	
	/**
	 * 移动端TOKEN失效时间
	 */
	public static long TOKEN_EXPIRE = 60 * 60 * 24L;
	
	/**
	 * H5 token失效时间
	 */
	public static long TOKEN_EXPIRE_H5 = 60 * 30L;
	
	/**
	 * 移动端秘钥
	 */
	public static final String JSON_KEY = ConfigPropUtils.get("des_json_key");
	
	/**
	 * 返回ajax的异常处理提示键值
	 */
    public static final String ERROR_KEY = "success";
    
	/**
	 * 返回错误提示页面的信息键
	 */
    public static final String ERROR_MESSAGE = "message";
    
	/**
	 * 返回错误提示页面前缀
	 */
    public static final String ERROR_VIEW = "error";
    
    public static final String OK = "200";
    
    /**
     * @description 静态资源读取路径
     * @author shiyang
     * 2016年11月2日 下午5:31:08
     */
    public static final String FUND_FILE_READPATH = ConfigPropUtils.get("fund_file_readpath");
    
    public static final String FUND_WAP_URL = ConfigPropUtils.get("fund_wap_url");
   
    /**
     * @description 消息图形验证码
     * @author shiyang
     */
    public static final String SMS_REGISTER_PC_PIC_CODE_KEY = "SMS_PIC_CODE_KEY";
	
}
