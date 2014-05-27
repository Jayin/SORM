package io.github.jayin.test;

import io.github.jayin.test.entity.Animal;

import com.annotation.core.Droper;
import com.annotation.utils._;

import android.test.AndroidTestCase;

public class DropTest extends AndroidTestCase {

	
	public void dropIndex1(Class<?> cls){
		String sql = new Droper().Index().from(cls).build();
		_.d(sql);
	}
	
	public void dropTable1(Class<?> cls){
		String sql = new Droper().Table().from(cls).build();
		_.d(sql);
	}
	
	public void main(){
//		dropIndex1(Animal.class);
//		dropTable1(Animal.class);
		dropIndex2(Animal.class);
 		dropTable2(Animal.class);
		
	}
	
	public void dropIndex2(Class<?> cls){
		  new Droper().Index().from(cls).excute(getContext());
	}
	
	public void dropTable2(Class<?> cls){
		  new Droper().Table().from(cls).excute(getContext());
	}
}
