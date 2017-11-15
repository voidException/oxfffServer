
package org.geilove.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.geilove.dao.OpenidUserMapper;
import org.geilove.dao.UserMapper;
import org.geilove.pojo.*;
import org.geilove.requestParam.*;
import org.geilove.service.LinkService;
import org.geilove.service.RegisterByShareService;
import org.geilove.service.SelRedMoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.geilove.vo.UserLoginVo;
import org.geilove.service.RegisterLoginService;
import org.geilove.util.MD5;
import org.geilove.util.Md5Util;
import org.geilove.vo.UserRegisterVo;
import org.geilove.response.UserProfileRsp;
import org.geilove.response.CommonRsp;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.geilove.util.DesUtil;
import org.springframework.web.servlet.ModelAndView;

import static org.geilove.util.DesUtil.decrypt;
import static org.geilove.util.DesUtil.encrypt;

@Controller
@RequestMapping("/user")
public class RegisterLoginController {

    @Resource
    private RegisterLoginService registerLoginService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private OpenidUserMapper openidUserMapper;

    @Resource
    private LinkService linkService;

    @Resource
    private RegisterByShareService registerByShareService;

    @Resource
    private SelRedMoneyService selRedMoneyService;

    class LinkThread extends Thread {

        public SubOpenIDEmailParam subOpenIDEmailParam;

        public LinkThread(SubOpenIDEmailParam subOpenIDEmailParam) {  //构造方法

            this.subOpenIDEmailParam = subOpenIDEmailParam;
        }

        public void run() {
            linkService.linkEmail(this.subOpenIDEmailParam);
            System.out.print(subOpenIDEmailParam.getEmail());
        }
    }

