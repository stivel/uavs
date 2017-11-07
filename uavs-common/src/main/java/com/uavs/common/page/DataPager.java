package com.uavs.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;

public class DataPager<T> implements Serializable {
	private List<T> rows; // 当页记录
	private long page = 1l; // 页号
	private long size = 20l;// 每页记录数
	private long total = 0l; // 总记录条数
	private long max;//总页数
	private String pageHtml;

	public DataPager() {

	}
	
	public DataPager(long max,List<T> rows) {
		this.max = max;
		this.rows = rows;
		buildPageHtml();
	}

	public DataPager(Pager pager) {
		this.page = pager.getPage();
		this.size = pager.getRows();
	}

	public DataPager(DataPager dp) {
		this.rows = dp.getRows();
		this.page = dp.getPage();
		this.size = dp.getSize();
		this.total = dp.getTotal();
		this.max = dp.getMax();
		this.pageHtml = dp.getPageHtml();
	}
	
	
	

	public static <T> DataPager<T> getDataPager(DataPager<T> dataPager, Pager pager) {
		return getDataPager(dataPager, pager.getPage());
	}


	public static <T> DataPager<T> getDataPager(DataPager<T> dataPager, int pageNum) {
		Page<T> page = (Page<T>) dataPager.getRows();
		if (dataPager.getRows() != null && dataPager.getRows().size() > 0 & page.getPages() < pageNum) {
			dataPager.setRows(new ArrayList<T>());
		}
		return dataPager;
	}

	/**
	 * 
	 * buildEcPageHtml
	 *
	 * @Title: buildEcPageHtml @Description: TODO @param @return void @throws
	 */
	private void buildEcPageHtml() {
		long max;
		if (total > 0 && total % size == 0) {
			max = total / size;
		} else if (total > 0 && total % size > 0) {
			max = total / size + 1;
		} else {
			max = 1;
		}
		this.max = max;
		long prev;
		if (page == 1) {
			prev = 1;
		} else {
			prev = page - 1;
		}
		long next;
		if (max == 1) {
			next = 1;
		} else if (max == page) {
			next = max;
		} else {
			next = page + 1;
		}

		// 网站列表页分页处理 2015/12/11
		String html = "";
		if (page == 1) {
			html += "<a class='page_pre nopage'></a>";
		} else {
			html += "<a class='page_pre' href='javascript:pagerGoto(" + prev + ")'></a>";
		}

		if (page == max) {
			html += "<a class='page_nex nopage'></a>";
		} else {
			html += "<a class='page_nex' href='javascript:pagerGoto(" + next + ")'></a>";
		}
		html += "<a class='current_value'>" + page + "</a><a class='total_value'>/" + max + "</a>";

	}

	/**
	 * 
	 * buildPageHtml
	 *
	 * @Title: buildPageHtml @Description: TODO @param @return void @throws
	 */
	private void buildPageHtml() {
		// int index=1;
		long max;
		if (total > 0 && total % size == 0) {
			max = total / size;
		} else if (total > 0 && total % size > 0) {
			max = total / size + 1;
		} else {
			max = 1;
		}
		this.max = max;
		long prev;
		if (page == 1) {
			prev = 1;
		} else {
			prev = page - 1;
		}
		long next;
		if (max == 1) {
			next = 1;
		} else if (max == page) {
			next = max;
		} else {
			next = page + 1;
		}

		// 新的分页处理,耿世坤,2015/10/23
		String html = "";
		if (page == 1) {
			html += "<li class='disabled'><a>上一页</a></li>";
		} else {
			html += "<li><a href='javascript:pagerGoto(" + prev + ")'>上一页</a></li>";
		}
		if (max <= 5) {
			for (int i = 1; i < max + 1; i++) {
				if (i == page) {
					html += "<li  class='active'><a href='javascript:pagerGoto(" + i + ")'>" + i + "</a></li>";
				} else {
					html += "<li><a href='javascript:pagerGoto(" + i + ")'>" + i + "</a></li>";
				}
			}
		} else if (max >= 6) {
			if (page == 5) {
				html += "<li><a href='javascript:pagerGoto(" + 1 + ")'>" + 1 + "</a></li>";
			}
			if (page >= 6) {
				html += "<li><a href='javascript:pagerGoto(" + 1 + ")'>" + 1 + "</a></li>";
				html += "<li><span>...</span></li>";
			}

			if (page - 1 > 0) {
				if (page - 2 > 0) {
					if (page - 3 > 0) {
						html += "<li><a href='javascript:pagerGoto(" + (page - 3) + ")'>" + (page - 3) + "</a></li>";
					}
					html += "<li><a href='javascript:pagerGoto(" + (page - 2) + ")'>" + (page - 2) + "</a></li>";
				}
				html += "<li><a href='javascript:pagerGoto(" + (page - 1) + ")'>" + (page - 1) + "</a></li>";
			}

			html += "<li class='active'><a href='javascript:pagerGoto(" + page + ")'>" + page + "</a></li>";

			if (page + 1 <= max) {
				html += "<li><a href='javascript:pagerGoto(" + (page + 1) + ")'>" + (page + 1) + "</a></li>";
			}
			if (page + 2 <= max) {
				html += "<li><a href='javascript:pagerGoto(" + (page + 2) + ")'>" + (page + 2) + "</a></li>";
			}
			if (page + 3 == max) {
				html += "<li><a href='javascript:pagerGoto(" + max + ")'>" + max + "</a></li>";
			}
			if (page + 3 < max) {
				html += "<li><span>...</span></li>";
				html += "<li><a href='javascript:pagerGoto(" + max + ")'>" + max + "</a></li>";
			}
		}
		if (page == max) {
			html += "<li class='disabled'><a>下一页</a></li>";
		} else {
			html += "<li><a href='javascript:pagerGoto(" + next + ")'>下一页</a></li>";

		}
		html += "<input type='hidden' name='page' id='pagerForm_page' value='" + page + "'></input>";

		this.pageHtml = html;

	}


