package com.annotation.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.annotation.utils.DBUtils;
import com.annotation.utils.DBHelper;

public class Query {
	private Class<?> _entity;
	private Selector selector;

	public Query(Selector selector) {
		this.selector = selector;
		_entity = selector.getEntity();
	}

	public <T> List<T> excute(Context context) {
		List<T> result = new ArrayList<T>();
		if (selector == null)
			throw new NullPointerException("selector Can't be null");
		String sql = selector.build();
		// 执行查询，获得cursor
		SQLiteDatabase db =  new DBHelper(context).getReadableDatabase();
		//create table if not exist
		DBUtils.createTable(db, _entity);
		
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			T entity = DBUtils.cursor2Entity(cursor, _entity);
			if (entity != null)
				result.add(entity);
		}

		return result;
	}

}
