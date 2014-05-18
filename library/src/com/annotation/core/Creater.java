package com.annotation.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.annotation.PrimaryKey;
import com.annotation.Unique;
import com.annotation.entity.ColumnInfo;
import com.annotation.entity.Sqlable;
import com.annotation.utils.ReflectionUtils;
import com.annotation.utils._;

public class Creater implements Sqlable {
	private String _table;
	List<ColumnInfo> columnInfos;

	public Creater() {
		columnInfos = new ArrayList<ColumnInfo>();
	}

	public Creater from(Class<?> cls) {
		_table = ReflectionUtils.getTableName(cls);
		columnInfos = buildColumns(cls);
		return this;
	}

	private List<ColumnInfo> buildColumns(Class<?> cls) {
		Field[] fields = ReflectionUtils.getColumnFields(cls);
		List<ColumnInfo> list = new ArrayList<ColumnInfo>();
		for (Field field : fields) {
			String typeName = ReflectionUtils.getTypeName(field);
			String columnName = ReflectionUtils.getColumnName(field);
			StringBuffer constraint = getConstraints(field);
			ColumnInfo info = new ColumnInfo();
			info.setColumnName(columnName);
			info.setTypeName(typeName);
			info.setConstraint(constraint);
			list.add(info);
		}
		if(!cls.getSuperclass().equals(Object.class))
			list.addAll(buildColumns(cls.getSuperclass()));
		return list;

	}

	// expand in here
	private StringBuffer getConstraints(Field field) {
		StringBuffer sb = new StringBuffer();
		Annotation[] annotations = field.getAnnotations();
		for (Annotation a : annotations) {
			if (a.annotationType().equals(Unique.class)) {
				sb.append(a.annotationType().getSimpleName()).append(" ");
			} else if (a.annotationType().equals(PrimaryKey.class)) {
				sb.append("Primary Key").append(" ");
				if (field.getAnnotation(PrimaryKey.class).autoIncrement()) {
					sb.append("AUTOINCREMENT").append(" ");
				}
			}
		}
		// sb.deleteCharAt(sb.length()); //多个空格也ok
		return sb;
	}

	public String build() {
		if (columnInfos.size() == 0) {
			throw new IllegalStateException(
					"there are no columns define for this table.");
		}
		StringBuffer builder = new StringBuffer();
		builder.append("Create Table").append(" ").append(_table).append(" ");
		builder.append("(");
		for (ColumnInfo info : columnInfos) {
			builder.append(info.build()).append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
}
