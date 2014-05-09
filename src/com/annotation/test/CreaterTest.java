package com.annotation.test;

import com.annotation.core.Creater;

public class CreaterTest {

	public static void main(String[] args) {
		System.out.println(new Creater().from(User.class).build());
	}

}
