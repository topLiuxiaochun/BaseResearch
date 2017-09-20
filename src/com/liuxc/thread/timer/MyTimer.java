package com.liuxc.thread.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
	private static Timer timer = new Timer();

	static class MyTask extends TimerTask {

		@Override
		public void run() {
			System.out.println("current time=" + new Date());
		}
		
	}
	
	public static void main(String[] args) {

		try {
			MyTask myTask = new MyTask();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = "2017-09-10 17:38:00";
			Date date = sdf.parse(dateStr);
			System.out.println("定时任务时间：" + dateStr + ",当前时间：" + sdf.format(new Date()));
			timer.schedule(myTask, date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
