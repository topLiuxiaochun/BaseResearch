package com.liuxc.export.client;

import java.util.List;

import com.liuxc.export.beans.Menu;
import com.liuxc.export.constant.Constant;
import com.liuxc.export.excel.MenuReadExcel;
import com.liuxc.export.util.FileUtil;

public class MenuClient {

	public static void main(String[] args) throws Exception {
		String excel2003_2007 = Constant.MENU_INFO_XLS_PATH;
		String excel2010 = Constant.MENU_INFO_XLSX_PATH;

		// read the 2003-2007 excel
		List<Menu> list = new MenuReadExcel().readExcel(excel2003_2007);
		if (list != null) {
			String rowData = "";
			for (Menu menu : list) {
				/*System.out.println("code : " + menu.getCode()
						 + ", pCode : " + menu.getpCode()
						 + ", url : " + menu.getUrl()
						 + ", name : " + menu.getName()
						 + ", seq : " + menu.getSeq()
						 + ", type : " + menu.getType());*/
				rowData +=  menu.getCode() +
						"," + menu.getpCode() +
						"," + menu.getUrl() +
						"," + menu.getName() +
						"," + menu.getSeq() +
						"," + menu.getType() + "\n";
				
				FileUtil.createFile("memberUnit", rowData);
			}
		}
		System.out.println("======================================");
		// read the 2010 excel
		List<Menu> list1 = new MenuReadExcel().readExcel(excel2010);
		if (list1 != null) {
			/*for (Menu menu : list1) {
				System.out.println("code : " + menu.getCode()
						 + ", pCode : " + menu.getpCode()
						 + ", url : " + menu.getUrl()
						 + ", name : " + menu.getName()
						 + ", seq : " + menu.getSeq()
						 + ", type : " + menu.getType());
			}*/
		}
	}

}
