package com.liuxc.core.java.designPatterns.dynamicProxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 方法调用handler，必须实现InvocationHandler
 * 
 * @since:2017年8月31日
 * @author:liuxc
 */
public class DynamicProxyHandler implements InvocationHandler {

	private Object subject;
	
	public DynamicProxyHandler(Object subject) {
		this.subject = subject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//　在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before method invoked");
        
        System.out.println("Method:" + method);
        
        //  当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);
        
        //　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after method invoked");
		return null;
	}

}
