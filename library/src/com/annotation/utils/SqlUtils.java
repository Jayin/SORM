package com.annotation.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.annotation.Index;

public class SqlUtils {
	
	public static Field[] getIndexField(Class<?> cls){
		Field[] fields = ReflectionUtils.getColumnFields(cls);
		List<Field> list = new ArrayList<Field>();
		for (Field f : fields) {
			if (f.getAnnotation(Index.class) != null)
				list.add(f);
		}
		return list.toArray(new Field[]{});
	}
}
