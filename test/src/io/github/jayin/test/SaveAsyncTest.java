package io.github.jayin.test;

import io.github.jayin.test.entity.Admin;
import io.github.jayin.test.entity.User;

import java.util.List;

import android.test.AndroidTestCase;

import com.annotation.core.Query;
import com.annotation.core.Selector;
import com.annotation.entity.ORMcallback;
import com.annotation.utils._;

public class SaveAsyncTest extends AndroidTestCase {
	long total = 0;
	public void insert1() {
		User u1 = new User();
		u1.setAge(12);
		u1.setName("Jack");
		u1.setUserid(3112002720L);
		u1.saveAsync(getContext(), new ORMcallback() {

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

	// 插入3个
	public void insert2() {
		final Admin a1 = new Admin();
		a1.setAdminName("a111");
		a1.setId(1);
		a1.saveAsync(getContext(), new ORMcallback() {

			@Override
			public void onFinish() {
				_.d("onFinish");
			}

			@Override
			public void onFaild() {
				_.d("onFaild");
			}
		});
		final Admin a2 = new Admin();
		a2.setAdminName("a222");
		a2.setId(2);
		a2.saveAsync(getContext(), new ORMcallback() {

			@Override
			public void onFinish() {
				_.d("onFinish");
			}

			@Override
			public void onFaild() {
				_.d("onFaild");
			}
		});
		final Admin a3 = new Admin();
		a3.setAdminName("a333");
		a3.setId(3);
		a3.saveAsync(getContext(), new ORMcallback() {

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
	
	private synchronized void addTime(long cost){
		_.d("cost-->"+cost);
		total += cost;
		_.d("total="+total);
		_.d("active thread:"+Thread.activeCount());
	}
	
	public void insert3(){
		for(int i=0;i<100;i++){
			Admin a = new Admin();
			a.setAdminName("name"+i);
			a.setId(i);
			final long start  =System.currentTimeMillis();
			a.saveAsync(getContext(),  new ORMcallback() {

				@Override
				public void onFinish() {
					long end = System.currentTimeMillis();
					_.d("onFinish");
					addTime(end - start);
				}

				@Override
				public void onFaild() {
					_.d("onFaild");
				}
			});
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void delete1() {
		Selector selector = new Selector().from(User.class);
		List<User> users = new Query(selector).excute(getContext());
		for (User u : users) {
			u.deleteAsync(getContext(), new ORMcallback() {

				@Override
				public void onFinish() {
					_.d("onFinish");

				}

				@Override
				public void onFaild() {
					_.d("onFaild");

				}
			});
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 删除3个
	public void delete2() {
		Selector selector = new Selector().from(Admin.class);
		List<Admin> admins = new Query(selector).excute(getContext());
		for (Admin a : admins) {
			a.deleteAsync(getContext(), new ORMcallback() {

				@Override
				public void onFinish() {
					_.d("onFinish");

				}

				@Override
				public void onFaild() {
					_.d("onFaild");

				}
			});
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
