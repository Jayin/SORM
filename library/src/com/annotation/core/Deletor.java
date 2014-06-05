package com.annotation.core;

import com.annotation.entity.WhereImpl;
import com.annotation.utils.ReflectionUtils;

public class Deletor extends WhereImpl<Deletor>{

	private String _talbe;

	public Deletor() {
		super();
	}

	public Deletor from(Class<?> cls) {
		_talbe = ReflectionUtils.getTableName(cls);
		return this;
	}

	@Override
	public String build() {
		StringBuffer builder = new StringBuffer();
		builder.append("Delete").append(" ");
		builder.append("From").append(" ").append(_talbe).append(" ");
		if (hasWhere()){
			builder.append("Where").append(" ").append(getWhere());
		}
		return builder.toString();
	}

}
