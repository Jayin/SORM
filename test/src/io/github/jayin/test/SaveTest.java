package io.github.jayin.test;

import io.github.jayin.test.entity.Admin;
import io.github.jayin.test.entity.User;
import android.test.AndroidTestCase;

public class SaveTest extends AndroidTestCase {

	public void insert1() {
		User u1 = new User();
//		u1.set__id(Long.valueOf(1));
		u1.setAge(12);
		u1.setName("Jack");
		u1.setUserid(3112002720L);
		u1.save(getContext());
	}
	
	public void insert2() {
		 Admin a1 = new Admin();
		 a1.setAdminName("a111");
		 a1.setId(1);
		 a1.save(getContext());
		 
		 a1.setAdminName("a222");
		 a1.setId(2);
		 a1.save(getContext());
		 
		 a1.setAdminName("a333");
		 a1.setId(3);
		 a1.save(getContext());
	}
	
	public void insert3() {
		 Admin a1 = new Admin();
		 a1.setAdminName("a1");
		 a1.setId(1);
		 a1.save(getContext());
		 
		 a1.setAdminName("a2");
		 a1.setId(2);
		 a1.save(getContext());
		 
		 a1.setAdminName("a3");
		 a1.setId(3);
		 a1.save(getContext());
	}
	
	public void insert4() {
		for(int i=0;i<100;i++){
			 Admin a1 = new Admin();
			 a1.setAdminName("a"+i);
			 a1.setId(i+100);
			 a1.save(getContext());
		 }
	}
}
