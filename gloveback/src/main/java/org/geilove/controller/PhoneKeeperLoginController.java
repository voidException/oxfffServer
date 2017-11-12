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
        String phone=request.getParameter("phone");
        String verifycode=request.getParameter("verifycode");
        String userPassword=request.getParameter("userPassword");
        String nickName=request.getParameter("nickName");
        String cityName=request.getParameter("cityName");
        // 1. 校验字段

        // 2.校验 手机号验证码匹配

        // 3. 查询是否已经注册，昵称，手机号有一个都不行

        // 4.返回注册的结果

        return 0;
    }
}












