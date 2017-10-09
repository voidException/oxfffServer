package org.geilove.controller;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;

import org.geilove.requestParam.CompleteProfileParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.geilove.pojo.User;
import org.geilove.requestParam.FindpwParam;
import org.geilove.vo.UserLoginVo;
import org.geilove.service.RegisterLoginService;
import org.geilove.util.MD5;
import org.geilove.util.Md5Util;
import org.geilove.vo.UserRegisterVo;
import org.geilove.response.UserProfileRsp;
import org.geilove.response.CommonRsp;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class UpdateUserProfileController {
	@Resource
	private RegisterLoginService rlService;


	@RequestMapping(value="/goAddProfile.do",method = RequestMethod.GET)
	public String mobileMainPage(){
		String index="front/completeProfile";
		return index;
	}
	//适用于App移动端
	@RequestMapping("/completeProfile.do")
	public @ResponseBody CommonRsp completeProfile(HttpServletRequest request){
		CommonRsp commonRsp=new CommonRsp();
		String token=request.getParameter("token");			
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32);		
		Long userid=Long.valueOf(useridStr).longValue();		
		String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户密码不对，非法");
			return commonRsp;
		}
		User user=new User();
		String  sex=request.getParameter("sex");
		String userTag=request.getParameter("label");
		String  selfIntroduce=request.getParameter("content");	
		user.setUserid(userid);
		user.setSex(sex);
		user.setUsertag(userTag);
		user.setSelfintroduce(selfIntroduce);
		try{
			int returnTag=rlService.updateUserSelective(user);
			//System.out.println(returnTag);
			if(returnTag!=1){
				commonRsp.setMsg("完善资料失败");
				commonRsp.setRetcode(2000);
				return commonRsp;
			}
		}catch(Exception e){
			
		}
		commonRsp.setMsg("更新资料成功");
		commonRsp.setRetcode(2000);
		return commonRsp;
	}

	//适用于网页端更新
	@RequestMapping(value = "/completeProfileJSP.do",method = RequestMethod.POST)
	public @ResponseBody CommonRsp completeProfileJSP(@RequestBody CompleteProfileParam completeProfileParam, HttpServletRequest request){
		CommonRsp commonRsp=new CommonRsp();

		String  token=completeProfileParam.getToken();
		String  realName=completeProfileParam.getRealName();
		String  identity=completeProfileParam.getIdentity();
		String  address=completeProfileParam.getAddress();
        String  college=completeProfileParam.getCollege();
        String  selfIntroduce=completeProfileParam.getSelfIntroduce();
        if (token==null || token.length()<33){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("非法操作");
			return commonRsp;
		}
		String userPassword="";
		String useridStr;
		Long userid=0L;
		String passwdTrue="";
		try{
			userPassword=token.substring(0,32); //token是password和userID拼接成的。
			useridStr=token.substring(32);
			userid=Long.valueOf(useridStr).longValue();
			passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		}catch (Exception e){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户密码不对，非法");
			return commonRsp;
		}

		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户密码不对，非法");
			return commonRsp;
		}

		if (realName==null &&identity==null &&address==null &&college==null ){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("无效更新");
			return commonRsp;
		}
		if (realName.length()>8 || identity.length()!=18 ||selfIntroduce.length()>100 ||address.length()>25 ||college.length()>25 ){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("字段长度不对");
			return commonRsp;
		}

		User user=new User();
		user.setUserid(userid);
		user.setRealname(realName);
		user.setIdentitycard(identity);
		user.setAddress(address);
		user.setUniversity(college);
		user.setSelfintroduce(selfIntroduce);

		try{
			int returnTag=rlService.updateUserSelective(user);

			if(returnTag!=1){
				commonRsp.setMsg("完善资料失败");
				commonRsp.setRetcode(2001);
				return commonRsp;
			}
		}catch(Exception e){
			commonRsp.setMsg("完善资料出现异常");
			commonRsp.setRetcode(2001);
			return commonRsp;

		}
		commonRsp.setMsg("更新资料成功");
		commonRsp.setRetcode(2000);
		return commonRsp;
	}

}
