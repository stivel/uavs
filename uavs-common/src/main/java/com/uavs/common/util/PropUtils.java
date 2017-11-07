package com.uavs.common.util;

import java.io.InputStreamReader;
import java.util.Properties;

public class PropUtils {
	   
	  public static Properties getProps(String fileName){
		  try {
			  Properties   props = new Properties();
			   props.load((new InputStreamReader(PropUtils.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"))); 
	          return props;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		  return new Properties();
	  }
}
