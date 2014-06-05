package com.annotation.core;

import java.lang.reflect.Field;

import com.annotation.entity.WhereImpl;
import com.annotation.utils.ReflectionUtils;

public class Updater extends WhereImpl<Updater> {
	String _table;
	StringBuffer _targetColumn;

	public Updater() {
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
	public String build() {
		StringBuffer builder = new StringBuffer();
		builder.append("Update").append(" ");
		builder.append(_table).append(" ");
		builder.append("Set").append(" ").append(_targetColumn).append(" ");
		if(hasWhere())
			builder.append("Where").append(" ").append(getWhere());
		return builder.toString();
	}
}
