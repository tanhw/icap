/**
 * 
 */
package com.toolbox.util;

import com.toolbox.annotation.BeanCopyAllowNull;
import com.toolbox.annotation.BeanCopyIgnore;
import org.apache.commons.beanutils.PropertyUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Bean 属性操作
 * @ author sys
 *
 */
public class BeanUtil {

	/**
	 * COPY 2 个对象的值, 2 对象必须是同一个类
	 * @ author sys
	 * @param obj1 : 目标对象
	 * @param obj2 : 被复制对象
	 * @throws Exception
	 */
	public static void copyProperty(Object obj1, Object obj2) throws Exception{
		Class<?> c = obj2.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields){
			String fieldName = field.getName();
			if (field.isAnnotationPresent(BeanCopyIgnore.class))
				continue;
			Object valueObject = PropertyUtils.getProperty(obj2, fieldName);
			if (valueObject != null) {
				PropertyUtils.setProperty(obj1, fieldName, valueObject);
			} else if (field.isAnnotationPresent(BeanCopyAllowNull.class))
				PropertyUtils.setProperty(obj1, fieldName, valueObject);
		}
	}
	
	
	/**
	 * COPY 第一个对象中存在属性的值。2 对象可以不同类
	 * @ author sys
	 * @param obj1 : 目标对象
	 * @param obj2 : 被复制对象
	 * @throws Exception
	 */
	public static void copyFixedProperty(Object obj1, Object obj2) throws Exception{
		Class<?> c = obj2.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if (field.isAnnotationPresent(BeanCopyIgnore.class))
				continue;
			Object valueObject = PropertyUtils.getProperty(obj2, fieldName);
			try {
				if (valueObject != null) {
					PropertyUtils.setProperty(obj1, fieldName, valueObject);
				} else if (field.isAnnotationPresent(BeanCopyAllowNull.class))
					PropertyUtils.setProperty(obj1, fieldName, valueObject);
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * LIST 序列 克隆 （深克隆） 
	 * @author 许西
	 * @param src
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {  
	    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
	    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
	    out.writeObject(src);  
	  
	    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
	    ObjectInputStream in = new ObjectInputStream(byteIn);  
	    @SuppressWarnings("unchecked")  
	    List<T> dest = (List<T>) in.readObject();  
	    return dest;  
	}  
}
