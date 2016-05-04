package com.toolbox.sign;

import com.toolbox.convert.ConvertTools;

/** 
 * Mac工具类，采用ECB算法 
 *  
 * @author zhg
 *  
 */  
public class MacEcbUtils {  
    public static byte[] IV = new byte[8];  
  
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
  
    /** 
     * mac计算,数据不为8的倍数，需要补0，将数据8个字节进行异或，再将异或的结果与下一个8个字节异或，一直到最后，将异或后的数据进行DES计算 
     *  
     * @param key 
     * @param Input 
     * @return 
     */  
    public static byte[] clacMac(byte[] key, byte[] Input) {  
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
        
        byte[] buff = DESUtils.encrypt(oper1, key);  
        // 取8个长度字节  
        byte[] retBuf = new byte[8];  
        System.arraycopy(buff, 0, retBuf, 0, 8);  
        return retBuf;  
    }  
    
    public static String getMac(byte[] key, byte[] Input) {
    	return ConvertTools.bytesToHexString(clacMac(key, Input));
    }
  
    public static void main(String[] args) {  

		 byte[] buff = "1371122198806276845  9906315202875296".getBytes();
		 System.out.println(buff.length);
		 System.out.println(ConvertTools.bytesToHexString(buff));
		 byte[] MACKEY = "WEIHAIGJ".getBytes();
		 System.out.println(getMac(MACKEY, buff));
    }    
} 


