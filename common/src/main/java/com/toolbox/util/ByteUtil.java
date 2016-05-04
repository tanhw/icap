package com.toolbox.util;


public class ByteUtil {

	private ByteUtil(){
		
	}
	
	
	/**
	 * 字节长度转换整型长度(十十进制)
	* @author xi.xu 
	* @date 2015年7月7日 下午3:19:30  
	* @param len
	* @return 
	* @throws
	 */
	public static int getByteInt(byte[] len) {
		String str = "";
		for (byte b : len) {
			str += (char) b;
		}
		return Integer.parseInt(str);
	}
	
	
	/**
	 * 字节长度转换整型长度(十六进制)
	* @author xi.xu 
	* @date 2015年7月7日 下午3:20:27  
	* @param b
	* @return 
	* @throws
	 */
	public static int getByteIntBinary(byte b) {
		return b < 0 ? (256 + b) : b;
	}
	
	/**
	 * byte[] 拼接 
	 * @param bytesBefore
	 * @param bytesAfter
	 * @return
	 */
	public static byte[] getNewArray(byte[] bytesBefore ,byte [] bytesAfter){
		
		//为拼装字节数组做容器
		
		byte[] bytes =null;
		if(bytesAfter!=null&&bytesBefore!=null){
			bytes = new byte[bytesBefore.length + bytesAfter.length];
			
			System.arraycopy(bytesBefore, 0, bytes, 0, bytesBefore.length);

			System.arraycopy(bytesAfter, 0, bytes, bytesBefore.length, bytesAfter.length);
		}else {
			if(bytesAfter==null){
				bytes = bytesBefore;
			}else if (bytesBefore==null){
				bytes = bytesAfter;
			}
			
		}
		
		return bytes;
		
	}
	/**
	 * 用于中文字符的补位使用
	 * @param oldBytes
	 * @param length 
	 * @return
	 */
	
	public static byte[] getChinsesBytes(byte[] oldBytes,int length){	
		byte[] newBytes = new byte [length-oldBytes.length];
		for(int i=0;i<newBytes.length;i++){
			newBytes[i] =" ".getBytes()[0];
		}
		
		return getNewArray(oldBytes, newBytes) ;
	}
	
}