    //登录
    @RequestMapping(value = "/login.do", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    UserProfileRsp loginUser(@RequestBody UserLoginVo userLoginVo, HttpServletResponse httpServletResponse) {
//		httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        //这里应该先验证用户邮箱和密码是不是符合要求，避免浪费资源查询数据库
        UserProfileRsp userProfileRsp = new UserProfileRsp();
        User user = registerLoginService.userLogin(userLoginVo.getUserEmail());
        if (user == null) {
            userProfileRsp.setData(null);
            userProfileRsp.setMsg("不存在此用户");
            userProfileRsp.setRetcode(2001); //不存在此用户
        } else {
            String pw = Md5Util.getMd5(userLoginVo.getUserPassword());
            if (pw.equals(user.getUserpassword())) {
                String token = pw + user.getUserid();
                user.setBackupfour(token);
                userProfileRsp.setData(user);
                userProfileRsp.setMsg("登录成功");
                userProfileRsp.setRetcode(2000);
            } else {
                userProfileRsp.setData(null);
                userProfileRsp.setMsg("您输入的密码不对");
                userProfileRsp.setRetcode(2002); //用户密码错误
            }
        }
        return userProfileRsp;
    }

    // ***************注册，注册的时候应该在UserInfo表中加入一条记录，收听几个官方账号 ************
    /* *****************其它从User表分出去的功能表也要加入新记录
	* */
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public @ResponseBody
    CommonRsp registerUser(@RequestBody UserRegisterVo userRegisterVo, HttpServletResponse httpServletResponse) {
//		httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        //这里假设昵称唯一可用，邮箱可用，两次输入的密码一样
        //如果可以注册，应对密码md5加密
        CommonRsp commonRsp = new CommonRsp();
        User userRegister = new User();
        String userEmail = userRegisterVo.getUserEmail();
        String userPassword = userRegisterVo.getUserPassword();
        String userNickName = userRegisterVo.getUserNickName();
        if (userEmail.length() < 10 || userEmail.length() > 30 || userPassword.length() < 6 || userPassword.length() > 18 || userNickName.length() < 2 || userNickName.length() > 30) {
            commonRsp.setMsg("长度不合法");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        String regEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        String regPass = "^[0-9a-zA-Z]{5,17}$"; //邮箱密码的正则表达式
        Pattern pattern = Pattern.compile(regEmail);
        Matcher matcher = pattern.matcher(userEmail);
        boolean emailb = matcher.matches();
        if (emailb == false) {
            commonRsp.setMsg("邮箱不符合格式");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }

        Pattern patternPW = Pattern.compile(regPass);
        Matcher matcherPW = patternPW.matcher(userPassword);
        boolean pwb = matcherPW.matches();
        if (pwb == false) {
            commonRsp.setMsg("密码不符合格式");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        //这里对userPassword 加密存入数据库
        String pwMD5 = Md5Util.getMd5(userRegisterVo.getUserPassword());
        userRegister.setUseruuid(UUID.randomUUID().toString()); //用户的UUID
        userRegister.setUsernickname(userRegisterVo.getUserNickName());
        userRegister.setUseremail(userRegisterVo.getUserEmail());
        userRegister.setUserpassword(pwMD5);
        userRegister.setBackuptwo(userRegisterVo.getCityName()); //用户所处的城市
        userRegister.setUserphoto("http://www.geilove.org/path/geilove.png");
        userRegister.setPhotoupload((byte) 1);
        userRegister.setNotsay((byte) 1);
        userRegister.setCertificatetype((byte) 1);
        userRegister.setUsertype((byte) 1);
        userRegister.setNotsay((byte) 1);
        //这里需要先查询是否有该邮箱和昵称的用户已经注册
        Map<String, Object> map = new HashMap<>();

        map.put("userEmail", userEmail);
        map.put("userNickname", userNickName);

        User checkUser;
        try {
            checkUser = registerLoginService.selectByNicknameOrEmail(map);
            if (checkUser != null) {
                commonRsp.setMsg("用户昵称或邮箱已存在");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        } catch (Exception e) {
            commonRsp.setMsg(e.getMessage());
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        try {
           int tag = registerLoginService.userRegister(userRegister);
           if (tag==0){
               commonRsp.setMsg("注册失败");
               commonRsp.setRetcode(2001);
               return  commonRsp;
           }
        } catch (Exception e) {
            e.printStackTrace();
            commonRsp.setMsg("注册出现问题");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        commonRsp.setRetcode(2000);
        return commonRsp;
    }

    /* 1、 跳转到用户输入注册的邮箱  */
    @RequestMapping(value = "/putEmail.do", method = RequestMethod.GET)
    public String putEmail() {
        String index = "front/putEmail";
        return index;
    }

    /* 2. 这个是找回密码。这里接受邮箱，校验后，发送重置密码的链接到用户邮箱*/
    @RequestMapping(value = "/findpassword.do", method = RequestMethod.POST)
    public @ResponseBody
    CommonRsp findPassword(@RequestBody FindpwParam param) throws MessagingException {
        CommonRsp rsp = new CommonRsp();
        String userEmail = param.getUserEmail();

        if (userEmail.length() < 10 || userEmail.length() > 30) {
            rsp.setMsg("邮箱长度不合法");
            rsp.setRetcode(2001);
            return rsp;
        }
        String regEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regEmail);
        Matcher matcher = pattern.matcher(userEmail);
        boolean emailb = matcher.matches();
        if (emailb == false) {
            rsp.setMsg("邮箱不符合格式");
            rsp.setRetcode(2001);
            return rsp;
        }
        String userPassword = null;
        userPassword = registerLoginService.findPasswd(userEmail);
        if (userPassword == null) {
            rsp.setMsg("不存在此用户");
            rsp.setRetcode(2001);
            return rsp;
        }

        String encryptEmail = ""; //放入超链接当中
        String key = "xxoolqqyywyl@@**^^";  //重要，密钥(加密)
        try {
            encryptEmail = encrypt(userEmail, key);
            //userEmail=decrypt(encrypt(userEmail, key), key); //解密
        } catch (Exception e) {
            rsp.setMsg("加密抛出异常");
            rsp.setRetcode(2001);
            return rsp;
        }

        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.126.com");
        // 发件人的账号
        props.put("mail.user", "aixinteam@126.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "818ooXXaa$$");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(userEmail);
        message.setRecipient(RecipientType.TO, to);

        message.setSubject("互助管家找回密码(复制链接在浏览器打开)");
        // String  Url="http://www.geilove.org/glove/user/"+encryptEmail+"/findPasswordUrl.do";
        String Url = "http://localhost:8080/glove/user/" + encryptEmail + "/findPasswordUrl.do";
        // 应该做一个网页，让用户重置密码,这个超链接的参数是加密的用户邮箱和时间参数，用户点击后进入新的页面，然后提交的时候获得2个参数发送到服务器端
        message.setContent("<div>" + Url + "</div>", "text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
        rsp.setMsg("发送成功");
        rsp.setRetcode(2000);
        return rsp;
    }

    /* 3. 用户点击邮箱中的超链接，此路由处理超链接。  encryptEmail是用户邮箱的加密*/
    @RequestMapping(value = "/{encryptEmail}/findPasswordUrl.do", method = RequestMethod.GET)
    public ModelAndView findPasswordUrl(@PathVariable String encryptEmail) {
        //解密邮箱，放回到findPasswordUrl.jsp 中
        Map<String, String> model = new HashMap();
        model.put("result", encryptEmail);
        ModelAndView modelAndView = new ModelAndView("front/findPasswordUrl", model);
        return modelAndView;
    }

    /* 4. 从findPasswordUrl.jsp 中取得用户的加密邮箱，和输入的两次新密码，执行密码修改（更新）*/
    @RequestMapping(value = "/changePasswd.do", method = RequestMethod.POST)
    @ResponseBody
    public CommonRsp changePasswd(@RequestBody ChangePwParam changePwParam) {
        CommonRsp commonRsp = new CommonRsp();
        if (changePwParam == null) {
            commonRsp.setMsg("参数不能为空");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        String encryptEmail = changePwParam.getEncryptEmail();
        String againPass = changePwParam.getAgainPass();
        String originPass = changePwParam.getOriginPass();
        if (encryptEmail == null || againPass == null || originPass == null) {
            commonRsp.setMsg("参数不全");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        if (!originPass.equals(againPass)) {
            commonRsp.setMsg("密码不一致");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }

        String key = "xxoolqqyywyl@@**^^";  //重要，密钥（解密）
        String userEmail;
        try {
            userEmail = decrypt(encryptEmail, key);  //解密
        } catch (Exception e) {
            commonRsp.setMsg("解密抛出异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        //解密后检查邮箱是否符合要求
        String regEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regEmail);
        Matcher matcher = pattern.matcher(userEmail);
        boolean emailb = matcher.matches();
        if (!emailb) {
            commonRsp.setMsg("邮箱不符合格式");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        String regPass = "^[0-9a-zA-Z]{5,17}$"; //邮箱密码的正则表达式
        Pattern patternPW = Pattern.compile(regPass);
        Matcher matcherPW = patternPW.matcher(originPass);
        boolean pwb = matcherPW.matches();
        if (!pwb) {
            commonRsp.setMsg("密码不符合格式");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userEmail", userEmail);
        User checkUser;
        try {
            checkUser = registerLoginService.selectByNicknameOrEmail(map);
        } catch (Exception e) {
            commonRsp.setMsg("用户检查抛出异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        if (checkUser == null) {
            commonRsp.setMsg("无此用户");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        Long userid = checkUser.getUserid();
        //接下来执行密码更新
        User user = new User();
        user.setUserid(userid);
        user.setUserpassword(MD5.string2MD5(originPass)); //密码要加密
        try {

            int updateTag = registerLoginService.updateUserSelective(user);
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
        commonRsp.setMsg("密码重置成功");
        commonRsp.setRetcode(2000);
        return commonRsp;
    }

    /****************************修改密码********************************************************/
    // 1.跳转到修改密码页面 //
    @RequestMapping(value = "/resetPassword.do", method = RequestMethod.GET)
    public String mobileMainPage() {
        String index = "front/resetPasswd";
        return index;
    }
    // 2.修改密码  //适合于web公众号，注意@RequestBody ResetPasswdParam resetPasswdParam 导致app的form方法不能使用

    @RequestMapping(value = "/resetpass.do", method = RequestMethod.POST)
    public @ResponseBody
    CommonRsp resetPassword(@RequestBody ResetPasswdParam resetPasswdParam, HttpServletRequest request) {
        System.out.print("resetpass.do");
        CommonRsp commonRsp = new CommonRsp();
        //String  originPass=request.getParameter("originPass");
        //String  newPass=request.getParameter("newPass");
        //String  againPass=request.getParameter("againPass");
        //String  token=request.getParameter("token");
        String originPass = resetPasswdParam.getOriginPass();
        String newPass = resetPasswdParam.getNewPass();
        String againPass = resetPasswdParam.getAgainPass();
        String token = resetPasswdParam.getToken();
        String userPassword = "";
        String useridStr = "";
        Long userid = 0L;
        String passwdTrue = "";
        try {
            userPassword = token.substring(0, 32); //token是password和userID拼接成的。
            useridStr = token.substring(32);
            userid = Long.valueOf(useridStr).longValue();
            passwdTrue = registerLoginService.selectMD5Password(Long.valueOf(userid));

        } catch (Exception e) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("非法操作");
            return commonRsp;
        }


        if (!userPassword.equals(passwdTrue)) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户身份验证失败");
            return commonRsp;
        }
        String md5pass = MD5.string2MD5(originPass); //对原始密码加密
        if (!md5pass.equals(passwdTrue)) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户密码不对，非法");
            return commonRsp;
        }
        if (!againPass.equals(newPass)) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("两次输入新密码不一致");
            return commonRsp;
        }
        if (newPass.length() < 6 || newPass.length() > 18 || againPass.length() < 6 || againPass.length() > 18) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("新密码长度不合法");
            return commonRsp;
        }
        String regPass = "^[0-9a-zA-Z]{5,17}$"; //邮箱密码的正则表达式
        Pattern patternPW = Pattern.compile(regPass);
        Matcher matcherNewPW = patternPW.matcher(newPass);
        Matcher matcherAgainPW = patternPW.matcher(newPass);

        boolean pwb = matcherNewPW.matches();
        boolean againpwb = matcherAgainPW.matches();
        if (pwb == false || againpwb == false) {
            commonRsp.setMsg("密码不符合格式");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        //接下来调用更新user表的方法，对密码进行更新
        User user = new User();
        user.setUserid(userid);
        user.setUserpassword(MD5.string2MD5(newPass)); //密码要加密
        try {
            int tag = registerLoginService.updateUserSelective(user);
            if (tag != 1) {
                commonRsp.setMsg("密码更新失败");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        } catch (Exception e) {
            commonRsp.setMsg("密码更新出现异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        commonRsp.setMsg("密码更新成功");
        commonRsp.setRetcode(2000);
        return commonRsp;
    }

    @RequestMapping(value = "/resetpassapp.do", method = RequestMethod.POST)
    public @ResponseBody
    CommonRsp resetPasswordApp(HttpServletRequest request) {
        //System.out.print("resetpass.do");
        CommonRsp commonRsp = new CommonRsp();
        String originPass = request.getParameter("originPass");
        String newPass = request.getParameter("newPass");
        String againPass = request.getParameter("againPass");
        String token = request.getParameter("token");

        String userPassword = "";
        String useridStr = "";
        Long userid = 0L;
        String passwdTrue = "";
        try {
            userPassword = token.substring(0, 32); //token是password和userID拼接成的。
            useridStr = token.substring(32);
            userid = Long.valueOf(useridStr).longValue();
            passwdTrue = registerLoginService.selectMD5Password(Long.valueOf(userid));

        } catch (Exception e) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("非法操作");
            return commonRsp;
        }


        if (!userPassword.equals(passwdTrue)) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户身份验证失败");
            return commonRsp;
        }
        String md5pass = MD5.string2MD5(originPass); //对原始密码加密
        if (!md5pass.equals(passwdTrue)) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户密码不对，非法");
            return commonRsp;
        }
        if (!againPass.equals(newPass)) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("两次输入新密码不一致");
            return commonRsp;
        }
        if (newPass.length() < 6 || newPass.length() > 18 || againPass.length() < 6 || againPass.length() > 18) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("新密码长度不合法");
            return commonRsp;
        }
        String regPass = "^[0-9a-zA-Z]{5,17}$"; //邮箱密码的正则表达式
        Pattern patternPW = Pattern.compile(regPass);
        Matcher matcherNewPW = patternPW.matcher(newPass);
        Matcher matcherAgainPW = patternPW.matcher(newPass);

        boolean pwb = matcherNewPW.matches();
        boolean againpwb = matcherAgainPW.matches();
        if (pwb == false || againpwb == false) {
            commonRsp.setMsg("密码不符合格式");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        //接下来调用更新user表的方法，对密码进行更新
        User user = new User();
        user.setUserid(userid);
        user.setUserpassword(MD5.string2MD5(newPass)); //密码要加密
        try {
            int tag = registerLoginService.updateUserSelective(user);
            if (tag != 1) {
                commonRsp.setMsg("密码更新失败");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        } catch (Exception e) {
            commonRsp.setMsg("密码更新出现异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        commonRsp.setMsg("密码更新成功");
        commonRsp.setRetcode(2000);
        return commonRsp;
    }

    //通过用户的昵称，获得用户的基本资料信息
    @RequestMapping(value = "/getprofile/bynickname.do", method = RequestMethod.POST)
    public @ResponseBody
    UserProfileRsp getInfoByUserName(HttpServletRequest request) {
        UserProfileRsp userProfileRsp = new UserProfileRsp();
        String nickname = request.getParameter("nickname"); //获得用户的昵称
        //根据昵称获取用户的基本资料
        User user = new User();
        try {
            user = registerLoginService.byAtUserProfile(nickname);
            if (user == null) {
                userProfileRsp.setMsg("不存在此用户");
                userProfileRsp.setRetcode(2001);
                userProfileRsp.setData(null);
                return userProfileRsp;
            }
        } catch (Exception e) {
            userProfileRsp.setData(user);
            userProfileRsp.setMsg("获取用户资料抛出异常");
            userProfileRsp.setRetcode(2001);
        }
        userProfileRsp.setData(user);
        userProfileRsp.setMsg("根据@获取用户信息成功了");
        userProfileRsp.setRetcode(2000);
        return userProfileRsp;
    }

    //用户进入公众号的"我" 关联openId和userEmail
    @RequestMapping(value = "/openidemail.do", method = RequestMethod.POST)
    public @ResponseBody
    UserProfileRsp openIdlinkEmail(@RequestBody Openidemail openidemail) {
        UserProfileRsp userProfileRsp = new UserProfileRsp();
        //OpenidToemailParam openidToemailParam=new OpenidToemailParam();
        String email = openidemail.getEmail();
        String token = openidemail.getToken();
        String openId = openidemail.getOpenId();
        //用户可能在微信之外打开此页面
        if (openId == null) {
            userProfileRsp.setRetcode(2001);
            userProfileRsp.setMsg("请在微信中打开此页面");
            userProfileRsp.setData(null);
            return userProfileRsp;
        }
        // 1.用openId 查user表
        User user = new User();
        try {
            user = userMapper.selectByopenId(openId); //待实现，帮助人user
        } catch (Exception e) {
            //抛出异常了，但不应该阻止，这里记录日志
        }

        if (user != null) {
            userProfileRsp.setData(user);
            userProfileRsp.setMsg("成功");
            userProfileRsp.setRetcode(2000);
            return userProfileRsp;
        }
        //2. 代码走到这里，说明没有关联email，用openId查openIdUser表，
        OpenidUser openidUser = null;
        try {
            openidUser = openidUserMapper.selectByOpenId(openId);
        } catch (Exception e) {
            //记录日志，这里不终止程序，openidUser一定是null,因为下面需要关联
        }

        if (openidUser == null) { //用户未曾捐钱过
            if (email != null && token != null && openId != null) { //未曾捐钱，但有账号，也应该关联
                //开启线程，关联
                SubOpenIDEmailParam openidTemail = new SubOpenIDEmailParam();
                openidTemail.setEmail(email);
                openidTemail.setOpenId(openId);
                openidTemail.setToken(token);
                //开启线程
                LinkThread linkThread = new LinkThread(openidTemail);
                linkThread.start();
                user.setAmountaccept(0);
                user.setUserdonate(0);
                user.setUserhelpsman(0);
                user.setAcceptmoney(0);
                userProfileRsp.setData(user);
                userProfileRsp.setRetcode(2000);
                userProfileRsp.setMsg("数据获取成功");
                return userProfileRsp;
            } else {
                userProfileRsp.setRetcode(2001);
                userProfileRsp.setMsg("请求数据为空");
                return userProfileRsp;
            }

        }
        if (openidUser != null) { //用户捐钱

            SubOpenIDEmailParam openidTemail = new SubOpenIDEmailParam();
            openidTemail.setEmail(email);
            openidTemail.setOpenId(openId);
            openidTemail.setToken(token);
            openidTemail.setUsertotaldonate(openidTemail.getUsertotaldonate());
            openidTemail.setUsertotalhelp(openidTemail.getUsertotalhelp());
            //开启线程
            LinkThread linkThread = new LinkThread(openidTemail);
            linkThread.start();

            user.setAcceptmoney(0);
            user.setAmountaccept(0);
            user.setUserdonate(openidUser.getUsertotaldonate());
            user.setUserhelpsman(openidUser.getUsertotalhelp());
            userProfileRsp.setMsg("获取数据成功");
            userProfileRsp.setRetcode(2000);
            userProfileRsp.setData(user);
            return userProfileRsp;

        }
        return userProfileRsp;
    }

}





















