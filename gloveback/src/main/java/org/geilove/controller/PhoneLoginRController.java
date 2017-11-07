package org.geilove.controller;

/**
 * 手机号注册，登录，验证
 */
import org.geilove.dao.UserMapper;
import org.geilove.pojo.User;
import org.geilove.response.UserProfileRsp;
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
    /**手机号登录*/
    @RequestMapping(value="/login.do",method=RequestMethod.POST)
    @ResponseBody
    public Object mobileLogin(HttpServletRequest  request){
        UserProfileRsp userProfileRsp=new UserProfileRsp();

        String phone=request.getParameter("phone");
        String userPass=request.getParameter("userPass");
        if (phone==null || phone.length()!=11){
            userProfileRsp.setRetcode(2001);
            userProfileRsp.setMsg("手机号码有误");
        }
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
        User user;
        try{
            user=userMapper.getUserByPhone(phone);
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
