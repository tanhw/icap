/**
 * 
 */
package com.toolbox.httpRequest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * post提交到指定的URL
 * @ author sys
 *
 */
public class XmlPost {	
	
	private String url;
	
	
	private String requestEncoding="UTF-8";
	
	private String recvEncoding="UTF-8";
	
	private String xmlStr;
	
	
	
	/**
	 * 获取提交返回值的字符编码
	 * @author  zg
	 * @return
	 */
	public String getRecvEncoding() {
		return recvEncoding;
	}

	/**
	 * 设定提交返回值的字符编码
	 * 默认为UTF-8
	 * @author  zg
	 * @param recvEncoding
	 */
	public void setRecvEncoding(String recvEncoding) {
		this.recvEncoding = recvEncoding;
	}

	/**
	 * 设定提交的URL地址
	 * @author  zg
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取提交的字符编码
	 * @author  zg
	 * @return
	 */
	public String getRequestEncoding() {
		return requestEncoding;
	}

	/**
	 * 设定提交的字符编码
	 * 默认为UTF-8
	 * @author  zg
	 * @return
	 */
	public void setRequestEncoding(String requestEncoding) {
		this.requestEncoding = requestEncoding;
	}

	
	
	/**
	 * POST请求提交
	 * @author  zg
	 * @return String
	 * @throws Exception
	 */
	public String doPost() throws Exception{
		if (this.url==null || this.url.equals("")) throw new Exception("请求URL错误");
		
        
        StringBuffer params = new StringBuffer(xmlStr);
        


        
        URL linkUrl = new URL(this.url);
        HttpURLConnection urlCon=null;
        String requestString="";
        
        try{
	        urlCon = (HttpURLConnection) linkUrl.openConnection();
	        urlCon.setRequestMethod("POST");
	        
	        urlCon.setDoOutput(true);
	        byte[] b = params.toString().getBytes();
	        urlCon.getOutputStream().write(b, 0, b.length);
	        urlCon.getOutputStream().flush();
	        urlCon.getOutputStream().close();
	        
	        InputStream in = urlCon.getInputStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(in,this.recvEncoding));
	        
	        String revLine = rd.readLine();
	        StringBuffer revStr = new StringBuffer();
	        String crlf=System.getProperty("line.separator");
	        while (revLine != null)
	        {
	        	revStr.append(revLine);
	        	revStr.append(crlf);
	            revLine = rd.readLine();
	        }
	        
	        requestString = revStr.toString();
            rd.close();
            in.close();

        }
        catch(Exception e){
        	throw new Exception(e);
        }
        finally{
        	if (urlCon!=null)
        		urlCon.disconnect();
        }
        
        return requestString;
	}

	/**
	 * @param smlStr the smlStr to set
	 */
	public void setXmlStr(String xmlStr) {
		this.xmlStr = xmlStr;
	}

	/**
	 * @return the smlStr
	 */
	public String getXmlStr() {
		return xmlStr;
	}
	
}
