package io.github.jayin.test;

import android.test.AndroidTestCase;

public class SaveTest extends AndroidTestCase {

	public void insert() {
		User u1 = new User();
		u1.set__id(Long.valueOf(1));
		u1.setAge(12);
		u1.setName("Jack");
		u1.setUserid(3112002720L);
		u1.save(getContext());
	}
}
