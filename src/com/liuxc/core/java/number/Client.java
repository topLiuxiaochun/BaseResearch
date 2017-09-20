package com.liuxc.core.java.number;

public class Client {

	public static void main(String[] args) {
		String numStr = "123";
		Integer numInteger = Integer.valueOf(numStr);
		System.out.println(numInteger);

		int hashSeed = 20;
		int hashStart = 50;
		
		hashSeed ^= hashStart;//按位运算
		System.out.println(hashSeed);//38
		
		Integer num1 = 128;
		Integer num2 = 128;
		Integer numObject = new Integer(12);
		System.out.println(num1 == numObject);
		
		System.out.println(num1 == num2);
		
		int temp = hashSeed = hashStart;
		System.out.println(temp);
		System.out.println(hashSeed);
	}

}
