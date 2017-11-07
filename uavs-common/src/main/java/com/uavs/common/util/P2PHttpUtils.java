package com.uavs.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.uavs.common.security.DESecbpkcs7;

/**
 * @description 调用大金融
 * @author shiyang
 * 2016年11月22日 下午8:30:13
 */
public class P2PHttpUtils {
	public static Log log = LogFactory.getLog(P2PHttpUtils.class);
	//测试环境
	public static String spec =ConfigPropUtils.get("p2p_url");
	public static String key = ConfigPropUtils.get("p2p_key");
	public static String p2p_token_url =ConfigPropUtils.get("p2p_token_url");
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> execute(String serviceId,Map<String,?> data){
		long startTime = System.currentTimeMillis();
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("Code","error");
		retMap.put("Message", "三方服务器繁忙...");
		try {
			log.info("请求参数  serviceId："+serviceId);
			String jsonString = JSON.toJSONString(data);
			log.info(" 加密前data "+jsonString);
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("ServiceId", serviceId);
			String encrypt = DESecbpkcs7.getEncrypt(key, jsonString);
			paramMap.put("Data", encrypt);
			String jsonStrEncrypt = JSON.toJSONString(paramMap);
			log.info(" 加密后请求参数 "+jsonStrEncrypt);
			String result = null;
			for(int i = 0; i<3;i++){
				if("S0061".equals(serviceId)){
					result = HttpClientUtil.postWay(p2p_token_url, jsonStrEncrypt, 3000, "UTF-8"); 
				}else{
					result = HttpClientUtil.postWay(spec, jsonStrEncrypt, 3000, "UTF-8"); 
				}
				if(StringUtils.isNotBlank(result)){
					break;
				}
			}
			Map<String,Object> dataMap = JSON.parseObject(result, Map.class);
			String dataStr = String.valueOf(dataMap.get("Data"));
			String decrypt = DESecbpkcs7.getDecrypt(key, dataStr);
			log.info("【大金融返回】   "+decrypt);
			Map<String,Object> parseObject = JSON.parseObject(decrypt, Map.class);
			log.info("请求时间共计   "+(System.currentTimeMillis()-startTime)+"毫秒");
			return parseObject;
		} catch (Exception e) {
			log.error(" 请求异常   ",e);
		}
		log.info("请求时间共计   "+(System.currentTimeMillis()-startTime)+"毫秒");
		return retMap;
	}
	
	/**    
	 * @Description: 只用作大金融，注册，实名，不重复发起  
	 * @param serviceId
	 * @param data
	 * @param errorCount 异常重复次数
	 * @return      
	 * @return: Map<String,Object>
	 * @auth shiyang
	 * @throws   
	 */
	public static Map<String,Object> execute(String serviceId,Map<String,?> data ,Integer errorCount){
		long startTime = System.currentTimeMillis();
		if(null == errorCount){
			errorCount=1;
		}
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("Code","error");
		retMap.put("Message", "三方服务器繁忙...");
		try {
			log.info("请求参数  serviceId："+serviceId);
			String jsonString = JSON.toJSONString(data);
			log.info(" 加密前data "+jsonString);
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("ServiceId", serviceId);
			String encrypt = DESecbpkcs7.getEncrypt(key, jsonString);
			paramMap.put("Data", encrypt);
			String jsonStrEncrypt = JSON.toJSONString(paramMap);
			log.info(" 加密后请求参数 "+jsonStrEncrypt);
			String result = null;
			for(int i = 0; i<errorCount;i++){
				try{
					result = HttpClientUtil.postWay(spec, jsonStrEncrypt, 30000, "UTF-8"); 
				}catch (Exception e) {
					log.error("第 "+ i +"次请求错误 再次请求   ",e);
				}
				if(StringUtils.isNotBlank(result)){
					log.error("第 "+ i +"次请求汇中网错误 再次请求   "+serviceId+"请求数据"+jsonString);
					break;
				}
			}
			Map<String,Object> dataMap = JSON.parseObject(result, Map.class);
			String dataStr = String.valueOf(dataMap.get("Data"));
			String decrypt = DESecbpkcs7.getDecrypt(key, dataStr);
			log.info("【大金融返回】   "+decrypt+"      请求【加密前参数】"+jsonString);
			Map<String,Object> parseObject = JSON.parseObject(decrypt, Map.class);
			log.info("请求时间共计   "+(System.currentTimeMillis()-startTime)+"毫秒");
			return parseObject;
		} catch (Exception e) {
			log.error(" 请求异常   ",e);
		}
		log.info("请求时间共计   "+(System.currentTimeMillis()-startTime)+"毫秒");
		return retMap;
	}
	
//	public static Map<String,Object> execute(String serviceId,Map<String,?> data){
//		long startTime = System.currentTimeMillis();
//		Map<String,Object> retMap = null;
//		try {
//			log.info("请求参数  serviceId："+serviceId);
//			String jsonString = JSON.toJSONString(data);
//			log.info(" 加密前data "+jsonString);
//			Map<String,String> paramMap = new HashMap<String,String>();
//			paramMap.put("ServiceId", serviceId);
//			String encrypt = DESecbpkcs7.getEncrypt(key, jsonString);
//			paramMap.put("Data", encrypt);
//			String jsonStrEncrypt = JSON.toJSONString(paramMap);
//			log.info(" 加密后请求参数 "+jsonStrEncrypt);
//			HttpUriRequest request =RequestBuilder.post()
//					.setUri( spec)
//					 .setHeader("Content-type","application/json; charset=utf-8")
//					 .setHeader("Accept", "application/json")
//					 .setEntity(new StringEntity(jsonStrEncrypt, Charset.forName("UTF-8")))
//					.build();
//				Map<String,String> executeJsonResult = LocalHttpClient.executeJsonResult(request, Map.class);
//				String dataStr = executeJsonResult.get("Data");
//				String decrypt = DESecbpkcs7.getDecrypt(key, dataStr);
//				log.info(" 返回值解密后   "+decrypt);
//			    retMap = JSON.parseObject(decrypt,Map.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error(" 请求异常   ");
//			log.info("请求时间共计   "+(System.currentTimeMillis()-startTime)+"毫秒");
//			return null;
//		}
//		log.info("请求时间共计   "+(System.currentTimeMillis()-startTime)+"毫秒");
//		return retMap;
//	}
	
	

//public static void main(String[] args) {
//		  spec = "http://yzdlr.hzdjr.com/hzcfdlr/dlrdata";
////		spec = "http://10.10.19.105:8080/HzDeputy-wap/app/team/function/isShowDialog.htm";
//		  key="1eaea089069a7cc2da866749";
//		Map<String,String> dataMap = new HashMap<String,String>();
//		String serviceId= "S0036";
//		dataMap.put("Id", "0211d300cb3d4a6dbbcd9ee64d69a7b9");
//			Map<String, Object> execute = execute(serviceId, dataMap);
//			if(execute == null){
//				return ;
//			}
//			System.out.println( " 次     "+execute);
//}

}
