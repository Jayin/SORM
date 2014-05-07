package com.annotation.test;

import com.annotation.core.Creator;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 System.out.println(new Creator().from(User.class).build());
	}

}
