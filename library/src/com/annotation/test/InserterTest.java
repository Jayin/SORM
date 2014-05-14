package com.annotation.test;

import com.annotation.core.Inserter;

public class InserterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User u = new User();
		u.setAge(11);
		u.setName("aaa");
		u.setSaveTime(12354546);
		u.setId(1);
		System.out.println(new Inserter().insert(u ).build());
	}


}
