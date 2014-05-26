package com.annotation.core;

import java.lang.reflect.Field;

import com.annotation.Index;
import com.annotation.entity.Sqlable;
import com.annotation.utils.ReflectionUtils;
import com.annotation.utils._;

public class Indexer implements Sqlable {
	private String _indexName;
	private String _talbe;
	private boolean _unique = false;
	private StringBuffer _indexedColumns;// index name equals column name
	private String _expression = null;

	public Indexer() {
		_indexedColumns = new StringBuffer();
	}

	public Indexer unique(boolean isUnique) {
		_unique = isUnique;
		return this;
	}

	public Indexer from(Class<?> cls) {
		_talbe = ReflectionUtils.getTableName(cls);
		_indexName = "index_" + _talbe;
		Field[] fields = ReflectionUtils.getColumnFields(cls);
		for (Field f : fields) {
			if (f.getAnnotation(Index.class) != null)
				_indexedColumns.append(f.getName()).append(",");
		}
		if (_indexedColumns.length() > 0)
			_indexedColumns.deleteCharAt(_indexedColumns.length() - 1);
		return this;
	}

	public Indexer where(String expression) {
		_expression = expression;
		return this;
	}

	@Override
	public String build() {
		StringBuffer builder = new StringBuffer();
		if (_unique) {
			builder.append("Create Unique Index ");
		} else {
			builder.append("Create Index ");
		}
		builder.append(_indexName).append(" on ").append(_talbe);
		builder.append(" (").append(_indexedColumns).append(") ");
		if (!_.isEmpty(_expression))
			builder.append(" Where ").append(_expression);
		return builder.toString();
	}

}
