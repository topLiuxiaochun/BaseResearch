package com.liuxc.core.java.collection;

import java.util.Arrays;
import java.util.List;

public class ListClient {

	public static void main(String[] args) {
		List<Integer> integers = Arrays.asList(1,2,3);
		int n = 0;
		for (int i : integers) {
			n += i;
		}
		

		System.out.println(n==6);
	}

}
