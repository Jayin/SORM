package io.github.jayin.test2;

import android.content.Context;

public abstract class BaseTest {

	private Context context;

	public BaseTest(Context context) {
		this.context = context;
		setup();
		testQueue();
		tearDown();
	}

	protected void setup() {

	}

	protected void tearDown() {

	}

	public Context getContext() {
		return context;
	}

	protected abstract void testQueue();
}
