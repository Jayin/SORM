package io.github.jayin.test2;

import java.util.List;

import android.content.Context;

import com.annotation.Unique;
import com.annotation.core.Creator;
import com.annotation.core.Model;

public class CreatorTest extends BaseTest{

	public CreatorTest(Context context) {
		super(context);
	}

	@Override
	protected void testQueue() throws Exception {
		create_test1();
		
	}
	
	public void create_test1() throws Exception{
		String sql =new Creator().from(Student.class).build();
		if(!sql.equals("Create Table Student (name TEXT ,id INTEGER Unique,age INTEGER ,height INTEGER ,wight INTEGER ,__id INTEGER )")){
			throw new Exception("CreatorTest Error");
		}
	    
	}
	 
}

class Student extends Human {
	@Unique
	long id;
	
	int age;
	
	String name;

	List<Integer> friendid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getFriendid() {
		return friendid;
	}

	public void setFriendid(List<Integer> friendid) {
		this.friendid = friendid;
	}

}

class Human extends Model {

	int height;

	int wight;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWight() {
		return wight;
	}

	public void setWight(int wight) {
		this.wight = wight;
	}

}
