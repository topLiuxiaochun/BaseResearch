/**
 * 
 */
package com.liuxc.thread;

/**
 * ThreadLocal类使用测试
 * @since:2017年10月29日
 * @author:liuxc
 */
public class ThreadLocalTest {

	private ThreadLocal<Long> serialNoLocal = new ThreadLocal<Long>();
	
	private ThreadLocal<String> nameLocal = new ThreadLocal<String>();
	
	public void setLocalVar() {
		serialNoLocal.set(Thread.currentThread().getId());
		nameLocal.set(Thread.currentThread().getName());
	}
	
	/**
	 * 以JDK1.7启动程序报错：Unsupported major.minor version 52.0。换成JDK1.8OK啦。
	 * @param args
	 * @since:2017年10月29日
	 * @author:liuxc
	 */
	public static void main(String[] args) {
		ThreadLocalTest local = new ThreadLocalTest();
//		local.setLocalVar();
		System.out.println(local.serialNoLocal.get() + "_" + local.nameLocal.get());
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
//				local.setLocalVar();
				System.out.println(local.serialNoLocal.get() + "_" + local.nameLocal.get());
			}
		});

		thread.start();
		
		System.out.println(local.serialNoLocal.get() + "_" + local.nameLocal.get());
	}

}
