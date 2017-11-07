package com.uavs.common.util.yys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.uavs.common.util.DateUtils;
import com.uavs.common.util.HttpClientUtil;
import com.uavs.common.util.PropUtils;

/**
 * 杨鼹鼠 工具类
 * 
 * @author shiyang
 *
 */
public class YYSUtils {
	public static Log log = LogFactory.getLog(YYSUtils.class);
	/**
	 * 洋鼹鼠商户号
	 */
	public static String yys_mchNo =  getPropertiesValue("yys_mchNo");
	/**
	 * 秘钥
	 */
	public static String yys_key =  getPropertiesValue("yys_key");
	/**
	 * 请求地址
	 */
	public static String yys_url= getPropertiesValue("yys_url");
	/**
	 * 请求页面地址
	 */
	public static String yys_url_page= getPropertiesValue("yys_url_page");
	
	/**
	 * 获取token
	 */
	public static String yys_url_token = yys_url+"/gettoken.htm";
	
	/**
	 * 订单查询
	 */
	public static String yys_url_order_query = yys_url+"/order/query.htm";

	/**
	 * 订单详情查询
	 */
	public static String yys_url_order_get = yys_url+"/order/get.htm";

	/**
	 * 订单列表页
	 */
	public static String yys_url_order_list_page = yys_url_page+"/customer/myorder.html";
	
	/**
	 * 用户授权登录
	 */
	public static String yys_url_accesstoken = yys_url+"/mbycustomer/token.htm";
	
	public static Map<String, Object> execute(String url,Map<String,String> data) {
		long startTime = System.currentTimeMillis();
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("Code", "error");
		retMap.put("Message", "三方服务器繁忙...");
		try {
			String result = HttpClientUtil.postWay(url, data, 3000, "UTF-8");
			log.info("请求时间共计   " + (System.currentTimeMillis() - startTime) + "毫秒");
			Map<String, Object> parseObject = JSON.parseObject(result, Map.class);
			return parseObject;
		} catch (Exception e) {
			log.error(" 请求异常   ", e);
			log.info("请求时间共计   " + (System.currentTimeMillis() - startTime) + "毫秒");
			return null;
		}
	}
	public static String executeString(String url,Map<String,String> data) {
		long startTime = System.currentTimeMillis();
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("Code", "error");
		retMap.put("Message", "三方服务器繁忙...");
		try {
			String result = HttpClientUtil.postWay(url, data, 3000, "UTF-8");
			log.info("请求时间共计   " + (System.currentTimeMillis() - startTime) + "毫秒");
			return result;
		} catch (Exception e) {
			log.error(" 请求异常   ", e);
			log.info("请求时间共计   " + (System.currentTimeMillis() - startTime) + "毫秒");
			return null;
		}
	}

	/**
	 * @Description: 洋鼹鼠签名传 @param parm @return @return: String @auth
	 * shiyang @throws
	 */
	public static String getSign(String parm) {
		return MD5Util.md5Hex(parm);
	}
	public static String getAccessToken(String uid,String token) {
		Properties props = getProperties();
		String userName = props.getProperty("yys_mchNo");
		String customerUserName =uid;
		String timestamp= DateUtils.dateToString("yyyyMMddHHmmss", new Date());
		String parm=userName+timestamp+token+props.getProperty("yys_key");
		String sign = getSign(parm);
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("userName", userName);
		dataMap.put("thirdUserName", customerUserName);
		dataMap.put("timestamp", timestamp);
		dataMap.put("token", token);
		dataMap.put("sign", sign);
		Map<String, Object> execute = execute(yys_url_accesstoken, dataMap);
		if(null == execute.get("token")){
			return null;
		}
		return String.valueOf(execute.get("token"));
	}
	private static Properties getProperties(){
		
		return  PropUtils.getProps("open.properties");
	}
	public static String getPropertiesValue(String key){
		
		return getProperties().getProperty(key);
	}
	public static void main(String[] args) {
		String uid= "7168931F84C26E4D3F97AE74AE3D2A1";
		String token = "SEJTRWCFRVGUSPZMEQQSQQSYJAEJBU";
		String accessToken = getAccessToken(uid, token);
//		getSignTest();
	}
	private static void getSignTest() {
		String timestamp = "201707201738";
		String appUserName = "mianbaoyun";
		String appKey = "AXUSXEAUQRPGTKBNPVBI";
		System.out.println("---" + MD5Util.md5Hex(appUserName + timestamp + appKey));
	}

}
