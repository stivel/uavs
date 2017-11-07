package com.uavs.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

/**

 * 数据格式化工具类

 * @author Administrator

 *

 */
public class NumberUtil {
	
	/**

	 * 小数-->百分比 保留 N位小数 

	 * 默认:四舍五入

	 * 2016-7-1

	 * v-haowang

	 */
	public static String percentFormat(double number,int n){
		NumberFormat nf = NumberFormat.getPercentInstance(); 
		nf.setMinimumFractionDigits(n);// 小数点后保留几位

		String str = nf.format(number);
		return str;
	}
	
	/**

	 * double 保留n位小数  四舍五入

	 * @return

	 */
	public static double formatDouble(double d,int n){
		String format="#.";
		for(int i=0;i<n;i++){
			format+="#";
		}
		return Double.valueOf(new DecimalFormat(format).format(d));
	}
	
	/**

	 * 数据格式化 

	 * #,###.00

	 * 2016-7-1

	 * v-haowang

	 */
	public static String numFormat(Object o){
		String str = "--";
		try{
			Format fm = new DecimalFormat("#,##0.00");
			str = fm.format(Double.parseDouble(String.valueOf(o)));
		} catch (Exception e) {
			System.out.println("类型转换失败 ");
			str = "--";
		}
		return str;
	}
	
	/**

	 * double转String(去掉科学计数法E)

	 * 1234E5.123--->

	 * 2016-7-1

	 * v-haowang

	 */
	public static String dToString(double d){
		String str = "--";
		try{
			Format fm = new DecimalFormat("#");
			str = fm.format(d);
		} catch (Exception e) {
			str = "--";
		}
		return str;
	}
	/**

	 * 获取6位随机数

	 * @return

	 */
	public static int getRandomNum(){
		int[] ret = new int[6];
		for (int i = 0; i < 6; i++) {
			int random=(int)(Math.random()*10);
			ret[i] = random;
		}
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < ret.length; i++) {
			sb.append(ret[i]);
		}
		return Integer.parseInt(sb.toString());
	}
	
	/**
	 * 去千分位格式化
	 * @param o
	 * @return
	 * @author zhoujie
	 * @date 2017年3月15日 下午7:47:17
	 */
	public static String deleteFormat(Object o){
		if(null==o||StringUtils.isBlank(o.toString())){
			return "--";
		}
		String str = "--";
		try{
			str = new DecimalFormat().parse(o.toString()).doubleValue()+"";
		} catch (Exception e) {
			System.out.println("类型转换失败 ");
			str = "--";
		}
		return str;
	}
	
	/**
	 * 去除null
	 * @param b
	 * @return
	 * @author zhoujie
	 * @date 2017年4月21日 下午5:51:18
	 */
	public static BigDecimal dropNull(BigDecimal b){
		return null==b?new BigDecimal("0"):b;
	}
	
	/**
	 * 获取某个区间的随机数
	 * @param start 开始区间
	 * @param end	结束区间
	 * @return
	 * @author zhoujie
	 * @date 2017年4月21日 下午6:45:25
	 */
	public static int randomNumber(int start,int end){
		Random r = new Random();
		int a = start+r.nextInt(end-start+1);
		return a;
	}
	
	public static void main(String[] args) {
		System.out.println(randomNumber(99,100));
		Object d = 1000;
		System.out.println(numFormat(d));
		System.out.println(deleteFormat("10,273.78"));
	}
	
}