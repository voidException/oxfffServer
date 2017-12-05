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
import org.geilove.util.Md5Util;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import net.sf.json.JSONObject;

@RequestMapping(value = "/wechat")
@Controller
public class WechatLoginController {
    @Resource
    private RegisterLoginService registerLoginService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private PhoneService phoneService;

    public static final String WX_AUTH_LOGIN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String WX_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    //appid和appSecret 是在公众平台上申请的
    //AppId
    public static final String WX_APP_ID = "wxbdcf30c9232401a4";
    //AppSecret
    public static final String WX_APP_KEY = "68b8ef8f7c8e84e0d427e838d69a16c9";

    //@Autowired
    // private AccessTokenService accessTokenServiceImpl; //待实现

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public Object wechatlogin(HttpServletRequest request, HttpServletResponse response) {

        Response<User> resp = new Response<User>();
        //String  state=request.getParameter("state"); //如何判断用户的请求是真实的？
        String code = request.getParameter("code");
        String accessToken = null;
        String expiresIn = null;
        String openid = null;
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Configure.putaohuzhuappid + "&secret=" + Configure.putaohuzhuAppSecret + "&code=" + code + "&grant_type=authorization_code";
        try {

            URL urlGet = new URL(url);

            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

            http.setRequestMethod("GET"); // 必须是get方式请求

            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

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
            openid = demoJson.getString("openid");

            // System.out.println("accessToken===="+accessToken);
            //System.out.println("expiresIn==="+expiresIn);
            is.close();

            String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid + "&lang=zh_CN";

            URL url1 = new URL(getUserInfoUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
            // 将返回的输入流转换成字符串
            InputStream inputStream = urlConnection.getInputStream();
            // 指定编码格式
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader in = new BufferedReader(inputStreamReader);
            String jsonUserStr = in.readLine().toString();
            //System.out.println("jsonUserStr = "+jsonUserStr);
            // 释放资源
            inputStream.close();
            inputStream = null;
            urlConnection.disconnect();
            JSONObject userJsonInfo = JSONObject.fromObject(jsonUserStr);
            //System.out.println(userJsonInfo.get("headimgurl"));
            // 开发
            String userPhoto = userJsonInfo.getString("headimgurl");
            String nickname = userJsonInfo.getString("nickname");
            String sex = userJsonInfo.getString("sex");
            String city = userJsonInfo.getString("city");
            String province = userJsonInfo.getString("province");
            String country = userJsonInfo.getString("country");
            String unionid = userJsonInfo.getString("unionid"); //这个是不变的
            //根据unionid查询数据库，如果有此用户，就返回给app，否则就存入一份到数据库，然后返回给app
            User user;
            try {
                user = userMapper.getUserByunionid(unionid);
                if (user != null) {
                    String token = user.getUserpassword() + user.getUserid();
                    String userToken = user.getUserpassword() + user.getUseruuid();
                    user.setToken(token);
                    user.setUsertoken(userToken);
                    resp.success(user);
                    return resp;
                }
            } catch (Exception e) {

            }
            user = new User(); //存入数据库一份，返回给前端一份
            user.setUseruuid(UUID.randomUUID().toString());
            user.setUserphoto(userPhoto);
            user.setUsernickname(nickname);
            user.setSex(sex);
            user.setCityname(city);
            user.setAddress(province);
            user.setCountry(country);
            user.setUnionid(unionid);
            user.setPhonebind("no");
            user.setCertificatetype((byte) 0); //0无认证申请，1申请中，2申请通过
            user.setUsertype((byte) 1); //1 是普通用户，2是企业用户
            user.setRegisterdate(new Date());
            user.setPhotoupload((byte) 1); //这里无意义，统一用远程服务器的

            //存入数据库
            try {
                int insertTag = userMapper.insert(user);
                if (insertTag != 1) {
                    resp.failByNoData();
                    resp.setMsg("数据入库出错");
                    return resp;
                }
            } catch (Exception e) {

                resp.failByException();
                return resp;
            }

            resp.success(user);
            return resp;

        } catch (Exception e) {

            resp.failByException();
            return resp;
        }
    }//login.do

    //微信登录后绑定手机号
    @RequestMapping(value = "/wechatBindPhone.do", method = RequestMethod.POST)
    @ResponseBody
    public Object wechatBindPhone(HttpServletRequest request, HttpServletResponse response) {
        Response<User> resp = new Response<User>();
        String phone = request.getParameter("phone");
        String userPass = request.getParameter("userPass");
        String verifyCode = request.getParameter("verifyCode");
        String unionid = request.getParameter("unionid");
        String userPhoto = request.getParameter("userPhoto");
        String nickName = request.getParameter("nickName");

        if (phone == null || "".equals(phone) || phone.length() != 11 || userPass.length() < 6 || userPass.length() > 18) {
            resp.failByNoData();
            resp.setMsg("手机号或密码长度不对");
            return resp;
        }
        if (verifyCode == null || "".equals(verifyCode) || verifyCode.length() != 4) {
            resp.failByNoData();
            resp.setMsg("验证码长度不对");
            return resp;
        }
        //正则表达式校验手机号码
        try {
            Boolean B = isPhoneLegal(phone);
            if (B == false) {
                resp.failByNoData();
                resp.setMsg("手机格式不对");
                return resp;
            }
        } catch (Exception e) {
            resp.failByNoData();
            resp.setMsg("校验手机格式出现异常");
            return resp;
        }
        // 2.校验密码的正确性
        String regPass = "^[0-9a-zA-Z]{5,17}$"; //邮箱密码的正则表达式
        Pattern patternPW = Pattern.compile(regPass);
        Matcher matcherPW = patternPW.matcher(userPass);
        boolean pwb = matcherPW.matches();
        if (pwb == false) {
            resp.failByNoData();
            resp.setMsg("密码不符合格式");
            return resp;
        }
        // 3.校验验证码格式，是否为4个数字
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(verifyCode);
        if (!isNum.matches()) {
            resp.failByNoData();
            resp.setMsg("验证码不符合格式");
            return resp;
        }

        // 4.验证手机号和密码是否匹配
        User user = null;
        try {
            Message message = phoneService.checkPhoneCode(phone, verifyCode);
            if (message == null) {
                resp.failByNoData();
                resp.setMsg("验证码不对");
                return resp;
            }
        } catch (Exception e) {
            resp.failByException();
            return resp;
        }
        // 1.先查询手机号
        try {
            user = userMapper.getUserByPhone(phone);

        } catch (Exception e) {

        }
        if (user == null) { //手机号没注册，就把手机号绑定，
            user = new User();
            user.setUserphone(phone);
            user.setUnionid(unionid);
            String passmd5 = Md5Util.getMd5(userPass); //对密码进行加密
            user.setUserpassword(passmd5);

            user.setPhonebind("yes");

            try {
                int uptag = userMapper.updateByUnionid(user); //通过unionid进行更新
                if (uptag != 1) {
                    resp.failByNoData();
                    resp.setMsg("绑定失败");
                    return resp;
                }
            } catch (Exception e) {
                resp.failByException();
                return resp;
            }
        } else {
            //手机号已经注册，那就把微信的信息迁移到手机号账户里
            //迁移微信信息时，需要把微信登录时生成的临时微信用户删除
            try {
                int deleteWechatUser = userMapper.deleteUserByUnionid(unionid);
                if (deleteWechatUser == 1) {
                    user.setUnionid(unionid);
                    user.setPhonebind("yes");
                    user.setUserphone(phone);
                    user.setUserphoto(userPhoto);
                    user.setUsernickname(nickName);
                    int updateTag = userMapper.updateByPhone(user);
                    if (updateTag != 1) {
                        resp.failByNoData();
                        resp.setMsg("更新失败");
                        return resp;
                    }
                } else {
                    resp.failByException();
                    resp.setMsg("绑定失败");
                    return resp;
                }
            } catch (Exception e) {
                resp.failByException();
                resp.setMsg("绑定异常");
                return resp;
            }
        }//else
        //家下来查询数据库，然后把最新的用户信息返回给App
        try {
            user = userMapper.getUserByunionid(unionid);

            if (user != null) {
                String token = user.getUserpassword() + user.getUserid();
                String userToken = user.getUserpassword() + user.getUseruuid();
                user.setToken(token);
                user.setUsertoken(userToken);
                resp.success(user);
                return resp;
            } else {
                resp.failByNoData();
                return resp;
            }
        } catch (Exception e) {
            resp.failByException();
            return resp;
        }

    } //wechatBindPhone


    /************************************手机号校验方法*************************************************/
    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
