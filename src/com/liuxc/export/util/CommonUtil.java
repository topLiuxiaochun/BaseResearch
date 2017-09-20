package com.liuxc.export.util;

import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.liuxc.export.constant.Constant;

public class CommonUtil {
	
	private static DecimalFormat df = new DecimalFormat("0");

	/**
	 * get postfix of the path
	 * 
	 * @param path
	 * @return
	 */
	public static String getPostfix(String path) {
		if (path == null || Constant.EMPTY.equals(path.trim())) {
			return Constant.EMPTY;
		}
		if (path.contains(Constant.POINT)) {
			return path.substring(path.lastIndexOf(Constant.POINT) + 1,
					path.length());
		}
		return Constant.EMPTY;
	}
	
	/**
	 * 解析xlsx
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getValue(XSSFCell xssfCell) {
		String cellValue = "";
		if(xssfCell == null) return "";
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			cellValue = String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(xssfCell)) {
				cellValue = String.valueOf(com.liuxc.export.util.DateUtil.format("yyyy-MM-dd", xssfCell.getDateCellValue()));
			} else {
				double srcValue = xssfCell.getNumericCellValue();
				/*数字格式化方式1*/
				int intValue = (int)srcValue;
				cellValue = (srcValue - intValue == 0) ? String.valueOf(intValue) : String.valueOf(srcValue);
				/*数字格式化方式2*/
				cellValue = df.format(srcValue);
				System.err.println("cellValue=" + cellValue);
			}
		} else {
			cellValue = String.valueOf(xssfCell.getStringCellValue());
		}
		return cellValue;
		/*
		return String.valueOf(xssfRow.getStringCellValue());
		 */
	}

	/**
	 * 解析xls
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getValue(HSSFCell hssfCell) {
		String cellValue = "";
		if(hssfCell == null) return "";
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			cellValue = String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(hssfCell)) {
				cellValue = String.valueOf(com.liuxc.export.util.DateUtil.format("yyyy-MM-dd", hssfCell.getDateCellValue()));
			} else {
				double srcValue = hssfCell.getNumericCellValue();
				/*数字格式化方式1*/
				int intValue = (int)srcValue;
				cellValue = (srcValue - intValue == 0) ? String.valueOf(intValue) : String.valueOf(srcValue);
				/*数字格式化方式2*/
				cellValue = df.format(srcValue);
				System.err.println("cellValue=" + cellValue);
			}
		} else {
			cellValue = String.valueOf(hssfCell.getStringCellValue());
		}
		return cellValue;
		/*
		return String.valueOf(hssfCell.getStringCellValue());
		*/
	}
}
