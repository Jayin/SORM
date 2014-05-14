package com.annotation.test;

import android.test.AndroidTestCase;

import com.annotation.core.Creater;

public class CreaterTest extends AndroidTestCase{

	public void main(){
		System.out.println(new Creater().from(User.class).build());
	}

}
