package com.liuxc.common.commonsAllJar;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StringUtilsClient {

	public static void main(String[] args) {
		System.out.println(StringUtils.isEmpty(null));//true
		System.out.println(StringUtils.isEmpty(""));//true
		System.out.println(StringUtils.isEmpty(" "));//false
		System.out.println(StringUtils.isEmpty("null"));//false
		System.out.println(StringUtils.isEmpty(" null "));//false
		System.out.println(StringUtils.isBlank(null));//true
		System.out.println(StringUtils.isBlank(""));//true
		System.out.println(StringUtils.isBlank(" "));//true
		System.out.println(StringUtils.isBlank("null"));//false
		System.out.println(StringUtils.isBlank(" null "));//false
		
		
		//默认半角空格分割  
		String str1 = "aaa bbb ccc";  
		String[] dim1 = StringUtils.split(str1); // => ["aaa", "bbb", "ccc"]  
		  
		System.out.println(dim1.length);//3  
		System.out.println(dim1[0]);//"aaa"  
		System.out.println(dim1[1]);//"bbb"  
		System.out.println(dim1[2]);//"ccc"  
		  
		//指定分隔符  
		String str2 = "aaa,bbb,ccc";  
		String[] dim2 = StringUtils.split(str2, ","); // => ["aaa", "bbb", "ccc"]  
		  
		System.out.println(dim2.length);//3  
		System.out.println(dim2[0]);//"aaa"  
		System.out.println(dim2[1]);//"bbb"  
		System.out.println(dim2[2]);//"ccc"  
		  
		//去除空字符串  
		String str3 = "aaa,,bbb";  
		String[] dim3 = StringUtils.split(str3, ","); // => ["aaa", "bbb"]  
		  
		System.out.println(dim3.length);//2  
		System.out.println(dim3[0]);//"aaa"  
		System.out.println(dim3[1]);//"bbb"  
		  
		//包含空字符串  
		String str4 = "aaa,,bbb";  
		String[] dim4 = StringUtils.splitPreserveAllTokens(str4, ","); // => ["aaa", "", "bbb"]  
		  
		System.out.println(dim4.length);//3  
		System.out.println(dim4[0]);//"aaa"  
		System.out.println(dim4[1]);//""  
		System.out.println(dim4[2]);//"bbb"  
		  
		//指定分割的最大次数（超过后不分割）  
		String str5 = "aaa,bbb,ccc";  
		String[] dim5 = StringUtils.split(str5, ",", 2); // => ["aaa", "bbb,ccc"]  
		  
		System.out.println(dim5.length);//2  
		System.out.println(dim5[0]);//"aaa"  
		System.out.println(dim5[1]);//"bbb,ccc"  
		
		
		//数组元素拼接
		String[] joinArray = {"aaa", "bbb", "ccc"};
		String joinStr = StringUtils.join(joinArray, ",");
		System.out.println("joinStr=" + joinStr);
		
		List<String> joinList = new ArrayList<String>();
		joinList.add("fuck");
		joinList.add("you");
		joinList.add("now");
		//集合元素拼接
		System.out.println("joinList=" + StringUtils.join(joinList, ","));

	}

}
