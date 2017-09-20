package com.liuxc.core.java.designPatterns.singleton;

/**
 * 在第一次调用时才初始化对象称为懒汉式单例。懒汉式单例存在并发高时线程不安全问题。
 * <p>
 * 
 * 
 * @since:2017年9月1日
 * @author:liuxc
 */
public class LanhanSingleton {

	private static LanhanSingleton instance = null;
	private String config = "singleton config";
	/**
	 * object不是static修饰，报错为
	 * Cannot make a static reference to the non-static field lock//不能对非静态字段锁进行静态引用
	 */
	private static final Object lock = new Object();
	
	private LanhanSingleton() {
		System.out.println("private Singleton constructor execute");
	}
	
	public static LanhanSingleton getInstance() {
		synchronized (lock) {
			if (instance == null) {
				instance = new LanhanSingleton();
			}
		}
		return instance;
	}
	
	public String getConfig() {
		return config;
	}
}
