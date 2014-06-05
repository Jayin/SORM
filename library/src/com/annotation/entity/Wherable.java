package com.annotation.entity;

public interface Wherable<T> extends Sqlable{
	public T where(String column, String operation, String value);
	
	public T where(String expression);

	public T and();

	public T or();
}
