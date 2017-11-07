package com.uavs.common.constances;

import java.util.HashMap;
import java.util.Map;

public class SysConstances {
	/**
	 * cfg.properties
	 */
	public static Map<String,String> CFG_MAP=new HashMap<>();
	/**
	 *conn.properties
	 */
	public static Map<String,String> CONN_MAP=new HashMap<>();
	/**
	 *lookup code
	 */
	public static Map<String,String> LOOKUP_MAP=new HashMap<>();
	
	public static int SESSION_TIME_OUT = 7 * 24 * 60 *60;
	/**
	 * 系统环境
	 */
	public static String ENVIRONMENT="dev";	
}
