package com.uavs.common.util;

import java.util.UUID;

public class KeyCodeGenerator {
	/**
	 * 获得32位UUID
	 */
	public static String random32UUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String temp = str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return temp.toUpperCase();
	}
	/**
	 * 获得16位UUID
	 */
	public static String random16UUID() {
		String key = String.valueOf(System.currentTimeMillis())
				+ UUID.randomUUID().toString();
		StringBuffer buf = null;
		String uuid=Md5Util.MD5(key);
		return uuid.substring(8, 24).toUpperCase(); // 16位的加密
	}
}
