package io.github.jayin.test2;

import android.test.AndroidTestCase;

public class AllTest extends AndroidTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mainTest();
		
	}
	
	public void mainTest(){
		new CreatorTest(getContext());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	

}
