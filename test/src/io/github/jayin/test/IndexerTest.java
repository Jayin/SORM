package io.github.jayin.test;

import io.github.jayin.test.entity.Animal;
import io.github.jayin.test.entity.User;
import android.test.AndroidTestCase;

import com.annotation.core.Creator;
import com.annotation.core.Indexer;
import com.annotation.utils._;

public class IndexerTest extends AndroidTestCase {

	public void createIndexTest(Class<?> cls) {
		String sql = new Indexer().unique(true).from(cls)
				.where("id = 1").build();
		_.d(sql);
		//mostly
		sql =  new Indexer().from(cls).build();
		_.d(sql);
	}

	public void createTable(Class<?> cls) {
		String sql = new Creator().from(cls).build();
		_.d(sql);
	}

	public void testAll() {
		createTable(Animal.class);
		createIndexTest(Animal.class);
	}
	
	public void createIndexAfterTable(){
		User u = new User();
		u.setAge(1);
		u.setName("jayin");
		u.setUserid(3112002722L);
		u.save(getContext());
		_.d("test finished");
	}

}
