package com.core.password;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;

public class RSAUtils {
	  /** 指定加密算法为RSA */
    private static final String ALGORITHM = "RSA";
    
    private static final String PROVIDER = "RSA/ECB/NoPadding";
    /** 密钥长度，用来初始化 */
    private static final int KEYSIZE = 1024;
    /** 指定密钥对存放文件 */
    private static Map<Integer,KeyPair> KeyList = new HashMap<Integer,KeyPair>();


    /**
     * 生成密钥对
     * @param index 密钥索引
     * @throws Exception
     */
    private static void generateKeyPair(Integer index) throws Exception {

        /** 为RSA算法创建一个KeyPairGenerator对象 */
    	KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
    	keyPairGenerator.initialize(KEYSIZE);
        
        /** 生成密钥对 */
    	KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        KeyList.put(index, keyPair);
    }
    
    private static String getModulus(Integer index){
    	RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyList.get(index).getPublic();
    	return new String(Hex.encodeHex(rsaPublicKey.getModulus().toByteArray()));
    }
    
    private static String getPublicExponent(Integer index){
    	RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyList.get(index).getPublic();
    	return new String(Hex.encodeHex(rsaPublicKey.getPublicExponent().toByteArray()));
    }
    
    /**
     * 获得公钥
     * @param index 公钥索引 如果没有则生成
     * @return 数组 数组第一位为Modulus 第二位为 PublicExponent
     */
    public static String[] getPublicKeys(Integer index){
    	String[] result = new String[2];
    	if(!KeyList.containsKey(index)){
    		try {
				generateKeyPair(index);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	result[0] = getModulus(index).substring(2);
    	result[1] = getPublicExponent(index).substring(1);
    	return result;
    	
    }
    
    /**
     * 加密方法
     * @param source 源数据
     * @return
     * @throws Exception
     */
    public static String encrypt(String source, Integer index) throws Exception {
        Key publicKey = KeyList.get(index).getPublic();
        
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
       
        return new String(Hex.encodeHex(b1));
    }
    
    /**
     * 解密算法
     * @param cryptograph    密文
     * @param index 密钥索引
     * @return
     * @throws Exception
     */
    public static String decrypt(String cryptograph,Integer index) throws Exception {
        Key privateKey = KeyList.get(index).getPrivate();
        
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(PROVIDER);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        byte[] b1 = Hex.decodeHex(cryptograph.toCharArray());
        
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    public static void main(String[] args) throws Exception {
        String source = "恭喜发财!";// 要加密的字符串
        System.out.println("准备用公钥加密的字符串为：" + source);
        
        generateKeyPair(1);
        
        System.out.println("公共模数为:" + getPublicKeys(1)[0]);
        System.out.println("公钥为:" + getPublicKeys(1)[1]);
        
        String cryptograph = encrypt(source,1);// 生成的密文
        System.out.print("用公钥加密后的结果为:" + cryptograph);
        System.out.println();

        String target = decrypt(cryptograph,1);// 解密密文
        System.out.println("用私钥解密后的字符串为：" + target);
        System.out.println();
    }
}
