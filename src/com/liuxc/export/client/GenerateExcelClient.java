package com.liuxc.export.client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.liuxc.export.beans.MemberUnit;
import com.liuxc.export.excel.GenerateExcel;
import com.liuxc.export.excel.GenerateExcelService;

public class GenerateExcelClient {

	public static void main(String[] args) throws Exception {
		GenerateExcelService generateExcel = new GenerateExcel();
		List<String> nameList = new ArrayList<String>();
		Field[] fields = MemberUnit.class.getDeclaredFields();
		for (Field field : fields) {
			nameList.add(field.getName());
		}
		generateExcel.generateXlsExcel("上证基金通会员单位", nameList);
		generateExcel.generateXlsxExcel("上证基金通会员单位", nameList);

	}

}
