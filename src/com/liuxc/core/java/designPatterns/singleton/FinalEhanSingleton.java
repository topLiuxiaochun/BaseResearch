package com.liuxc.core.java.designPatterns.singleton;

/**
 * 饿汉式单例
 * <p>
 * 
 * 
 * @since:2017年9月1日
 * @author:liuxc
 */
public class FinalEhanSingleton {

	private static final FinalEhanSingleton instance = new FinalEhanSingleton();
	private String config = "final singleton config";
	
	private FinalEhanSingleton() {
		System.out.println("private Singleton constructor execute");
	}
	
	public static FinalEhanSingleton getInstance() {
		return instance;
	}
	
	public String getConfig() {
		return config;
	}
}
