package com.annotation.core;

import com.annotation.entity.Sqlable;
import com.annotation.entity.Wherable;
import com.annotation.utils.ReflectionUtils;

public class Selecter implements Sqlable ,Wherable<Selecter>{
	String _table;
	int __limit, __offset;
	boolean _distinct, _all;
	StringBuffer _where, _groupBy, _orderBy, _resultColumn;

	public Selecter(String... resultColumn) {
		_resultColumn = new StringBuffer();
		_where = new StringBuffer();
		_groupBy = new StringBuffer();
		_orderBy = new StringBuffer();
		__limit = -1;
		__offset = -1;
		all();
		for (String c : resultColumn) {
			_resultColumn.append(c).append(",");
		}
		if(_resultColumn.length()>0){
			_resultColumn.deleteCharAt(_resultColumn.length()-1);
		}
	}

	public Selecter from(Class<?> cls) {
		_table = ReflectionUtils.getTableName(cls);
		return this;
	}

	public Selecter distinct() {
		_distinct = true;
		_all = false;
		return this;
	}

	public Selecter all() {
		_distinct = false;
		_all = true;
		return this;
	}

	public Selecter where(String column, String operation, String value) {
		_where.append(column).append(" ");
		_where.append(operation).append(" ");
		_where.append(value).append(" ");
		return this;
	}

	public Selecter and() {
		_where.append("and").append(" ");
		return this;
	}

	public Selecter or() {
		_where.append("or").append(" ");
		return this;
	}

	public Selecter groupBy(String... columns) {
		for (String c : columns) {
			_groupBy.append(c).append(",");
		}
		if(_groupBy.length()>0){
			_groupBy.deleteCharAt(_groupBy.length()-1).append(" ");
		}
		return this;
	}

	public Selecter orderBy(String... columns) {
		for (String c : columns) {
			_orderBy.append(c).append(",");
		}
		if(_orderBy.length()>0){
			_orderBy.deleteCharAt(_orderBy.length()-1).append(" ");
		}
		return this;
	}

	public Selecter limit(int _limit) {
		__limit = _limit;
		return this;
	}

	public Selecter offset(int _offset) {
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
		if(_resultColumn.length()>0){
			builder.append(_resultColumn).append(" ");
		}else{
			builder.append("*").append(" ");
		}
		
		builder.append("From").append(" ").append(_table).append(" ");
		if (_where.length() > 0)
			builder.append("Where").append(" ").append(_where).append(" ");

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

}
