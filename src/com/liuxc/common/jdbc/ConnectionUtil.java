package com.liuxc.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.liuxc.common.entity.Employee;

public class ConnectionUtil {
	
	private static Connection conn;
	
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws Exception{
		List<Employee> list = queryList();

		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&user=root&password=root");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static List<Employee> queryList() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * FROM employee "
					+ "where EXISTS (select 1 from employee , deptstructure where employee.Department=deptstructure.deptCode) limit 20";
		List<Employee> employeeList = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery(sql);
			employeeList = new ArrayList<Employee>();
			while (rs.next()) {
				int employeeId = rs.getInt("employeeId");
				String employeeName = rs.getString("employeeName");
				Timestamp createTime = rs.getTimestamp("createTime");
				Timestamp departureTime = rs.getTimestamp("departureTime");
				
				
				Employee employee = new Employee();
				employee.setEmployeeId(employeeId);
				employee.setEmployeeName(employeeName);
				employee.setCreateTime(createTime);
				employee.setDepartureTime(departureTime);
				
				employeeList.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		return employeeList;
		
	}

}
