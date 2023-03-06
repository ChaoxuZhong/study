package com.chaoxuzhong.study.scrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESUtil {

    public static final String ALGORITHM = "AES";
    public static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    public static final int KEY_SIZE = 128;

    /**
     * 生成指定位数的密钥
     * @param keyStr 密钥字符串
     * @return 密钥对象
     */
    public static Key generateKey(String keyStr) throws NoSuchAlgorithmException {
        byte[] keyBytes = keyStr.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        return keySpec;
    }

    /**
     * 使用指定的密钥对文本进行加密
     * @param plainText 明文
     * @param key 密钥对象
     * @return 加密后的密文
     */
    public static String encrypt(String plainText, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 使用指定的密钥对密文进行解密
     * @param cipherText 密文
     * @param key 密钥对象
     * @return 解密后的明文
     */
    public static String decrypt(String cipherText, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        byte[] decryptedBytes = cipher.doFinal(cipherBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        // 加密
        String plaintext = "待加密";
        String key = "用自己的+生日+某种常用规则";
        String ciphertext = AESUtil.encrypt(plaintext, generateKey(key));
        System.out.println("密文：" + ciphertext);

        // 解密
        plaintext = "kwBqAfTR0asc8r1iOHXssiEk0ACN0hwOHxpCRrFINPQ=";
        String decryptedText = AESUtil.decrypt(plaintext, generateKey(key));
        System.out.println("解密后的明文：" + decryptedText);
    }


}
