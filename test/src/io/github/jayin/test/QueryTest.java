package io.github.jayin.test;

import io.github.jayin.test.entity.User;

import java.util.List;

import android.test.AndroidTestCase;

import com.annotation.core.Query;
import com.annotation.core.Selector;
import com.annotation.utils._;

public class QueryTest extends AndroidTestCase {

	public void insert(){
		User u  =new User();
		u.setAge(18);
		u.setName("Mars");
		u.setUserid(1);
		u.save(getContext());
		
		u.setName("Jack");
		u.setUserid(2);
		u.save(getContext());
		
		u.setName("Mark");
		u.setUserid(3);
		u.save(getContext());
		
		u.setName("Waton");
		u.setUserid(4);
		u.save(getContext());
	}
	
	public void query1(){
		Selector selector = new Selector().from(User.class);
		List<User> users = new Query(selector).excute(getContext());
		_.d(users.toString());
	}
	
	public void query2(){
		Selector selector = new Selector().from(User.class).where("userid", "=", "1");
		List<User> users = new Query(selector).excute(getContext());
		_.d(users.toString());
	}
}
