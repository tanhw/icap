package com.tools;

public class HexNumberLengthTool {

	public static String formmate(String s, int length,String mark) {
		
		int templength = s.length();
		StringBuffer sb = new StringBuffer();
		
		if(templength < length) {//需要补充
			
			for(int i=0;i<length - templength;i++) {
				sb.append(mark);
			}
		}
		sb.append(s);
		return sb.toString();
	}
	
}
