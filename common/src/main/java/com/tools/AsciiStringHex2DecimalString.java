package com.tools;

public class AsciiStringHex2DecimalString {
	
	//将16进制表示ascii码内容为内容的字符串转化为成ascii字符组成的字符串(数字0-9)
	
	public static String hexAscii2CharAsciiString(String s) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i < s.length();i=i+2 ){
			String sub = s.substring(i,i+2);
			int temp = Integer.parseInt(sub,16);
			char c = (char)temp;
			sb.append(c);
		}
		return sb.toString();
	}
	
	
	public static String CharAsciiString2hexAsciiString(String s ) {
		
		StringBuffer sb = new StringBuffer();
		char [] c = s.toCharArray();
		for(int i=0; i < c.length; i++){
			
			int temp = (int)c[i];
			String hex = Integer.toHexString(temp);
			sb.append(hex);
		}
		return sb.toString();
		
	}
	
	/**
	 * 将10进制数字字符串转化为指定的后补F的16进制ascii字符串如:666转换为363636
	 * @param s
	 * @param length
	 * @return
	 * @throws Exception 
	 */
	public static String CharAsciiString2hexAsciiStringWithLength(String s,int length ) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		char [] c = s.toCharArray();
		for(int i=0; i < c.length; i++){
			
			int temp = (int)c[i];
			String hex = Integer.toHexString(temp);
			sb.append(hex);
		}
		String temp = sb.toString();
		if(temp.length() >length) {
			throw new Exception("传入长度不足!");
		}
		for(int m =0;m<length-temp.length();m++) {
			sb.append("F");
		}
		
		return sb.toString();
		
	}
	
	
	public static void main(String[] args) throws Exception {
		String s ="10000000";
		System.out.println(CharAsciiString2hexAsciiStringWithLength(s, 16));
	}


}
