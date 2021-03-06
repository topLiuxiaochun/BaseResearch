package com.liuxc.jms;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	AtomicInteger count = new AtomicInteger(0);
	
	
	//链接工厂
	ConnectionFactory connectionFactory;
	//链接对象
	Connection connection;
	
	//事务管理
	Session session;
	
	//ThreadLocal<T>
	ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<MessageProducer>();
	
	public void init() {
		try {
			connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
			
			connection = connectionFactory.createConnection();
			
			connection.start();
			
			session = connection.createSession(true, Session.SESSION_TRANSACTED);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void sendMessage(String message) {
		try {
//			创建一个消息队列
			Queue queue = session.createQueue(message);
//			消息生产者
			MessageProducer messageProducer = null;
			
			if (threadLocal.get() != null) {
				messageProducer = threadLocal.get();
			} else {
				messageProducer = session.createProducer(queue);
				threadLocal.set(messageProducer);
			}
			
			while(true) {
				Thread.sleep(1000);
				
				int num = count.getAndIncrement();
//				创建一条消息
				TextMessage textMessage = session.createTextMessage(Thread.currentThread().getName()
						+ "productor:我是豆腐西施，正在生产东西！,count:" + num);
				
				System.out.println(Thread.currentThread().getName()
						+ "productor:我是豆腐西施，正在生产东西！,count:" + num);
//				发送消息
				messageProducer.send(textMessage);
//				提交事务
				session.commit();
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
