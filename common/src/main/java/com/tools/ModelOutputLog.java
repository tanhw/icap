/**
 * Copyright (c) 2010-2018 HuaTeng, Inc. All rights reserved.
 *
 * @CreateDate 2012-4-1 ★ 上午9:47:36
 *
 * @Description 华腾软件系统有限公司
 */
package com.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <a href="ModelOutputLog.java.html"><b><i>View Source</i></b></a>
 * 
 * Description ★ 实体对象的属性日志输出
 * 
 * @author Administrator
 */
public class ModelOutputLog {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelOutputLog.class);

	/**
	 * 对象日志输出方法
	 * 
	 * @param obj
	 */
	public static void outPutLog(Object obj) {
		StringBuffer formatString = new StringBuffer();
		try {
			Class<?> clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			Method method = null;
			Object value = null;
			Object result = null;
			for (Field f : fields) {
				f.setAccessible(true);
				method = clazz.getMethod(parGetName(f.getName()));
				result = method.invoke(obj);
				if (result instanceof String) {
					value = (String) result;
				} else if (result instanceof Integer) {
					value = (Integer) result;
				} else if (result instanceof char[]) {
					value = new String((char[]) result);
				} else {
					value = null;
				}
				if (Validator.isNotNull(value)) {
					LOGGER.info(formatString.append("属性@").append(f.getName()).append("的值为: ").append(String.valueOf(value)).toString());
					formatString.delete(0, formatString.length());
				}
			}
		} catch (Exception e) {
			formatString.delete(0, formatString.length());
			LOGGER.error("对象日志输出方法错误.", e);
		}
	}

	/**
	 * 组装属性的get方法
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String parGetName(String fieldName) {
		StringBuffer stringBuffer = new StringBuffer();
		if (Validator.isNull(fieldName)) {
			return null;
		}
		return stringBuffer.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
	}

}
