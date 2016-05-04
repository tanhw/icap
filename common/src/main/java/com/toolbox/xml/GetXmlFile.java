/**
 * 
 */
package com.toolbox.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.List;

/**
 * @ author sys
 * 
 */
public class GetXmlFile {

	private String fileName = "";

	private Document document = null;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @ author sys
	 * @param fileName
	 */
	public GetXmlFile(String fileName) {
		
		URL fileUrl = GetXmlFile.class.getResource(fileName);
		
		if (fileUrl != null)
			this.fileName = GetXmlFile.class.getResource(fileName).toString();
		else
			this.fileName = fileName;
		
		try {
			SAXReader reader = new SAXReader();
			reader.setValidation(false);
			reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			this.document = reader.read(this.fileName);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	public GetXmlFile(InputStream inputStream) {
		if (inputStream != null) {
			try {
				SAXReader reader = new SAXReader();
				reader.setValidation(false);
				reader.setFeature(
						"http://apache.org/xml/features/nonvalidating/load-external-dtd",
						false);
				this.document = reader.read(inputStream);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
	}

	public GetXmlFile() {
	}

	/**
	 * 设置 XML 格式的字符串
	 * 
	 * @ author sys
	 * @param xmlString
	 * @return
	 */
	public Document setXmlString(String xmlString) {
		StringReader sr = new StringReader(xmlString);
		InputSource is = new InputSource(sr);
		SAXReader reader = new SAXReader();
		try {
			this.document = reader.read(is);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return this.document;
	}

	public Document GetDocument() {
		return this.document;
	}

	@SuppressWarnings("unchecked")
	public List<Element> GetListByXpath(String xpath) {
		return this.document.selectNodes(xpath);
	}

}
