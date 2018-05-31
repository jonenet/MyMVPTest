package com.jess.arms.utils.cipher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import timber.log.Timber;


/**
 * Function: The type Rsa encryptor.
 * Created by dubin on 16/4/19.
 */
public class RSAEncryptor {

    /**
     * The constant RSA_PUBLIC_KEY.
     */
//	public String RSA_PRIVATE_KEY = "";
    public static String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDE0vrZ/0pXlkPd90k9TW89B6fx" +
            "or0ihR1ATJEsbeCA5HnZYs29KySHzmELshZ0CKAKBHoovF91brMxHCR+rqFa55ag" +
            "FGQcbfbMlCIYmkz4NXFp0AlzNdfhuHwuxMuOmLkwr/fvN5wB0kYmRoUXA/WAO126" +
            "H66JA/fo4SndarKw/QIDAQAB";

    /**
     * The constant RSA_ENCRYPT.
     */
    public static final int RSA_ENCRYPT = 0;
    /**
     * The constant RSA_OTHER_ENCRYPT.
     */
    @Deprecated
    public static final int RSA_OTHER_ENCRYPT = 1;
    /**
     * The constant RSA_OTHER_ENCRYPT.
     */
    public static final int RSA_OTHER_ENCRYPT_NEW = 2;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // String privateKeyPath = "D://rsa_public_key.pem"; // replace your
        // public key path here
        // String publicKeyPath = "D://pkcs8_private_key.pem"; // replace your
        // private path here

        try {

//             RSAEncryptor rsaEncryptor = new RSAEncryptor(privateKeyPath,
//             publicKeyPath);
            RSAEncryptor rsaEncryptor = new RSAEncryptor();
            String test = "JAVA";
            String testRSAEnWith64 = rsaEncryptor.encryptWithBase64(test);
            String testRSADeWith64 =
                    rsaEncryptor.decryptWithBase64("eCxzMcojnFt08gVqMwOmeVxMz1jgzO0DwaxQs3zx8Butj2CMX/bD4r21awBCiwzVB9Cu8xxU2fNzbjGRQ7X0YKFp+msBGX/8teTgnl0TdsX16YmrDVqB51cMha4QFd4fsqRFTOTmybkPWjMFjpe4GpvSeo+mXQKDD0ZBOrCyB5o=");
//			String testRSADeWith64 = rsaEncryptor
//					.decryptWithBase64("ZaNv5YnP6qRFgejnU/YFNcod/12hUqjJ3LoqD4RxiOdn+Hpv6MhcbnrCH0QYYv/fWyaP+d880w3UdV0lW1dJTXoa5XzEdCR/W6XZMLUkYx71RcYwTdtaIwTn6XFBF4rEpQcs8G0OUrcTrl25G7OrYeG1rIFdQszPvvMPLpoIUfo=");
            System.out.println("\nEncrypt: \n" + testRSAEnWith64);
            System.out.println("\nDecrypt: \n" + testRSADeWith64);

//             NSLog the encrypt string from Xcode , and paste it here.
//             请粘贴来自IOS端加密后的字符串
            String rsaBase46StringFromIOS =
                    "nIIV7fVsHe8QquUbciMYbbumoMtbBuLsCr2yMB/WAhm+S/kGRPlf+k2GH8imZIYQ"
                            + "\r" +
                            "QBDssVLQmS392QlxS87hnwMRJIzWw6vdRv/k79TgTfu6tI/9QTqIOvNlQIqtIcVm"
                            + "\r" +
                            "R/suvydoymKgdlB+ce5/tHSxfqEOLLrL1Zl2PqJSP4A=";

            String decryptStringFromIOS =
                    rsaEncryptor.decryptWithBase64(rsaBase46StringFromIOS);
            System.out.println("Decrypt result from ios client: \n" +
                    decryptStringFromIOS);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Instantiates a new Rsa encryptor.
     *
     * @param publicKeyFilePath  the public key file path
     * @param privateKeyFilePath the private key file path
     * @throws Exception the exception
     */
    public RSAEncryptor(String publicKeyFilePath, String privateKeyFilePath)
    {
        /*
         * String public_key = getKeyFromFile(publicKeyFilePath);
		 * System.out.println("public_key:"+public_key); String private_key =
		 * getKeyFromFile(privateKeyFilePath);
		 * System.out.println("private_key:"+private_key);
		 */
        loadPublicKey(RSA_PUBLIC_KEY);
//		loadPrivateKey(RSA_PRIVATE_KEY);
    }

    public RSAEncryptor(String publicKeyFilePath)
            throws Exception {
        RSA_PUBLIC_KEY = publicKeyFilePath;
        loadPublicKey(RSA_PUBLIC_KEY);
    }

    /**
     * Instantiates a new Rsa encryptor.
     *
     * @throws Exception the exception
     */
    public RSAEncryptor() throws Exception {
//		RSA_PRIVATE_KEY = AppContext.getRsaPrivateKey();
//		loadPrivateKey(RSA_PRIVATE_KEY);
        loadPublicKey(RSA_PUBLIC_KEY);
        // load the PublicKey and PrivateKey manually
    }

    /**
     * Gets key from file.
     *
     * @param filePath the file path
     * @return the key from file
     * @throws Exception the exception
     */
    public String getKeyFromFile(String filePath) throws Exception {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(
                    filePath));

            String line = null;
            List list = new ArrayList();
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }

