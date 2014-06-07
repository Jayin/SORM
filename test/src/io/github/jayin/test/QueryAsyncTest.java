package io.github.jayin.test;

import io.github.jayin.test.entity.Admin;

import java.util.List;

import android.test.AndroidTestCase;

import com.annotation.core.Query;
import com.annotation.core.Selector;
import com.annotation.entity.QueryCallback;
import com.annotation.utils._;

public class QueryAsyncTest extends AndroidTestCase {

	public void query1() {
		new SaveAsyncTest().insert3();

		Selector selector = new Selector().from(Admin.class);

		List<Admin> res = new Query(selector).excute(getContext());
		new Query(selector).excuteAsync(getContext(), new QueryCallback() {

			@SuppressWarnings("unchecked")
			@Override
			public void onFinish(Object result) {
				List<Admin> res = (List<Admin>) result;
				_.d(res.toString());

			}
		});

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
