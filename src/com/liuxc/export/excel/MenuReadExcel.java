package com.liuxc.export.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.liuxc.export.beans.Menu;
import com.liuxc.export.constant.Constant;
import com.liuxc.export.util.CommonUtil;

public class MenuReadExcel {

	/**
	 * read the Excel file
	 * 
	 * @param path
	 *            the path of the Excel file
	 * @return
	 * @throws IOException
	 */
	public List<Menu> readExcel(String path) throws IOException {
		if (path == null || Constant.EMPTY.equals(path)) {
			return null;
		} else {
			String postfix = CommonUtil.getPostfix(path);
			if (!Constant.EMPTY.equals(postfix)) {
				if (Constant.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readXls(path);
				} else if (Constant.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readXlsx(path);
				}
			} else {
				System.out.println(path + Constant.NOT_EXCEL_FILE);
			}
		}
		return null;
	}

	/**
	 * Read the Excel 2010
	 * 
	 * @param path
	 *            the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public List<Menu> readXlsx(String path) throws IOException {
		System.out.println(Constant.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		Menu menu = null;
		List<Menu> list = new ArrayList<Menu>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					menu = new Menu();
					XSSFCell code = xssfRow.getCell(0);
					XSSFCell pCode = xssfRow.getCell(1);
					XSSFCell url = xssfRow.getCell(2);
					XSSFCell name = xssfRow.getCell(3);
					XSSFCell seq = xssfRow.getCell(4);
					XSSFCell type = xssfRow.getCell(5);

					menu.setCode(CommonUtil.getValue(code));
					menu.setpCode(CommonUtil.getValue(pCode));
					menu.setUrl(CommonUtil.getValue(url));
					menu.setName(CommonUtil.getValue(name));
					menu.setSeq(CommonUtil.getValue(seq));
					menu.setType(CommonUtil.getValue(type));
					System.out.println("code : " + menu.getCode()
							 + ", pCode : " + menu.getpCode()
							 + ", url : " + menu.getUrl()
							 + ", name : " + menu.getName()
							 + ", seq : " + menu.getSeq()
							 + ", type : " + menu.getType());
					list.add(menu);
				}
			}
		}
		return list;
	}

	/**
	 * Read the Excel 2003-2007
	 * 
	 * @param path
	 *            the path of the Excel
	 * @return
	 * @throws IOException
	 */
	public List<Menu> readXls(String path) throws IOException {
		System.out.println(Constant.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		Menu menu = null;
		List<Menu> list = new ArrayList<Menu>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					menu = new Menu();
					HSSFCell code = hssfRow.getCell(0);
					HSSFCell pCode = hssfRow.getCell(1);
					HSSFCell url = hssfRow.getCell(2);
					HSSFCell name = hssfRow.getCell(3);
					HSSFCell seq = hssfRow.getCell(4);
					HSSFCell type = hssfRow.getCell(5);
					
					menu.setCode(CommonUtil.getValue(code));
					menu.setpCode(CommonUtil.getValue(pCode));
					menu.setUrl(CommonUtil.getValue(url));
					menu.setName(CommonUtil.getValue(name));
					menu.setSeq(CommonUtil.getValue(seq));
					menu.setType(CommonUtil.getValue(type));
					System.out.println("code : " + menu.getCode()
							 + ", pCode : " + menu.getpCode()
							 + ", url : " + menu.getUrl()
							 + ", name : " + menu.getName()
							 + ", seq : " + menu.getSeq()
							 + ", type : " + menu.getType());
					list.add(menu);
				}
			}
		}
		return list;
	}
	
}
