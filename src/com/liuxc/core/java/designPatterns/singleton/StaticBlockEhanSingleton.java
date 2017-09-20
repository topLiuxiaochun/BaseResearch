package com.liuxc.core.java.designPatterns.singleton;

/**
 * 静态代码块实现饿汉式单例
 * <p>
 * 
 * 
 * @since:2017年9月1日
 * @author:liuxc
 */
public class StaticBlockEhanSingleton {

	private static StaticBlockEhanSingleton instance = null;
	
	private String config = "static singleton config";
	
	private StaticBlockEhanSingleton() {
		System.out.println("private Singleton constructor execute");
	}
	
	static {
		instance = new StaticBlockEhanSingleton();
	}
	public static StaticBlockEhanSingleton getInstance() {
		return instance;
	}
	
	public String getConfig() {
		return config;
	}
}
