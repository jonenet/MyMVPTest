package com.jess.arms.utils.cipher;/**
 * 描述：<br>
 * 时间：15/9/23 16:38 <br>
 * 修改人：XIANGYANG550@pingan.com.cn
 */

import java.net.URLEncoder;
import java.security.Key;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Function:
 * Created by dubin on 16/4/19.
 */
public class Des3Util {
    //密钥
    private static String secretKey = "liuyunqiang@lx100$#365#$";
    // 向量
    private final static String iv = "01234567";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * The Des 3.
     */
    static Des3Util des3;

    private Des3Util() {
    }

    /**
     * Gets des 3.
     *
     * @return the des 3
     */
    public static Des3Util getDes3() {
        if (null == des3) {
            des3 = new Des3Util();
        }
        return des3;
    }

//      public void setDecryptKey(String key) {
//               this.secretKey = key;
//      }

    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     * @param secretKey the secret key
     * @return string
     * @throws Exception the exception
     */
    public static String encode(String plainText, String secretKey) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return Base64.encodeToString(encryptData, Base64.DEFAULT);
        //Base64.encode(encryptData,Base64.DEFAULT);
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @param secretKey   the secret key
     * @return string
     * @throws Exception the exception
     */
    public static String decode(String encryptText, String secretKey) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText, Base64.DEFAULT));
//        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

        return new String(decryptData, encoding);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        UUID uuid = UUID.randomUUID();
        String randomKey = uuid.toString().replace("-", "");
        UUID uuid1 = UUID.randomUUID();
        String loginToken = uuid1.toString().replace("-", "");

        String plainText = randomKey + "_" + loginToken + "_" + System.currentTimeMillis();
        System.out.println(plainText);
        String encodeData = Des3Util.encode(plainText, secretKey);
        System.out.println(encodeData);
        System.out.println(URLEncoder.encode(encodeData));
        String decodeData = Des3Util.decode(encodeData, secretKey);
        System.out.println(decodeData);
    }


}