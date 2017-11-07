package com.uavs.common.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getId(String sub){
		long nextLong = new Random().nextInt();
		nextLong = Math.abs(nextLong);
	    String strDate = sub+DateUtils.getDateTime("yyyymmddHHmmss"+nextLong);
		return strDate;
	}
	
	public static String getRedisRandom(String sub){
		double random = Math.random();
        String sRandom = String.valueOf((int) (random * 899 + 100));
	    String strDate = sub+DateUtils.getDateTime("yyyymmddHHmmss"+sRandom);
		return strDate;
	}
	
	public static void main(String[] args) {
		System.out.println(UUIDUtil.getRedisRandom("ORDER"));
	}
}
