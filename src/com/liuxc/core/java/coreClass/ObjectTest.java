package com.liuxc.core.java.coreClass;

import java.util.HashMap;
import java.util.Map;

public class ObjectTest {

	public static void main(String[] args) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		User user = new User("u001", "u001", 20, 1);
		User userCopy = new User("u001", "u001", 20, 1);
		Integer num1 = 66;
		Integer num2 = 66;
		
		System.out.println(user.hashCode());
		System.out.println(userCopy.hashCode());
		
		/**
		 * 返回true:在-128~127之间返回true，参看public static Integer valueOf(int i)
		 */
		System.out.println(num1 == num2);
		
		Integer num3 = 966;
		Integer num4 = 966;
		/**
		 * 返回false
		 */
		System.out.println(num3 == num4);
		
//		User colneUser = user.
	}
	
	public void cloneTest() {
		
	}

}

class User {
	private String id;
	private String name;
	private Integer age;
	private int identity;
	
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println();
		super.finalize();
		System.out.println();
	}
	public User() {
		
	}
	public User(String id, String name, Integer age, int identity) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.identity = identity;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", identity=" + identity + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + identity;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identity != other.identity)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
