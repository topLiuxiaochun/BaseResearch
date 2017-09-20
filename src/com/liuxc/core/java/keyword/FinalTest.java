/**
 * 
 */
package com.liuxc.core.java.keyword;

import java.util.Random;


/**
 * final 关键字对JVM类加载器的影响。static final 修饰的变量也就是常亮在类加载的准备阶段就赋值成功，参考类加载机制资料：http://www.importnew.com/18548.html
 * 参考：http://blog.csdn.net/a352193394/article/details/7342583
 * 
 * @since:2017年8月23日
 * @author:liuxc
 */
public class FinalTest {
	
	static {
		System.out.println("FinalTest static block execute");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(FinalServer.A);
		System.out.println(FinalServer.B);
	}

}

class FinalServer {
	
//	private static final int A = 6/3;
	/**
	 * static final变量A因为在编译的时候就知道它确切的值6/3，所以访问
	 * FinalServer.A不会引起FinalServer类的初始化。也就是static静态代码块不会被加载。
	 */
	public static final int A = 6/3;
	
	/**
	 * static final变量B因为在编译的时候无法知道它确切的值，只有等到运行的时候才能知道，所以访问
	 * FinalServer.B会引起FinalServer类的初始化。也就是static静态代码块的加载。
	 */
	public static final int B = new Random().nextInt(100);
	
	
	
	static {
		System.out.println("FinalServer static block execute");
	}

}


