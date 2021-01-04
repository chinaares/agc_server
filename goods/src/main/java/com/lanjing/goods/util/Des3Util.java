package com.lanjing.goods.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-31 17:32
 */
@SuppressWarnings("all")
public class Des3Util {
    // 密钥 长度不得小于24
    private final static String secretKey = "123456789012345678901234";
    // 向量 可有可无 终端后台也要约定
    private final static String iv = "01234567";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) {
        Cipher cipher = null;
        byte[] encryptData = null;
        try {
            cipher = getCipher(Cipher.ENCRYPT_MODE);
            encryptData = cipher.doFinal(plainText.getBytes(encoding));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.encode(encryptData);
    }

    private static Cipher getCipher(int encryptMode) {
        Cipher cipher = null;
        try {
            Key desKey;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
            desKey = keyFactory.generateSecret(spec);

            cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(encryptMode, desKey, ips);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipher;
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText) throws Exception {
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE);

        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

        return new String(decryptData, encoding);
    }

    public Object encryption() {
        return null;
    }

    public Object Decrypt() {
        return null;
    }
}
