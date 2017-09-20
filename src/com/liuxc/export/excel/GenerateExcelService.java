package com.liuxc.export.excel;

import java.util.List;

public interface GenerateExcelService {

	/* (non-Javadoc)
	 * @see com.liuxc.export.excel.GenerateExcelService#generateXlsExcel(java.lang.String, java.util.List)
	 */
	void generateXlsExcel(String sheetName, List<String> cellNameList)
			throws Exception;

	/* (non-Javadoc)
	 * @see com.liuxc.export.excel.GenerateExcelService#generateXlsxExcel(java.lang.String, java.util.List)
	 */
	void generateXlsxExcel(String sheetName, List<String> cellNameList)
			throws Exception;

}