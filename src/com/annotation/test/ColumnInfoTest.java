package com.annotation.test;

import com.annotation.core.ColumnInfo;

public class ColumnInfoTest {

	
	public static void main(String[] args) {
		 ColumnInfo info = new ColumnInfo();
		 info.setColumnName("age");
		 info.setTypeName("number");
		 info.addConstraint("no null");
		 info.addConstraint("between 10 and 20");
		 System.out.println("Column define-->"+info.build());

	}
}
