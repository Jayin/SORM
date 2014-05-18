package com.annotation.entity;

public class ColumnInfo {
	/** 列名**/
	private String columnName;
	/** 类型名 **/
	private String typeName;
	/** 约束**/
	private StringBuffer constraint = new StringBuffer();

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public StringBuffer getConstraint() {
		return constraint;
	}

	public void setConstraint(StringBuffer constraint) {
		this.constraint = constraint;
	}

	public void addConstraint(String newConstraint) {
		this.constraint.append(newConstraint).append(" ");
	}

	public String build() {
		if(constraint.length()>0)constraint.deleteCharAt(constraint.length() - 1);
		return columnName + " " + typeName + " " + constraint.toString();
	}
}
