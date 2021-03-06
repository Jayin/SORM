package com.annotation.utils;



public class NameBuilder {
	/**
	 * build Index
	 * @param tableName
	 * @return index_tableName
	 */
	public static String buildIndex(String tableName){
		return "index_"+tableName;
	}

	/**
	 * build the getter method name<br>
	 * e.g. if the Field is age ,it return 'getAge'
	 * @param fieldName
	 * @return
	 */
	public static String buildGetter(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}
	/**
	 * build the setter method name<br>
	 * e.g. if the Field is age ,it return 'setAge'
	 * @param fieldName
	 * @return
	 */
	public static String buildSetter(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}
}
