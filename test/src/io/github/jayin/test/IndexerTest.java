package io.github.jayin.test;

import io.github.jayin.test.entity.Animal;

import com.annotation.core.Creater;
import com.annotation.core.Indexer;
import com.annotation.utils._;

import android.test.AndroidTestCase;

public class IndexerTest extends AndroidTestCase {

	public void createIndexTest() {
		String sql = new Indexer().unique(true).from(Animal.class)
				.where("id = 1").build();
		_.d(sql);
		//mostly
		sql =  new Indexer().from(Animal.class).build();
		_.d(sql);
	}

	public void createTable() {
		String sql = new Creater().from(Animal.class).build();
		_.d(sql);
	}

	public void testAll() {
		createTable();
		createIndexTest();
	}

}
