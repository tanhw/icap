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
 * 字段 缓存 数据库字段和 mybatis 隐射关系
 * 
 * @ author sys
 * 
 */
public class ColumnCache implements InitializingBean {

	private Resource[] mapperLocations;

	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	private static final Map<String, String> COLUMN_MAP = new HashMap<String, String>();

	private static final Map<String, Map<String, String>> MODELS_COLUMN_MAP = new HashMap<String, Map<String, String>>();

	@SuppressWarnings("unchecked")
	private void init() {
		try {
			for (Resource mapperLocation : this.mapperLocations) {
				if (mapperLocation == null) {
					continue;
				}

				GetXmlFile xmlFile = new GetXmlFile(mapperLocation.getInputStream());

				List<Element> resultMapList = xmlFile.GetListByXpath("/mapper/resultMap");
				for (Element resultMap : resultMapList) {
					String modelName = resultMap.attributeValue("type");

					Map<String, String> columnMap = MODELS_COLUMN_MAP.get(modelName);
					if (columnMap == null) {
						columnMap = new HashMap<String, String>();
					}

					List<Element> columnList = resultMap.elements();
					for (Element element : columnList) {
						String property = element.attributeValue("property");
						String column = element.attributeValue("column");
						COLUMN_MAP.put(property, column);
						columnMap.put(property, column);
					}

					MODELS_COLUMN_MAP.put(modelName, columnMap);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 根据 映射值 获取 数据库字段 名称
	 * 
	 * @ author sys
	 * @param property
	 * @return
	 */
	public static String getColumnByProperty(String property) {
		return COLUMN_MAP.get(property);
	}

	/**
	 * 根据 映射值 获取 数据库字段 名称
	 * 
	 * @ author sys
	 * @param <T>
	 * @param t
	 * @param property
	 * @return
	 */
	public static String getColumnByProperty(Class<?> t, String property) {
		String modelName = t.getName();

		Map<String, String> columnMap = MODELS_COLUMN_MAP.get(modelName);

		if (columnMap == null)
			return null;
		return columnMap.get(property);
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		init();
	}
}
