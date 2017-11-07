package com.uavs.common.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.alibaba.fastjson.JSONArray;

public class ExcelUtils<T> {
	/**
	 * 每个EXCEL文件的SHEET个数
	 */
	private int sheets = 10;
	/**
	 * 每个SHEET数据量
	 */
	private int rows = 100000;
	/**
	 * 每个cell最大宽度
	 */
	private int cellWithMax = 8000;
	/**
	 * 标题font
	 */
	private int titlesFont = 24;
	/**
	 * 内容font
	 */
	private int textFont = 13;
	/**
	 * 数据标题
	 */
	public List<String> titles;
	/**
	 * sheet名称
	 */
	private String sheetName = "sheet";
	
	private boolean isAddTitle = false;
	
	private int titleIndex = 0;
	
	private int defaultWidth;

	private int stepCount;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * workbook
	 */
	private Workbook workBook;
	/**
	 * 文件后缀
	 */
	private String fileSubName;
	/**
	 * sql 查询条件
	 */
	private String sqlCondition;


	public void createWork(List<String> titles,String fileName) {
		this.titles = titles;
		this.fileName = fileName;
		createWorkBook();
	}

	public void createWorkBook() {
		this.workBook = new SXSSFWorkbook(5000);
		this.fileSubName = ".xlsx";
	}

	public List<List<Object>> returnRows(T rows) {
  		return new ArrayList<List<Object>>();
	}

	public String fileName() {
		return this.fileName;
	}

	/**
	 * 把数据写入Excel
	 * 
	 * @author zhongcheng
	 * @param datas
	 *            数据
	 */
	public void pushDataToExcel(T tDatas) {
		if (null == tDatas) {
			throw new NullPointerException("Datas is null!");
		}
		if (null == workBook) {
			createWorkBook();
		}
		pushData(tDatas);
	}

	public void pushData(T tDatas) {
		List<List<Object>> datas = returnRows(tDatas);
		int dataSize = datas.size();
		int nowSheets = workBook.getNumberOfSheets();
		if (nowSheets > 0) {
			Sheet lastSheet = workBook.getSheetAt(nowSheets - 1);
			int lastSheetRows = lastSheet.getLastRowNum();
			int surplusRows = rows - lastSheetRows;// 剩余可容纳行数
			if (surplusRows > 0) {
				List<List<Object>> tempList = null;
				if (dataSize > surplusRows) {
					tempList = datas.subList(0, surplusRows);
					pushDataToSheet(lastSheet, tempList);
					datas = datas.subList(surplusRows, dataSize);
					forEachDatas(datas);
				} else {
					pushDataToSheet(lastSheet, datas);
				}
			} else {
				forEachDatas(datas);
			}
		} else {// 第一次添加数据
			Sheet sheet = null;
			if (dataSize > rows) {
				forEachDatas(datas);
			} else {
				sheet = createSheet(sheetName);
				pushDataToSheet(sheet, datas);
			}
		}

	}

	/**
	 * 对数据进行切分，并写入SHEET中
	 * 
	 * @author zhongcheng
	 * @param datas
	 *            数据
	 */
	private void forEachDatas(List<List<Object>> datas) {
		int dataSize = datas.size();
		int subSize = dataSize / rows;
		for (int i = 0; i < subSize; i++) {
			List<List<Object>> tempList = datas.subList(i * rows, (i + 1)
					* rows);
			Sheet sheet = createSheet(null);
			pushDataToSheet(sheet, tempList);
		}
		if (dataSize % rows > 0) {
			List<List<Object>> tempList = datas.subList(subSize * rows,
					dataSize);
			Sheet sheet = createSheet(null);
			pushDataToSheet(sheet, tempList);
		}
	}
	
	public void sheetData(T tDatas, String sheetName) {

	}

