package com.annotation.core;

import android.content.Context;

import com.annotation.Ignore;
import com.annotation.PrimaryKey;
import com.annotation.entity.ORMcallback;
import com.annotation.utils.DBUtils;

public class Model {
	private static Object lock = new Object();

	@Ignore
	private Context context;

	@PrimaryKey
	private Long __id = null;

	private boolean saveDone = false, deleteDone = false;

	public void save(Context context) {
		synchronized (lock) {

			String sql =DBUtils.createSaveSql(this);
			saveDone = DBUtils.save(context, this.getClass(), sql);

		}
	}

	public void saveAsync(final Context context, final ORMcallback callback) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Model.this.save(context);
				if (callback != null) {
					if (saveDone)
						callback.onFinish();
					else
						callback.onFaild();
				}
			}
		}).start();
	}

	public void delete(Context context) {
		synchronized (lock) {
			String sql = null;
			sql = new Deletor().from(this.getClass())
					.where("__id", "=", String.valueOf(__id)).build();
			deleteDone = DBUtils.delete(context, this.getClass(), sql,
					this.__id);

		}
	}

	public void deleteAsync(final Context context, final ORMcallback callback) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Model.this.delete(context);
				if (callback != null) {
					if (deleteDone)
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
