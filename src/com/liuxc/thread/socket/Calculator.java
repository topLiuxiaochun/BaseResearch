package com.liuxc.thread.socket;

public class Calculator extends Thread {

	public int total;
	@Override
	public void run() {
		synchronized (this) {
			for(int i=0; i<101; i++) {
				total += i;
			}
			notifyAll();
		}
	}
	
}
