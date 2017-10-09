package org.geilove.utils;

import java.util.Random;

/**
 * 随机字符串生成函数
 */
public class RandomStr {

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public  static  void  main(String[] args){
        String str=getRandomString(20);
        System.out.print(str);
    }

}
