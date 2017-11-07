package com.uavs.common.util;


import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 读取excel
 * @author zhongcheng
 */
public class ExcelReader {
	public List<List<Object>> readXls(InputStream is) throws Exception {
		Workbook hssfWorkbook = createWorkBook(is);
		List<List<Object>> list = new ArrayList<List<Object>>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			int lastRowNum = hssfSheet.getLastRowNum();
			// 循环行Row
			for (int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (null == hssfRow) {
					break;
				}
				Cell tCell = hssfRow.getCell(0);
				if (null == tCell) {
					break;
				}
				String str = getValue(tCell);
				if (null == str || str.trim().length() == 0) {
					break;
				}
				if (hssfRow != null) {
					List<Object> inList = new ArrayList<Object>();
					Iterator<Cell> it = hssfRow.cellIterator();
					while (it.hasNext()) {
						Cell cell = it.next();
						cell.setCellType(Cell.CELL_TYPE_STRING);
						inList.add(getValue(cell));
					}
					list.add(inList);
				}
			}
		}
		return list;
	}

	public static Workbook createWorkBook(InputStream in) throws Exception {
		if (!in.markSupported()) {
			in = new PushbackInputStream(in, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(in)) {
			return new HSSFWorkbook(in);
		}
		if (POIXMLDocument.hasOOXMLHeader(in)) {
			return new XSSFWorkbook(OPCPackage.open(in));
		}
		throw new Exception("MSG_此Excel版本目前无法解析");
	}

	@SuppressWarnings("static-access")
	private String getValue(Cell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}
