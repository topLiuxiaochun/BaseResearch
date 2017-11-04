package com.liuxc.jms.client;

import com.liuxc.jms.Producer;

public class ProducerClient {

	public static void main(String[] args) {
		Producer producter = new Producer();
		producter.init();
		ProducerClient client = new ProducerClient();
		
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
		
		private Producer producer;
		
		public ProducterThread(Producer producter) {
			this.producer = producter;
		}

		@Override
		public void run() {
			while(true) {
				try {
					producer.sendMessage("active-mq");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
