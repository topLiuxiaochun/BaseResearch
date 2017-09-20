package com.liuxc.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockWithCondition {

	public static void main(String[] args) {
		MyCountCondition myCount = new MyCountCondition("10086", 1000);
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Thread t5 = new DrawThread("老牛", myCount, 7000); 
		Thread t1 = new SaveThread("张三", myCount, 2000); 
        Thread t2 = new SaveThread("李四", myCount, 3600); 
        Thread t3 = new DrawThread("王五", myCount, 2700); 
        Thread t4 = new SaveThread("老张", myCount, 600); 
        Thread t6 = new DrawThread("胖子", myCount, 800); 
        //执行各个线程 
        pool.execute(t1); 
        pool.execute(t2); 
        pool.execute(t3); 
        pool.execute(t4); 
        pool.execute(t5); 
        pool.execute(t6); 
        //关闭线程池 
        pool.shutdown(); 
	}

}

class SaveThread extends Thread {
	private String name;//操作人 
	private MyCountCondition myCount;//账户
	private int x;//存款金额
	public SaveThread(String name, MyCountCondition myCount, int x) {
		super();
		this.name = name;
		this.myCount = myCount;
		this.x = x;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		myCount.saving(x, name);
	}
}
class DrawThread extends Thread {
	private String name;//操作人 
	private MyCountCondition myCount;//账户
	private int x;//存款金额
	public DrawThread(String name, MyCountCondition myCount, int x) {
		super();
		this.name = name;
		this.myCount = myCount;
		this.x = x;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		myCount.drawing(x, name);
	}
	
	
}
class MyCountCondition {
	
	private String oid;//账号 
    private int cash;//账户余额
     
    private Lock lock = new ReentrantLock();
    private Condition saveCondition = lock.newCondition();
    private Condition drawcCondition = lock.newCondition();
    
	public MyCountCondition(String oid, int cash) {
		super();
		this.oid = oid;
		this.cash = cash;
	}
	 /** 
     * 存款 
     * 
     * @param x        操作金额 
     * @param name 操作人 
     */ 
    public void saving(int x, String name) { 
    	lock.lock();//获取锁
    	if (x > 0) {
			this.cash += x;//存款
			System.out.println(name + "存款" + x + "，当前余额为" + cash);
		}
    	drawcCondition.signalAll();//唤醒所有等待线程
    	lock.unlock();//释放锁
    	
    }
    /** 
     * 取款 
     * 
     * @param x        操作金额 
     * @param name 操作人 
     */ 
    public void drawing(int x, String name) { 
            lock.lock();//获取锁
            try {
				if (cash < x) {
					System.out.println(name + "取款" + x + "，当前余额为" + cash + "...钱不够...");
					drawcCondition.await();//阻塞取款操作
				} else {
					cash -= x;
					System.out.println(name + "取款" + x + "，当前余额为" + cash);
				}
				saveCondition.signalAll();//唤醒所有存款操作
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();//释放锁
			}
    }
}