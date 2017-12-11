package org.geilove.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import com.github.qcloudsms.*;
import org.json.JSONObject;

public class SmsSingleSendera {

    public  static void send() throws  Exception{
//        try {
//            SmsSingleSender sender = new   SmsSingleSender(1400053434, "5ea3d86f854fb6121a7b3cb92c1b1e9e");
//            SmsSingleSenderResult result = sender.send(0, "86", "15269969506", "【腾讯】验证码测试1234", "", "123");
//           // System.out.print(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //假设短信模板 id 为 123，模板内容为：测试短信，{1}，{2}，{3}，上学。
        SmsSingleSender sender = new SmsSingleSender(1400053434,"5ea3d86f854fb6121a7b3cb92c1b1e9e");
        ArrayList<String> params = new ArrayList<String>();
        //params.add("指定模板单发");
        params.add("您的"); //
        params.add("3912"); //这里换成验证码
        SmsSingleSenderResult   result = sender.sendWithParam("86", "15269969506", 64781, params, "", "", "");
        System.out.println(result);
    }
    public  static void  main(String[] args){
        try{
            send();
        }catch (Exception e){
        }
    }

}