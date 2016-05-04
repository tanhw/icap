/**
 * 
 */
package com.core.controller.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.controller.service.tbl.ISysParaService;
import com.toolbox.convert.ConvertTools;
import com.toolbox.sign.DESUtils;

/**
 * 数据库字段加密解密类
 * 
 * @ author sys
 *
 */
@Component
public class DbHandler {

	private static String datakey;
	
	@Autowired
	private ISysParaService paraService;
	
	/**
	 * 数据加密
	 * 
	 * @ author sys
	 * @param data
	 * @return
	 */
	public static String encodeData(String data) {

		return ConvertTools.bytesToHexString(DESUtils.encrypt(data.getBytes(),
				ConvertTools.hexStringToByte(datakey)));

	}
	
	
	/**
	 * 数据加密
	 * 
	 * @ author sys
	 * @param data
	 * @return
	 */
	public static String encodeData(byte data[]) {
		return ConvertTools.bytesToHexString(DESUtils.encrypt(data,
				ConvertTools.hexStringToByte(datakey)));

	}
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 数据解密
	 * 
	 * @ author syss
	 * @return
	 */
	public static String decodeData(String data) {
		return new String(DESUtils.decrypt(ConvertTools.hexStringToByte(data),
				ConvertTools.hexStringToByte(datakey)));

	}

	@PostConstruct
	private void init() throws Exception {
		
		// 获取参数表中软加加密钥
		datakey = paraService.getParaValue("FEP", "KEY");
		
	}
}
