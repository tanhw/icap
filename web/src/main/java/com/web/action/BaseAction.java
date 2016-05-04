/**
 * 
 */
package com.web.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.toolbox.propertyEditor.DatePropertyEditor;

/**
 * 交互基础类，可以格式化日期
 * @ author sys
 *
 */
public abstract class BaseAction {
	@InitBinder  
    protected void initBinder(WebDataBinder binder) {   
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(false);   
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));   
        
        String dateTime="yyyy-MM-dd HH:mm:ss";
        binder.registerCustomEditor(Timestamp.class,new DatePropertyEditor(dateTime));  
    }
	
	public abstract String index(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
