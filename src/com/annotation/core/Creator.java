package com.annotation.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.annotation.Column;
import com.annotation.Ignore;
import com.annotation.PrimaryKey;
import com.annotation.Unique;
import com.annotation.entity.ColumnInfo;
import com.annotation.entity.Sqlable;
import com.annotation.utils.ReflectionUtils;
import com.annotation.utils._;

public class Creator implements Sqlable{
	private String _table;
	List<ColumnInfo> columnInfos;

	public Creator() {
		 columnInfos = new ArrayList<ColumnInfo>();
	}

	public Creator from(Class<?> cls) {
		_table = ReflectionUtils.getTableName(cls);
		columnInfos = buildColumns(cls);
		return this;
	}

	private List<ColumnInfo> buildColumns(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		List<ColumnInfo> list = new ArrayList<ColumnInfo>();
		for (Field field : fields) {
			if (field.getAnnotation(Ignore.class) == null) {
				String typeName = getTypeName(field);
				if (typeName != null) {
					String columnName = getColumnName(field);
					StringBuffer constraint = getConstraints(field);
					ColumnInfo info = new ColumnInfo();
					info.setColumnName(columnName);
					info.setTypeName(typeName);
					info.setConstraint(constraint);
					list.add(info);
				}
			}
		}
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

	/**
	 * return null if not support that data.<br>
	 * return null means that you can't save it in current version<br>
	 * only support int long float double string
	 * 
	 * @param field
	 * @return
	 */
	private String getTypeName(Field field) {
		Class<?> type = field.getType();
		if (type.equals(Long.class) || type.equals(Integer.class)
				|| type.equals(int.class) || type.equals(long.class)) {
			return "INTEGER";
		} else if (type.equals(Float.class) || type.equals(Double.class)
				|| type.equals(float.class) || type.equals(double.class)) {
			return "REAL";
		} else if (type.equals(String.class)) {
			return "TEXT";
		} else {
			return null;
		}
	}

	/**
	 * get Column from @Column.name() (trying to) default the `field` name if
	 * not exist @Column or not add @Column.name()
	 * 
	 * @param field
	 * @return
	 */
	private String getColumnName(Field field) {
		Column c = field.getAnnotation(Column.class);
		if (c == null || _.isEmpty(c.name())) {
			return field.getName();
		} else {
			return c.name();
		}
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
