/**
 * 
 */
package com.core.controller.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.toolbox.xml.GetXmlFile;

/**
 * 提示信息索引类
 * @ author sys
 *
 */
public class MessageHandler implements InitializingBean {
	private Resource[] mapperLocations;

	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}
	
	private final static Map<String, String> ErrorMsg=new HashMap<String, String>();
	private final static Map<String, String> SuccessMsg=new HashMap<String, String>();
		
	private void init(){
		try {
			for (Resource mapperLocation : this.mapperLocations) {
				if (mapperLocation == null) {
					continue;
				}

				GetXmlFile xmlFile = new GetXmlFile(mapperLocation.getInputStream());
				
				List<Element> errorList = xmlFile.GetListByXpath("/Root/ErrorMsgList/Msg");
				for (Element element : errorList) {
					ErrorMsg.put(element.attributeValue("code"), element.attributeValue("value"));
				}
				
				List<Element> successList=xmlFile.GetListByXpath("/Root/SuccessMsgList/Msg");
				
				for (Element element:successList){
					SuccessMsg.put(element.attributeValue("code"), element.attributeValue("value"));
				}
				
					
			}
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * 根据错误代码或者错误信息
	 * @ author sys
	 * @param code
	 * @return
	 */
	public static String getErrorMsgByCode(String code){
		if (code == null || code.equals("")) code="E99999";
		String msgValue=ErrorMsg.get(code);
		if (msgValue==null) {
			code="E99999";
			msgValue=getErrorMsgByCode(code);
		}
		
		return msgValue;
	}
	
	
	/**
	 * 根据成功代码获取成功信息
	 * @ author sys
	 * @param code
	 * @return
	 */
	public static String getSuccessMsgByCode(String code){
		if (code == null || code.equals("")) {
			code="S00000";
		}
		String msgValue=SuccessMsg.get(code);
		if (msgValue==null) {
			code="S00000";
			msgValue=getSuccessMsgByCode(code);
		}
		
		return msgValue;
	}

	public void afterPropertiesSet() throws Exception {
		init();
		
	}
}
