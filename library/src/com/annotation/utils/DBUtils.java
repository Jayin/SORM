package com.annotation.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.annotation.core.Creater;
import com.annotation.core.Indexer;

public class DBUtils {
	/**
	 * genearate a entity from Cursor
	 * 
	 * @param cursor
	 * @param _entity
	 * @return null if parse error
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cursor2Entity(Cursor cursor, Class<?> _entity) {
		T entity = null;
		try {
			entity = (T) _entity.newInstance();
			for (int i = 0; i < cursor.getColumnCount(); i++) {
				String val = cursor.getString(i);
				// 提取属性
				Field field = ReflectionUtils.getDeclaredField(_entity,
						cursor.getColumnName(i));
				// 提取属性类型
				String fieldType = field.getType().getName();
				Method setter = ReflectionUtils.getMethod(_entity,
						NameBuilder.buildSetter(field.getName()));
				// 数据转换并赋值
				if (fieldType.equals("int")) {
					setter.invoke(entity, Integer.parseInt(val));

				} else if (fieldType.equals(Integer.class.getName())) {
					setter.invoke(entity, Integer.valueOf(val));

				} else if (fieldType.equals("long")) {
					setter.invoke(entity, Long.parseLong(val));

				} else if (fieldType.equals(Long.class.getName())) {
					setter.invoke(entity, Long.valueOf(val));

				} else if (fieldType.equals("float")) {
					setter.invoke(entity, Float.parseFloat(val));

				} else if (fieldType.equals(Float.class.getName())) {
					setter.invoke(entity, Float.valueOf(val));

				} else if (fieldType.equals("double")) {
					setter.invoke(entity, Double.parseDouble(val));

				} else if (fieldType.equals(Double.class.getName())) {
					setter.invoke(entity, Double.valueOf(val));

				} else {
					// String
					setter.invoke(entity, val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return entity;
	}

	/**
	 * create table if not exist<br>
	 * create index automatically after creating table
	 * 
	 * @param db
	 * @param cls
	 * @throws Exception
	 */
	public static void createTable(SQLiteDatabase db, Class<?> cls)
			throws Exception {
		try {
			String sql = "select * from sqlite_master where type =\"table\" and name = \""
					+ ReflectionUtils.getTableName(cls) + "\"";
			Cursor cursor = db.rawQuery(sql, null);
			boolean isExist = false;
			if (cursor.moveToNext()) {
				isExist = true;
			}
			if (!isExist) {
				// create table
				sql = new Creater().from(cls).build();
				db.execSQL(sql);
				// create index
				sql = new Indexer().from(cls).build();
				if (sql != null)
					db.execSQL(sql);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * execute a sql with transaction
	 * 
	 * @param context
	 * @param sql
	 * @throws SQLException
	 */
	public static void execSQL(Context context, String sql) throws SQLException {
		SQLiteDatabase db = null;
		try {
			db = new DBHelper(context).getReadableDatabase();
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (db != null) {
				db.endTransaction();
				db.close();
			}
		}
	}
}
