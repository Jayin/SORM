package io.github.jayin.test;

import io.github.jayin.test.entity.Admin;
import android.test.AndroidTestCase;

import com.annotation.core.Creator;
import com.annotation.core.Selector;
import com.annotation.utils._;

public class SelectorTest extends AndroidTestCase {

	public void test1() {
		String create = new Creator().from(Admin.class).build();
		_.d(create);
		String sql = new Selector().from(Admin.class).build();
		_.d(sql);
	}
}
