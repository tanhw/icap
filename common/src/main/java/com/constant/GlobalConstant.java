/**
 * 
 */
package com.constant;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * global.properties文件读取类
 * @ author sys
 * @param
 */
public class GlobalConstant {

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("global");

	/**
	 * 获取属性值
	 * @ author sys
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}
}
