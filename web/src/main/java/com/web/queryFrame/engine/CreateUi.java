/**
 * 
 */
package com.web.queryFrame.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.toolbox.xml.GetXmlFile;
import com.web.queryFrame.models.UiColumn;
import com.web.queryFrame.models.UiListParam;

/**
 * UI初始化
 * @ author sys
 *
 */
class CreateUi implements InitializingBean {

	private Resource[] mapperLocations;
	private Resource[] listLocations;
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());


	public void setListLocations(Resource[] listLocations) {
		this.listLocations = listLocations;
	}

	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}
	
	public CreateUi(){
	
		
	}
	
	
	@SuppressWarnings("unused")
	private  void init() {
		try {
			Map<String,List<UiColumn>> listColumnMap=new HashMap<String, List<UiColumn>>();
			Map<String, UiListParam> listParaMap=new HashMap<String, UiListParam>();
			Map<String, List<String>> listJsMap=new HashMap<String, List<String>>();
			
			Map<String , List<UiColumn>> columnMap=new HashMap<String, List<UiColumn>>();
			
			for (Resource mapperLocation : this.mapperLocations) {
				if(mapperLocation==null){
					continue;
				}
				GetXmlFile xmlFile = new GetXmlFile(mapperLocation.getInputStream());
				List<Element> columnList=xmlFile.GetListByXpath("/Root/Column");
				
				List<UiColumn> columns=new ArrayList<UiColumn>();
				
				for (Element element : columnList) {
					UiColumn uiColumn=new UiColumn();
					uiColumn.setField(element.attributeValue("field"));
					uiColumn.setColumnName(element.attributeValue("columnName"));
					uiColumn.setWidth(element.attributeValue("width"));
					uiColumn.setIsSort(element.attributeValue("isSort"));
					uiColumn.setAlign(element.attributeValue("align"));
					uiColumn.setFormatter(element.attributeValue("formatter"));
					uiColumn.setStyler(element.attributeValue("styler"));
					columns.add(uiColumn);
				}
				columnMap.put(mapperLocation.getFilename().toUpperCase(), columns);
				
			}
			
			for(Resource lLocation:listLocations){
				if(lLocation==null) {
					continue;
				}
				GetXmlFile xmlFile = new GetXmlFile(lLocation.getInputStream());
				if(lLocation==null){
					continue;
				}
				String fileName=lLocation.getFilename();
				//获取ImportColumn
				List<Element> importColumnList=xmlFile.GetListByXpath("/Root/ImportColumn");
				List<UiColumn> columns=new ArrayList<UiColumn>();
				for (Element element : importColumnList) {
					columns.addAll(columnMap.get(element.attributeValue("url").toUpperCase()));
				}
				listColumnMap.put(fileName, columns);
				
				//获取List
				List<Element> listParamList=xmlFile.GetListByXpath("/Root/List");
				Element listParam= listParamList.get(0);
				UiListParam uiListParam=new UiListParam();
				uiListParam.setUrl(listParam.attributeValue("url"));
				uiListParam.setSortName(listParam.attributeValue("sortName"));
				uiListParam.setSortOrder(listParam.attributeValue("sortOrder"));
				uiListParam.setCheckbox(listParam.attributeValue("checkbox"));
				listParaMap.put(fileName, uiListParam);
				
				//获取JS
				List<Element> importJsList=xmlFile.GetListByXpath("/Root/ImportJS");
				List<String> jsList=new ArrayList<String>();
				for (Element js : importJsList) {
					jsList.add(js.attributeValue("url"));
				}
				listJsMap.put(fileName,jsList);
			}
			

			UiHandler.setUiListColumn(listColumnMap);
			UiHandler.setUiListParam(listParaMap);
			UiHandler.setUiJs(listJsMap);
			
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		
	}

	public void afterPropertiesSet()  {
		init();
	}
}
