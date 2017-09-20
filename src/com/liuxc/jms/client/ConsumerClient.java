package com.liuxc.jms.client;

import com.liuxc.jms.Consumer;

public class ConsumerClient {

	public static void main(String[] args) {
		Consumer consumer = new Consumer();
		consumer.init();
		ConsumerClient client = new ConsumerClient();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(client.new ConsumerThread(consumer)).start();
		new Thread(client.new ConsumerThread(consumer)).start();
		new Thread(client.new ConsumerThread(consumer)).start();
		new Thread(client.new ConsumerThread(consumer)).start();
		new Thread(client.new ConsumerThread(consumer)).start();

	}

	private class ConsumerThread implements Runnable {
		
		private Consumer consumer;
		
		public ConsumerThread(Consumer consumer) {
			this.consumer = consumer;
		}

		@Override
		public void run() {
			while(true) {
				try {
					consumer.getMessage("active-mq");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
