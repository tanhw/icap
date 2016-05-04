package com.toolbox.sign;

import com.toolbox.convert.ConvertTools;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author zhg 
 * @version $Revision: 1.1 $ 建立日期 2012-9-11  
 ** 
 * DES 算法 
 *  

 *  
 */  
public class DESedeUtils {


	private static final String Algorithm = "DESede";  //定义 加密算法,可用 DES,DESede,Blowfish

	//keybyte为加密密钥，长度为24字节
	//src为被加密的数据缓冲区（源）
	public static byte[] encryptMode( byte[] src, byte[] keybyte) {
		try {
			//生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			//加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	//keybyte为加密密钥，长度为24字节
	//src为加密后的缓冲区
	public static byte[] decryptMode( byte[] src,byte[] keybyte) {
		try {
			//生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			//解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	//转换成十六进制字符串
	public static String byte2hex(byte[] b) {
		String hs="";
		String stmp="";

		for (int n=0;n<b.length;n++) {
			stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length()==1) hs=hs+"0"+stmp;
			else hs=hs+stmp;
			if (n<b.length-1)  hs=hs+":";
		}
		return hs.toUpperCase();
	}

	/**
	 * 加密
	 * 
	 * @param src  需要加密数据
	 * @param key  加密密钥
	 * @return 加密后数据
	 * 
	 * 
	 */
	public static String getDESCode(String src,String key){
		
		//将数据转换成BCD格式
		byte [] keyBcd = ConvertTools.ASCII_To_BCD(key.getBytes(), key.length());

		byte [] srcBcd = ConvertTools.ASCII_To_BCD(src.getBytes(), src.length());
			
		//temp
		byte[] tempArray = new byte[24];

		System.arraycopy(keyBcd,0,tempArray,0,16);

		System.arraycopy(keyBcd,0,tempArray,16,8);

		byte[] resultArray = DESedeUtils.encryptMode(srcBcd, tempArray);

		String result = new String(ConvertTools.bytesToHexString(resultArray));
		
		return result.substring(0,result.length()-16);
		
	}
	/**
	 * 加密
	 * 
	 * @param src  需要加密数据
	 * @param key  加密密钥
	 * @return 加密后数据
	 * 
	 * 
	 */
	public static String getDESCode(byte [] src,String key){
		
		//将数据转换成BCD格式
		byte [] keyBcd = ConvertTools.ASCII_To_BCD(key.getBytes(), key.length());
		
		//temp
		byte[] tempArray = new byte[24];

		System.arraycopy(keyBcd,0,tempArray,0,16);

		System.arraycopy(keyBcd,0,tempArray,16,8);

		byte[] resultArray = DESedeUtils.encryptMode(src, tempArray);

		String result = new String(ConvertTools.bytesToHexString(resultArray));
		
		return result.substring(0,result.length()-16);
	}
	
}


