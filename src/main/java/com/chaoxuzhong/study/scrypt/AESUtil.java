package com.chaoxuzhong.study.scrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding"; // 加密算法

    /**
     * AES 加密
     *
     * @param plaintext 明文
     * @param key       密钥
     * @param iv        初始化向量
     * @return 加密后的密文
     */
    public static String encrypt(String plaintext, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(ciphertext);
    }

    /**
     * AES 解密
     *
     * @param ciphertext 密文
     * @param key        密钥
     * @param iv         初始化向量
     * @return 解密后的明文
     */
    public static String decrypt(String ciphertext, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] plaintext = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(plaintext);
    }

    public static void main(String[] args) throws Exception {
        String plaintext = "Hello, world!";
        String key = "1234567890123456";
        String iv = "1234567890123456";
        String ciphertext = AESUtil.encrypt(plaintext, key, iv);
        String decryptedText = AESUtil.decrypt(ciphertext, key, iv);
        System.out.println("密文：" + ciphertext);
        System.out.println("解密后的明文：" + decryptedText);
    }


}