	/**
	 * 创建一个新的SHEET,并且添加标题
	 * 
	 * @author zhongcheng
	 * @return
	 */
	protected Sheet createSheet(String sheetName) {
		Sheet sheet = null;
		if (null != sheetName && sheetName.trim().length() > 0) {
			sheet = workBook.createSheet(sheetName);
		} else {
			sheet = workBook.createSheet();
		}
		int titleSize = 0;
		if (isAddTitle) {
			// 创建表头表头信息展示
			Row titleRow = sheet.createRow(titleIndex);
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(titleIndex, titleIndex,
					0, titles.size() - 1));
			// 报表标题样式
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue(fileName);
			titleCell.setCellStyle(getTitleStyle());
			titleIndex++;
			// 创建查询条件信息展示
			Row sqlConditionRow = sheet.createRow(titleIndex);
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(titleIndex, titleIndex,
					0, titles.size() - 1));
			// 报表标题样式
			Cell sqlConditionRowStyleCell = sqlConditionRow.createCell(0);
			sqlConditionRowStyleCell.setCellValue(sqlCondition);
			titleIndex++;
		}

		// 列表标题
		if (null != titles && (titleSize = titles.size()) > 0) {
			// SHEET中添加标题
			Row row = sheet.createRow(titleIndex);
			// 报表标题样式
			for (int i = 0; i < titleSize; i++) {
				Cell cell = row.createCell(i);
				String cellValue = titles.get(i).toString();
				cell.setCellValue(cellValue);
				cell.setCellStyle(getRowTitleStyle());
				// 行宽
				if (0 == defaultWidth) {
					sheet.setColumnWidth(i, cellValue.getBytes().length * 259);
				} else {
					sheet.setColumnWidth(i, defaultWidth);
				}
			}
			// 冻结报表首行（标题行）
			sheet.createFreezePane(0, titleIndex + 1, 0, titleIndex + 1);
		}
		return sheet;
	}

	/**
	 * 把数据写入知道SHEET中
	 * 
	 * @author xuzhen
	 * @param sheet
	 *            SHEET
	 * @param datas
	 *            数据
	 */
	protected void pushDataToSheet(Sheet sheet, List<List<Object>> listDatas) {
		int lastRowNumber = sheet.getLastRowNum();
		int dataSize = listDatas.size();
		sheet.autoSizeColumn(1);
		CellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		for (int i = 0; i < dataSize; i++) {
			List<Object> tempDatas = listDatas.get(i);
			Row row = sheet.createRow(null == this.titles ? i + lastRowNumber
					: i + lastRowNumber + 1);
			int tempSize = tempDatas.size();
			for (int j = 0; j < tempSize; j++) {
				Cell cell = row.createCell(j);
				Object value_obj = tempDatas.get(j);
				String cellValue = null == value_obj ? "" : value_obj
						.toString();
				cell.setCellStyle(cellStyle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(cellValue);
				if (cellValue.getBytes().length * 259 > sheet.getColumnWidth(j)) {
					sheet.setColumnWidth(
							j,
							cellValue.getBytes().length * 259 > cellWithMax ? cellWithMax
									: cellValue.getBytes().length * 259);
				}
			}
		}
		stepCount += dataSize;
		int nowSheets = workBook.getNumberOfSheets();
	}

	public void writeExcelFile(OutputStream out) throws IOException {
		this.workBook.write(out);
	}

	public Font getWorkBookFont() {
		Font font = workBook.createFont();
		font.setFontName("仿宋");
		font.setFontHeightInPoints((short) this.textFont);
		return font;
	}

	private Font getWorkBookTitleFont() {
		Font font = workBook.createFont();
		font.setFontName("仿宋");
		font.setFontHeightInPoints((short) this.titlesFont);
		return font;
	}

	/**
	 * 报表标题样式
	 * 
	 * @author zhongcheng
	 */
	private CellStyle getTitleStyle() {
		// 报表标题样式
		CellStyle titleStyle = workBook.createCellStyle();
		titleStyle.setFont(getWorkBookTitleFont());
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return titleStyle;
	}

	/**
	 * 列表标题样式
	 * 
	 * @author zhongcheng
	 */
	private CellStyle getRowTitleStyle() {
		// 报表标题样式
		CellStyle rowTitleStyle = workBook.createCellStyle();
		rowTitleStyle.setFont(getWorkBookFont());
		rowTitleStyle.setBorderLeft(CellStyle.BORDER_THIN);
		rowTitleStyle.setBorderTop(CellStyle.BORDER_THIN);
		rowTitleStyle.setBorderRight(CellStyle.BORDER_THIN);
		rowTitleStyle.setBorderBottom(CellStyle.BORDER_THIN);
		rowTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		rowTitleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return rowTitleStyle;
	}
	
//  public static void main(String[] args) {
//  // 测试学生
//	  StatementExcelUtil ex = new StatementExcelUtil();
//	  List<String> titles = new ArrayList<String>();
//      titles.add("账务单号");
//      titles.add("客户名称");
//      titles.add("EAN编码");
//      
//	  List<Book> dataset = new ArrayList<Book>();
//	  dataset.add(new Book("三国", "111111", "10"));
//	  dataset.add(new Book("水浒", "222222", null));
//	  dataset.add(new Book("西游记", "333333", null));
//  try {
//      OutputStream out = new FileOutputStream("E:\\a.xlsx");
//      
//      ex.createWork(titles, "a.xlsx");
//      ex.pushDataToExcel(new Book("三国", "111111", "10"));
//      ex.writeExcelFile(out);
//      
//      out.close();
//      JOptionPane.showMessageDialog(null, "导出成功!");
//      System.out.println("excel导出成功！");
//  } catch (FileNotFoundException e) {
//      e.printStackTrace();
//  } catch (IOException e) {
//      e.printStackTrace();
//  }
//}
//  static  class Book{
//	  public Book(String name,String isbn,String num){
//		  this.name = name;
//		  this.isbn = isbn;
//		  this.num = num;
//	  }
//	  public String name;
//	  public String isbn;
//	  public String num;
//  }
//  
//  static class StatementExcelUtil extends ExcelUtils<JSONArray> {
//
//      @Override
//      public List<List<Object>> returnRows(JSONArray rows) {
//    	  List<List<Object>> list =  new ArrayList<List<Object>>();
//    	  List<Object> li = new ArrayList<Object>();
//    	  li.add("sdf");
//    	  li.add("xdfdf");
//    	  li.add("eee");
//    	  list.add(li);
//    	  
//    	  
//  		return list;
//  	  }
//  }
}
