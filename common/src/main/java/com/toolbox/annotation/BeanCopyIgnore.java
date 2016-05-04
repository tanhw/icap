/**
 * 
 */
package com.toolbox.annotation;

import java.lang.annotation.*;

/**
 * Bean Copy 忽略字段注解
 * @ author sys
 *
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanCopyIgnore {

}
