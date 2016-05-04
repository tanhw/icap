package com.tools;

public class PosBatchIdTool {
	
	//负责处理pos批次号的组装
	
	//当batchId不足6位，左补0
	public static String createBatchId(String s ) throws Exception {
		int length = s.length();
		StringBuffer sb = new StringBuffer();
		if(length < 6) {
			int shortcount = 6-length;
			for (int i = 0 ; i < shortcount ; i ++) {
				sb.append(0);
			}
		} else if (length > 6) {
			throw new Exception("非法批次号！");
		} 
		sb.append(s);
		
		return sb.toString();
		
	}
	
	//剔除左边的0增加1
	public static String removeleft0add1(String s) throws Exception {
		if(s.equals("999999")) {
			return "000001";
		}
		char [] c = s.toCharArray();
		int index = 0;
		for(int i=0;i<c.length;i++) {
			char temp = c[i];
			if(!(String.valueOf(temp).equals("0"))) {
				index = i;
				break;
			}
		}
		
		int data = Integer.parseInt(s.substring(index)) + 1;
		
		return createBatchId(String.valueOf(data));
	}
	

}
