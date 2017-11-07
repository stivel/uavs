package com.uavs.common.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * 工具类
 * @author zhangyinghua
 * @date 2016-10-18
 * @version v1.0.0
 */
public class Helper {

	/**
	 * 是否是ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
	
	/**
	 * 以JSON响应
	 * @param response
	 * @param json 字符串
	 * @throws IOException
	 */
	public static void responseJson(HttpServletResponse response, String json) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(json);
		response.getWriter().flush();
	}

	
	/**
	 * 以JSON响应 重载
	 * @param response
	 * @param data Map类型
	 * @throws IOException
	 */
	public static void responseJson(HttpServletResponse response, Map<String, Object> data) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(JSON.toJSONString(data));
	}
}
