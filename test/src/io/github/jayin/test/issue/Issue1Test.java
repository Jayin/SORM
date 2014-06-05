package io.github.jayin.test.issue;

import io.github.jayin.test.entity.Admin;
import android.test.AndroidTestCase;

import com.annotation.core.Deletor;
import com.annotation.core.Selector;
import com.annotation.core.Updater;
import com.annotation.utils._;

public class Issue1Test extends AndroidTestCase {

	public void deletorTest() {
		String sql = new Deletor().from(Admin.class).where("id = 1").and()
				.where("name = Jayin").build();
		// System.out.println(sql);
		_.d(sql);
	}

	public void selectorTest() {
		String sql = new Selector().from(Admin.class).where("id = 1").and()
				.where("name = Jayin").build();
		_.d(sql);
	}

	public void UpdaterTest() {
		Admin a = new Admin();
		a.setAdminName("Jayin");
		a.setId(111);
		String sql = new Updater().update(a).where("id = 1").and()
				.where("name = Jayin").build();
		_.d(sql);
	}

	public void test_all() {
		deletorTest();
		selectorTest();
		UpdaterTest();

	}
}
