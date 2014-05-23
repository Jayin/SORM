package io.github.jayin.test;

import io.github.jayin.test.entity.Admin;

import java.util.List;

import android.test.AndroidTestCase;

import com.annotation.core.Query;
import com.annotation.core.Selector;
import com.annotation.utils._;

public class DeleteTest extends AndroidTestCase {

	public void insert1() {
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

	public void delete1() {
//		insert1();
		List<Admin> admins = new Query(new Selector().from(Admin.class))
				.excute(getContext());
		_.d(admins.toString());
		for(Admin a: admins){
			a.delete(getContext());
		}
//		admins.get(0).delete(getContext());
	}
}
