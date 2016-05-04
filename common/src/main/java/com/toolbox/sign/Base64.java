/**
 * 
 */
package com.toolbox.sign;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Base64加解密
 * 
 * @ author sys
 * 
 */
public class Base64 {

	private static BASE64Encoder encoder = new sun.misc.BASE64Encoder();

	private static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

	public Base64() {
	}

	/**
	 * 签名摘要
	 * @ author sys
	 * @param s
	 * @return
	 */
	
	public static String encode(String s) throws Exception{
		return URLEncoder.encode(encoder.encode(s.getBytes()),"UTF-8");
	}


	/**
	 * 签名摘要
	 * @ author sys
	 * @param s
	 * @return
	 */

	public static String encode(byte[] s) throws Exception{
		return URLEncoder.encode(encoder.encode(s),"UTF-8");
	}

	/**
	 * 验签
	 * @ author sys
	 * @param s
	 * @return
	 */
	public static String decode(String s) throws Exception{
		s = URLDecoder.decode(s,"UTF-8");
		try {
			byte[] temp = decoder.decodeBuffer(s);
			return new String(temp);
		} catch (IOException ioe) {
			// handler
		}
		return s;
	}
}
