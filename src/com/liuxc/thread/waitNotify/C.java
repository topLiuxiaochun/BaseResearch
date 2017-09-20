package com.liuxc.thread.waitNotify;

/**
 * 消费者
 * 
 * @since:2017年8月28日
 * @author:liuxc
 */
public class C {

	private String lock;

	public C(String lock) {
		super();
		this.lock = lock;
	}
	
	public void getValue() {
		try {
			synchronized (lock) {
				while ("".equals(ValueObject.valueString)) {
					System.out.println(" 消费者 " + Thread.currentThread().getName() + "waiting...");
					lock.wait();
				}
				System.out.println(" 消费者 " + Thread.currentThread().getName() + "runnable...");
				
				ValueObject.valueString = "";
				lock.notify();
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
