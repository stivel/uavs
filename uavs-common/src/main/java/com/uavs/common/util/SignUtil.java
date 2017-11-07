package com.uavs.common.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

 
public class SignUtil {
 
    /**   
     * @Description: 签名算法
     * @param map
     * @param key
     * @return
     * @author shiyang   
     * @date 2015年6月10日 下午2:46:16   
    */
    public static String getSign(Map<String,String> map,String key){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue().trim() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        sb.append("key="+key);
        String upperCase = Md5Util.MD5(sb.toString()).toUpperCase();
        return upperCase;
    }
    public static String getSignStr(Map<String,String> map,String key){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue().trim() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String upperCase = Md5Util.MD5(sb.toString()+"key="+key).toUpperCase();
        sb.append("sign="+upperCase);
        return sb.toString();
    }
    
    public static void main(String[] args) throws IllegalAccessException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("dealNo", "zm53345678911242227");
		map.put("code", "zm_money");
		map.put("totalCount", "3.00");
		map.put("productName", "蓝马庄");
		map.put("userId", "0987654321");
		String key="74c3872f01fb43d9bf6363ad6ecbf3a5";
		String sign = getSignStr(map, key);
		String sign2 = getSign(map, key);
		
		System.out.println(sign+"   "+sign2);
//		t();
		 
	}
    public static void t(){
    	Map<String,String> map = new HashMap<String,String>();
		map.put("orderId", "YSPf0b2000003800-2");
		map.put("status", "success");
		map.put("userId", "13ad335920e455e0e053268d640abf0b");
		 
		String sign = getSignStr(map, "74c3872f01fb43d9bf6363ad6ecbf3a5");
		System.out.println(sign);
    }
    
}
