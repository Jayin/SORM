package com.annotation.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.annotation.Ignore;
import com.annotation.PrimaryKey;
import com.annotation.entity.ORMcallback;
import com.annotation.utils.DBHelper;
import com.annotation.utils.DBUtils;
import com.annotation.utils._;

public class Model {
	private static Object lock = new Object();

	@Ignore
	private Context context;

	@PrimaryKey
	private Long __id = null;

	private boolean done = false;

	public void save(Context context) {
		synchronized (lock) {
			SQLiteDatabase db = null;
			String sql = null;
			try {
				db = new DBHelper(context).getWritableDatabase();
				if (__id == null) {
					// insert
					sql = new Inserter().insert(this).build();
				} else {
					// update
					sql = new Updater().update(this)
							.where("__id", "=", __id + "").build();
				}
				db.beginTransaction();
				DBUtils.createTable(db, this.getClass());
				db.execSQL(sql);
				db.setTransactionSuccessful();
				done = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.endTransaction();
					db.close();
				}
			}
		}

	}

	public void saveAsync(final Context context, final ORMcallback callback) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Model.this.save(context);
				if (callback != null) {
					if (done)
						callback.onFinish();
					else
						callback.onFaild();
				}
			}
		}).start();
	}

	public void delete(Context context) {
		synchronized (lock) {
			if (this.__id == null)
				return;
			SQLiteDatabase db = null;
			String sql = null;
			try {
				db = new DBHelper(context).getWritableDatabase();
				sql = new Deletor().from(this.getClass())
						.where("__id", "=", String.valueOf(__id)).build();
				db.beginTransaction();
				DBUtils.createTable(db, this.getClass());
				db.execSQL(sql);
				db.setTransactionSuccessful();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.endTransaction();
					db.close();
				}
			}
		}
	}

	public void deleteAsync(final Context context, final ORMcallback callback) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Model.this.delete(context);
				if (callback != null) {
					if (done)
						callback.onFinish();
					else
						callback.onFaild();
				}
			}
		}).start();
	}

	public Long get__id() {
		return __id;
	}

	public void set__id(Long __id) {
		this.__id = __id;
	}

	@Override
	public String toString() {
		return " Model [__id=" + __id + "] ";
	}
}
