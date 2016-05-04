/**
 * 
 */
package com.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能菜单编号，对应数据库表 T_MENU，字段MENU_CODE
 * @ author sys
 * @param
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RightCode {

	/**
	 * 功能代码
	 * 对于 T_SYSMENU表中的 MENUCODE
	 * @ author sys
	 * @return
	 */
	String menuCode();
	
	/**
	 * 功能描述，允许为空
	 * @ author sys
	 * @return
	 */
	String menuDesc() default "";
}
