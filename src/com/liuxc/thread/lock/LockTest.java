package com.liuxc.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Java多线程 ：普通锁
 * <p>
 * 在Java5中，专门提供了锁对象，利用锁可以方便的实现资源的封锁，用来控制对竞争资源并发访问的控制，这些
 * 内容主要集中在java.util.concurrent.locks 包下面，里面有三个重要的接口Condition、Lock、ReadWriteLock。
 * @author wisdom
 *
 */
public class LockTest {

	public static void main(String[] args) {
		//创建并发访问的账户
		MyCount myCount = new MyCount("917359828", 10000);
		//创建一个锁对象 Reentrant:再进入的
		Lock myLock = new ReentrantLock();
		//创建一个线程池 
		ExecutorService pool = Executors.newCachedThreadPool();
		//创建一些并发访问用户，一个信用卡，存的存，取的取，好热闹啊 
		User user1 = new User("张三", myCount, 3000, myLock);
		User user2 = new User("张三他父亲", myCount, -5000, myLock);
		User user3 = new User("张三他弟弟", myCount, 2000, myLock);
		User user4 = new User("张三他妹妹", myCount, -4000, myLock);
		//在线程池中执行各个用户的操作
		pool.submit(user4);
		pool.submit(user3);
		pool.submit(user2);
		pool.submit(user1);
		//关闭线程池 
        pool.shutdown(); 

	}

}
/**
 * 信用卡用户
 * @author wisdom
 *
 */
class User implements Runnable {

	private String name;
	private MyCount myCount;
	private int handleCash;
	private Lock myLock;
	
	
	public User(String name, MyCount myCount, int handleCash, Lock myLock) {
		super();
		this.name = name;
		this.myCount = myCount;
		this.handleCash = handleCash;
		this.myLock = myLock;
	}



	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		//获取锁 
		myLock.lock();
		//执行现金业务 
        System.out.println(name + "正在操作" + myCount + "账户，金额为" + handleCash + "，当前金额为" + myCount.getCash()); 
        myCount.setCash(myCount.getCash() + handleCash); 
        System.out.println(name + "操作" + myCount + "账户成功，金额为" + handleCash + "，当前金额为" + myCount.getCash()); 
        //释放锁，否则别的线程没有机会执行了 
		myLock.unlock();
	}
	
}

/**
 * 信用卡账户，可随意透支
 * @author wisdom
 *
 */
class MyCount {
	
	private String account;// 账号	
	private int cash;// 账户余额
	
	public MyCount() {
		super();
	}

	public MyCount(String account, int cash) {
		super();
		this.account = account;
		this.cash = cash;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	@Override
	public String toString() {
		return "MyCount [account=" + account + ", cash=" + cash + "]";
	}

}