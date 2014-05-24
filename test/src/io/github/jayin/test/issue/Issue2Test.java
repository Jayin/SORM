package io.github.jayin.test.issue;

import io.github.jayin.test.entity.Student;

import java.util.List;

import android.test.AndroidTestCase;

import com.annotation.core.Deletor;
import com.annotation.core.Query;
import com.annotation.core.Selector;
import com.annotation.core.Updater;
import com.annotation.utils._;

public class Issue2Test extends AndroidTestCase {
	public static String injection = "jayin\" or 1=\"1";

	public void testDelete() {
		String sql = new Deletor().from(Student.class)
				.where("name", "=", injection).build();
		_.d("Issue2Test-->delete sql-->" + sql);
	}

	public void testSelect() {
		String sql = new Selector().from(Student.class)
				.where("name", "=", injection).build();
		_.d("Issue2Test-->select sql-->" + sql);
	}

	public void testUpdate() {
		Student s = new Student();
		String sql = new Updater().update(s).where("name", "=", injection)
				.build();
		_.d("Issue2Test-->Update sql-->" + sql);
	}

	public void testInsert() {
		Student s = new Student();
		s.setName("\\");
		s.save(getContext());

		List<Student> students = new Query(new Selector().from(Student.class))
				.excute(getContext());
		_.d("Issue2Test-->insert /query result-->"+students.toString());
		if (students.size() > 0) {
			_.d("Issue2Test-->insert sql-->"+students.get(0).toString());
		}
	}

	public void testAll() {
		testInsert();
		testDelete();
		testSelect();
		testUpdate();
	}
}
