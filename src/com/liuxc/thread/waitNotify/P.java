package com.liuxc.thread.waitNotify;

/**
 * 生产者
 * 
 * @since:2017年8月28日
 * @author:liuxc
 */
public class P {

	private String lock;

	public P(String lock) {
		super();
		this.lock = lock;
	}
	
	public void setValue() {
		try {
			synchronized (lock) {
				while (!"".equals(ValueObject.valueString)) {
					System.out.println(" 生产者 " + Thread.currentThread().getName() + "waiting...");
					lock.wait();
				}
				System.out.println(" 生产者 " + Thread.currentThread().getName() + "runnable...");
				String valueString = System.currentTimeMillis() + "_" + System.nanoTime();
				
				ValueObject.valueString = valueString;
				lock.notify();
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
