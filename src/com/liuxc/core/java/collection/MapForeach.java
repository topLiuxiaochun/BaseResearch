package com.liuxc.core.java.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class MapForeach implements MapForeachService {

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();  
        for (int i=0; i<4000; i++){  
            map.put(i, "第"+i+"个");  
        }  
        //循环第一种，耗时一直都是最高的  
        long t1 = System.nanoTime();  
        Object key[] = map.keySet().toArray();  
        for (int i=0; i<map.size();i++){  
            map.get(key[i]);  
        }  
        long t2 = System.nanoTime();   
        //循环第二种  
        for(Entry<Integer, String> entry : map.entrySet()){  
            entry.getValue();  
        }  
        long t3 = System.nanoTime();  
        //循环第三种  
        Iterator<Integer> it = map.keySet().iterator();  
        while(it.hasNext()){  
            Integer ii = (Integer)it.next();   
            map.get(ii);  
        }  
        long t4 = System.nanoTime();  
        //循环第四种  
        for (Integer kk : map.keySet()){  
            map.get(kk);  
        }  
        long t5 = System.nanoTime();  
        System.out.println("第一种方法耗时：" + (t2-t1)/1000 + "微秒");  
        System.out.println("第二种方法map.entrySet()耗时：" + (t3-t2)/1000 + "微秒");  
        System.out.println("第三种方法map.keySet().iterator()耗时：" + (t4-t3)/1000 + "微秒");  
        System.out.println("第四种方法map.keySet()耗时：" + (t5-t4)/1000 + "微秒"); 

	}
	
	/**
	 *  400万数据耗时
	 *  第一种方法耗时：151771微秒
		第二种方法map.entrySet()耗时：54822微秒
		第三种方法map.keySet().iterator()耗时：83081微秒
		第四种方法map.keySet()耗时：77279微秒

		结论：第二种最快
	 */
	/**
	 *  40万数据耗时
	 *  第一种方法耗时：36261微秒
		第二种方法map.entrySet()耗时：19816微秒
		第三种方法map.keySet().iterator()耗时：18994微秒
		第四种方法map.keySet()耗时：16232微秒
		
		结论：第二种和第四种最快
	 */
	/**
	 *  4万数据耗时
	 *  第一种方法耗时：20918微秒
		第二种方法map.entrySet()耗时：12051微秒
		第三种方法map.keySet().iterator()耗时：7901微秒
		第四种方法map.keySet()耗时：6054微秒
		
		结论：第四种最快几乎是第二种的三分之一，其次是第三种
	 */
	/**
	 *  4千数据耗时
	 *  第一种方法耗时：2836微秒
		第二种方法map.entrySet()耗时：1297微秒
		第三种方法map.keySet().iterator()耗时：2981微秒
		第四种方法map.keySet()耗时：2183微秒
		
		结论：快慢依次为第二种、第四种、第三种、第一种
	 */
	/**
	 *  40数据耗时
	 *  第一种方法耗时：283微秒
		第二种方法map.entrySet()耗时：105微秒
		第三种方法map.keySet().iterator()耗时：33微秒
		第四种方法map.keySet()耗时：31微秒
		
		结论：第二种和第四种最快
	 */

}
