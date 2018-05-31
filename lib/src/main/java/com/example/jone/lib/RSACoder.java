package com.example.jone.lib;


import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * Created by ex-zhoulai on 2018/5/30.
 */

public class RSACoder {

    /**
     * RSA 加密，签名，校验
     */
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";


    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);

        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);

        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }


    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static byte[] decryptBASE64(String data) {
//        return Base64.decodeBase64(data);
//        return Base64.getDecoder().decode(data);
        return null;
    }

    public static String encryptBASE64(byte[] data) {
//        return new String(Base64.getEncoder().encode(data));
        return null;
    }

    private static final String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaQv+rp/rc6C+QPY8y1Cs0gwjP9eG/vJo3s8xf8UOrvvewwFivnv1TIMQaGR76JLM4ubmwTMpjCqgPgU3AVRRau9zZTsne697lHDpPUO8klPRF9VCGOvHsW+8Qh+7U8YkwmJgYSrS47iBmxRhlvC9AQ3Q7HujL6dHbc1GP79PvdwIDAQAB";

    public static void main(String[] args) {

//        try {
//            Map<String, Object> keyMap = RSACoder.initKey();
//
//            String publicKey = RSACoder.getPublicKey(keyMap);
//            String privateKey = RSACoder.getPrivateKey(keyMap);
//
//            System.err.println("publicKey : " + publicKey);
//            System.err.println("privateKey : " + privateKey);
//
//            System.err.println("publicKey--privateKey");
//            String inputStr = "aaaa";
//            byte[] data = inputStr.getBytes();
//
//            //公钥加密
//            byte[] encodedData = RSACoder.encryptByPublicKey(data, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaQv+rp/rc6C+QPY8y1Cs0gwjP9eG/vJo3s8xf8UOrvvewwFivnv1TIMQaGR76JLM4ubmwTMpjCqgPgU3AVRRau9zZTsne697lHDpPUO8klPRF9VCGOvHsW+8Qh+7U8YkwmJgYSrS47iBmxRhlvC9AQ3Q7HujL6dHbc1GP79PvdwIDAQAB");
//
////            //私钥解密
////            byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
////                    privateKey);
//
////            String outputStr = new String(decodedData);
//            String outputStr = new String(encodedData);
//            System.out.println("你好");
////            System.err.println("before: " + inputStr + "    " + "after: " + outputStr);
//            System.err.println("before: " + outputStr + "    " + "after: ");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {

            String resultSha = hexString(encryptSha1("aaaa"));
//            String aaaa = encrypt(key, resultSha);
            System.out.println(resultSha);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public static String hexString(byte[] bytes) {
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String encrypt(String keyStr, String content) {
        try {
            byte[] keyBytes = Base64.decode(keyStr, Base64.DEFAULT);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return new String(Base64.encode(cipher.doFinal(content.getBytes()), Base64.DEFAULT), Charset.defaultCharset());
        } catch (Exception e) {
            return null;
        }
    }

    //byte字节转换成16进制的字符串MD5Utils.hexString
    public static byte[] encryptSha1(String info) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA1");
        byte[] srcBytes = info.getBytes();
        // 使用srcBytes更新摘要
        sha.update(srcBytes);
        // 完成哈希计算，得到result
        return sha.digest();
    }


}
