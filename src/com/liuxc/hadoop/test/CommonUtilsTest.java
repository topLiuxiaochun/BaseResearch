package com.liuxc.hadoop.test;

import java.io.IOException;

import com.liuxc.hadoop.hdfs.CommonUtils;

public class CommonUtilsTest {

	public static void main(String[] args) {
		String src = "E:\\20170204金融部门\\项目文档\\大数据技术平台\\交接文档\\代码结构列表.xlsx";
		/*String dst = "hdfs:10.2.4.14:8020/tmp";*/
		String dst = "hdfs://10.2.4.14:8020/tmp";
		try {
			CommonUtils.uploadFile(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 测试上传文件
		// uploadFile("D:\\c.txt", "/user/hadoop/test/");
		// 测试创建文件
		/*
		 * byte[] contents = "hello world 世界你好\n".getBytes();
		 * createFile("/user/hadoop/test1/d.txt",contents);
		 */
		// 测试重命名
		// rename("/user/hadoop/test/d.txt", "/user/hadoop/test/dd.txt");
		// 测试删除文件
		// delete("test/dd.txt"); //使用相对路径
		// delete("test1"); //删除目录
		// 测试新建目录
		// mkdir("test1");
		// 测试读取文件
//		readFile("test1/d.txt");
	}

}
