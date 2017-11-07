package com.uavs.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.uavs.common.constances.SysConstances;
import com.uavs.common.reader.PropertiesReader;

public class CommListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		String environment = this.findEnvironment(event);
		SysConstances.ENVIRONMENT = environment;
		event.getServletContext().setAttribute("ENVIRONMENT", environment);// 环境配置写入上下文
		
		PropertiesReader.cacheCfgProperties(environment, event);
		PropertiesReader.cacheConnProperties(environment);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * 
	 * checkEnvironment(获得环境配置)
	 *
	 * @Title: checkEnvironment @Description: TODO @param @param
	 *         event @param @return @return String @throws
	 */
	private String findEnvironment(ServletContextEvent event) {
		String contextConfigLocation = event.getServletContext().getInitParameter("contextConfigLocation");
		String environment = "dev";
		if (contextConfigLocation.indexOf("/dev/") > 0) {
			environment = "dev";
		} else if (contextConfigLocation.indexOf("/test/") > 0) {
			environment = "test";
		} else if (contextConfigLocation.indexOf("/prod/") > 0) {
			environment = "prod";
		} else if (contextConfigLocation.indexOf("/pre/") > 0) {
			environment = "pre";
		}
		return environment;
	}


}
