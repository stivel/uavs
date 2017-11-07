package com.uavs.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 短连接工具类
 * @ClassName: ShortUrlUtil 
 * @author zhoujie
 * @date 2017年6月4日 下午6:05:47
 */
public class ShortUrlUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	
	/**
	 * 创建短连接
	 * @param longUrl
	 * @return
	 * @author zhoujie
	 * @date 2017年6月4日 下午6:05:21
	 */
	public static String createShortUrl(String longUrl){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("url", longUrl);
		String json = HttpClientUtil.postWay("http://980.so/api.php", paramMap, 3000, "UTF-8");
		logger.info("调用 http://980.so/api.php 返回短连接："+json);
	    return StringUtil.isBlank(json)?longUrl:json;
	}
	
	public static void main(String[] args) {
		System.out.println(createShortUrl("http://tmdrk.imwork.net/mobile/activityXs/share.htm?mobileNumber=13665627148"));
	}
}
