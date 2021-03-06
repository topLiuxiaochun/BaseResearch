package com.liuxc.core.java.collection;

import java.util.Map;

public class LocalHashMapClient {

	public static void main(String[] args) {
		Map<String, Object> hashMap = new LocalHashMap<String, Object>();
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

}
