package com.liuxc.jms;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

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
	ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<MessageConsumer>();
	
	public void init() {
		try {
			connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
			
			connection = connectionFactory.createConnection();
			
			connection.start();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void getMessage(String message) {
		try {
			Queue queue = session.createQueue(message);
			MessageConsumer messageConsumer = null;
			
			if (threadLocal.get() != null) {
				messageConsumer = threadLocal.get();
			} else {
				messageConsumer = session.createConsumer(queue);
				threadLocal.set(messageConsumer);
			}
			
			while(true) {
				Thread.sleep(1000);
				
				TextMessage textMessage = (TextMessage) messageConsumer.receive();
				
				if (textMessage != null) {
					textMessage.acknowledge();
					System.out.println(Thread.currentThread().getName()+": Consumer:我是消费者，我正在消费Msg"+textMessage.getText()+"--->"+count.getAndIncrement());
				} else {
					break;
				}
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
