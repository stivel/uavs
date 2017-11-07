package com.uavs.common.security;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import org.apache.commons.lang.ArrayUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 非对称加密
 * @author shiyang
 * @version 1.0.0
 *
 */
public class RSAUtils {
	/** 指定加密算法为RSA */
    private static final String ALGORITHM = "RSA";
    /** 密钥长度，用来初始化 */
    private static final int KEYSIZE = 1024;
    /** 指定公钥存放文件 */
//    private static String PUBLIC_KEY_FILE = "PublicKey";
    /** 指定私钥存放文件 */
//    private static String PRIVATE_KEY_FILE = "PrivateKey11";
     
 
    /**
    * @Description
    * @param filePath 文件存放目录
    * @param publicKeyName 公钥文件名称
    * @param privateKeyName 私钥文件名称
    * @throws Exception
    * @author shiyang
    * @version 1.0.0
    */
    public static void generateKeyPair(String filePath,String publicKeyName,String privateKeyName
    		) throws Exception {
        
//        /** RSA算法要求有一个可信任的随机数源 */
//        SecureRandom secureRandom = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
//        keyPairGenerator.initialize(KEYSIZE, secureRandom);
        keyPairGenerator.initialize(KEYSIZE);
        
        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        /** 得到公钥 */
        Key public_Key = keyPair.getPublic();
        
        /** 得到私钥 */
        Key private_Key = keyPair.getPrivate();
        
        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            /** 用对象流将生成的密钥写入文件 */
            oos1 = new ObjectOutputStream(new FileOutputStream(filePath+"/"+publicKeyName));
            oos2 = new ObjectOutputStream(new FileOutputStream(filePath+"/"+privateKeyName));
            oos1.writeObject(public_Key);
            oos2.writeObject(private_Key);
        } catch (Exception e) {
            throw e;
        }
        finally{
            /** 清空缓存，关闭文件输出流 */
            oos1.close();
            oos2.close();
        }
    }
     
    /**
    * @Description 加密方法
    * @param source 源数据
    * @param publickeyPath 公钥路径
    * @return
    * @throws Exception
    * @author shiyang
    * @version 1.0.0
    */
    public static String encrypt(String source,String publickeyPath) throws Exception {
//        generateKeyPair();
        Key publicKey;
        ObjectInputStream ois = null;
        try {
            /** 将文件中的公钥对象读出 */
            ois = new ObjectInputStream(new FileInputStream(
            		publickeyPath));
            publicKey = (Key) ois.readObject();
        } catch (Exception e) {
            throw e;
        }
        finally{
            ois.close();
        }
        
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] b = source.getBytes();
//        /** 执行加密操作 */
//        byte[] b1 = cipher.doFinal(b);
        byte[] data = source.getBytes();
        
     // 加密时超过117字节就报错。为此采用分段加密的办法来加密  
        byte[] dataReturn =null;
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < data.length; i += 100) {  
            byte[] subarray = ArrayUtils.subarray(data, i,  
                    i + 100);
			byte[] doFinal = cipher.doFinal(subarray);  
            sb.append(new String(doFinal));  
            dataReturn = ArrayUtils.addAll(dataReturn, doFinal);  
        }  
        
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(dataReturn);
    }
  
    /**
    * @Description 解密算法
    * @param cryptograph 	密文
    * @param privateKeyPath 私钥路径
    * @return 解密后的资源
    * @throws Exception
    * @author shiyang
    * @version 1.0.0
    */
    public static String decrypt(String cryptograph,String privateKeyPath) throws Exception {
        Key privateKey;
        ObjectInputStream ois = null;
        try {
            /** 将文件中的私钥对象读出 */
            ois = new ObjectInputStream(new FileInputStream(
            		privateKeyPath));
            privateKey = (Key) ois.readObject();
        } catch (Exception e) {
            throw e;
        }
        finally{
            ois.close();
        }
        
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);
        
        /** 执行解密操作 */
//        byte[] b = cipher.doFinal(b1);
        
        StringBuilder sb = new StringBuilder(); 
        String dataReturn=null;
        for (int i = 0; i < b1.length; i += 128) {  
            byte[] subarray = ArrayUtils.subarray(b1, i,  
                    i + 128);
			byte[] doFinal = cipher.doFinal(subarray);  
            sb.append(new String(doFinal));  
        }  
        dataReturn = sb.toString();  
        
        return new String(dataReturn);
    }


}
