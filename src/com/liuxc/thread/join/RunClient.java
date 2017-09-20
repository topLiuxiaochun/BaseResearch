package com.liuxc.thread.join;

public class RunClient {

	public static void main(String[] args) {
		try {
			ThreadB b = new ThreadB();
			ThreadA a = new ThreadA(b);

			b.start();
			a.start();
			
			b.join(3000);
			
			System.out.println("main end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
