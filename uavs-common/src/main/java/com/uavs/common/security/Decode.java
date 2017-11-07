package com.uavs.common.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class Decode {
    /** 指定加密算法为RSA */
    private static final String ALGORITHM = "RSA";
    /** 密钥长度，用来初始化 */
    private static final int KEYSIZE = 1024;
    /** 指定公钥存放文件 */
//    private static String PUBLIC_KEY_FILE = "PublicKey";
    /** 指定私钥存放文件 */
    private static String PRIVATE_KEY_FILE = "PrivateKey11";

    
    

    /**
     * 解密算法
     * @param cryptograph    密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String cryptograph) throws Exception {
        Key privateKey;
        ObjectInputStream ois = null;
        try {
            /** 将文件中的私钥对象读出 */
            ois = new ObjectInputStream(new FileInputStream(
                    PRIVATE_KEY_FILE));
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
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    public static void main(String[] args) throws Exception {
//        String source = "恭喜发财!恭喜发财!恭喜发财!恭喜发财!";// 要加密的字符串
//        System.out.println("准备用公钥加密的字符串为：" + source);
//        
//        String cryptograph = encrypt(source);// 生成的密文
//        System.out.print("用公钥加密后的结果为:" + cryptograph);
//        System.out.println();
String cryptograph="YiQrelJajZZ9ns2Du6ENIIreEoF1Bt0J2LtKJIQFVu16k9K+pqPsL8Y29v1Vp5BqHElPhAaCVUW8duqEYkDsTBNqM0pmMtposMp2I9MRUr6jPxLTkeH76X70Yu61JlxdNZy7acNSVeXQKwabHSccmIXiu2+LUnyNtPGaa74Y4p4=";
        String target = decrypt(cryptograph);// 解密密文
        System.out.println("用私钥解密后的字符串为：" + target);
        System.out.println();
    }
}