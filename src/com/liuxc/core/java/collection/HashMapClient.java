package com.liuxc.core.java.collection;

import java.util.HashMap;
import java.util.Map;

public class HashMapClient {
	
	private static String valueString = "value";

	public static void main(String[] args) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String firstNotNull = "firstNotNull";
		System.out.println("firstNotNull.hashCode=" + firstNotNull.hashCode());
		hashMap.put("firstNotNull", "1002");
		hashMap.put("firstNotNull", "1002001");
	    System.out.println(2104903554 & 15);
		
		hashMap.put(null, "1001");
		
		
		for(int i=0; i<500; i++) {
			hashMap.put("name" + i, i);
		}
		
		hashMap.put("name", "1003");
		
		Object nameObject = hashMap.get("name");
		
		System.out.println(nameObject);
		
		for(String key : hashMap.keySet()) {
			System.out.println("key=" + key + ",value=" + hashMap.get(key));
		}
		
	}
	
	
	private final class Entry extends EntryIterator{
		
		private Entry() {
			super();
		}

		public String getValueString() {
			return valueString;
		}
	}

	private abstract class EntryIterator {
		private String next;
		EntryIterator() {
			System.out.println("EntryIterator");
		}
	}
}

