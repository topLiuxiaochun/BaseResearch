package com.liuxc.core.java.keyword.extend;

public class SonClass extends SuperClass{
	
	public SonClass() {
		super();
		System.out.println(this);
	}

	/**
	 * 继承父类SuperClass，考察重写或者重载getAge方法
	 */
	protected int getAge(int a, int b) {
		return 0;
	}

	@Override
	protected String getSuperName() {
		return super.getSuperName();
	}

	public void doSomething() {
		// 问题A：怎么创建一个InnerClass对象
		new InnerClass();
	}
	
	public class InnerClass {
		private String name = "InnerClass.name=" + this.getClass().getName();
	}
	
	public static void main(String[] args) {
		SonClass son = new SonClass();
		// 问题B：怎么创建一个InnerClass对象 
		InnerClass currentClass = son.new InnerClass();
		
		System.out.println(currentClass.name);
		
		SuperClass superClass = new SonClass();
		
		System.out.println(superClass.superName);
	}
}
