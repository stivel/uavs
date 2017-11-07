package com.uavs.common.framework;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.uavs.common.page.DataPager;
import com.google.common.collect.Maps;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {
	protected String SUCCESS = "success";
	protected Map<String, Object> cookies2Map(HttpServletRequest request) {
		Map<String, Object> cookieMap = Maps.newHashMap();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie c : cookies) {
				cookieMap.put(c.getName(), c.getValue());
			}
		}

		return cookieMap;
	}
	protected Map<String, Object> paramsToMap(HttpServletRequest request) {
		Map<String, Object> param = Maps.newHashMap();
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			if (null != request.getParameter(name) && !"".equals(request.getParameter(name).trim())) {
				try {
					param.put(StringUtils.trim(name),
							java.net.URLDecoder.decode(request.getParameter(name).toString(), "UTF-8").trim());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return param;
	}

	/**
	 * 处理参数列表为Map<String, String>
	 * 
	 * @author DongLuning
	 * @param request
	 * @return
	 */
	protected Map<String, String> params2Map(HttpServletRequest request) {
		Map<String, String> param = Maps.newHashMap();
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			if (null != request.getParameter(name) && !"".equals(request.getParameter(name).trim())) {
				try {
					param.put(StringUtils.trim(name),
							java.net.URLDecoder.decode(request.getParameter(name).toString(), "UTF-8").trim());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return param;
	}

	protected void paramsToModel(HttpServletRequest request, Model model, String[] paramsKey) {
		Map<String, Object> params = paramsToMap(request);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			boolean flag = false;
			if (null != paramsKey) {
				for (String key : paramsKey) {
					if (key.equals(entry.getKey())) {
						flag = true;
						break;
					}
				}
			} else {
				flag = true;
			}

			if (flag) {
				model.addAttribute(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * @description   成功响应
	 */
	public String success(Object body){
		ResultBean bean = new ResultBean();
		Header header = new Header();
		header.setMsg("SUCCESS");
		header.setCode("200");
		header.setFlag(true);
		bean.setHeader(header);
		if(body == null)
			body = "";
		bean.setBody(body);
		return JSON.toJSON(bean).toString();
	 }
	 /**
	 * @description  异常响应
	 */
	public String error(String errorCode,String errorMsg){
		ResultBean bean = new ResultBean();
		Header header = new Header();
		header.setMsg(errorMsg);
		header.setCode(errorCode);
		header.setFlag(false);
		bean.setHeader(header);
		return JSON.toJSON(bean).toString();
	 }

	/**
	 * @description   成功响应
	 * @param body
	 * @return
	 * @author shiyang
	 * 2016年10月20日 下午2:45:21
	 */
	public Object renderSuccess(Object body){
		ResultBean bean = new ResultBean();
		Header header = new Header();
		header.setMsg("SUCCESS");
		header.setCode("200");
		header.setFlag(true);
		bean.setHeader(header);
		if(body == null)
			body = "";
		bean.setBody(body);
		return JSON.toJSON(bean);
	}
	
	/**
	 * 创建数据分页类
	 * 
	 * @param params
	 * @param dataFormat
	 * @return
	 */
	protected static DataPager createDataPager(Map params) {
		DataPager dataPager = new DataPager();
		if (null != params.get("page") && StringUtils.isNotEmpty((String) params.get("page"))) {
			dataPager.setPage(Integer.parseInt(params.get("page").toString()));
		}
		if (null != params.get("size") && StringUtils.isNotEmpty((String) params.get("size"))) {
			dataPager.setSize(Integer.parseInt(params.get("size").toString()));
		}
		return dataPager;
	}

	/**
	 * Description: 获取所有的传递参数
	 *
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Author wuwenbin
	 */
	public Map<String, Object> getRequestParam() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		Iterator<?> iter = request.getParameterMap().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = entry.getKey().toString();
			String value = request.getParameter(key);
			try {
				value = new String(value.getBytes("utf-8"),request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * Description: 获取所有的传递参数
	 *
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Author wuwenbin
	 */
	public Map<String, String> getRequestParamString() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> result = new HashMap<String, String>();
		Iterator<?> iter = request.getParameterMap().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = entry.getKey().toString();
			String value = request.getParameter(key);
			try {
				value = new String(value.getBytes("utf-8"),request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 
	 * ajaxSuccess(异步操作完成)
	 *
	 * @Title: ajaxSuccess @Description: TODO @param @param param @param @param
	 *         successMsg @param @param errorMsg @param @return @return
	 *         String @throws
	 */
	protected String ajaxSuccess(Object param, String successMsg, String errorMsg) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();

		if (param instanceof Integer) {
			int value = ((Integer) param).intValue();
			if (value > 0) {
				node.put("success", true);
				node.put("count", value);
				node.put("msg", successMsg);
			} else {
				node.put("success", false);
				node.put("count", value);
				node.put("msg", errorMsg);
			}

		} else if (param instanceof String) {
			String s = (String) param;
		} else if (param instanceof Double) {
			double d = ((Double) param).doubleValue();
		} else if (param instanceof Float) {
			float f = ((Float) param).floatValue();
		} else if (param instanceof Long) {
			long l = ((Long) param).longValue();
		} else if (param instanceof Boolean) {
			boolean b = ((Boolean) param).booleanValue();
			if (b) {
				node.put("success", true);
				node.put("msg", successMsg);
			} else {
				node.put("success", false);
				node.put("msg", errorMsg);
			}
		} else if (param instanceof Date) {
			Date d = (Date) param;
		}

		String json = "";
		try {
			json = mapper.writeValueAsString(node);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 
	 * ajaxSuccess(异步操作完成)
	 *
	 * @Title: ajaxSuccess @Description: TODO @param @param
	 *         param @param @return @return String @throws
	 */
	protected String ajaxSuccess(Object param) {
		return this.ajaxSuccess(param, "操作成功！", "操作失败！");
	}
    /**
     * @description  获取客户端ip
     * @param request
     * @return
     * @author shiyang
     * 2016年10月26日 下午2:11:17
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown host";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
