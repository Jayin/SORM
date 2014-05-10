package com.annotation.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.annotation.Ignore;
import com.annotation.entity.Sqlable;
import com.annotation.utils.ReflectionUtils;

public class Inserter implements Sqlable {
	// insert into talbe(column1,column2...) values("1","3")
	private StringBuffer _targetColumn, _values;
	private String _table;

	public Inserter() {
		_targetColumn = new StringBuffer();
		_values = new StringBuffer();
	}

	public Inserter insert(Object obj) {
		try {
			_table = ReflectionUtils.getTableName(obj.getClass());
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getAnnotation(Ignore.class) != null || ReflectionUtils.getTypeName(field)==null)
					continue;
				String columnName = ReflectionUtils.getColumnName(field);
				_targetColumn.append(columnName).append(",");
				String method_name = "get"
						+ columnName.substring(0, 1).toUpperCase()
						+ columnName.substring(1);
				// get the result return by getXXX();
				Method getter = ReflectionUtils.getMethod(obj.getClass(),
						method_name);
				try {
					Object _value = getter.invoke(obj);
					_values.append("\"").append(_value.toString()).append("\"")
							.append(",");
				} catch (NullPointerException e) {
					throw new IllegalStateException(
							"you should create the method:" + method_name
									+ "() in class " + _table);
				}

			}
			_targetColumn.deleteCharAt(_targetColumn.length() - 1);
			_values.deleteCharAt(_values.length() - 1);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return this;
	}

	public String getValue(Object value) {
		Class<?> c = value.getClass();
		return c.toString();
	}

	@Override
	public String build() {
		StringBuffer builder = new StringBuffer();
		builder.append("Insert Into").append(" ");
		builder.append(_table).append(" ");
		builder.append("(").append(_targetColumn).append(")").append(" ");
		builder.append("Values").append(" ");
		builder.append("(").append(_values).append(")").append(" ");
		return builder.toString();
	}

}
