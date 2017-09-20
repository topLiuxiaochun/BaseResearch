package com.liuxc.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(3);
		MyThread thread1 = new MyThread();
		MyThread thread2 = new MyThread();
		MyThread thread3 = new MyThread();
		MyThread thread4 = new MyThread();
		
//		thread1.start();
//		thread2.start();
//		thread3.start();
//		thread4.start();
		pool.execute(thread4);
		pool.execute(thread2);
		pool.execute(thread1);
		pool.execute(thread3);
		
		pool.shutdown();

	}
	
	

}

class MyThread extends Thread {

	@Override
	public void run() {
		System.out.println("线程-" + Thread.currentThread().getName() + "在执行");
	}
	
}
