package com.annotation.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		_where.append("\"").append(value.replaceAll("\\\"", "\\\\\"")).append("\"").append(" ");
		return this;
	}
	
	//只支持单条例如：id = 1  不能: id = 1 and age=18
	public Deletor where(String expression){
		Pattern p = Pattern.compile("(\\S+)\\s*([!<>=]+)\\s*(\\S+)");
		Matcher m = p.matcher(expression);
		if(m.matches()){
			return where(m.group(1),m.group(2),m.group(3));
		}
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
