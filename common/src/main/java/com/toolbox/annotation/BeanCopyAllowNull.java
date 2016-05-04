/**
 * 
 */
package com.toolbox.annotation;

import java.lang.annotation.*;

/**
 * 允许接收的值是空或者null
 * @ author sys
 *
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanCopyAllowNull {
	
}
