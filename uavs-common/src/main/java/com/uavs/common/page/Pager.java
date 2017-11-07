package com.uavs.common.page;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class Pager implements Serializable{
	private Integer page = 1; // 页号
	private Integer rows = 20;// 每页记录数
	private Integer startRow=0;//起始
	public Pager(){
		
	}
	
	public Pager(Map params){
		if (null!=params.get("page")&&StringUtils.isNotEmpty((String)params.get("page"))) {
			this.page=Integer.parseInt(params.get("page").toString());
		}
		if (null!=params.get("rows")&&StringUtils.isNotEmpty((String)params.get("rows"))) {
			this.rows=Integer.parseInt(params.get("rows").toString());
		}
		this.startRow=(page-1)*rows;
	}
	
	public Pager(HttpServletRequest request){
		Map params=this.formatParam(request);
		if (null!=params.get("page")&&StringUtils.isNotEmpty((String)params.get("page"))) {
			this.page=Integer.parseInt(params.get("page").toString());
		}
		if (null!=params.get("rows")&&StringUtils.isNotEmpty((String)params.get("rows"))) {
			this.rows=Integer.parseInt(params.get("rows").toString());
		}
		this.startRow=(page-1)*rows;
	}
	public Pager(Map params,int rows){
		if (null!=params.get("page")&&StringUtils.isNotEmpty((String)params.get("page"))) {
			this.page=Integer.parseInt(params.get("page").toString());
		}
		this.rows=rows;
		this.startRow=(page-1)*rows;
	}
	public Pager(int page,int rows){
		this.rows=rows;
		this.page=page;
		this.startRow=(page-1)*rows;
	}
	
	public Pager(HttpServletRequest request,int rows){
		Map params=this.formatParam(request);
		if (null!=params.get("page")&&StringUtils.isNotEmpty((String)params.get("page"))) {
			this.page=Integer.parseInt(params.get("page").toString());
		}
		this.rows=rows;
		this.startRow=(page-1)*rows;
	}
	protected Map<String, Object> formatParam(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			if (null != request.getParameter(name)&& !"".equals(request.getParameter(name).trim())) {
				try {
					param.put(StringUtils.trim(name),java.net.URLDecoder.decode(request.getParameter(name).toString(),"UTF-8").trim());
				} catch (UnsupportedEncodingException e1) {
					System.err.println(e1.getMessage());
				}
			}
		}
		return param;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}


	
	
}
