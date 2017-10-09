package org.geilove.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

//这个主要是为关于我们，常见问题，意见反馈等提供路由
@Controller
@RequestMapping(value="/path/pages")
public class JspPageController {

	@RequestMapping(value="/header2.do",method = RequestMethod.GET)
	public String goHeader(){
		String index="common/header2";
		return index;
	}
	@RequestMapping(value="/helpApp.do",method = RequestMethod.GET)
	public String helpApp(){
		String index="front/helpApp";
		return index;
	}
	@RequestMapping(value="/helpAixinshe.do",method = RequestMethod.GET)
	public String helpAixinshe(){
		String index="front/helpAixinshe";
		return index;
	}
	@RequestMapping(value="/aboutus.do",method = RequestMethod.GET)
	public String aboutus(){
		String index="front/aboutus";
		return index;
	}
	@RequestMapping(value="/aboutwe.do",method = RequestMethod.GET)
	public String aboutwe(){
		String index="front/aboutwe";
		return index;
	}
	@RequestMapping(value="/faq.do",method = RequestMethod.GET)
	public String faq(){
		String index="front/faq";
		return index;
	}
	@RequestMapping(value="/feedback.do",method = RequestMethod.GET)
	public String feedback(){
		String index="front/feedback";
		return index;
	}
	@RequestMapping(value="/wechat.do",method = RequestMethod.GET)
	public String wechat(){
		String index="front/wechat";
		return index;
	}
	//分享到朋友圈
	@RequestMapping(value="/shareTotimeline.do",method=RequestMethod.GET)
	public  String shareTimeline( HttpServletRequest request){
		String index="front/wechat";
		return index;
	}

	@RequestMapping(value="/productDetail/{productuuid}",method=RequestMethod.GET)
	public  String goProductDetail(@PathVariable("productuuid") String productuuid, HttpServletRequest request){
		String index="front/productDetail";
		return index;
	}

	@RequestMapping(value="/login.do",method = RequestMethod.GET)
	public String login(){
		String index="front/login";
		return index;
	}
	@RequestMapping(value="/register.do",method = RequestMethod.GET)
	public String register(){
		String index="front/register";
		return index;
	}
	@RequestMapping(value="/loginRegister.do",method = RequestMethod.GET)
	public String loginPC(){
		String index="front/loginRegister";
		return index;
	}


	@RequestMapping(value="/publishItem.do",method = RequestMethod.GET)
	public String publishItem(){
		String index="front/publishItemPC";
		return index;
	}
	@RequestMapping(value="/helpCenter.do",method = RequestMethod.GET)
	public String goHelpCenter(){
		String index="front/helpCenterPC";
		return index;
	}

	@RequestMapping(value="/appDownload.do",method = RequestMethod.GET)
	public String appDownload(){
		String index="front/appDownload";
		return index;
	}
	@RequestMapping(value="/userProtocol.do",method = RequestMethod.GET)
	public String userProtocol(){
		String index="front/userProtocolPC";
		return index;
	}

	@RequestMapping(value="/mobileMain.do",method = RequestMethod.GET)
	public String woWechat(){
		String index="mobilewap/mobileMain";
		return index;
	}
	@RequestMapping(value="/mobileWo.do",method = RequestMethod.GET)
	public String mobileMainPage(){
		String index="mobilewap/mobileWo";
		return index;
	}
	@RequestMapping(value="/publishHelpInfo.do",method = RequestMethod.GET)
	public String publishHelpInfo(){
		String index="front/publishHelpInfo";
		return index;
	}

	@RequestMapping(value="/userSelfHelp.do",method = RequestMethod.GET)
	public String userSelfHelp(){
		String index="front/userSelfHelp";
		return index;
	}
	@RequestMapping(value="/helpTweetList.do",method = RequestMethod.GET)
	public String helpTweetList(){
		String index="front/helpTweetList";
		return index;
	}
	@RequestMapping(value="/jianduchu.do",method = RequestMethod.GET)
	public String jianduchu(){
		String index="mobilewap/supervise";
		return index;
	}

}
