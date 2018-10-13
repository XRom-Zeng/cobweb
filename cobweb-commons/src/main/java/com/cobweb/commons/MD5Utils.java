package com.cobweb.commons;

import com.google.common.primitives.Bytes;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    private static String salt = "XRom";

    /**
     * MD5密码加密
     *
     * @param pwd  密码
     * @param salt 盐值
     * @return
     */
    public static String encode(String pwd, String salt) {
        // 用于加密的字符
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        salt = StringUtils.isBlank(salt) ? MD5Utils.salt : salt;

        // 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
        MessageDigest mdInst = null;
        try {
            mdInst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        // MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
        mdInst.update(Bytes.concat(pwd.getBytes(), salt.getBytes()));

        // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
        byte[] md = mdInst.digest();

        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (byte byte0 : md) { // i = 0
            str[k++] = md5String[byte0 >>> 4 & 0xf]; // 5
            str[k++] = md5String[byte0 & 0xf]; // F
        }

        // 返回经过加密后的字符串
        return new String(str);
    }

}
