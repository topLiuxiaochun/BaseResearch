package com.liuxc.export.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * custom date format
	 * @param format date format
	 * @param date to handle date 
	 * @return
	 */
	public static String format(String format, Date date) {
		if(date == null || "".equals(date)) return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (format != null && !"".equals(format)) {
				sdf = new SimpleDateFormat(format);
			}
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return sdf.format(date);
		}
	}
	/**
	 * default date format is "yyyy-MM-dd", such as "2017-06-22".
	 * <p>
	 * if null || "" return ""
	 * </p>
	 * @param date to handle date 
	 * @return
	 */
	public static String format(Date date) {
		return format("", date);
	}
}
