package com.annotation.core;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.annotation.Ignore;
import com.annotation.PrimaryKey;
import com.annotation.utils.DBHelper;
import com.annotation.utils.ReflectionUtils;

public class Model {

	@Ignore
	private Context context;

	@PrimaryKey
	private Long __id = null;

	public void save(Context context) {
		SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
		try {
			String sql = null;
			if (__id == null) {
				// insert
				sql = new Inserter().insert(this).build();
			} else {
				// update
				sql = new Updater().update(this).where("__id", "=", __id + "")
						.build();
			}
			createTable(db, this.getClass());
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * create table if not exist
	 * 
	 * @param db
	 * @param cls
	 */
	private void createTable(SQLiteDatabase db, Class<? extends Model> cls) {
		String sql = "select * from sqlite_master where type =\"table\" and name = \""
				+ ReflectionUtils.getTableName(cls) + "\"";
		Cursor cursor = db.rawQuery(sql, null);
		boolean isExist = false;
		if (cursor.moveToNext()) {
			isExist = true;
		}
		if (!isExist) {
			sql = new Creater().from(cls).build();
			db.execSQL(sql);
		}
	}

	public void delete(Context context) {

	}

	public Long get__id() {
		return __id;
	}

	public void set__id(Long __id) {
		this.__id = __id;
	}

}
