package com.toolbox.sign;

import com.toolbox.convert.ConvertTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

//import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
/** 
 * @author zhg 
 * @version $Revision: 1.1 $ 建立日期 2012-9-11  
** 
* DES 算法 
*  

*  
*/  
public class DESUtils {  
 
   private static final Logger LOG = LoggerFactory.getLogger(DESUtils.class);  
   private final static String DES = "DES";  
   private final static String CIPHER_ALGORITHM = "DES/ECB/NoPadding";  
 
   /** 
    * 加密String明文输入,String密文输出 
    *  
    * @param strMing String明文 
    * @return String密文 
    * @throws DesException 
    */  
//   public static String getEncString(String strMing, byte[] byteKey) {
//       byte[] byteMi = null;
//       byte[] byteMing = null;
//       byte[] buf = strMing.getBytes();
//       int len = 8 - buf.length % 8;
//       byteMing = new byte[buf.length + len];
//       System.arraycopy(buf, 0, byteMing, 0, buf.length);
//       byteMi = encrypt(byteMing, byteKey);
//       return Base64.encode(byteMi);
//   }
 
   /** 
    * 解密 以String密文输入,String明文输出 
    *  
    * @param strMi String密文 
    * @return String明文 
    */  
//   public static String getDesString(String strMi, byte[] byteKey) {
//       byte[] bytebase64 = null;
//       byte[] byteMi = null;
//       String strMing = "";
//       try {
//           byteMi = strMi.getBytes();
//           bytebase64 = Base64.decode(byteMi);
//           strMing = new String(decrypt(bytebase64, byteKey));
//       } catch (Exception e) {
//           LOG.error("解密Des错误", e);
//       }
//       return strMing.trim();
//   }
 
   public static String getKeyString(byte[] data,byte[] key) {
	   return ConvertTools.bytesToHexString(encrypt(data,key));
   }

   /** 
    * 加密 
    *  
    * @param src 数据源 
    * @param key 密钥，长度必须是8的倍数 
    * @return 返回加密后的数据 
    * @throws DesException 
    */  
   public static byte[] encrypt(byte[] src, byte[] key) {  
       // DES算法要求有一个可信任的随机数源  
       SecureRandom sr = new SecureRandom();  
       try {  
           // 从原始密匙数据创建DESKeySpec对象  
           DESKeySpec dks = new DESKeySpec(key);  
           // 创建一个密匙工厂，然后用它把DESKeySpec转换成  
           // 一个SecretKey对象  
           SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);  
           SecretKey securekey = keyFactory.generateSecret(dks);  
           // Cipher对象实际完成加密操作,NoPadding为填充方式 默认为PKCS5Padding  
           Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
           // 用密匙初始化Cipher对象  
           cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
           // 现在，获取数据并加密  
           // 正式执行加密操作  
           return cipher.doFinal(src);  
       } catch (NoSuchAlgorithmException e) {  
            LOG.error("算数错误", e);  
       } catch (InvalidKeyException e) {  
            LOG.error("无效key错误", e);  
       } catch (InvalidKeySpecException e) {  
            LOG.error("无效key戳无", e);  
       } catch (NoSuchPaddingException e) {  
            LOG.error("填充错误", e);  
       } catch (IllegalBlockSizeException e) {  
            LOG.error("非法数据块", e);  
       } catch (BadPaddingException e) {  
            LOG.error("错误的填充", e);  
       }  
       return null;  
   }  
 
   /** 
    * 生成密钥 
    *  
    * @return 
    * @throws NoSuchAlgorithmException 
    */  
   public static byte[] initKey() throws NoSuchAlgorithmException {  
       KeyGenerator kg = KeyGenerator.getInstance(DES);  
       kg.init(56);  
       SecretKey secretKey = kg.generateKey();  
       return secretKey.getEncoded();  
   }  
 
   /** 
    * 解密 
    *  
    * @param src 数据源 
    * @param key 密钥，长度必须是8的倍数 
    * @return 返回解密后的原始数据 
    * @throws DesException 
    * @throws Exception 
    */  
   public static byte[] decrypt(byte[] src, byte[] key) {  
       // DES算法要求有一个可信任的随机数源  
       SecureRandom sr = new SecureRandom();  
       try {  
           // 从原始密匙数据创建一个DESKeySpec对象  
           DESKeySpec dks = new DESKeySpec(key);  
           // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成  
           // 一个SecretKey对象  
           SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);  
           SecretKey securekey = keyFactory.generateSecret(dks);  
           // Cipher对象实际完成解密操作  
           Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
           // 用密匙初始化Cipher对象  
           cipher.init(Cipher.DECRYPT_MODE, securekey, sr);  
           // 现在，获取数据并解密  
           // 正式执行解密操作  
           return cipher.doFinal(src);  
       } catch (NoSuchAlgorithmException e) {  
           LOG.error("算数错误", e);  
       } catch (InvalidKeyException e) {  
           LOG.error("无效key错误", e);  
       } catch (InvalidKeySpecException e) {  
           LOG.error("无效key戳无", e);  
       } catch (NoSuchPaddingException e) {  
           LOG.error("填充错误", e);  
       } catch (IllegalBlockSizeException e) {  
           LOG.error("非法数据块", e);  
       } catch (BadPaddingException e) {  
           LOG.error("错误的填充", e);  
       }  
       return null;  
   }  
   
   
}  