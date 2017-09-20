package com.liuxc.core.java.keyword;
/**
 * 继承：子类访问父类静态变量和子类静态变量初始化过程详解。
 * 参考：http://blog.csdn.net/a352193394/article/details/7342583
 * 
 * @since:2017年8月23日
 * @author:liuxc
 */
public class ExtendsTest {

	public static void main(String[] args) {
		/**
		 * 直接访问Child.nameChild，会先初始化Parent类，然后初始化Child类
		 */
//		System.out.println(Child.nameChild);
		/**
		 * 直接访问Child.name，则只会直接初始化parent类，不会初始化Child类
		 */
		System.out.println(Child.name);
		
		
//		Child child = new Child();
//		child.f1();
		/**
		 * final 方法不允许被重写
		 */
//		child.f2();
		/**
		 * 父类private方法允许在子类中创建同名的方法
		 */
//		child.f3();
		
	}

}


class Child extends Parent {
	static String nameChild = "child name field";
	
	static {
		System.out.println("child static block execute...nameChild=" + nameChild);
	}
	
	public void f1() {
		System.out.println("child execute f1");
	}
	
	/**
	 * Cannot override the final method from Parent
	 */
	/*
	public void f2() {
		System.out.println("parent execute f1");
	}
	*/
	public void f3() {
		System.out.println("child execute f3");
	}
}

class Parent {
	static String name = "parent name field";
	
	static {
		System.out.println("parent static block execute...name=" + name);
	}
	
	public void f1() {
		System.out.println("parent execute f1");
	}
	
	public final void f2() {
		System.out.println("parent execute final f2");
	}
	
	private void f3() {
		System.out.println("parent execute final f3");
	}
}