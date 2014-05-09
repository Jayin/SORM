package com.annotation.entity;

public interface Wherable<T> {
	public T where(String column, String operation, String value);

	public T and();

	public T or();
}
