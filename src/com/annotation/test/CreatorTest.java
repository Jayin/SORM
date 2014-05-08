package com.annotation.test;

import com.annotation.core.Creator;

public class CreatorTest {

	public static void main(String[] args) {
		System.out.println(new Creator().from(User.class).build());
	}

}
