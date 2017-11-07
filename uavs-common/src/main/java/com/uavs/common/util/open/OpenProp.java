package com.uavs.common.util.open;

import java.util.Properties;

import com.uavs.common.util.PropUtils;

public class OpenProp {
	private static Properties getProperties() {

		return PropUtils.getProps("open.properties");
	}

	public static String getPropertiesValue(String key) {

		return getProperties().getProperty(key);
	}
}
