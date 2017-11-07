package com.uavs.common.reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.springframework.core.io.ClassPathResource;

import com.uavs.common.constances.SysConstances;

public class PropertiesReader {
	/**
	 * 缓存cfg.properties
	 * @param event FDY
	 */
	public static void cacheCfgProperties(String environment, ServletContextEvent event) {
		Map<String, String> map = new HashMap<>();
		System.err.println("loading....  "+environment+"/common.properties");
		ClassPathResource cp = new ClassPathResource("common.properties");
		Properties properties = new OrderedProperties();
		try {
			properties.load(cp.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("load cfg.properties error!");
		}
		for (Iterator its = properties.keySet().iterator(); its.hasNext();) {
			String zkey = (String) its.next();
			map.put(zkey, properties.getProperty(zkey).trim());
		}
		SysConstances.CFG_MAP = map;
	}
	
	/**
	 * 缓存conn.properties
	 */
	public static void cacheConnProperties(String environment) {
		Map<String, String> map = new HashMap<>();
		System.err.println("loading....  "+environment+"/jdbc.properties");
		ClassPathResource cp = new ClassPathResource("jdbc.properties");
		Properties properties = new OrderedProperties();
		try {
			properties.load(cp.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("load conn.properties error!");
		}
		for (Iterator its = properties.keySet().iterator(); its.hasNext();) {
			String zkey = (String) its.next();
			map.put(zkey, properties.getProperty(zkey).trim());
		}
		SysConstances.CONN_MAP = map;
	}
}
