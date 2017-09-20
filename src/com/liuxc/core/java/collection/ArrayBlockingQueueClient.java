package com.liuxc.core.java.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueClient {
	private final int queueSize = 10;
	private BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(queueSize);
 
	public static void main(String[] args) {

		ArrayBlockingQueueClient client = new ArrayBlockingQueueClient();
		Producer producer = client.new Producer();
		Consumer consumer = client.new Consumer();
		
		producer.start();
		consumer.start();
	}
	
	class Producer extends Thread{

		@Override
		public void run() {
			product();
		}
		
		public void product() {
			while(true) {
				try {
					blockingQueue.put(1);
					System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-blockingQueue.size()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class Consumer extends Thread{

		@Override
		public void run() {
			consumer();
		}
		
		public void consumer() {
			while(true) {
				try {
					blockingQueue.take();
					System.out.println("从队列取走一个元素，队列剩余"+blockingQueue.size()+"个元素");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
