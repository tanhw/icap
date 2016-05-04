/**
 * 
 */
package com.toolbox.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Date日期转换
 * @ author sys
 *
 */
public class CustomDateTimeSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value,JsonGenerator jgen,SerializerProvider provider) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	} 

}
