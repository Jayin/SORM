package com.annotation.core;

import com.annotation.entity.Sqlable;
import com.annotation.entity.Wherable;
import com.annotation.utils.ReflectionUtils;

public class Deletor implements Sqlable ,Wherable<Deletor>{

	private String _talbe;
	private StringBuffer _where;

	public Deletor() {
		_where = new StringBuffer();
	}

	public Deletor from(Class<?> cls) {
		_talbe = ReflectionUtils.getTableName(cls);
		return this;
	}

	public Deletor where(String column, String operation, String value) {
		_where.append(column).append(" ");
		_where.append(operation).append(" ");
		_where.append("\"").append(value).append("\"").append(" ");
		return this;
	}

	public Deletor and() {
		_where.append("and").append(" ");
		return this;
	}

	public Deletor or() {
		_where.append("or").append(" ");
		return this;
	}
	
	@Override
	public String build() {
		StringBuffer builder = new StringBuffer();
		builder.append("Delete").append(" ");
		builder.append("From").append(" ").append(_talbe).append(" ");
		builder.append("Where").append(" ").append(_where);
		return builder.toString();
	}

}
