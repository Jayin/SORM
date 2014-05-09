package com.annotation.test;

import com.annotation.Column;
import com.annotation.PrimaryKey;
import com.annotation.Table;
import com.annotation.Unique;

@Table
public class User {
	@Column
	private String name;

	@Column
	private int age;
	
	@PrimaryKey 
	private int id;

	@Unique
	private long saveTime;

	private StringBuffer sb;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
