package com.annotation.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import com.annotation.entity.Sqlable;
import com.annotation.utils.NameBuilder;
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
			Field[] fields = ReflectionUtils.getColumnFields(obj.getClass());
			for (Field field : fields) {
				String columnName = ReflectionUtils.getColumnName(field);
				_targetColumn.append(columnName).append(",");
				String method_name = NameBuilder.buildGetter(columnName);
				_values.append("\"").append(ReflectionUtils.invokeGetMethod(obj, method_name))
						.append("\"").append(",");
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
