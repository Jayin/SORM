package com.annotation.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.annotation.entity.QueryCallback;
import com.annotation.utils.DBHelper;
import com.annotation.utils.DBUtils;

public class Query {
	private Class<?> _entity;
	private Selector selector;

	public Query(Selector selector) {
		this.selector = selector;
		_entity = selector.getEntity();
	}

	public <T extends Model> List<T> excute(Context context) {
		List<T> result = new ArrayList<T>();
		if (selector == null)
			throw new NullPointerException("selector Can't be null");
		String sql = selector.build();
		// 执行查询，获得cursor
		SQLiteDatabase db = null;
		try {
			db = new DBHelper(context).getReadableDatabase();
			// create table if not exist
			DBUtils.createTable(context, _entity);

			Cursor cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				T entity = DBUtils.cursor2Entity(cursor, _entity);
				if (entity != null)
					result.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return result;
	}

	public void excuteAsync(final Context context, final QueryCallback callback) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (callback != null) {
					callback.onFinish(excute(context));
				}
			}
		}).start();
	}
}
