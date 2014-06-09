package com.annotation.core;

import android.content.Context;

import com.annotation.entity.Sqlable;
import com.annotation.utils.DBUtils;
import com.annotation.utils.NameBuilder;
import com.annotation.utils.ReflectionUtils;

public class Droper implements Sqlable {
	/**
	 * true ->drop index<br>
	 * false->drop table
	 */
	private boolean drop;
	private String name;

	public Droper Index() {
		drop = true;
		return this;
	}

	public Droper Table() {
		drop = false;
		return this;
	}

	public Droper() {

	}

	public Droper from(Class<?> cls) {
		name = ReflectionUtils.getTableName(cls);
		return this;
	}

	@Override
	public String build() {
		StringBuffer builder = new StringBuffer();
		builder.append("Drop ");
		// drop index
		if (drop) {
			builder.append(" Index ");
			builder.append(" If Exists ");
			builder.append(NameBuilder.buildIndex(name));
		} else {
			builder.append(" Table ");
			builder.append(" If Exists ");
			builder.append(name);
		}
		return builder.toString();
	}

	public void excute(Context context) {
		try {
			DBUtils.execSQL(context, build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
