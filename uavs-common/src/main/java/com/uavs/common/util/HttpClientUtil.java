package com.uavs.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	/**
	 * post方式 王浩 @2015-5-14 功能:超时和编码 timeout:超时(毫秒) encoding:编码
	 */
	@SuppressWarnings("deprecation")
	public static String postWay(String url, Map<String, String> map,
			int timeout, String encoding) {
		logger.info("请求地址"+url);
		if(null == map){
			map = new HashMap<String, String>();
		}else{
			logger.info("请求参数"+JSON.toJSONString(map));
		}
		String result = "";
		PostMethod postMethod = null;
		try {
			// 读取内容
			HttpClient httpClient = new HttpClient();
			// 设置超时
			httpClient.setConnectionTimeout(timeout);
			if (encoding != null && !"".equals(encoding)) {
				// 设置编码
				httpClient.getParams().setParameter(
						HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
			}
			postMethod = new PostMethod(url);
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			// 参数
			if (map != null && map.size() != 0) {
				int i = map.size();
				NameValuePair[] params = new NameValuePair[i];
				int k = 0;
				for (String key : map.keySet()) {
					params[k] = new NameValuePair(key, map.get(key));
					k++;
				}
				postMethod.setRequestBody(params);
			}
			httpClient.executeMethod(postMethod);
			// 第一种方式
			result = postMethod.getResponseBodyAsString();
			logger.info(url+" 请求响应"+ result);
		} catch (Exception e) {
			logger.error("httpclient请求异常", e);
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}
	/**
	 * 以json格式传送参数
	 * @param url
	 * @param json
	 * @param timeout
	 * @param encoding
	 * @return
	 */
	public static String postWay(String url, String json,int timeout, String encoding) {
		String result = "";
		PostMethod postMethod = null;
		try {
			// 读取内容
			HttpClient httpClient = new HttpClient();
			// 设置连接超时
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			postMethod = new PostMethod(url);
			RequestEntity se = new StringRequestEntity(json, "application/json", encoding);
			postMethod.setRequestEntity(se);
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
			//请求超时
			postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 30000);
			httpClient.executeMethod(postMethod);
			result = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			logger.error("httpclient请求异常" +"请求地址"+url+"数据"+json, e);
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}
	
	
}
