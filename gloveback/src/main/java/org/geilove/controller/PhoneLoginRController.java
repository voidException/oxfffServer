package org.geilove.controller;

/**
 *  葡萄互助------手机号注册，登录，验证
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
public class PhoneLoginRController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private PhoneService phoneService;
    @Resource
    private RegisterLoginService registerLoginService;

    //**************获取验证码*****************/
    @RequestMapping(value="/getCode.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getCode(HttpServletRequest  request){
        CommonRsp commonRsp=new CommonRsp();
        String phone=request.getParameter("phone");

        try {
            commonRsp=phoneService.getVerifyCode(phone);

        }catch (Exception e){
            commonRsp.setMsg("抛出异常");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        return  commonRsp;

    }


    /********************************手机号登录***********************/
    @RequestMapping(value="/login.do",method=RequestMethod.POST)
    @ResponseBody
    public Object mobileLogin(HttpServletRequest  request){
        UserProfileRsp userProfileRsp=new UserProfileRsp();

        String phone=request.getParameter("phone");
        String userPassword=request.getParameter("userPass");

        //校验手机号长度
        if (phone==null || phone.length()!=11 || userPassword.length() < 6 || userPassword.length() > 18){
            userProfileRsp.setRetcode(2001);
            userProfileRsp.setMsg("手机号长度或密码长度不对");
            return userProfileRsp;
        }
        //正则表达式校验手机号格式
        try{
            Boolean B=isPhoneLegal(phone);
            if (B==false){
                userProfileRsp.setMsg("手机格式不对");
                userProfileRsp.setRetcode(2001);
                return userProfileRsp;
            }
        }catch (Exception e){
            userProfileRsp.setMsg("校验手机格式抛出异常");
            userProfileRsp.setRetcode(2001);
            return userProfileRsp;
        }
        //校验密码
        String regPass = "^[0-9a-zA-Z]{5,17}$"; //邮箱密码的正则表达式
        Pattern patternPW = Pattern.compile(regPass);
        Matcher matcherPW = patternPW.matcher(userPassword);
        boolean pwb = matcherPW.matches();
        if (pwb == false) {
            userProfileRsp.setMsg("密码不符合格式");
            userProfileRsp.setRetcode(2001);
            return userProfileRsp;
        }
        User user;
        try{
            String passmd5 = Md5Util.getMd5(userPassword); //对密码进行加密
            Map<String,Object> map=new HashMap<>();
            map.put("phone",phone);
            map.put("passmd5",passmd5);
            user=userMapper.getUserByPhonePass(map);
            if (user==null){
                userProfileRsp.setMsg("用户不存在");
                userProfileRsp.setRetcode(2001);
                return userProfileRsp;
            }
        }catch (Exception e){
            userProfileRsp.setMsg("查询出现异常");
            userProfileRsp.setRetcode(2001);
            return userProfileRsp;
        }
        userProfileRsp.setMsg("成功");
        userProfileRsp.setRetcode(2000);
        userProfileRsp.setData(user);
        return  userProfileRsp;
    }
/********************************葡萄互助手机号注册***********************/

@RequestMapping(value="/register.do",method=RequestMethod.POST)
@ResponseBody
public Object mobileRegister(HttpServletRequest  request){
    CommonRsp commonRsp=new CommonRsp();

    String  phone=request.getParameter("phone"); //手机号
    String  userPassword=request.getParameter("userPassword"); //密码
    String  verifycode=request.getParameter("verifycode"); //验证码
    String  shareUserUUID=request.getParameter("shareUserUUID");
    commonRsp=phoneService.phoneRegister(phone,userPassword,verifycode,shareUserUUID);
    return commonRsp;
}
    /********************************手机号找回密码（重置密码）***********************/

    @RequestMapping(value="/resetpass.do",method=RequestMethod.POST)
    @ResponseBody
    public Object resetPasswd(HttpServletRequest  request){
        CommonRsp commonRsp=new CommonRsp();

        String  phone=request.getParameter("phone"); //手机号
        String  userPassword=request.getParameter("userPassword"); //密码
        String  verifycode=request.getParameter("verifycode"); //验证码
        // 1.先校验手机号的正确性
        //正则表达式校验手机号码
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
        // 2.校验密码的正确性
        String regPass = "^[0-9a-zA-Z]{5,17}$"; //邮箱密码的正则表达式
        Pattern patternPW = Pattern.compile(regPass);
        Matcher matcherPW = patternPW.matcher(userPassword);
        boolean pwb = matcherPW.matches();
        if (pwb == false) {
            commonRsp.setMsg("密码不符合格式");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        // 3.校验验证码格式，是否为4个数字
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(verifycode);
        if( !isNum.matches() ){
            commonRsp.setMsg("验证码格式不对");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }

        // 4.验证手机号和密码是否匹配
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

        User user = new User();
        user.setUserphone(phone);
       // user.setUserid(new Long(135));
        user.setUserpassword(MD5.string2MD5(userPassword)); //密码要加密
        try {
           // int aa= userMapper.updateByPrimaryKeySelective(user);
            int updateTag = userMapper.updateByPhone(user);
            if (updateTag != 1) {
                commonRsp.setMsg("密码重置失败");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        } catch (Exception e) {
            commonRsp.setMsg("密码重置抛出异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        commonRsp.setRetcode(2000);
        commonRsp.setMsg("更新密码成功");

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
