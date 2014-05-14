package com.annotation.utils;

public class _ {
	/**
	 * Returns true if the string is null or 0-length.
	 * 
	 * @return
	 */
	public static boolean isEmpty(CharSequence str) {
		if (str == null || str.length() == 0)
			return true;
		return false;
	}

	public static boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void o(CharSequence cs) {
		System.out.println(cs);
	}

}
