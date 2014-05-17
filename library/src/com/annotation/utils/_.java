package com.annotation.utils;

import android.util.Log;

public class _ {
	private static final String TAG = "debug";
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

	public static void d(String s) {
		Log.d(TAG, s);
	}

}
