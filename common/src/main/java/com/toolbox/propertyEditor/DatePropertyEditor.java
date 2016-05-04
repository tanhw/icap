/**
 * 
 */
package com.toolbox.propertyEditor;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 
 * @ author sys
 *
 */
public class DatePropertyEditor extends PropertyEditorSupport {

	private String format = "yyyy-MM-dd HH:mm:ss"; //缺省

	public DatePropertyEditor(){}
	
	public DatePropertyEditor(String format){
		this.format=format;
	}
	
	public void setFormat(String format) {
	   this.format = format;
	}

	public void setAsText(String text) throws IllegalArgumentException {

	   if (text != null || StringUtils.hasText(text)) {
	     try {
	    	 if (text !=null && !text.equals(""))
	    		 setValue(new Timestamp((new SimpleDateFormat(this.format).parse(text)).getTime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   } 
	}

}
