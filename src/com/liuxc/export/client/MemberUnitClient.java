package com.liuxc.export.client;

import java.util.List;

import com.liuxc.export.beans.MemberUnit;
import com.liuxc.export.constant.Constant;
import com.liuxc.export.excel.MemberUnitReadExcel;
import com.liuxc.export.util.FileUtil;

public class MemberUnitClient {

	public static void main(String[] args) throws Exception {
		String excel2003_2007 = Constant.MEMBERUNIT_INFO_XLS_PATH;
		String excel2010 = Constant.MEMBERUNIT_INFO_XLSX_PATH;

		// read the 2003-2007 excel
		List<MemberUnit> list = new MemberUnitReadExcel().readExcel(excel2003_2007);
		if (list != null) {
			String rowData = "";
			for (MemberUnit memberUnit : list) {
				/*System.out.println("code : " + memberUnit.getCode()
						 + ", name : " + memberUnit.getName()
						 + ", url : " + memberUnit.getUrl()
						 + ", phone : " + memberUnit.getPhone()
						 + ", SSEMemberUnit : " + memberUnit.getSSEMemberUnit()
						 + ", htfFastLine : " + memberUnit.getHtfFastLine()
						 + ", SHETF : " + memberUnit.getSHETF() + "\n");*/
				rowData += memberUnit.getCode() + 
						"," + memberUnit.getName() + 
						"," + memberUnit.getUrl() + 
						"," + memberUnit.getPhone() + 
						"," + memberUnit.getSSEMemberUnit() + 
						"," + memberUnit.getHtfFastLine() + 
						"," + memberUnit.getSHETF() + "\n";
			}
			System.err.println("xls start...");
			System.err.println("rowData=" + rowData);
			System.err.println("xls end...");
			FileUtil.createFile("memberUnit", rowData);
		}
		System.out.println("======================================");
		// read the 2010 excel
//		List<MemberUnit> list1 = new MemberUnitReadExcel().readExcel(excel2010);
//		if (list1 != null) {
//			for (MemberUnit memberUnit : list1) {
//				System.out.println("code : " + memberUnit.getCode()
//						 + ", name : " + memberUnit.getName()
//						 + ", url : " + memberUnit.getUrl()
//						 + ", phone : " + memberUnit.getPhone()
//						 + ", SSEMemberUnit : " + memberUnit.getSSEMemberUnit()
//						 + ", htfFastLine : " + memberUnit.getHtfFastLine()
//						 + ", SHETF : " + memberUnit.getSHETF());
//			}
//		}
	}

}