            // remove the firt line and last line
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < list.size() - 1; i++) {
                stringBuilder.append(list.get(i)).append("\r");
            }

            String key = stringBuilder.toString();
            return key;
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    /**
     * Decrypt with base 64 string.
     *
     * @param base64String the base 64 string
     * @return the string
     * @throws Exception the exception
     */
    public String decryptWithBase64(String base64String) throws Exception {
        // http://commons.apache.org/proper/commons-codec/ :
        // org.apache.commons.codec.binary.Base64
        // sun.misc.BASE64Decoder
        byte[] binaryData = decrypt(getPrivateKey(),
                Base64.decode(base64String, Base64.DEFAULT)
                /*
                                                                 * org.apache.
																 * commons
																 * .codec.
																 * binary.
																 * Base64.
																 * decodeBase64
																 * (base46String
																 * .getBytes())
																 */);
        String string = new String(binaryData);
        return string;
    }

    /**
     * Encrypt with base 64 string.
     *
     * @param string the string
     * @return the string
     * @throws Exception the exception
     */
    public String encryptWithBase64(String string) throws Exception {
        // http://commons.apache.org/proper/commons-codec/ :
        // org.apache.commons.codec.binary.Base64
        // sun.misc.BASE64Encoder
        byte[] binaryData = encrypt(getPublicKey(), string.getBytes());
        String base64String = Base64.encodeToString(binaryData, Base64.DEFAULT) /*
																			 * org.
																			 * apache
																			 * .
																			 * commons
																			 * .
																			 * codec
																			 * .
																			 * binary
																			 * .
																			 * Base64
																			 * .
																			 * encodeBase64
																			 * (
																			 * binaryData
																			 * )
																			 */;
//		XLog.e("加密：", base64String);
        return base64String;
    }

    /**
     * Encrypt with base 64 string.
     *
     * @param string      the string
     * @param encryptKind the encrypt kind
     * @return the string
     * @throws Exception the exception
     */
    public String encryptWithBase64(String string, int encryptKind) throws Exception {
        // http://commons.apache.org/proper/commons-codec/ :
        // org.apache.commons.codec.binary.Base64
        // sun.misc.BASE64Encoder

        switch (encryptKind) {
            case RSA_ENCRYPT:
                loadPublicKey(RSA_PUBLIC_KEY);
                break;

            case RSA_OTHER_ENCRYPT:
                loadPublicKey(RSA_PUBLIC_KEY);
                break;
            case RSA_OTHER_ENCRYPT_NEW:
                loadPublicKey(RSA_PUBLIC_KEY);
                break;
            default:
                break;
        }

        byte[] binaryData = encrypt(getPublicKey(), string.getBytes());
        String base64String = Base64.encodeToString(binaryData, Base64.DEFAULT) /*
																			 * org.
																			 * apache
																			 * .
																			 * commons
																			 * .
																			 * codec
																			 * .
																			 * binary
																			 * .
																			 * Base64
																			 * .
																			 * encodeBase64
																			 * (
																			 * binaryData
																			 * )
																			 */;
//		XLog.e("加密：", base64String);
        return base64String;
    }

    /**
     * The constant sharedInstance.
     */
// convenient properties
    public static RSAEncryptor sharedInstance = null;

    /**
     * Sets shared instance.
     *
     * @param rsaEncryptor the rsa encryptor
     */
    public static void setSharedInstance(RSAEncryptor rsaEncryptor) {
        sharedInstance = rsaEncryptor;
    }

    // From: http://blog.csdn.net/chaijunkun/article/details/7275632

    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;

    /**
     * 公钥
     */
    private RSAPublicKey publicKey;

    /**
     * 获取私钥
     *
     * @return 当前的私钥对象 private key
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * 获取公钥
     *
     * @return 当前的公钥对象 public key
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * 随机生成密钥对
     */
    public void genKeyPair() {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA/None/PKCS1Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGen.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param in 公钥输入流
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPublicKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(String publicKeyStr) {
        try {
//            BASE64Decoder base64Decoder = new BASE64Decoder();
//            byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
            byte[] buffer = Base64.decode(publicKeyStr, Base64.DEFAULT);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            this.publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            Timber.e("无此算法");
        } catch (InvalidKeySpecException e) {
            Timber.e("公钥非法");
//        } catch (IOException e) {
//            throw new Exception("公钥数据内容读取错误");
        } catch (NullPointerException e) {
            Timber.e("公钥数据为空");
        }
    }

    /**
     * 从文件中加载私钥
     *
     * @param in the in
     * @throws Exception the exception
     */
    public void loadPrivateKey(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPrivateKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    /**
     * Load private key.
     *
     * @param privateKeyStr the private key str
     * @throws Exception the exception
     */
    public void loadPrivateKey(String privateKeyStr) throws Exception {
        try {
//            BASE64Decoder base64Decoder = new BASE64Decoder();
//            byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
            byte[] buffer = Base64.decode(privateKeyStr, Base64.DEFAULT);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory
                    .getInstance("RSA");
            this.privateKey = (RSAPrivateKey) keyFactory
                    .generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new Exception("私钥非法");
//        } catch (IOException e) {
//            throw new Exception("私钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 加密过程
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return byte [ ]
     * @throws Exception 加密过程中的异常信息
     */
    public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)
            throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/None/PKCS1Padding");// , new
            // BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文 byte [ ]
     * @throws Exception 解密过程中的异常信息
     */
    public byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)
            throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/None/PKCS1Padding");// , new
            // BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 字节数据转十六进制字符串
     *
     * @param data 输入数据
     * @return 十六进制内容 string
     */
    public static String byteArrayToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
            // 取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i < data.length - 1) {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }

}
