package io.github.jayin.test.entity;

import com.annotation.Column;
import com.annotation.Table;
import com.annotation.Unique;
import com.annotation.core.Model;

@Table
public class Animal extends Model {
	@Unique
	private int id;

	@Column
	private int age;

	@Column
	private String kind;

	@Column
	private String from;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
 
}
