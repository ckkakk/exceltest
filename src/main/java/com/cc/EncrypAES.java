package com.rj.yltrash.buttjoint.utils;

import cn.hutool.core.util.HexUtil;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;


public class EncrypAES {

    //KeyGenerator 提供对称密钥生成器的功能，支持各种算法
    private KeyGenerator keygen;
    //SecretKey 负责保存对称密钥
    private SecretKey deskey;
    //Cipher 负责完成加密或解密工作
    private Cipher c;
    //该字节数组负责保存加密的结果
    private byte[] cipherByte;
    public EncrypAES(String secRan) throws NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        //实例化支持 DES 算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        String charset = "UTF-8";
        SecureRandom secureRandom =
                SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(secRan.getBytes(charset));
        keygen = KeyGenerator.getInstance("AES");
        keygen.init(128, secureRandom);
        //生成密钥
        deskey = keygen.generateKey();
        //生成 Cipher 对象,指定其支持的 DES 算法
        c = Cipher.getInstance("AES");
    }
    /**
     * 对字符串加密
     */
    public byte[] Encrytor(String str) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对 Cipher 对象进行初始化，ENCRYPT_MODE 表示加密模式
        c.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] src = str.getBytes();
        // 加密，结果保存进 cipherByte
        cipherByte = c.doFinal(src);
        return cipherByte;
    }
    /**
     * 对字符串解密
     */
    public byte[] Decryptor(byte[] buff) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对 Cipher 对象进行初始化，DECRYPT_MODE 表示加密模式
        c.init(Cipher.DECRYPT_MODE, deskey);
        cipherByte = c.doFinal(buff);
        return cipherByte;
    }

    public String decryptor(String d) throws Exception {
        byte[] bytes = Decryptor(HexUtil.decodeHex(d));
        return new String(bytes, "UTF-8");
    }
}
