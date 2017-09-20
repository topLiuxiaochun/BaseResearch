package com.liuxc.hadoop.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class HiveTest {

	private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;
    @Before
    public void getConnection() {
    	try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            connection = DriverManager.getConnection("jdbc:hive2://10.2.4.14:10000/default");
            System.out.println(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //关闭连接
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 创建表
    @Test
    public void createTable() {
        String sql = "create table menu(moduleName string, fileName string, filePath string, memo string) row format delimited fields terminated by ',' ";
        try {
            ps = connection.prepareStatement(sql);
            ps.execute(sql);
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //添加数据
    @Test
    public void insert() throws SQLException {
        String sql = "load local data inpath 'D:\\file\\memberUnit.txt' into table menu";
        //记得先在文件系统中上传goods.txt
        ps = connection.prepareStatement(sql);
        ps.execute();
        close();
    }
    @Test
    public void showTables() throws SQLException{
    	String sql = "show tables";
    	ps = connection.prepareStatement(sql);
    	rs = ps.executeQuery(sql);
    }
}
