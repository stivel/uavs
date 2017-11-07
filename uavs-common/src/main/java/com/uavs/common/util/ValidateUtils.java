package com.uavs.common.util;

import java.util.regex.Pattern;

/**
 * 验证工具
 * @ClassName: ValidateUtils 
 * @author zhoujie
 * @date 2017年5月12日 下午1:54:29
 */
public class ValidateUtils {
	/**
     * 正则表达式：验证非负整数
     */
    public static final String REGEX_NUMBER = "^\\d+$";
	/**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$";
 
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    
    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
    
    /**
     * 校验非负整数
     * @param mobile
     * @return
     * @author zhoujie
     * @date 2017年5月12日 下午2:54:36
     */
    public static boolean isNumber(String number) {
        return Pattern.matches(REGEX_NUMBER, number);
    }
    
    /**
     * 校验手机号
     * @param mobile
     * @return
     * @author zhoujie
     * @date 2017年5月12日 下午1:55:50
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
 
    /**
     * 校验邮箱
     * @param email
     * @return 校验通过返回true，否则返回false
     * @author zhoujie
     * @date 2017年5月12日 下午1:55:19
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
    
    /**
     * 校验身份证
     * 
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return IdcardValidator.isValidatedAllIdcard(idCard);
    }
    public static void main(String[] args) {
    	String str = "38745630@qq.cn";
    	System.out.println(isEmail(str));
    	System.out.println(isMobile("18090070786"));
	}
}
