package com.liuxc.hadoop.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaDataMySQL {

	public static void main(String[] args) {
		try {
			getMetadata();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getMetadata() throws Exception{
		//1. JDBC连接MYSQL的代码很标准。 
		Connection conn = null;
		ResultSet tableRet = null;
		ResultSet colRet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdadmin?user=root&password=root");
			
			String m_TableName = "operationGroupInfo";
			//2. 下面就是获取表的信息。 
			DatabaseMetaData m_DBMetaData = conn.getMetaData(); 
			tableRet = m_DBMetaData.getTables(null, "%",m_TableName,new String[]{"TABLE"}); 
			while(tableRet.next()) {
				System.out.println(tableRet.getString("TABLE_NAME"));
			}
			//3. 提取表的名字。 
			
			/*通过getString("TABLE_NAME")，就可以获取表的名字了。 
		从这里可以看出，前面通过getTables的接口的返回，JDBC是将其所有的结果，保存在一个类似table的内存结构中，而其中TABLE_NAME这个名字的字段就是每个表的名字。*/
			
			//4. 提取表内的字段的名字和类型 
			String columnName = ""; 
			String columnType = ""; 
			colRet = m_DBMetaData.getColumns(null,"%", m_TableName,"%"); 
			while (colRet.next()) {
				columnName = colRet.getString("COLUMN_NAME");
				columnType = colRet.getString("TYPE_NAME");
				int datasize = colRet.getInt("COLUMN_SIZE");
				int digits = colRet.getInt("DECIMAL_DIGITS"); 
				int nullable = colRet.getInt("NULLABLE"); 
				System.out.println(columnName+" "+columnType+" "+datasize+" "+digits+" "+ nullable); 

			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (colRet != null) {
				colRet.close();
			}
			if (tableRet != null) {
				tableRet.close();
			}
			if (conn != null) {
				conn.close();
			}
		}


	}
}
