package com.liuxc.thread.client;

import com.liuxc.thread.MyThread;

public class Client {

	public static void main(String[] args) {
		try {
			MyThread myThread = new MyThread();
			myThread.start();
			Thread.sleep(5000);
			myThread.interrupt();
			
			System.out.println("是否停止1？=" + MyThread.interrupted());
			System.out.println("是否停止2？=" + MyThread.interrupted());
			
//			System.out.println("是否停止1？=" + myThread.isInterrupted());
//			System.out.println("是否停止2？=" + myThread.isInterrupted());
		} catch (InterruptedException e) {
			System.err.println("main catch ...");
			e.printStackTrace();
		}
		System.err.println("end ...");
	}

}
