package com.liuxc.thread.socket.client;

import com.liuxc.thread.socket.Calculator;
/**
 * 线程之间通信交互，博客地址http://lavasoft.blog.51cto.com/62575/99157
 * 
 * 参考http://www.cnblogs.com/luxiaoxun/p/3870265.html对比理解更好
 * @author wisdom
 *
 */
public class Client extends Thread {

	Calculator c;

	public Client(Calculator c) {
		this.c = c;
	}

	@Override
	public void run() {
		synchronized (c) {
			try {
				System.out.println(Thread.currentThread() + "等待计算结果。。。");
				c.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() + "计算结果为：" + c.total);
		}
	}

	public static void main(String[] args) {
		 Calculator calculator = new Calculator(); 

         //启动三个线程，分别获取计算结果 
         new Client(calculator).start(); 
         new Client(calculator).start(); 
         new Client(calculator).start(); 
         //启动计算线程 
         calculator.start(); 
	}

}
