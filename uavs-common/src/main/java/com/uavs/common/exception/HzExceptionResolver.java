package com.uavs.common.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.uavs.common.util.Constant;
import com.uavs.common.util.Helper;

/**
 * 全局异常处理类
 * 
 * @author zhangyinghua
 * @date 20167-10-18
 * @version v1.0
 */
public class HzExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Log loger = LogFactory.getLog(HzExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		BaseException baseException = null;
		if (ex instanceof BaseException) {
			ex.printStackTrace();
			baseException = (BaseException) ex;
		} else {
			baseException = new BaseException("未知错误，请联系管理员");//运行时异常抛出未知错误
		}
		String message = baseException.getMessage();
		if (Helper.isAjaxRequest(request)) { //如果是ajax请求返回JSON
			Map<String, Object> data = new HashMap<String, Object>();
			data.put(Constant.ERROR_KEY, message);
			try {
				Helper.responseJson(response, data);
				return null;
			} catch (IOException e) {
				loger.error("对AJAX请求返回异常信息时报错");
				e.printStackTrace();
			}
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(Constant.ERROR_MESSAGE, message);
		modelAndView.setViewName(Constant.ERROR_VIEW);
		return modelAndView;
	}
}
