package com.liuxc.thread.async;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 异步处理写入文件程序
 * <p>
 * 1，从控制台发送消息到消息服务器(由一个队列模拟)。
 * 2，将消息队列写入到文件(对写文件的操作设置延时以模拟性能瓶颈)。
 * 3，消息服务器作为控制台和文件写入之间的缓冲区。
 * 
 * <p>
 * 参考：http://www.cnblogs.com/fangfan/p/4047932.html
 * <p>
 * @author liuxc
 *
 */
public class AsyncHandle {

	/**
	 * 控制资源释放
	 */
	private CountDownLatch latch;
	/**
	 * 处理完成标识
	 */
	private volatile boolean handleFinish;
	/**
	 * 消息写入本地文件完成
	 */
	private volatile boolean sendFinish;
	
	/**
	 * 阻塞队列
	 */
	private BlockingQueue<String> queue;
	
	private BufferedWriter bw;
	
	public AsyncHandle(CountDownLatch latch) {
		this.latch = latch;
		/**
		 * 使用链表实现
		 */
		queue = new LinkedBlockingQueue<String>();
		File file = new File("E:\\upload\\readme.txt");
		
		try {
			bw = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	public void handle() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!handleFinish){
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					String headObject = queue.peek();// 取出队列第一个元素
//					System.out.println("获取消息");
					if (headObject != null) {
						// 取出并移除队列第一个元素
						queue.poll();
						try {
							System.out.println(headObject + "被输出到外部文件中");
							bw.write(headObject);
							bw.newLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					// 若队列为空并且消息发送完成.
					if(queue.isEmpty() && sendFinish) {
						// 计数器 1 -> 0
						latch.countDown();
						
						handleFinish = true;
						break;
					}
					
				}
				
			}
		} ).start();
	}
	/**
	 * <pre>
     * 给出消息发送完成的标识.
     * </pre>
	 *
	 */
	public void sendFinish() {
		sendFinish = true;
	}
	/**
	 * 资源释放
	 */
	public void release() {
		if (bw != null) {
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 其实使用queue = null就够了.
		if (queue != null) {
			queue.clear();
			queue = null;
		}
	}
	/**
	 * <pre>往队列发送消息</pre>
	 * @param text
	 */
	public void sendMessage(String text) {
		if (text != null && !"".equals(text)) {
			queue.add(text);
		}
	}
	
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);
		AsyncHandle handle = new AsyncHandle(latch);
		System.out.println("bootstrap...");
		handle.handle();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String text = scanner.next();
			// 若用户选择退出
			if ("exit".equals(text)) {
				// 表示消息已经发送完成
				handle.sendFinish();
				break;
			}
			handle.sendMessage(text);
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 释放资源：文件流、队列
		handle.release();
		// 关闭控制台输入
		scanner.close();
	}
	
}
