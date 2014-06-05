package com.annotation.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unchecked")
public abstract class WhereImpl<T> implements Wherable<T> {

	private StringBuffer _where;

	public WhereImpl() {
		_where = new StringBuffer();
	}

	@Override
	public T where(String column, String operation, String value) {
		_where.append(column).append(" ");
		_where.append(operation).append(" ");
		_where.append("\"").append(value.replaceAll("\\\"", "\\\\\""))
				.append("\"").append(" ");
		return (T) this;
	}

	// 只支持单条例如：id = 1 不能: id = 1 and age=18
	@Override
	public T where(String expression) {
		Pattern p = Pattern.compile("(\\S+)\\s*([!<>=]+)\\s*(\\S+)");
		Matcher m = p.matcher(expression);
		if (m.matches()) {
			return where(m.group(1), m.group(2), m.group(3));
		}
		return (T) this;
	}

	@Override
	public T and() {
		_where.append("and").append(" ");
		return (T) this;
	}

	@Override
	public T or() {
		_where.append("or").append(" ");
		return (T) this;
	}

	public StringBuffer getWhere() {
		return this._where;
	}

	public boolean hasWhere() {
		return this._where.length() > 0;
	}
}
