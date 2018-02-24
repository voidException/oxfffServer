package org.geilove.controller;
import org.geilove.vo.LearnUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/learn")
@SessionAttributes({"user","uname"}) //对应到login中设置session
public class LearnController {

    @RequestMapping("/goLoginPage.do")
    public ModelAndView hello(@CookieValue(value="hitCounter",defaultValue="0")Long hitCounter, HttpServletResponse response){
        ModelAndView mAndView=new ModelAndView("learn/login");
        hitCounter++;
        Cookie hit=new Cookie("hitCounter",hitCounter.toString());
        hit.setHttpOnly(true);//如果设置了"HttpOnly"属性，那么通过程序(JS脚本、Applet等)将无法访问该Cookie
        hit.setMaxAge(60*60);//设置生存期为1小时
//		hit.setDomain("/");//子域，在这个子域下才可以访问该Cookie
//		hit.setPath("/hello");//在这个路径下面的页面才可以访问该Cookie
//		hit.setSecure(true);//如果设置了Secure，则只有当使用https协议连接时cookie才可以被页面访问
        response.addCookie(hit);
        return mAndView;
    }

    @RequestMapping("/login.do")
    public String login( LearnUser user, ModelMap map,HttpServletResponse response) {
        //user会自己注入session中
        //将uname放入session作用域中，这样转发页面也可以取到这个数据。
        Cookie cookie = new Cookie("testCookie", "aaaaaaa");
        cookie.setMaxAge(30 * 60);// 设置为30min
        cookie.setPath("/");
        System.out.println("已添加===============");
        response.addCookie(cookie);

        map.addAttribute("uname", user.getUname());
        map.addAttribute("user", user);
        return "learn/loginSuccess";
    }
//    @RequestMapping("/login2.do")
//    public String login2( HttpServletRequest request, ModelMap map,HttpServletResponse response) {
//        //user会自己注入session中
//        //将uname放入session作用域中，这样转发页面也可以取到这个数据。
//
//        map.addAttribute("uname", "aihaitao");
//        return "learn/loginSuccess";
//    }

    @RequestMapping(value = "/getSession.do")
    public String show(ModelMap modelMap, HttpSession session,HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组

        if (null==cookies) {//如果没有cookie数组
            System.out.println("没有cookie");
        } else {
            for(Cookie cookie : cookies){
                System.out.println("cookieName:"+cookie.getName()+",cookieValue:"+ cookie.getValue());
            }
        }
        System.out.println("######################");
        for (Object key : modelMap.keySet()) {
            Object value = modelMap.get(key);
            System.out.println(key + " = " + value);
        }
        System.out.println("***********************************");
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String s = e.nextElement();
            System.out.println(session + " == " + session.getAttribute(s));
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        return "learn/session";
    }

    @RequestMapping("/loginOut.do")
    public String clear(SessionStatus status) {
        status.setComplete();
        return "learn/loginOut";
    }
}
