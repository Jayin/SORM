package io.github.jayin.test.entity;

import com.annotation.Column;
import com.annotation.Table;
import com.annotation.core.Model;

@Table
public class Student extends Model {
	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + "]";
	}

}