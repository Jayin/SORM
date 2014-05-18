package com.annotation.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.database.Cursor;

public class DBUtils {
	/**
	 * genearate a entity from Cursor
	 * @param cursor
	 * @param _entity
	 * @return null if parse error
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cursor2Entity(Cursor cursor, Class<?> _entity) {
		T entity = null;
		try {
			entity = (T) _entity.newInstance();
			for (int i = 0; i < cursor.getColumnCount(); i++) {
				String val = cursor.getString(i);
				// 提取属性
				Field field =ReflectionUtils.getDeclaredField(_entity, cursor.getColumnName(i));
				// 提取属性类型
				String fieldType = field.getType().getName();
				Method setter = ReflectionUtils.getMethod(_entity,
						NameBuilder.buildSetter(field.getName()));
				// 数据转换并赋值
				if (fieldType.equals("int")) {
					setter.invoke(entity, Integer.parseInt(val));
					
				} else if (fieldType.equals(Integer.class.getName())) {
					setter.invoke(entity, Integer.valueOf(val));
					
				} else if (fieldType.equals("long")) {
					setter.invoke(entity, Long.parseLong(val));
					
				} else if (fieldType.equals(Long.class.getName())) {
					setter.invoke(entity, Long.valueOf(val));
					
				} else if (fieldType.equals("float")) {
					setter.invoke(entity, Float.parseFloat(val));
					
				} else if (fieldType.equals(Float.class.getName())) {
					setter.invoke(entity, Float.valueOf(val));
					
				}else if (fieldType.equals("double")) {
					setter.invoke(entity, Double.parseDouble(val));
					
				} else if (fieldType.equals(Double.class.getName())) {
					setter.invoke(entity, Double.valueOf(val));
					
				}else{
					//String
					setter.invoke(entity, val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return entity;
	}

}
