package com.liuxc.common.commonsAllJar;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
/**
 * 测试org.apache.commons.beanutils.BeanUtils的常用方法
 * 复制JavaBean方法cloneBean
 * bean转换map方法describe
 * map转换bean方法populate
 */
import com.liuxc.common.entity.Employee;

public class BeanUtilsClient {

	public static void main(String[] args) throws Exception {
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("1001");
		employee.setSex("nv");
		employee.setDept("1");

		System.out.println(employee);
		
		// 复制bean
		Employee e = (Employee) BeanUtils.cloneBean(employee);
		
		System.out.println(e);
		
		// bean转换map
		@SuppressWarnings("unchecked")
		Map<String, String> beanMap = BeanUtils.describe(e);
		
		System.out.println("beanMap.dept=" + beanMap.get("dept"));
		
		beanMap.clear();
		beanMap.put("dept", "beanMap.setDept");
		
		Employee newEmployee = new Employee();
		// map转换bean
		BeanUtils.populate(newEmployee, beanMap);
		
		System.out.println(newEmployee);
	}

}
