package com.liuxc.export.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * 处理一个文件多个sheet的情况
 * @author liuxc
 *
 */
public class MoreSheetReadExcel {

	private static String path = "E:/20160906liuxc/luna-workspace-2017-common/BaseResearch/bin/excel/moreSheet.xls";
	
	public static void main(String[] args) throws Exception {
		readXlsExcel();
	}
	
	public static void readXlsExcel() throws Exception {
		Map<String, List<Object>> rowMap = new HashMap<String, List<Object>>();
		List<Object> rowList = new ArrayList<Object>();
		FileInputStream fis = null;
		HSSFWorkbook workbook = null;
		try {
			File file = new File(path);
			fis = new FileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			
			HSSFSheet sheet = null;
			
			for(int i=0; i<workbook.getNumberOfSheets(); i++) {
				System.out.println("第" + (i+1) + "个sheet");
				sheet = workbook.getSheetAt(i);
				System.out.println("workbook’s name=" + sheet.getSheetName());
				for(int j=0; j<sheet.getPhysicalNumberOfRows(); j++){
					HSSFRow row = sheet.getRow(j);
					String currentRow = "row0";
					currentRow = "row" + j;
					rowList.clear();
					for(int k=0; k<row.getPhysicalNumberOfCells(); k++) {
						/**
						 * 目前只是研究解析多个sheet，数据格式处理并没在这里调用
						 * TODO 单元格数据格式处理参考
						 * com.liuxc.export.util.CommonUtil.getValue(HSSFCell)
						 * 方法
						 */
						rowList.add(row.getCell(k));
//						System.out.println(row.getCell(k));
					}
					rowMap.put(currentRow, rowList);
					System.err.println(currentRow + "=" + rowMap.get(currentRow));
				}
				System.out.println("handle finish...");
			}
		} catch (Exception e) {
			System.err.println("解析出错啦");
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				workbook.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
	}
}
