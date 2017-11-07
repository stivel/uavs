package com.uavs.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.uavs.common.security.DESecbpkcs7;

/**
 * @description 调用大文娱
 * @author shiyang
 * 2016年11月22日 下午8:30:13
 */
public class DwyHttpUtils {
	public static Log log = LogFactory.getLog(DwyHttpUtils.class);
	//测试环境
	public static String URL =ConfigPropUtils.get("dwy_url");
	 
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> execute(String url,Map<String,String> data){
		data.put("from_host", "http://www.mianbaoyun.cn/");
		long startTime = System.currentTimeMillis();
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("Code","error");
		retMap.put("Message", "三方服务器繁忙...");
		try {
			log.info("请求参数 "+JSON.toJSONString(data));
			String result = null;
			for(int i = 0; i<1;i++){
				result = HttpClientUtil.postWay(URL+url, data, 3000, "UTF-8"); 
				if(StringUtils.isNotBlank(result)){
					break;
				}
			}
			result = new String(result.getBytes(), "UTF-8"); 
			log.info("【大文娱返回"+result);
			Map<String,Object> dataMap = JSON.parseObject(result, Map.class);
			return dataMap;
		} catch (Exception e) {
			log.error(" 请求异常   ",e);
			log.info("请求时间共计   "+(System.currentTimeMillis()-startTime)+"毫秒");
		}
		log.info("请求时间共计   "+(System.currentTimeMillis()-startTime)+"毫秒");
		return retMap;
	}
	 
}
