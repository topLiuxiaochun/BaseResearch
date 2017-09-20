package com.liuxc.export.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.liuxc.export.beans.MemberUnit;
import com.liuxc.export.constant.Constant;
import com.liuxc.export.util.CommonUtil;

public class MemberUnitReadExcel {

	/**
	 * read the Excel file
	 * 
	 * @param path
	 *            the path of the Excel file
	 * @return
	 * @throws IOException
	 */
	public List<MemberUnit> readExcel(String path) throws IOException {
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
	public List<MemberUnit> readXlsx(String path) throws IOException {
		System.out.println(Constant.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		MemberUnit memberUnit = null;
		List<MemberUnit> list = new ArrayList<MemberUnit>();
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
					memberUnit = new MemberUnit();
					XSSFCell code = xssfRow.getCell(0);
					XSSFCell name = xssfRow.getCell(1);
					XSSFCell url = xssfRow.getCell(2);
					XSSFCell phone = xssfRow.getCell(3);
					XSSFCell SSEMemberUnit = xssfRow.getCell(4);
					XSSFCell htfFastLine = xssfRow.getCell(5);
					XSSFCell SHETF = xssfRow.getCell(6);

					memberUnit.setCode(CommonUtil.getValue(code));
					memberUnit.setName(CommonUtil.getValue(name));
					memberUnit.setUrl(CommonUtil.getValue(url));
					memberUnit.setPhone(CommonUtil.getValue(phone));
					memberUnit.setSSEMemberUnit(CommonUtil.getValue(SSEMemberUnit));
					memberUnit.setHtfFastLine(CommonUtil.getValue(htfFastLine));
					memberUnit.setSHETF(CommonUtil.getValue(SHETF));

					list.add(memberUnit);
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
	public List<MemberUnit> readXls(String path) throws IOException {
		System.out.println(Constant.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		MemberUnit memberUnit = null;
		List<MemberUnit> list = new ArrayList<MemberUnit>();
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
					memberUnit = new MemberUnit();
					HSSFCell code = hssfRow.getCell(0);
					HSSFCell name = hssfRow.getCell(1);
					HSSFCell url = hssfRow.getCell(2);
					HSSFCell phone = hssfRow.getCell(3);
					HSSFCell SSEMemberUnit = hssfRow.getCell(4);
					HSSFCell htfFastLine = hssfRow.getCell(5);
					HSSFCell SHETF = hssfRow.getCell(6);

					memberUnit.setCode(CommonUtil.getValue(code));
					memberUnit.setName(CommonUtil.getValue(name));
					memberUnit.setUrl(CommonUtil.getValue(url));
					memberUnit.setPhone(CommonUtil.getValue(phone));
					memberUnit.setSSEMemberUnit(CommonUtil.getValue(SSEMemberUnit));
					memberUnit.setHtfFastLine(CommonUtil.getValue(htfFastLine));
					memberUnit.setSHETF(CommonUtil.getValue(SHETF));
					list.add(memberUnit);
				}
			}
		}
		return list;
	}

}
