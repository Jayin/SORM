package com.annotation.core;

import java.lang.reflect.Field;

import com.annotation.entity.WhereImpl;
import com.annotation.utils.ReflectionUtils;

public class Selector extends WhereImpl<Selector>{
	Class<?> _entity;
	String _table;
	int __limit, __offset;
	boolean _distinct, _all;
	StringBuffer  _groupBy, _orderBy, _resultColumn;

	public Selector(String... resultColumn) {
		super();
		_resultColumn = new StringBuffer();
		_groupBy = new StringBuffer();
		_orderBy = new StringBuffer();
		__limit = -1;
		__offset = -1;
		all();
		for (String c : resultColumn) {
			_resultColumn.append(c).append(",");
		}
		if (_resultColumn.length() > 0) {
			_resultColumn.deleteCharAt(_resultColumn.length() - 1);
		}
	}

	public Selector from(Class<?> cls) {
		_table = ReflectionUtils.getTableName(cls);
		_entity = cls;
		return this;
	}

	public Selector distinct() {
		_distinct = true;
		_all = false;
		return this;
	}

	public Selector all() {
		_distinct = false;
		_all = true;
		return this;
	}

	public Selector groupBy(String... columns) {
		for (String c : columns) {
			_groupBy.append(c).append(",");
		}
		if (_groupBy.length() > 0) {
			_groupBy.deleteCharAt(_groupBy.length() - 1).append(" ");
		}
		return this;
	}

	public Selector orderBy(String... columns) {
		for (String c : columns) {
			_orderBy.append(c).append(",");
		}
		if (_orderBy.length() > 0) {
			_orderBy.deleteCharAt(_orderBy.length() - 1).append(" ");
		}
		return this;
	}

	public Selector limit(int _limit) {
		__limit = _limit;
		return this;
	}

	public Selector offset(int _offset) {
		__offset = _offset;
		return this;
	}

	@Override
	public String build() {
		StringBuffer builder = new StringBuffer();
		builder.append("Select").append(" ");
		if (_distinct) {
			builder.append("Distinct").append(" ");
		} else {
			builder.append("All").append(" ");
		}
		
		if (_resultColumn.length() == 0) {
			for (Field f : ReflectionUtils.getColumnFields(_entity)) {
				_resultColumn.append(f.getName()).append(",");
			}
			if (_resultColumn.length() > 0) {
				_resultColumn.deleteCharAt(_resultColumn.length() - 1);
			}
		}
		builder.append(_resultColumn).append(" ");

		builder.append("From").append(" ").append(_table).append(" ");
		if(hasWhere())
			builder.append("Where").append(" ").append(getWhere()).append(" ");

		if (_groupBy.length() > 0)
			builder.append("Group By").append(" ").append(_groupBy).append(" ");

		if (_orderBy.length() > 0)
			builder.append("Order By").append(" ").append(_orderBy).append(" ");

		if (__limit > 0)
			builder.append("Limit").append(" ").append(__limit).append(" ");

		if (__offset > 0)
			builder.append("Offset").append(" ").append(__offset).append(" ");
		return builder.toString();
	}

	public Class<?> getEntity() {
		return _entity;
	}

}
