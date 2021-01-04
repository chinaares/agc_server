package com.lanjing.wallet.util;


import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class AESEncrypt {

    //获取密钥的算法
    private static final String KEY_ALGORITHM = "AES";
    //获取Cipher对象的算法
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) {
        System.out.println(getKey());
    }

    /**
     * 获取加密key
     *
     * @return
     */
    public static String getKey() {
        return RandomStringUtils.randomAlphanumeric(16);
    }

    /**
     * 加密
     *
     * @param content 加密数据
     * @param key     密钥(AES,密钥长度必须是16个字节)
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key)  {

        try {
            // 获取Cipher
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 生成密钥 制定密钥规则
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            // 加密
            byte[] bytes = cipher.doFinal(content.getBytes());

            return Base64.encode(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param encrypted 密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String encrypted, String key) throws Exception {
        // 获取Cipher
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        // 生成密钥
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);

        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        // 解密
        byte[] bytes = cipher.doFinal(Base64.decode(encrypted));

        return new String(bytes);
    }
}

