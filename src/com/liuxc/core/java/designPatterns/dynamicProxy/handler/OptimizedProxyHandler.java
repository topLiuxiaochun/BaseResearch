package com.liuxc.core.java.designPatterns.dynamicProxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 此类设计参考知乎大牛(雨夜偷牛的人)讨论区：https://www.zhihu.com/question/20794107
 * 
 * @since:2017年8月31日
 * @author:liuxc
 */
public class OptimizedProxyHandler implements InvocationHandler{

	// 委托对象
	private Object target;
	
	// 绑定委托对象，返回代理类
	public Object bind(Object target) {
		this.target = target;
		// 绑定该类实现的所有接口，取得代理类
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//这里就可以进行所谓的AOP编程了
        //在调用具体函数方法前，执行功能处理
		//　在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before method invoked");
        
        System.out.println("Method:" + method);
        
        //  当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(target, args);
        
        //　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after method invoked");
		return null;
	}
}
