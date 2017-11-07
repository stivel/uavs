package com.uavs.common.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigPropUtils {
	  private static Properties props;
	  static{
		  try {
	            props = new Properties();
	            InputStream in = ConfigPropUtils.class.getClassLoader().getResourceAsStream("config.properties");
	            props.load(in);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	  }
	  public static String get(String key){
		  return props.getProperty(key, null);
	  }
	  
}
