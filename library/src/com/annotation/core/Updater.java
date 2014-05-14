package com.annotation.core;

import java.lang.reflect.Field;
import com.annotation.entity.Sqlable;
import com.annotation.entity.Wherable;
import com.annotation.utils.ReflectionUtils;

public class Updater implements Sqlable, Wherable<Updater> {
	String _table;
	StringBuffer _where, _targetColumn;

	public Updater() {
		_where = new StringBuffer();
		_targetColumn = new StringBuffer();
	}

	public Updater update(Object obj) {
		_table = ReflectionUtils.getTableName(obj.getClass());
		Field[] fields = ReflectionUtils.getColumnFields(obj.getClass());
		for (Field field : fields) {
			try {
				_targetColumn.append(ReflectionUtils.getColumnName(field)).append(
						"=").append("\"").append(ReflectionUtils.invokeGetMethod(obj, field)).append("\"").append(","); 
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		if (_targetColumn.length() > 0)
			_targetColumn.deleteCharAt(_targetColumn.length() - 1);
		return this;
	}
	
	@Override
	public Updater where(String column, String operation, String value) {
		_where.append(column).append(" ");
		_where.append(operation).append(" ");
		_where.append("\"").append(value).append("\"").append(" ");
		return this;
	}

	@Override
	public Updater and() {
		_where.append("and").append(" ");
		return this;
	}
	
	@Override
	public Updater or() {
		_where.append("or").append(" ");
		return this;
	}

	@Override
	public String build() {
		StringBuffer builder = new StringBuffer();
		builder.append("Update").append(" ");
		builder.append(_table).append(" ");
		builder.append("Set").append(" ").append(_targetColumn).append(" ");
		builder.append("Where").append(" ").append(_where);
		return builder.toString();
	}
}
