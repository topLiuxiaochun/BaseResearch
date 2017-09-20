package com.liuxc.core.java.collection;

public class ArrayTest {

	public static void main(String[] args) {
//		int[] titleArray = new int[3]{1,2};//Cannot define dimension expressions when an array initializer is provided 
		
		int i[] = {1,2};
		
		int[] j[] = {{},new int[]{}};
		System.out.println(j[1].length);
		
		int k[][] = { {1,2}, new int[2] };
		
		int l[] = { 1, 2, 3, 4, };
		
		Integer[] idArray = new Integer[]{}; 

		System.out.println(idArray.length);
		System.err.println(idArray[2]);
	}

}
