package com.liuxc.core.java.designPatterns.dynamicProxy.impl;

import com.liuxc.core.java.designPatterns.dynamicProxy.Subject;
/**
 * 实际业务处理类
 * 
 * @since:2017年8月31日
 * @author:liuxc
 */
public class RealSubject implements Subject {

	@Override
	public void rent() {
		System.out.println("i want rent my house");
	}

	@Override
	public void hello(String message) {
		System.out.println("message=" + message);
	}

}
