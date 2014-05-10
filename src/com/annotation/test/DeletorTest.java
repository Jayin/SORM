package com.annotation.test;

import com.annotation.core.Deletor;

public class DeletorTest {
	public static void main(String[] args) {
		String sql = new Deletor()
						.from(User.class)
						.where("id", "=", "1")
						.or().where("\"1\"", "=", "1")
						.build();
		System.out.println(sql);
					   
	}
}
