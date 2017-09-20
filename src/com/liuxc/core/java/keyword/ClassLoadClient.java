package com.liuxc.core.java.keyword;

/**
 * Java虚拟机类加载机制
 * <p>
 * 参考地址：http://www.importnew.com/18548.html
 * <p>
 * 类从被加载到虚拟机内存中开始，到卸载出内存为止，它的整个生命周期包括：加载（Loading）、
 * 验证（Verification）、准备(Preparation)、解析(Resolution)、初始化(Initialization)、使用
 * (Using)和卸载(Unloading)7个阶段。其中准备、验证、解析3个部分统称为连接（Linking）。
 * @since:2017年9月2日
 * @author:liuxc
 */
public class ClassLoadClient {

	public static void main(String[] args) {
		staticFunction();
	}
	
	static ClassLoadClient client = new ClassLoadClient();
	
	static {
		System.out.println("static block executed");//4
		/**
		 * 为什么可以进行修改b的值，但是不能调用呢？
		 */
		b = 113;
//		System.out.println("b=" + b);//Cannot reference a field before it is defined
		
	}
	
	{
		System.out.println("not static block executed");//1
	}
	
	public ClassLoadClient() {
		System.out.println("constructor executed");//2
		/**
		 * 为什么可以进行修改b的值，又能调用呢？
		 */
		b = 211;
		System.out.println("a=" + a + ",b=" + b);//3:a=0;b=112 => a=111;b=0
	}
	
	public static void staticFunction() {
		System.out.println("staticFunctin invoked");//5
	}
	
	int a = 111;
	
	static int b = 112;
	
}