	/**
	 * 格式化+扩展多字段字段 
	 * 
	 * @param dataFormat
	 * @param clumns
	 *            扩展字段：list条数对应rows条数，每一条数据格式为一个Map,添加key为字段名，value为值
	 * @return
	 */
	public String toJsons(DataFormat dataFormat, List<List<Map<String, Object>>> table) {
		if (null == dataFormat) {
			dataFormat = new DataFormat();
		}
		dataFormat.formatDate("createTime", "yyyy-MM-dd HH:mm:ss");
		dataFormat.formatDate("updateTime", "yyyy-MM-dd HH:mm:ss");
		dataFormat.formatCode("status");
		dataFormat.formatCode("type");
		String json = null;
		try {
			json = JSON.toJSONString(this);
			JSONObject root = JSON.parseObject(json);
			JSONArray rows = root.getJSONArray("rows");
			JSONArray jsonArray = new JSONArray();
			if (CollectionUtils.isNotEmpty(rows)) {
				for (int i = 0; i < rows.size(); i++) {
					JSONObject rowNode = rows.getJSONObject(i);
					for (Map.Entry<String, FormatParam> entry : dataFormat.paramMap.entrySet()) {
						String field = entry.getKey();
						if (null != rowNode && null != rowNode.get(field)) {
							String value = dataFormat.dataFormat(entry.getValue(), rowNode.get(field).toString());
							rowNode.put(field + "_name", value);
						}
					}
					// 增加多个新属性
					List<Map<String, Object>> tr = table.get(i);
					for (Map<String, Object> td : tr) {
						rowNode.put(td.get("key").toString(), td.get("value"));
					}

					jsonArray.add(rowNode);
				}
			}
			JSONObject rootObj = new JSONObject();
			rootObj.put("rows", jsonArray);
			rootObj.put("page", root.getInteger("page"));
			rootObj.put("size", root.getInteger("size"));
			rootObj.put("total", root.getInteger("total"));
			json = rootObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}


	/**
	 * 转成json数据
	 * 
	 * @return
	 */
	public String toJson(DataFormat dataFormat) {
		if (null == dataFormat) {
			dataFormat = new DataFormat();
		}
		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = JSON.toJSONString(this);
			JSONObject root = JSON.parseObject(json);
			JSONArray rows = root.getJSONArray("rows");
			JSONArray jsonArray = new JSONArray();
			if (CollectionUtils.isNotEmpty(rows)) {
				for (int i = 0; i < rows.size(); i++) {
					JSONObject rowNode = rows.getJSONObject(i);
					for (Map.Entry<String, FormatParam> entry : dataFormat.paramMap.entrySet()) {
						String field = entry.getKey();
						if (null != rowNode && null != rowNode.get(field)) {
							String value = dataFormat.dataFormat(entry.getValue(), rowNode.get(field).toString());
							rowNode.put(field + "_name", value);
						}
					}
					jsonArray.add(rowNode);
				}
			}

			JSONObject rootObj = new JSONObject();
			rootObj.put("rows", jsonArray);
			rootObj.put("page", root.getInteger("page"));
			rootObj.put("size", root.getInteger("size"));
			rootObj.put("total", root.getInteger("total"));
			json = rootObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 把number型value 转换为字符串型 全部替换 包括page size等 全部替换是为了防止rows 中也包"[" 和 "]"等字符
	 * 
	 * @author daiyong 2015年11月6日
	 */
	private String formatNum2String(String json) {
		Pattern pattern = Pattern.compile(":([\\d|\\.|\\s]+),*");
		Matcher matcher = pattern.matcher(json);
		while (matcher.find()) {
			String numStr = matcher.group(1);
			if (matcher.group(0).endsWith(",")) {
				numStr = ":" + "\"" + numStr + "\"" + ",";
			} else {
				numStr = ":" + "\"" + numStr + "\"";
			}
			json = json.replaceFirst(":[\\d|\\.|\\s]+,*", numStr);
		}

		pattern = Pattern.compile("\\\\\":\"([\\d|\\.|\\s]+)\"");
		matcher = pattern.matcher(json);
		while (matcher.find()) {
			String goal = matcher.group(1);
			goal = "\\\\\":" + "\\\\" + goal + "\\\\";
			json = json.replaceFirst("\\\\\":\"([\\d|\\.|\\s]+)\"", goal);
		}
		return json;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {

		this.total = total;
		this.buildPageHtml();
	}

	public String getPageHtml() {
		return pageHtml;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}


	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	public static void main(String[] argus) {
		DataPager dp1 = new DataPager<String>();
		dp1.setTotal(22);
		dp1.setSize(5);
		dp1.setPage(5);
		dp1.buildPageHtml();
		System.out.println();

	}
}
