package com.toolbox.sign;

import java.util.LinkedList;
import java.util.List;

public class XorUtil {
	
	 public static byte[] clacXor(byte[] Input) {  
	        int length = Input.length;  
	        int x = length % 8;  
	        int addLen = 0;  
	        if (x != 0) {  
	            addLen = 8 - length % 8;  
	        }  
	        int pos = 0;  
	        byte[] data = new byte[length + addLen];  
	        System.arraycopy(Input, 0, data, 0, length);  
	        byte[] oper1 = new byte[8];  
	        System.arraycopy(data, pos, oper1, 0, 8);  
	        pos += 8;  
	        for (int i = 1; i < data.length / 8; i++) {  
	            byte[] oper2 = new byte[8];  
	            System.arraycopy(data, pos, oper2, 0, 8);  
	            byte[] t = bytesXOR(oper1, oper2);  
	            oper1 = t;  
	            pos += 8;  
	        }  
	        
	        // 取8个长度字节  
	        return oper1;  
	    }  
	 
	 
	  public static byte byteXOR(byte src, byte src1) {  
	        return (byte) ((src & 0xFF) ^ (src1 & 0xFF));  
	    }  
	  
	    public static byte[] bytesXOR(byte[] src, byte[] src1) {  
	        int length = src.length;  
	        if (length != src1.length) {  
	            return null;  
	        }  
	        byte[] result = new byte[length];  
	        for (int i = 0; i < length; i++) {  
	            result[i] = byteXOR(src[i], src1[i]);  
	        }  
	        return result;  
	    }  
	  

	    
	    public static void main(String[] args) {
			String s = "625906313734000900000000120000006515504907313332393830363836";
			
			char[] ora = s.toCharArray();
			StringBuffer sb2 = new StringBuffer();
			List<Byte> mc = new LinkedList<Byte>();
			
			for(int i=0;i<ora.length;i+=2) {
				String keycode = sb2.append(ora[i]).append(ora[i+1]).toString();
				sb2.delete(0, 2);
				byte temp = (byte)(Integer.parseInt(keycode, 16));
				mc.add(temp);
			}
			
			//进行异或计算
			byte[] dd = new byte[mc.size()];
			for(int i = 0;i<mc.size();i++) {
				dd[i] = mc.get(i);
			}
			@SuppressWarnings("unused")
			byte[] macdata = new byte[8];
			macdata = XorUtil.clacXor(dd);
			System.out.println();
		}
}
