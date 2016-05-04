/**
 * 
 */
package com.web.queryFrame.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.queryFrame.models.UiColumn;
import com.web.queryFrame.models.UiListParam;

/**
 * UI
 * @ author sys
 *
 */
public class UiHandler {

	private static final Map<String,List<UiColumn>> LIST_COLUMN_UI=new HashMap<String, List<UiColumn>>();
	
	private static final Map<String, UiListParam> LIST_PARAM_UI=new HashMap<String, UiListParam>();
	
	private static final Map<String,List<String>> LIST_JS_UI=new HashMap<String, List<String>>();
	
	public  static List<UiColumn> getUiListColumn(String xmlName){
		return LIST_COLUMN_UI.get(xmlName+".xml");
	}
	
	protected static void setUiListColumn(Map<String,List<UiColumn>> listUi){
		LIST_COLUMN_UI.putAll(listUi);
	}
	
	public static UiListParam getUiListParam(String xmlName){
		UiListParam uiParam=LIST_PARAM_UI.get(xmlName+".xml");
		return uiParam;
	}
	
	protected static void setUiListParam(Map<String, UiListParam> listParamUi){
		LIST_PARAM_UI.putAll(listParamUi);
	}
	
	public static List<String> getUiJs(String xmlName){
		return LIST_JS_UI.get(xmlName+".xml");
	}
	
	protected static void setUiJs(Map<String, List<String>> listJs){
		LIST_JS_UI.putAll(listJs);
	}
}
