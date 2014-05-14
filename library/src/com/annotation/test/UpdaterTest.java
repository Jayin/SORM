package com.annotation.test;

import com.annotation.core.Updater;

public class UpdaterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User u = new User();
		u.setAge(11);
		u.setName("bbb");
		u.setSaveTime(12354546);
		u.setId(1);
		String sql = new Updater()
						.update(u)
						.where("id", "=", "1")
						.build();
		System.out.println(sql);

	}

}
