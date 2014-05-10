package com.annotation.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.annotation.Column;
import com.annotation.Table;

public class ReflectionUtils {
	/**
	 * get the @Table name of this class<br>
	 * 
	 * @param cls
	 * @return
	 */
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

	/**
	 * get Column from @Column.name() (trying to) default the `field` name if
	 * not exist @Column or not add @Column.name()
	 * 
	 * @param field
	 * @return
	 */
	public static String getColumnName(Field field) {
		Column c = field.getAnnotation(Column.class);
		if (c == null || _.isEmpty(c.name())) {
			return field.getName();
		} else {
			return c.name();
		}
	}

	/**
	 * get Method by specified method name
	 * @param cls
	 * @param method_name
	 * @return null if not found
	 */
	public static Method getMethod(Class<?> cls, String method_name) {
		Method[] methods = cls.getDeclaredMethods();
		for (Method m : methods) {
			if (m.getName().equals(method_name)) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * return null if not support that data.<br>
	 * return null means that you can't save it in current version<br>
	 * only support int long float double string
	 * 
	 * @param field
	 * @return
	 */
	public static String getTypeName(Field field) {
		Class<?> type = field.getType();
		if (type.equals(Long.class) || type.equals(Integer.class)
				|| type.equals(int.class) || type.equals(long.class)) {
			return "INTEGER";
		} else if (type.equals(Float.class) || type.equals(Double.class)
				|| type.equals(float.class) || type.equals(double.class)) {
			return "REAL";
		} else if (type.equals(String.class)) {
			return "TEXT";
		} else {
			return null;
		}
	}
}
