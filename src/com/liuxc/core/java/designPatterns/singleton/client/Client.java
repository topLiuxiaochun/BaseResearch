package com.liuxc.core.java.designPatterns.singleton.client;

import com.liuxc.core.java.designPatterns.singleton.FinalEhanSingleton;
import com.liuxc.core.java.designPatterns.singleton.LanhanSingleton;
import com.liuxc.core.java.designPatterns.singleton.StaticBlockEhanSingleton;

public class Client {

	public static void main(String[] args) {
		LanhanSingleton singleton = LanhanSingleton.getInstance();
		System.out.println(singleton.getConfig());
		
		FinalEhanSingleton finalEhanSingleton = FinalEhanSingleton.getInstance();
		System.out.println(finalEhanSingleton.getConfig());
		
		StaticBlockEhanSingleton staticBlockEhanSingleton = StaticBlockEhanSingleton.getInstance();
		System.out.println(staticBlockEhanSingleton.getConfig());
	}
}
