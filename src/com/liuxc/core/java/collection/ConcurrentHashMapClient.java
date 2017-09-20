package com.liuxc.core.java.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapClient {

	private static ConcurrentHashMap<Integer, Object> currentMap = new ConcurrentHashMap<Integer, Object>();
	public static void main(String[] args) {
		
		(new Thread("thread3"){
			@Override
			public void run() {
				currentMap.put(3, "1003");
			}
		}).start();

		(new Thread("thread4"){
			@Override
			public void run() {
				currentMap.put(4, "1004");
			}
		}).start();
		
		(new Thread("thread7"){
			@Override
			public void run() {
				currentMap.put(7, "1007");
			}
		}).start();
		
		for(Map.Entry<Integer, Object> entry : currentMap.entrySet()) {
			System.out.println(entry.getKey() + ":" +  entry.getValue());
		}
	}

}
