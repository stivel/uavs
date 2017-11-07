package com.uavs.common.util.sign;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class SignatureUtil {

	 
	/**    
	 * @Description: 生产请求参数
	 * @param map
	 * @param paternerKey
	 * @return      
	 * @return: String
	 * @auth shiyang
	 * @throws   
	 */
	public static String generatePackage(Map<String, String> map,String paternerKey){
		String sign = generateSign(map,paternerKey);
		Map<String,String> tmap = MapUtil.order(map);
		String s2 = MapUtil.mapJoin(tmap,false,true);
		return s2+"&sign="+sign;
	}

	 
	/**    
	 * @Description: 生成sign MD5 加密 toUpperCase
	 * @param map
	 * @param paternerKey
	 * @return      
	 * @return: String
	 * @auth shiyang
	 * @throws   
	 */
	public static String generateSign(Map<String, String> map,String paternerKey){
		Map<String, String> tmap = MapUtil.order(map);
		if(tmap.containsKey("sign")){
			tmap.remove("sign");
		}
		String str = MapUtil.mapJoin(tmap, false, false);
		return DigestUtils.md5Hex(str+"&key="+paternerKey).toUpperCase();
	}
	
	
	public static boolean checkSign(Map<String, String> map,String paternerKey){
		String orgSign =map.get("sign");
		if(StringUtils.isBlank(orgSign)){
			return false;
		}
		return orgSign.equals(generateSign(map, paternerKey));
	}
	
	public static void main(String ...strings){
		Map<String, String> tmap  = new HashMap<>();
		tmap.put("mchNo", "meidaojia");
		tmap.put("timestamp", "20170719111111");
		tmap.put("uid", "77168931F84C26E4D3F97AE74AE3D2A1");
		System.out.println(generatePackage(tmap, "meidaojiakey"));
	}

	 


   

}
