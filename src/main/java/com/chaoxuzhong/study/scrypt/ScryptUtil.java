package com.chaoxuzhong.study.scrypt;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class ScryptUtil {

    private static final int SALT_LENGTH = 16; // salt 的字节长度
    private static final int ITERATIONS = 32768; // 迭代次数
    private static final int KEY_LENGTH = 256; // 生成密钥的位数
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256"; // 密钥工厂算法



    /**
     * 对明文进行 Scrypt 加密，使用 salt 参数作为盐值
     * @param plaintext 明文
     * @param salt 盐值字符串
     * @return 加密后的密文
     */
    public static String encrypt(String plaintext, String salt) throws Exception {
        byte[] saltBytes = salt.getBytes(); // 将 salt 转换成字节数组
        PBEKeySpec spec = new PBEKeySpec(plaintext.toCharArray(), saltBytes, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        byte[] result = new byte[saltBytes.length + keyBytes.length];
        System.arraycopy(saltBytes, 0, result, 0, saltBytes.length);
        System.arraycopy(keyBytes, 0, result, saltBytes.length, keyBytes.length);
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 对密文进行 Scrypt 解密，使用 salt 参数作为盐值
     * @param ciphertext 密文
     * @param salt 盐值字符串
     * @return 解密后的明文
     */
    public static String decrypt(String ciphertext, String salt) throws Exception {
        byte[] saltBytes = salt.getBytes(); // 将 salt 转换成字节数组
        byte[] keyBytes = Base64.getDecoder().decode(ciphertext);
        byte[] derivedKey = new byte[KEY_LENGTH / 8];
        PBEKeySpec spec = new PBEKeySpec(saltBytes, keyBytes, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        factory.generateSecret(spec).getEncoded();
        System.arraycopy(keyBytes, saltBytes.length, derivedKey, 0, derivedKey.length);
        return new String(derivedKey);
    }

    /**
     * 生成指定长度的随机盐值
     * @param length 盐值长度
     * @return 盐值字符串
     */
    public static String generateSalt(int length) {
        byte[] salt = new byte[length];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
