package com.uavs.common.constances;

import java.util.ArrayList;
import java.util.List;

import com.uavs.common.util.ConfigPropUtils;

public class ProdIOSConstances {
	
	/**
	 * @description 是否测试
	 * @author shiyang
	 * 2016年12月9日 下午3:44:56
	 */
	public static boolean isTest=false;
	
	/**
	 * @description 测试手机号
	 * @author shiyang
	 * 2016年12月9日 下午3:45:09
	 */
	public static List<String> mobiles = new ArrayList<String>();
	
	static{
		String isTestStr = ConfigPropUtils.get("istest");
		isTest =  Boolean.valueOf(isTestStr);
		String testMobileStr = ConfigPropUtils.get("test_mobile");
		String[] split = testMobileStr.split(",");
		for (String string : split) {
			mobiles.add(string);
		}
	}
}
