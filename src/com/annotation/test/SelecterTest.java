package com.annotation.test;

import com.annotation.core.Selecter;

public class SelecterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sql = new Selecter("id","title","content") //the result columns. select all(*) when nothing here
					.from(Database.class)  //table
					.distinct() // all() or distinct()
					.where("showFlag","=", 1 + "")   //`where` expression
					.and().where("version", "!=", "0")
					.groupBy("id","title")  //
					.orderBy("id")
					.limit(10)
					.offset(10)
					.build();
		System.out.println(sql);
	}

}