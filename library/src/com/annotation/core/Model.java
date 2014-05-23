package com.annotation.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.annotation.Ignore;
import com.annotation.PrimaryKey;
import com.annotation.utils.DBHelper;
import com.annotation.utils.DBUtils;
import com.annotation.utils._;

public class Model {

	@Ignore
	private Context context;

	@PrimaryKey
	private Long __id = null;

	public void save(Context context) {
		SQLiteDatabase db = null;
		String sql = null;
		try {
			db = new DBHelper(context).getWritableDatabase();
			if (__id == null) {
				// insert
				sql = new Inserter().insert(this).build();
			} else {
				// update
				sql = new Updater().update(this).where("__id", "=", __id + "")
						.build();
			}
//			db.beginTransaction();
			DBUtils.createTable(db, this.getClass());
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) {
//				db.endTransaction();
				db.close();
			}
		}
	}

	public void delete(Context context) {
		if (this.__id == null)
			return;
		SQLiteDatabase db = null;
		String sql = null;
		try {
			db = new DBHelper(context).getWritableDatabase();
			sql = new Deletor().from(this.getClass())
					.where("__id", "=", String.valueOf(__id)).build();
			_.d("delete -->sql:"+sql);
//			db.beginTransaction();
			DBUtils.createTable(db, this.getClass());
			db.execSQL(sql);
			_.d("delete ok");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) {
//				db.endTransaction();
				db.close();
			}

		}

	}

	public Long get__id() {
		return __id;
	}

	public void set__id(Long __id) {
		this.__id = __id;
	}

	@Override
	public String toString() {
		return "Model [__id=" + __id + "]";
	}
}
