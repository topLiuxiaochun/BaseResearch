package com.liuxc.jms.client;

import com.liuxc.jms.Producter;

public class ProducterClient {

	public static void main(String[] args) {
		Producter producter = new Producter();
		producter.init();
		ProducterClient client = new ProducterClient();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(client.new ProducterThread(producter)).start();
		new Thread(client.new ProducterThread(producter)).start();
		new Thread(client.new ProducterThread(producter)).start();
		new Thread(client.new ProducterThread(producter)).start();
		new Thread(client.new ProducterThread(producter)).start();

	}

	private class ProducterThread implements Runnable {
		
		private Producter producter;
		
		public ProducterThread(Producter producter) {
			this.producter = producter;
		}

		@Override
		public void run() {
			while(true) {
				try {
					producter.sendMessage("active-mq");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
