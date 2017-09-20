package com.liuxc.core.java.keyword;
/**
 * Java中都是值传递
 * 
 * @since:2017年8月23日
 * @author:liuxc
 */
public class ValueTransmitTest {

	public static void main(String[] args) {
		ValueTransmit valueTransmit = new ValueTransmit();
		String name = "10";
		valueTransmit.setNickname("before 20");
		
		valueTransmit.change(name);
		System.out.println("name=" + name);
		
		
		new ValueTransmitTest().changeValueTransmit(valueTransmit);
		System.out.println("nickname=" + valueTransmit.getNickname());

	}
	
	public void changeValueTransmit(ValueTransmit valueTransmit) {
		valueTransmit.setNickname("after 20");
	}

}

class ValueTransmit {
	
	private String nickname;
	
	public String change(String name) {
		return name="20";
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
