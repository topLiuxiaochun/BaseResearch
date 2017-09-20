package com.liuxc.thread.join;

public class ThreadB extends Thread {

	synchronized public  void run() {
		try {
			System.out.println(Thread.currentThread().getName() + ":ThreadB start");
			
			Thread.sleep(3000);
			
			System.out.println(Thread.currentThread().getName() + ":ThreadB end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
