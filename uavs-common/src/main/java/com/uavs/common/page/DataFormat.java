package com.uavs.common.page;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.uavs.common.constances.SysConstances;
import com.uavs.common.directive.MoneyDirective;
import com.google.common.collect.Maps;

/**
 * 数据格式化 工具类
 * @author zhongcheng
 */
public class DataFormat {
	private final static String FORMAT_TYPE_ID="FORMAT_TYPE_ID";
	private final static String FORMAT_TYPE_DATE="FORMAT_TYPE_DATE";
	private final static String FORMAT_TYPE_MONEY="FORMAT_TYPE_MONEY";
	private final static String FORMAT_TYPE_CODE="FORMAT_TYPE_CODE";
	private final static String FORMAT_TYPE_STATUS="FORMAT_TYPE_STATUS";
	public Map<String ,FormatParam> paramMap=new HashMap<String, FormatParam>();
	
	
	
	public void formatId(String field){
		paramMap.put(field, new FormatParam(field, null, FORMAT_TYPE_ID));
	}
	public void formatDate(String field,String dateFormatter){
		paramMap.put(field,new FormatParam(field, dateFormatter, FORMAT_TYPE_DATE));
	}
	public void formatMoney(String field){
		paramMap.put(field,new FormatParam(field, null, FORMAT_TYPE_MONEY));
	}
	public void formatCode(String field){
		paramMap.put(field,new FormatParam(field, null, FORMAT_TYPE_CODE));
	}
	/**
	 * 格式化状态。
	 * @author zhongcheng
	 * 示例：dataFormat.fromatStatus("isDelete", "未删除", "已删除");
	 * 结果：isDelete_name
	 * @param field
	 * @param trueStatus
	 * @param falseStatus
	 */
	public void formatStatus(String field, String trueStatus, String falseStatus) {
		Map<String, String> pattern = Maps.newHashMap();
		pattern.put("trueStatus", trueStatus);
		pattern.put("falseStatus", falseStatus);
		paramMap.put(field,new FormatParam(field, pattern, FORMAT_TYPE_STATUS));
	}
	/**
	 * 匹配所有数据
	 * @author zhongcheng
	 */
	public String dataFormat(FormatParam formatParam,String value){
			String val="";
			if(formatParam.getFormatType().equals(FORMAT_TYPE_ID)){//匹配IDNAME
				val= this.frormatId(formatParam,value);
			}
			if(formatParam.getFormatType().equals(FORMAT_TYPE_DATE)){//匹配日期
				val=this.foramtDate(formatParam,value);
			}
			if(formatParam.getFormatType().equals(FORMAT_TYPE_MONEY)){//匹配金钱
				val= this.foramtMoney(formatParam,value);
			}
			if(formatParam.getFormatType().equals(FORMAT_TYPE_CODE)){//匹配编码
				val= this.foramtCode(formatParam,value);
			}
			if(formatParam.getFormatType().equals(FORMAT_TYPE_STATUS)){//匹配状态
				val= this.foramtStatus(formatParam,value);
			}
			return val;
	}

	private String frormatId(FormatParam formatParam,String value){
		String result="";
		String key=value;
		//待实现
		return key;
	}
	private String foramtDate(FormatParam formatParam,String value){
		Date date=new Date();
		date.setTime(Long.valueOf(value));
		SimpleDateFormat df=new SimpleDateFormat((String)formatParam.getPattern());
		return df.format(date);
	}
	private String foramtMoney(FormatParam formatParam,String value){
		String result="";
		try {
			BigDecimal money=new BigDecimal(value);
			result=MoneyDirective.fmoney(money, 2);
		} catch (Exception e) {}
		return result;
	}
	private String foramtCode(FormatParam formatParam,String value){
		String result="";
		String key=value;
		//读取内存数据，将来可以改为读取redis数据
		result = SysConstances.LOOKUP_MAP.get(key);
		return result;
	}
	private String foramtStatus(FormatParam formatParam,String value) {
		String result = "";
		Map<String, String> pattern = (Map<String, String>) formatParam.getPattern();
		if (value.equals("true")) {
			result = pattern.get("trueStatus");
		}
		if (value.equals("false")) {
			result = pattern.get("falseStatus");
		}
		return result;
	}
}


