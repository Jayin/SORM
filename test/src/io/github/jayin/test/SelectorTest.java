package io.github.jayin.test;

import io.github.jayin.test.entity.Admin;

import com.annotation.core.Creater;
import com.annotation.core.Selector;
import com.annotation.utils._;

import android.test.AndroidTestCase;

public class SelectorTest extends AndroidTestCase {

	public void test1() {
		String create = new Creater().from(Admin.class).build();
		_.d(create);
		String sql = new Selector().from(Admin.class).build();
		_.d(sql);
	}
}
