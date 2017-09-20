package com.liuxc.core.java.designPatterns.dynamicProxy.client;

import java.lang.reflect.Proxy;

import com.liuxc.core.java.designPatterns.dynamicProxy.Subject;
import com.liuxc.core.java.designPatterns.dynamicProxy.handler.DynamicProxyHandler;
import com.liuxc.core.java.designPatterns.dynamicProxy.handler.OptimizedProxyHandler;
import com.liuxc.core.java.designPatterns.dynamicProxy.impl.RealSubject;
/**
 * 动态代理测试客户端
 * 
 * @since:2017年8月31日
 * @author:liuxc
 */
public class Client {

	public static void main(String[] args) {
		Subject subject = new RealSubject();
		
		DynamicProxyHandler proxyHandler = new DynamicProxyHandler(subject);
		
		Subject proxySubject = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), proxyHandler);

		System.out.println(proxySubject.getClass().getName());
		
		proxySubject.rent();
		
		proxySubject.hello("DynamicProxy invoked...");
		
		
		Subject proxy = (Subject) new OptimizedProxyHandler().bind(subject);
		
		proxy.hello("Optimized message");
	}

}
