package com.liuxc.hadoop.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
/**
 * Java编码参考：http://www.cnblogs.com/liuling/p/2013-6-17-01.html
 * 连接HDFS参考：http://blog.csdn.net/zhangzhaokun/article/details/5597433
 * <p>
 * 运行本程序必须配置：
 * conf.set("mapred.jop.tracker", "hdfs://10.2.4.14:9001");
 * conf.set("fs.defaultFS", "hdfs://10.2.4.14:8020");
 * 参考http://blog.csdn.net/kkdelta/article/details/19910657
 * <p>
 * @author wisdom
 *
 */
public class CommonUtils {

	// 上传本地文件
	public static void uploadFile(String src, String dst) throws IOException {
		Configuration conf = new Configuration();
		conf.set("mapred.jop.tracker", "hdfs://10.2.4.14:9001");
		conf.set("fs.defaultFS", "hdfs://10.2.4.14:8020");
		FileSystem fs = FileSystem.get(conf);
		Path srcPath = new Path(src); // 原路径
		Path dstPath = new Path(dst); // 目标路径
		// 调用文件系统的文件复制函数,前面参数是指是否删除原文件，true为删除，默认为false
		fs.copyFromLocalFile(false, srcPath, dstPath);

		// 打印文件路径
		System.out.println("Upload to " + conf.get("fs.defaultFS"));
		System.out.println("------------list files------------" + "\n");
		FileStatus[] fileStatus = fs.listStatus(dstPath);
		for (FileStatus file : fileStatus) {
			System.out.println(file.getPath());
		}
		fs.close();
	}

}
