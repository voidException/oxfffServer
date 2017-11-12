package org.geilove.controller;

/**
 * 微信登录，获取access_token，获取用户信息
 */
import org.geilove.dao.UserMapper;
import org.geilove.pojo.Message;
import org.geilove.pojo.Notification;
import org.geilove.pojo.User;
import org.geilove.service.MoneySourceService;
import org.geilove.service.PhoneService;
import org.geilove.service.RegisterLoginService;
import org.geilove.util.Response;
import org.geilove.utils.Configure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;
@RequestMapping(value="/wechat")
@Controller
public class WechatLoginController {
    @Resource
    private RegisterLoginService registerLoginService;
    @Resource
    private UserMapper  userMapper;
    @Resource
    private PhoneService phoneService ;

    public static final String WX_AUTH_LOGIN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String WX_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    //appid和appSecret 是在公众平台上申请的
    //AppId
    public static final String WX_APP_ID = "wxbdcf30c9232401a4";
    //AppSecret
    public static final String WX_APP_KEY = "68b8ef8f7c8e84e0d427e838d69a16c9";

    //@Autowired
   // private AccessTokenService accessTokenServiceImpl; //待实现

    @RequestMapping(value="/login.do",method= RequestMethod.POST)
    @ResponseBody
    public Object wechatlogin(HttpServletRequest request, HttpServletResponse response){
        Response<User> resp = new Response<User>();
        //String  state=request.getParameter("state"); //如何判断用户的请求是真实的？
        String  code=request.getParameter("code");
        String accessToken = null;
        String expiresIn = null;
        String openid=null;
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+Configure.putaohuzhuappid+"&secret="+Configure.putaohuzhuAppSecret+"&code="+code+"&grant_type=authorization_code";
        try {

            URL urlGet = new URL(url);

            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

            http.setRequestMethod("GET"); // 必须是get方式请求

            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            http.setDoOutput(true);

            http.setDoInput(true);

            http.connect();

            InputStream is = http.getInputStream();

            int size = is.available();

            byte[] jsonBytes = new byte[size];

            is.read(jsonBytes);

            String message = new String(jsonBytes, "UTF-8");

            JSONObject demoJson = JSONObject.fromObject(message);

            accessToken = demoJson.getString("access_token");
            expiresIn = demoJson.getString("expires_in");
            openid=demoJson.getString("openid");

            // System.out.println("accessToken===="+accessToken);
            //System.out.println("expiresIn==="+expiresIn);
            is.close();

            String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";

            URL url1 = new URL(getUserInfoUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)url1.openConnection();
            // 将返回的输入流转换成字符串
            InputStream inputStream = urlConnection.getInputStream();
            // 指定编码格式
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader in = new BufferedReader(inputStreamReader);
            String jsonUserStr =in.readLine().toString();
            System.out.println("jsonUserStr = "+jsonUserStr);
            // 释放资源
            inputStream.close();
            inputStream = null;
            urlConnection.disconnect();
            JSONObject userJsonInfo = JSONObject.fromObject(jsonUserStr);
            //System.out.println(userJsonInfo.get("headimgurl"));
            // 开发
            String userPhoto= userJsonInfo.getString("headimgurl");
            String nickname=userJsonInfo.getString("nickname");
            String sex=userJsonInfo.getString("sex");
            String city=userJsonInfo.getString("city");
            String province=userJsonInfo.getString("province");
            String country=userJsonInfo.getString("country");
            String unionid=userJsonInfo.getString("unionid"); //这个是不变的
            //根据unionid查询数据库，如果有此用户，就返回给app，否则就存入一份到数据库，然后返回给app
            User user;
            try {
                user=userMapper.getUserByunionid(unionid);
                if (user!=null){
                    resp.success(user);
                    return resp;
                }
            }catch (Exception e){

            }
            user = new User(); //存入数据库一份，返回给前端一份
            user.setUseruuid(UUID.randomUUID().toString());
            user.setUserphone(userPhoto);
            user.setUsernickname(nickname);
            user.setSex(sex);
            user.setCityname(city);
            user.setAddress(province);
            user.setCountry(country);
            user.setUnionid(unionid);
            user.setPhonebind("no");
            user.setRegisterdate(new Date());
            //存入数据库
            try {
                int insertTag=userMapper.insertSelective(user);
            }catch (Exception e){
            }
            resp.success(user);
            return resp;

        } catch (Exception e) {
            resp.failByException();
            return  resp;
        }
    }//login.do

    //微信登录后绑定手机号
    @RequestMapping(value="/wechatBindPhone.do",method= RequestMethod.POST)
    @ResponseBody
    public Object wechatBindPhone(HttpServletRequest request, HttpServletResponse response) {
        Response<User> resp = new Response<User>();
        String phone=request.getParameter("phone");
        String verifyCode=request.getParameter("verifyCode");
        String unionid=request.getParameter("unionid");
        String userPhoto=request.getParameter("userPhoto");
        String nickName=request.getParameter("nickName");

        try{
            Message message=phoneService.checkPhoneCode(phone,verifyCode);
            if (message==null){
                resp.failByNoData();
                resp.setMsg("验证码不对");
                return  resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        // 1.先查询手机号，如果有手机号
        User user=null;
        try{
            user=userMapper.getUserByPhone(phone);

        }catch (Exception e){

        }
        if (user==null){
            user=new User();
            user.setUserphone(phone);
            user.setUnionid(unionid);
            user.setPhonebind("yes");
            user.setWecharbind("yes");
            try{
               int uptag= userMapper.updateByPhone(user); //通过unionid进行更新
               if (uptag!=1){
                   resp.failByNoData();
                   resp.setMsg("更新失败");
                   return resp;
               }
            }catch (Exception e){
                resp.failByException();
                return resp;
            }
        }else {
            //手机号已经注册，那就把微信的信息迁移到手机号账户里
            user.setUnionid(unionid);
            user.setPhonebind("yes");
            user.setWecharbind("yes");
            user.setUserphone(userPhoto);
            user.setUsernickname(nickName);
            try{
                int updateTag=userMapper.updateByPhone(user);
                if (updateTag!=1){
                    resp.failByNoData();
                    resp.setMsg("更新失败");
                    return resp;
                }
            }catch (Exception e){
                resp.failByException();
                resp.setMsg("绑定抛出异常");
                return  resp;
            }
        }//else
        //家下来查询数据库，然后把最新的用户信息返回给App
        try{
            user=userMapper.selectByUserNickName(nickName);
            if (user!=null){
                resp.success(user);
                return  resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(user);
        return resp;
    } //wechatBindPhone
}
