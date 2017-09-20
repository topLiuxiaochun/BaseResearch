package com.liuxc.thread;


public class MyThread extends Thread {

	@Override
	public void run() {
		super.run();
		for (int i = 0; i <= 500000; i++) {
			if ((i/100) == 0) {
				System.out.println("i=" + (i + 0));
			}
//			System.out.println("i=" + (i + 0));
			
		}
	}

}
