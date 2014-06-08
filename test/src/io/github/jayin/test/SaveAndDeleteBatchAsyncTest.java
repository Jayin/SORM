package io.github.jayin.test;

import io.github.jayin.test.entity.Admin;

import java.util.ArrayList;
import java.util.List;

import com.annotation.core.Query;
import com.annotation.core.Selector;
import com.annotation.entity.ORMcallback;
import com.annotation.utils.DBUtils;
import com.annotation.utils._;

import android.test.AndroidTestCase;

public class SaveAndDeleteBatchAsyncTest extends AndroidTestCase {
	public void insert1() {
		List<Admin> admins = new ArrayList<Admin>();
		for (int i = 0; i < 100; i++) {
			Admin a = new Admin();
			a.setAdminName("Async" + i);
			a.setId(i);
			admins.add(a);
		}

		DBUtils.saveBatchAsync(getContext(), Admin.class, admins,
				new ORMcallback() {

					@Override
					public void onFinish() {
						_.d("onFinish");
					}

					@Override
					public void onFaild() {
						_.d("onFaild");
					}
				});

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void update1() {
		List<Admin> result = new Query(new Selector().from(Admin.class))
				.excute(getContext());
		for (Admin a : result) {
			_.d("deal with --->" + a.get__id());
			a.setAdminName("updat-" + a.getAdminName());
		}
		DBUtils.saveBatchAsync(getContext(), Admin.class, result,
				new ORMcallback() {

					@Override
					public void onFinish() {
						_.d("onFinish");
					}

					@Override
					public void onFaild() {
						_.d("onFaild");
					}
				});

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void delete1() {
		Selector selector = new Selector().from(Admin.class);
		List<Admin> admins = new Query(selector).excute(getContext());
		DBUtils.deleteBatchAsync(getContext(), Admin.class, admins,
				new ORMcallback() {

					@Override
					public void onFinish() {
						_.d("onFinish");
					}

					@Override
					public void onFaild() {
						_.d("onFaild");
					}
				});
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
