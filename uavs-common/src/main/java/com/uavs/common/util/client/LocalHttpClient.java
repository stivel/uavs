package com.uavs.common.util.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class LocalHttpClient {

	protected static HttpClient httpClient = HttpClientFactory.createHttpClient(100,10);

	public static void init(int maxTotal,int maxPerRoute){
		httpClient = HttpClientFactory.createHttpClient(maxTotal,maxPerRoute);
	}
 
	public static <T> T execute(HttpUriRequest request,ResponseHandler<T> responseHandler){
		try {
			return httpClient.execute(request, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 数据返回自动JSON对象解析
	 * @param request
	 * @param clazz
	 * @return
	 */
	public static <T> T executeJsonResult(HttpUriRequest request,Class<T> clazz){
		return execute(request,JsonResponseHandler.createResponseHandler(clazz));
	}

	/**
	 * 调用 API
	 * 
	 * @param parameters
	 * @return
	 */
	private static Log logger = LogFactory.getLog(LocalHttpClient.class);
	
	public static String postJSON(String parameters,String postUrl) {
		long startTime = 0L;
		long endTime = 0L;
		int status = 0;
		HttpPost method = new HttpPost(postUrl);
		String body = null;
		logger.info("parameters:" + parameters);

		if (method != null & parameters != null
				&& !"".equals(parameters.trim())) {
			try {

				// 建立一个NameValuePair数组，用于存储欲传送的参数
				method.addHeader("Content-type","application/json; charset=utf-8");
				method.setHeader("Accept", "application/json");
				method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
				startTime = System.currentTimeMillis();
				HttpResponse response = httpClient.execute(method);
				endTime = System.currentTimeMillis();
				int statusCode = response.getStatusLine().getStatusCode();
				
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					status = 1;
				}
				body = EntityUtils.toString(response.getEntity());
			} catch (IOException e) {
				// 网络错误
				status = 3;
				logger.info("网络异常：" + status);
			} finally {
				logger.info("调用接口状态：" + status);
			}
		}
		return body;
	}
	 
	 
	 
}
