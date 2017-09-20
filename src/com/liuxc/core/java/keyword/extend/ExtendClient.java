package com.liuxc.core.java.keyword.extend;

public class ExtendClient {

	public static void main(String[] args) {
		A a1 = new A();  
        A a2 = new B(){

			@Override
			public void hide() {
				super.hide();
				
				System.out.println("构造函数中重写方法hide...");
			}
        	
        };  
        B b = new B(){

			@Override
			public void hide() {
				super.hide();
				
				System.out.println("构造函数中重写方法hide...");
			}
        	
        };   
        C c = new C();   
        D d = new D();   
        a1.show(b);//   ①  
        a1.show(c);//   ②  
        a1.show(d);//   ③  
        System.out.println("**************************");
        a2.show(b);//   ④  
        a2.show(c); //  ⑤  
        a2.show(d);//   ⑥ 
//        a2.hide();
        System.out.println("**************************");
        b.show(b); //   ⑦  
        b.show(c);//    ⑧  
        b.show(d);//    ⑨ 
        b.hide();

	}

	
}
class A {
	public void show(D D) {
		System.out.println("A and D");
	}
	
	public void show(A A) {
		System.out.println("A and A");
	}
}

class B extends A{
	
	public void hide() {
		System.out.println("A cannot access B");
	}
	
	public void show(B B) {
		System.out.println("B and B");
	}
	
	public void show(A A) {
		System.out.println("B and A");
	}
	
}

class C extends B {
	
}
class D extends B {
	
}


