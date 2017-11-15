package org.geilove.controller;

/**
 * 适用于管家的登录注册找回密码
 */

import org.geilove.dao.UserMapper;
import org.geilove.pojo.Companyputao;
import org.geilove.pojo.Message;
import org.geilove.pojo.User;
import org.geilove.response.CommonRsp;
import org.geilove.response.UserProfileRsp;
import org.geilove.service.PhoneService;
import org.geilove.service.RegisterLoginService;
import org.geilove.util.MD5;
import org.geilove.util.Md5Util;
import org.geilove.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/phone")
public class PhoneKeeperLoginController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PhoneService phoneService;
    @Resource
    private RegisterLoginService registerLoginService;

    @RequestMapping(value="/keeperregister.do",method=RequestMethod.POST)
    @ResponseBody
    public Object mobileRegister(HttpServletRequest  request){
        CommonRsp commonRsp=new CommonRsp();
        String phone=request.getParameter("phone");
        String verifycode=request.getParameter("verifycode");
        String userPassword=request.getParameter("userPassword");
        String nickName=request.getParameter("nickName");
        String cityName=request.getParameter("cityName");
        // 1. 校验字段
        if (phone==null ||phone=="" ||verifycode==null || verifycode=="" ||
                userPassword==null ||userPassword=="" ||nickName==null || nickName=="" ||cityName==null ||cityName==""){
            commonRsp.setMsg("不能为空数据");
            return commonRsp;
        }

        try{
            Boolean B=isPhoneLegal(phone);
            if (B==false){
                commonRsp.setMsg("手机格式不对");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("校验手机格式抛出异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        // 校验密码的正确性
        String regPass = "^[0-9a-zA-Z]{5,17}$"; //邮箱密码的正则表达式
        Pattern patternPW = Pattern.compile(regPass);
        Matcher matcherPW = patternPW.matcher(userPassword);
        boolean pwb = matcherPW.matches();
        if (pwb == false) {
            commonRsp.setMsg("密码不符合格式");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        // 校验验证码格式，是否为4个数字
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(verifycode);
        if( !isNum.matches() ){
            commonRsp.setMsg("验证码格式不对");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        // 2.校验 手机号验证码匹配

        try{
            Message message = phoneService.checkPhoneCode(phone,verifycode);
            if (message==null){
                commonRsp.setMsg("验证码和手机号不匹配");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("查询验证码抛出异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        // 3. 查询是否已经注册，昵称，手机号有一个都不行
        Map<String, Object> map = new HashMap<>();

        map.put("phone", phone);
        map.put("nickName", nickName);

        User checkUser;
        try {
            checkUser = userMapper.selectByNicknameOrPhone(map);
            if (checkUser != null) {
                commonRsp.setMsg("用户昵称或手机已存在");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        } catch (Exception e) {
            commonRsp.setMsg(e.getMessage());
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        // 进行注册.返回注册的结果
        User userRegister = new User();
        String passmd5 = Md5Util.getMd5(userPassword); //对密码进行加密
        userRegister.setUseruuid(UUID.randomUUID().toString()); //用户的UUID
        userRegister.setUsernickname(nickName); //
        userRegister.setUserphone(phone);
        userRegister.setUserpassword(passmd5);
        userRegister.setUserphoto("http://www.geilove.org/path/geilove.png"); //默认头像地址
        userRegister.setPhotoupload((byte) 1);
        userRegister.setNotsay((byte) 1);
        userRegister.setCertificatetype((byte) 1);
        userRegister.setUsertype((byte) 1);
        userRegister.setNotsay((byte) 1);
        try{
            int registerTag= userMapper.insert(userRegister);
            if (registerTag!=1){
                commonRsp.setMsg("注册失败");
                commonRsp.setRetcode(2001);
                return  commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("注册抛出异常");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        commonRsp.setMsg("注册成功");
        commonRsp.setRetcode(2000);
        return commonRsp;
    }

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {
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
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}












