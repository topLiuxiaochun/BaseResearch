package com.liuxc.common.commonsAllJar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtilsClient {

	public static void main(String[] args) {
		try {
			Date date = DateUtils.parseDate("2017-09-16 19:35:00", "yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat();
			System.out.println(date);//Sat Sep 16 19:35:00 CST 2017
			System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
			
			
			// 十天之后
			Date newDate = DateUtils.addDays(date, 10);
			System.out.println(newDate);//Tue Sep 26 19:35:00 CST 2017
			
			// 上一个月
			Date preMonthDate = DateUtils.addMonths(date, -1);
			System.out.println("preMonthDate=" + preMonthDate);//preMonthDate=Wed Aug 16 19:35:00 CST 2017
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
