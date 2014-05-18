package com.annotation.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.annotation.Column;
import com.annotation.Ignore;
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
	 * get Field by specified field name<br>
	 * <strong>it will search the field from current Class to Supper
	 * Class</strong>
	 * 
	 * @param cls
	 * @param method_name
	 * @return null if not found
	 */
	public static Field getDeclaredField(Class<?> cls, String fieldName) {
		try {
			Field field = cls.getDeclaredField(fieldName);
			return field;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			if (cls.getSuperclass() != null)
				return getDeclaredField(cls.getSuperclass(), fieldName);
		}
		return null;
	}

	/**
	 * get Method by specified method name<br>
	 * <strong>it will search the method from current Class to Supper
	 * Class</strong>
	 * 
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
		if (cls.getSuperclass() != null)
			return getMethod(cls.getSuperclass(), method_name);
		else
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

	/**
	 * get the fields that not wrap with @Ignore or<br>
	 * it's type of Integer Long Float Double or String
	 * 
	 * @param cls
	 * @return
	 */
	public static Field[] getColumnFields(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		List<Field> list = new ArrayList<Field>();
		for (Field field : fields) {
			if (field.getAnnotation(Ignore.class) == null
					&& ReflectionUtils.getTypeName(field) != null)
				list.add(field);
		}
		return (Field[]) list.toArray(new Field[] {});
	}

	/**
	 * invoke the getXXX()
	 * 
	 * @param obj
	 * @param method_name
	 *            getXX();
	 * @return the value ,mostly it's a String,can be null
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static String invokeGetMethod(Object obj, String method_name)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method getter = ReflectionUtils.getMethod(obj.getClass(), method_name);
		try {
			Object _value = getter.invoke(obj);// may cause NPE
			if (_value == null)
				return null;
			return _value.toString();
		} catch (NullPointerException e) {
			throw new IllegalStateException("you should create the method:"
					+ method_name + "() in class "
					+ obj.getClass().getSimpleName());
		}
	}

	/**
	 * invoke the getXXX() by giving field
	 * 
	 * @param obj
	 * @param field
	 * @return the value ,mostly it's a String
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static String invokeGetMethod(Object obj, Field field)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		return invokeGetMethod(obj, NameBuilder.buildGetter(field.getName()));
	}
}
