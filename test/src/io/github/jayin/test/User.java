package io.github.jayin.test;

import com.annotation.Column;
import com.annotation.Table;
import com.annotation.core.Model;

@Table
public class User extends Model {
	@Column
	private long userid;
	@Column
	private int age;
	@Column
	private String name;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

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

	@Override
	public String toString() {
		return "User [userid=" + userid + ", age=" + age + ", name=" + name
				+ ", __id=" + get__id()
				+"]";
	}

}
