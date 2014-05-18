package io.github.jayin.test;

import io.github.jayin.test.entity.Animal;

import java.util.List;

import android.test.AndroidTestCase;

import com.annotation.core.Query;
import com.annotation.core.Selector;
import com.annotation.utils._;

public class Issue7Test extends AndroidTestCase {

	public void test() {
		Selector selector = new Selector().from(Animal.class);
		List<Animal> animals = new Query(selector).excute(getContext());
		_.d(animals.toString());
	}
}
