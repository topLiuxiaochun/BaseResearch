package com.liuxc.thread.join;

public class ThreadA extends Thread {

	private ThreadB b;
	
	public ThreadA(ThreadB b) {
		this.b = b;
	}

	@Override
	public void run() {
		try {
			synchronized (b) {
				System.out.println(Thread.currentThread().getName() + ":ThreadA start");
				
				Thread.sleep(3000);
				
				System.out.println(Thread.currentThread().getName() + ":ThreadA end");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
