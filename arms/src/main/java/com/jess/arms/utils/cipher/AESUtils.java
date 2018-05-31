package com.jess.arms.utils.cipher;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Function: AES工具类.
 * Created by dubin on 16/4/19.
 */
public class AESUtils {
	/** 密钥算法 */
	private static final String KEY_ALGORITHM = "AES";
	private static final int KEY_SIZE = 128;
	public static final String PUBLIC_KEY="l1S6eaPUyLz1yjgY";
	/** 加密/解密算法/工作模式/填充方法 */
	// public static final String CIPHER_ALGORITHM = "AES/ECB/NoPadding";

	/**
	 * 获取密钥
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static Key getKey() throws Exception {
		// 实例化
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		// AES 要求密钥长度为128位、192位或256位
		kg.init(KEY_SIZE);
		// 生成密钥
		SecretKey secretKey = kg.generateKey();
		return secretKey;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密数据
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	private static String decrypt(byte[] key, byte[] data) throws Exception {
		if (data == null || key == null) {
			throw new IllegalArgumentException("解密参数错误");
		}
		// 还原密钥
		Key k = new SecretKeySpec(key, KEY_ALGORITHM);
		/**
		 * 实例化 使用PKCS7Padding填充方式，按如下方式实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		// 初始化，设置解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return new String(cipher.doFinal(data), "UTF-8");
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static String decrypt(String key, String data) throws Exception {
		if (data == null || key == null) {
			throw new IllegalArgumentException("解密参数错误");
		}
		return decrypt(key.getBytes("UTF-8"), Base64.decode(data, Base64.DEFAULT));
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return bytes[] 加密数据
	 * @throws Exception
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] key, byte[] data) throws Exception {
		if (data == null || key == null) {
			throw new IllegalArgumentException("加密参数错误");
		}
		// 还原密钥
		Key k = new SecretKeySpec(key, KEY_ALGORITHM);
		/**
		 * 实例化 使用PKCS7Padding填充方式，按如下方式实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static String encrypt(String key, String data) throws Exception {
		if (data == null || key == null) {
			throw new IllegalArgumentException("加密参数错误");
		}
		byte[] dataBytes = data.getBytes("UTF-8");
		byte[] keyBytes = key.getBytes();
		return Base64.encodeToString(encrypt(keyBytes, dataBytes), Base64.DEFAULT);
	}

	/**
	 * 初始化密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getKeyStr() throws Exception {
		return Base64.encodeToString(getKey().getEncoded(), Base64.DEFAULT);
	}

	public static void main(String[] args) throws Exception {
		String key = "l1S6eaPUyLz1yjgY";
		String wenjian = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		String enText="h+jvywxZc8wEjGX3W9K9dw==\r\n";
//		enText= URLDecoder.decode(enText,"utf-8");
		System.out.println(enText);
//		StringBuffer buffer = new StringBuffer();
//		for (int index = 0; index < 1; index++) {
//			buffer.append(wenjian);
//		}
//		String jimm = buffer.toString();

//		String mw = AESUtils.encrypt(key,jimm);
//		System.out.println("密文:" + mw);

		String jm = AESUtils.decrypt(key,enText);
		System.out.println("明文:" + jm);
	}
}
