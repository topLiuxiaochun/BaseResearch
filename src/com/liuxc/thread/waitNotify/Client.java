package com.liuxc.thread.waitNotify;

/**
 *  出现假死状态，控制台输出如下
 *  
 *  
  	消费者 消费者1waiting...
	生产者 生产者1runnable...
	生产者 生产者1waiting...
	生产者 生产者2waiting...
	消费者 消费者2runnable...
	消费者 消费者2waiting...
	生产者 生产者1runnable...
	生产者 生产者1waiting...
	生产者 生产者2waiting...
	消费者 消费者1runnable...
	消费者 消费者1waiting...
	消费者 消费者2waiting...
	main RUNNABLE
	生产者1 WAITING
	消费者1 WAITING
	生产者2 WAITING
	消费者2 WAITING
 * 
 * @since:2017年8月28日
 * @author:liuxc
 */
public class Client {

	public static void main(String[] args) {
		String lock = new String("");
		P p = new P(lock);
		C c = new C(lock);

		ThreadP[] threadPs = new ThreadP[2];
		ThreadC[] threadCs = new ThreadC[2];
		
		for (int i = 0; i < 2; i++) {
			threadPs[i] = new ThreadP(p);
			threadPs[i].setName("生产者" + (i + 1));
			
			threadCs[i] = new ThreadC(c);
			threadCs[i].setName("消费者" + (i + 1));
			
			threadPs[i].start();
			threadCs[i].start();
			
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
		
		Thread.currentThread().getThreadGroup().enumerate(threadArray);
		
		for (int i = 0; i < threadArray.length; i++) {
			System.out.println(threadArray[i].getName() + " " + threadArray[i].getState()) ;
		}
		
		
	}

}
