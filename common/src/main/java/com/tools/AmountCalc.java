package com.tools;

public class AmountCalc {
	
	//格式化金额,剔除固定长度金额左边的0
	public static String formate(String s) {
		char [] c = s.toCharArray();
		int index = 0;
		for(int i=0;i<c.length;i++) {
			char temp = c[i];
			if(!(String.valueOf(temp).equals("0"))) {
				index = i;
				break;
			}
		}
		
		return s.substring(index);
	}
	
	
	//将2个固定长度字符串金额相加
	public static Long addDecimal(String s1,String s2) {
		s1 = formate(s1);
		s2 = formate(s2);
		
		long money1 = Long.parseLong(s1);
		long money2 = Long.parseLong(s2);
		
		long total = money1 + money2;
		return total;
		
		
	}
	
	//将2个固定长度字符串金额相加
	public static String addDecimalWith0(String s1,String s2) {
		
		int length1 = s1.length();
		int length2 = s2.length();
		int length = length1 > length2 ? length1:length2;
		
		
		
		s1 = formate(s1);
		s2 = formate(s2);
		
		long money1 = Long.parseLong(s1);
		long money2 = Long.parseLong(s2);
		
		long total = money1 + money2;
		
		StringBuffer sb = new StringBuffer();
		int currentlength = String.valueOf(total).toString().length();
		
		if( currentlength < length) {
			int count = length - currentlength;
			for(int i=0;i<count ;i++) {
				sb.append("0");
			}
		}
		
		return sb.toString() + total;
		
		
	}

}
