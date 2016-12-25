package com.helloweenvsfei.jsp.bean;

public class Person {
	private String name;    //姓名
	private int age;        //年龄
	private String sex;     //性别
	private boolean secret; //是否保密信息
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public boolean isSecret() {
		return secret;
	}
	public void setSecret(boolean secret) {
		this.secret = secret;
	}

	

}
