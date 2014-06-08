package io.github.jayin.test;

import io.github.jayin.test.entity.Admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.AndroidTestCase;

import com.annotation.core.Query;
import com.annotation.core.Selector;
import com.annotation.utils.DBHelper;
import com.annotation.utils.DBUtils;
import com.annotation.utils._;

public class ReleaseDBTest extends AndroidTestCase {

	public void init() {
		DBUtils.releaseDB(getContext());
	}

	public void second() {
		List<Admin> admins = new ArrayList<Admin>();
		File f = getContext().getDatabasePath(DBHelper.DB_Name);
		long befor_insert = f.length();
		
		_.d("befor_insert =" + befor_insert);
		for (int i = 0; i < 10000; i++) {
			Admin a = new Admin();
			a.setAdminName("ReleaseDBTest" + i);
			a.setId(i);
			admins.add(a);
		}
		DBUtils.saveBatch(getContext(), Admin.class, admins);
		
		f = getContext().getDatabasePath(DBHelper.DB_Name);
		long after_insert = f.length();
		_.d("after_insert =" + after_insert);

		Selector selector = new Selector().from(Admin.class);
		List<Admin> result = new Query(selector).excute(getContext());
		DBUtils.deleteBatch(getContext(), Admin.class, result);
		
		f = getContext().getDatabasePath(DBHelper.DB_Name);
		long after_delete = f.length();
		_.d("after_delete =" + after_delete);
	}

	public void release() {
		SharedPreferences sp = getContext().getSharedPreferences(
				DBUtils.PerferenceName, Context.MODE_PRIVATE);
		sp.edit()
				.putLong(DBUtils.Last_Clean,
						System.currentTimeMillis() - DBUtils.Interval - 200)
				.apply();
		
		File f = getContext().getDatabasePath(DBHelper.DB_Name);
		long befor_release = f.length();
		_.d("befor_release =" + befor_release);
		
		DBUtils.releaseDB(getContext());
		
		f = getContext().getDatabasePath(DBHelper.DB_Name);
		long after_release = f.length();
		_.d("after_release =" + after_release);
		
		_.d("total release = "+(befor_release - after_release));
	}

	public void go() {
		init();
		second();
	}
}
