package com.annotation.test;

import com.annotation.core.Deleter;

public class DeleterTest {
	public static void main(String[] args) {
		String sql = new Deleter()
						.from(User.class)
						.where("id", "=", "1")
						.and().where("name", "!=", "Jason")
						.build();
		System.out.println(sql);
					   
	}
}
