package com.zhiguang.li.utils;


import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesUtils {
    public static String key = "8607176c";
    /**
     * 适用于用户中心的DES加密
     *
     * @param source 待加密字符串
     * @param key    DES密钥
     * @return 返回DES加密后并经过BASE64编码后的字符串
     */
    public static String qucDesEncryptStr(String source, String key) {
        String encryptedStr = "";
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            // using DES in CBC mode
            //CBC是工作模式，DES一共有电子密码本模式（ECB）、加密分组链接模式（CBC）、加密反馈模式（CFB）和输出反馈模式（OFB）四种模式，
            //PKCS5Padding是填充模式
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // 初始化Cipher对象
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            //三个参数：Cipher.ENCRYPT_MODE, key, zeroIv，  zeroIv就是初始化向量，一个8为字符数组。
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            // 执行加密操作
            byte encryptedData[] = cipher.doFinal(source.getBytes());

            // 通过Base64将二进制数据变成文本
            //encryptedStr = new String(Base64.encodeBase64(encryptedData));
            encryptedStr = Base64.encodeToString(encryptedData, Base64.DEFAULT);

        } catch (Exception e) {
        }
        return encryptedStr;
    }

    /**
     * 解密
     *
     * @param decryptString
     * @param decryptKey
     * @return
     */
    public static String decryptDES(String decryptString, String decryptKey) {
        byte decryptedData[] = new byte[0];
        try {
            byte[] byteMi = Base64.decode(decryptString,Base64.DEFAULT);
            DESKeySpec dks = new DESKeySpec(decryptKey.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            // using DES in CBC mode
            //CBC是工作模式，DES一共有电子密码本模式（ECB）、加密分组链接模式（CBC）、加密反馈模式（CFB）和输出反馈模式（OFB）四种模式，
            //PKCS5Padding是填充模式
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 初始化Cipher对象
            IvParameterSpec iv = new IvParameterSpec(decryptKey.getBytes());
            //三个参数：Cipher.ENCRYPT_MODE, key, zeroIv，  zeroIv就是初始化向量，一个8为字符数组。
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            decryptedData = cipher.doFinal(byteMi);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String(decryptedData);
    }

}
