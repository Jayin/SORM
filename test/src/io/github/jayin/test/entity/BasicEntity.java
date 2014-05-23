package io.github.jayin.test.entity;

import com.annotation.core.Model;

public class BasicEntity extends Model {
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BasicEntity [id=" + id + super.toString()+"]"  ;
	}

}
