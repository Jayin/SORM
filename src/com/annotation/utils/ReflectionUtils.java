package com.annotation.utils;

import com.annotation.Table;

public class ReflectionUtils {
	public static String getTableName(Class<?> cls) {
		if (cls.getAnnotation(Table.class) != null) {
			if (_.isEmpty(cls.getAnnotation(Table.class).name())) {
				return cls.getSimpleName();
			} else {
				return cls.getAnnotation(Table.class).name();
			}
		} else {
			return cls.getSimpleName();
		}
	}
}
