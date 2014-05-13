package com.annotation.utils;

public class NameBuilder {

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
}