package com.annotation.test;

import com.annotation.core.Creater;

public class CreatorTest {

	public static void main(String[] args) {
		System.out.println(new Creater().from(User.class).build());
	}

}
