package com.uavs.common.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
 * 工具类
 * 
 * @author WangShi
 * @version 1.0 
 * @date 2015年7月3日 下午1:14:15
 * @since 1.0
 */
public class NgearUtil {
	
	private static Logger logger = Logger.getLogger(NgearUtil.class);
	
	private static Properties prop = null;
	
	/**
	 * 读取config.properties
	 * @param key
	 */
	public static String getValue(String key){
		String value = null;
		try{
			if(prop == null){
				InputStream in = NgearUtil.class.getResourceAsStream("/config.properties");
				prop = new Properties();
				prop.load(in);
			}
			value = prop.getProperty(key);
		}catch(Exception e){
			logger.error("读取config.properties", e);
		}
		return value;
	}
	
    /**
     * 通过文件名读取配置文件（需按照标准目录放置配置文件）
     * 
     * @param name  文件名
     * @param key   key
     * @return
     * @throws Exception
     */
    public static String getPropertiesByName(String name,String key) throws Exception{
        Resource resource = new ClassPathResource("/"+name);
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        return props.getProperty(key);
    }
    
    /**
     * 通过路径+文件名读取配置文件
     * 
     * @param urlName   路径+文件名
     * @param key       key
     * @return
     * @throws Exception
     */
    public static String getPropertiesByUrl(String urlName,String key) throws Exception{
        Resource resource = new ClassPathResource(urlName);
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        return props.getProperty(key);
    }

}
