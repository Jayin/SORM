package io.github.jayin.test2;

import android.content.Context;

public abstract class BaseTest {

	private Context context;

	public BaseTest(Context context) {
		this.context = context;
		try{
			setup();
			testQueue();
			tearDown();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	protected void setup() {

	}

	protected void tearDown() {

	}

	public Context getContext() {
		return context;
	}

	protected abstract void testQueue() throws Exception;
	
	 
}
