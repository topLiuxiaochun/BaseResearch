package com.liuxc.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * Java多线程 ：读写锁
 * @author wisdom
 *
 */
public class ReadWriteLockTest {
	public static void main(String[] args) {
		//创建并发访问的账户
		MyCountReadWrite myCount = new MyCountReadWrite("917359828", 10000);
		//创建一个锁对象 Reentrant:再进入的
		ReadWriteLock myLock = new ReentrantReadWriteLock(false);
		//创建一个线程池 
		ExecutorService pool = Executors.newFixedThreadPool(2);
		//创建一些并发访问用户，一个信用卡，存的存，取的取，好热闹啊 
		UserReadWrite user1 = new UserReadWrite("张三", myCount, 3000, myLock, false);
		UserReadWrite user2 = new UserReadWrite("张三他父亲", myCount, -5000, myLock, false);
		UserReadWrite user3 = new UserReadWrite("张三他弟弟", myCount, 2000, myLock, false);
		UserReadWrite user4 = new UserReadWrite("张三他妹妹", myCount, -4000, myLock, false);
		UserReadWrite user5 = new UserReadWrite("张三他媳妇", myCount, 1000, myLock, true);
		//在线程池中执行各个用户的操作
		pool.submit(user4);
		pool.submit(user3);
		pool.submit(user5);
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
class UserReadWrite implements Runnable {

	private String name;
	private MyCountReadWrite myCount;
	private int handleCash;
	private ReadWriteLock myLock;
	private boolean checked;
	
	
	public UserReadWrite(String name, MyCountReadWrite myCount, int handleCash, ReadWriteLock myLock, boolean checked) {
		super();
		this.name = name;
		this.myCount = myCount;
		this.handleCash = handleCash;
		this.myLock = myLock;
		this.checked = checked;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (checked) {
			myLock.readLock().lock();
			System.out.println("读：" + name + "正在查询" + myCount + "账户，当前金额为" + myCount.getCash()); 
			myLock.readLock().unlock();
		} else {
			//获取锁 
			myLock.writeLock().lock();
			//执行现金业务 
			System.out.println(name + "正在操作" + myCount + "账户，金额为" + handleCash + "，当前金额为" + myCount.getCash()); 
			myCount.setCash(myCount.getCash() + handleCash); 
			System.out.println(name + "操作" + myCount + "账户成功，金额为" + handleCash + "，当前金额为" + myCount.getCash()); 
			//释放锁，否则别的线程没有机会执行了 
			myLock.writeLock().unlock();
		}
	}
	
}

/**
 * 信用卡账户，可随意透支
 * @author wisdom
 *
 */
class MyCountReadWrite {
	
	private String account;// 账号	
	private int cash;// 账户余额
	
	public MyCountReadWrite() {
		super();
	}

	public MyCountReadWrite(String account, int cash) {
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
