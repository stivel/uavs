package com.uavs.common.util;


import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * md5工具类,签名和验签,通过org.apache.commons.codec.digest.DigestUtils实现
 */
public class Md5Util {
	public final static String MD5(String s) {
		return MD5(s, "UTF-8").toUpperCase();
	}

	public final static String MD5(String s, String input_charset) {
		return DigestUtils.md5Hex(getContentBytes(s, input_charset));
	}

	public final static String MD5(String s, String key, String input_charset) {
		s += key;
		return MD5(s, input_charset);
	}

	/**
	 * 验证签名
	 * 
	 * @param text
	 *            需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @param key
	 *            密钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果
	 */
	public static boolean verify(String text, String sign, String key, String input_charset) {
		text = text + key;
		return verify(text, sign, input_charset);
	}

	/**
	 * 验证签名
	 * 
	 * @param text
	 *            需要签名的字符串+密钥
	 * @param sign
	 *            签名结果
	 * @return
	 */
	public static boolean verify(String text, String sign) {
		return verify(text, sign, "UTF-8");
	}

	/**
	 * 验证签名
	 * 
	 * @param text
	 *            需要签名的字符串+密钥
	 * @param sign
	 *            签名结果
	 * @param input_charset
	 *            编码格式
	 * @return
	 */
	public static boolean verify(String text, String sign, String input_charset) {
		String mysign = Md5Util.MD5(text, input_charset);
		if (mysign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (StringUtils.isEmpty(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}
	
	public static void main(String argus[]){
		String str = "mianbaoyun面包云";
		System.out.println("加密前  "+str+ "   加密后"+Md5Util.MD5(str));
	}
}
