package com.liuxc.core.java;



public class StringUtils {

	public static void main(String[] args) {
		String base = "www,yyy";
		String[] baseArr = base.split(",");
		if (baseArr!=null && baseArr.length>0 && !"".equals(baseArr[0])) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < baseArr.length; i++) {
				stringBuilder.append("'" +baseArr[i] + "',");
			}
			stringBuilder.deleteCharAt(stringBuilder.length()-1);
			System.out.println(stringBuilder.toString());
			
		}

//		测试逆序输出给定的字符串
		System.out.println(StringUtils.reverseStr("com.baidu.www"));
		
		try {
//			StringUtils.string2Int("123f90");
			for(int i = 0; i < 5000000; i++) {
				StringUtils.string2Int("" + i);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

	/**
	 * 逆序输出给定的字符串
	 * @param str
	 * @return
	 */
	public static String reverseStr(String str) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = str.length()-1; i >= 0; i--) {
			char c = str.charAt(i);
			sBuilder.append(c);
			
		}
		return sBuilder.toString();
	}
	
	/**
	 * 字符串转int
	 * 输入90044 或 0933
	 * @param src
	 * @return
	 */
	public static int string2Int(String src) throws Exception {
		int total = 0;
		if (checkStr(src)) {
			int length = src.length();
			for (int i = 0; i < length; i++) {
				char c = src.charAt(i);
				for(int j=0; j < numberArray.length; j++) {
					if (c == numberArray[j]) {
						int totalMI = length-i-1;
						int totalNum = 1;
						for(int m = 0; m < totalMI; m++) {
							totalNum *= 10;
						}
						if (totalMI == 0) {
							total += j;
						} else {
							total += j*totalNum;
						}
					}
					
				}
			}
			System.out.println(total);
			System.out.println(total == Integer.valueOf(src));
			
		} else {
			total = -1;
			throw new NullPointerException("输入不合法");
		}
		return total;
	}
	
	static char[] numberArray = new char[]{'0','1','2','3','4','5','6','7','8','9'};
	
	/**
	 * 不是数字类型的字符串返回false，否则返回true
	 * @param src
	 * @return
	 */
	private static boolean checkStr(String src) {
		if(src.startsWith("0") && src.length() >= 2) return false;
		int length = src.length();
		for (int i = 0; i < length; i++) {
			char charAt = src.charAt(i);
			int total = 0;
			for(int j=0; j < numberArray.length; j++) {
				if (charAt != numberArray[j]) {
					total++;
				}
			}
			if (total == 10) {
				return false;
			}
		}
		return true;
	}
}
