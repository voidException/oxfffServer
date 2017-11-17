package org.geilove.util;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * Md5 工具
 */
public class Md5Util {

    private static MessageDigest md5 = null;
    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static String getMd5(String str) {
        byte[] bs = md5.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for(byte x:bs) {
            if((x & 0xff)>>4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }
     //测试
    public static void main(String[] args) {
      //  System.out.println(getMd5("1234567891234567").toString().length());
        System.out.println( UUID.randomUUID().toString());
    }
}

