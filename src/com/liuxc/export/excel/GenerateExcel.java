package com.liuxc.export.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 使用POI技术生成Excel文件
 * @author wisdom
 *
 */
public class GenerateExcel implements GenerateExcelService {

	
	/* (non-Javadoc)
	 * @see com.liuxc.export.excel.GenerateExcelService#generateXlsExcel(java.lang.String, java.util.List)
	 */
	@Override
	public void generateXlsExcel(String sheetName, List<String> cellNameList) throws Exception {
		if(sheetName == null || "".equals(sheetName)) sheetName = "default";
		if(cellNameList == null || cellNameList.size() < 0) 
			throw new Exception("文件定义格式不正确！");
		
		System.out.println("开始生成xls格式Excel文件...");
		FileOutputStream os;
		try {
			//1、创建workbook，对应一个excel
			HSSFWorkbook wb = new HSSFWorkbook();	

			//1.5、生成excel中可能用到的单元格样式
			//首先创建字体样式
			HSSFFont font = wb.createFont();//创建字体样式
			font.setFontName("宋体");//使用宋体
			font.setFontHeightInPoints((short) 10);//字体大小
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
			//然后创建单元格样式style
			HSSFCellStyle style1 = wb.createCellStyle();
			style1.setFont(font);//将字体注入
			style1.setWrapText(true);// 自动换行
			style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中    
			style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
			style1.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());// 设置单元格的背景颜色
			style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style1.setBorderTop((short) 1);// 边框的大小
			style1.setBorderBottom((short) 1);
			style1.setBorderLeft((short) 1);
			style1.setBorderRight((short) 1);

			//2、生成一个sheet，对应excel的sheet，参数为excel中sheet显示的名字
			HSSFSheet sheet = wb.createSheet(sheetName);//3、设置sheet中每列的宽度，第一个参数为第几列，0为第一列；第二个参数为列的宽度，可以设置为0。//Test中有三个属性，因此这里设置三列，第0列设置宽度为0，第1~3列用以存放数据
			sheet.setColumnWidth(0, 0);
//			sheet.setColumnWidth(1, 20*256);
//			sheet.setColumnWidth(2, 20*256);
//			sheet.setColumnWidth(3, 20*256);
			for (int i = 0; i < cellNameList.size(); i++) {
				sheet.setColumnWidth(i+1, 20*256);
			}
			
			//4、生成sheet中一行，从0开始
			HSSFRow row = sheet.createRow(0);
			row.setHeight((short) 800);// 设定行的高度//5、创建row中的单元格，从0开始
			HSSFCell cell = row.createCell(0);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式
//			cell = row.createCell(1);//从第1个单元格开始，设置每个单元格样式
//			cell.setCellValue("x");//设置单元格中内容
//			cell.setCellStyle(style1);//设置单元格样式
//			cell = row.createCell(2);//第二个单元格
//			cell.setCellValue("y");
//			cell.setCellStyle(style1);
//			cell = row.createCell(3);//第三个单元格
//			cell.setCellValue("value");
//			cell.setCellStyle(style1);
			for (int i = 0; i < cellNameList.size(); i++) {
				cell = row.createCell(i+1);//第 i+1 个单元格
				cell.setCellValue(cellNameList.get(i));
				cell.setCellStyle(style1);
			}
			//6、输入数据
			/*for(int i = 1; i <= list.size(); i++){
				cell = row.createCell(i);
			    //操作同第5步，通过setCellValue(list.get(i-1).getX())注入数据
			    
			}*/
			//7、如果需要单元格合并，有两种方式
//		1、sheet.addMergedRegion(new Region(1,(short)1,1,(short)11));//参数为(第一行，最后一行，第一列，最后一列)
			        
//		2、sheet.addMergedRegion(new CellRangeAddress(2, 3, 1, 1));//参数为(第一行，最后一行，第一列，最后一列)
			//8、输入excel
			String path = "E:\\20160906liuxc\\luna-workspace-2017-common\\BaseResearch\\src\\excel\\" + sheetName;
			os = new FileOutputStream(path+".xls");
			System.out.println("Excel文件生成完毕...");
			wb.write(os);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/* (non-Javadoc)
	 * @see com.liuxc.export.excel.GenerateExcelService#generateXlsxExcel(java.lang.String, java.util.List)
	 */
	@Override
	public void generateXlsxExcel(String sheetName, List<String> cellNameList) throws Exception {
		if(sheetName == null || "".equals(sheetName)) sheetName = "default";
		if(cellNameList == null || cellNameList.size() < 0) 
			throw new Exception("文件定义格式不正确！");
		
		System.out.println("开始生成xlsx格式Excel文件...");
		FileOutputStream os;
		try {
			//1、创建workbook，对应一个excel
			XSSFWorkbook wb = new XSSFWorkbook();	

			//1.5、生成excel中可能用到的单元格样式
			//首先创建字体样式
			XSSFFont font = wb.createFont();//创建字体样式
			font.setFontName("宋体");//使用宋体
			font.setFontHeightInPoints((short) 10);//字体大小
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
			//然后创建单元格样式style
			XSSFCellStyle style1 = wb.createCellStyle();
			style1.setFont(font);//将字体注入
			style1.setWrapText(true);// 自动换行
			style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中    
			style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
			style1.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());// 设置单元格的背景颜色
			style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style1.setBorderTop((short) 1);// 边框的大小
			style1.setBorderBottom((short) 1);
			style1.setBorderLeft((short) 1);
			style1.setBorderRight((short) 1);

			//2、生成一个sheet，对应excel的sheet，参数为excel中sheet显示的名字
			XSSFSheet sheet = wb.createSheet(sheetName);//3、设置sheet中每列的宽度，第一个参数为第几列，0为第一列；第二个参数为列的宽度，可以设置为0。//Test中有三个属性，因此这里设置三列，第0列设置宽度为0，第1~3列用以存放数据
			sheet.setColumnWidth(0, 0);
//			sheet.setColumnWidth(1, 20*256);
//			sheet.setColumnWidth(2, 20*256);
//			sheet.setColumnWidth(3, 20*256);
			for (int i = 0; i < cellNameList.size(); i++) {
				sheet.setColumnWidth(i+1, 20*256);
			}
			
			//4、生成sheet中一行，从0开始
			XSSFRow row = sheet.createRow(0);
			row.setHeight((short) 800);// 设定行的高度//5、创建row中的单元格，从0开始
			XSSFCell cell = row.createCell(0);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式
//			cell = row.createCell(1);//从第1个单元格开始，设置每个单元格样式
//			cell.setCellValue("x");//设置单元格中内容
//			cell.setCellStyle(style1);//设置单元格样式
//			cell = row.createCell(2);//第二个单元格
//			cell.setCellValue("y");
//			cell.setCellStyle(style1);
//			cell = row.createCell(3);//第三个单元格
//			cell.setCellValue("value");
//			cell.setCellStyle(style1);
			for (int i = 0; i < cellNameList.size(); i++) {
				cell = row.createCell(i+1);//第 i+1 个单元格
				cell.setCellValue(cellNameList.get(i));
				cell.setCellStyle(style1);
			}
			//6、输入数据
			/*for(int i = 1; i <= list.size(); i++){
				cell = row.createCell(i);
			    //操作同第5步，通过setCellValue(list.get(i-1).getX())注入数据
			    
			}*/
			//7、如果需要单元格合并，有两种方式
//		1、sheet.addMergedRegion(new Region(1,(short)1,1,(short)11));//参数为(第一行，最后一行，第一列，最后一列)
			        
//		2、sheet.addMergedRegion(new CellRangeAddress(2, 3, 1, 1));//参数为(第一行，最后一行，第一列，最后一列)
			//8、输入excel
			String path = "E:\\20160906liuxc\\luna-workspace-2017-common\\BaseResearch\\src\\excel\\" + sheetName;
			os = new FileOutputStream(path+".xlsx");
			System.out.println("Excel文件生成完毕...");
			wb.write(os);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
