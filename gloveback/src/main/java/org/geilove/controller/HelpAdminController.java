package org.geilove.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.geilove.pojo.T_user;
import org.geilove.service.T_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IDEA
 * Created by ${jie.chen} on 2016/7/14.
 * 后台Controller
 */
@Controller
@RequestMapping("/")
public class HelpAdminController {

    @Autowired
    private T_userService t_userService ;

    @RequestMapping("/loginadmin.do")
    public ModelAndView login(T_user user, Model model){
        ModelAndView mav=new ModelAndView();
        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword()) ;
        try {
            subject.login(token);
            String userName= (String) subject.getPrincipal();
            //System.out.println(userName);
            mav.addObject("userName",userName);
            mav.addObject("userToken","token"); //用于ajax请求携带
            mav.setViewName("putaohelp/admin");
            return  mav;

        }catch (Exception e){
            model.addAttribute("error","用户名或密码错误") ;
            mav.setViewName("putaohelp/login");
            return  mav;
        }

    }


    @RequestMapping("/admin.do")
    public String admin(Model model, HttpServletRequest request ){
//        Subject subject = SecurityUtils.getSubject();
//        String userName= (String) subject.getPrincipal();
//        System.out.println(userName);

        return "putaohelp/admin";
    }

    @RequestMapping("/helpindex.do")
    public String helpindex(){
        return "index" ;
    }
    @RequestMapping("/helplogin.do")
    public String helplogin(){
        return "putaohelp/login" ;
    }
    @RequestMapping("/student.do")
    public String student(){
        return "putaohelp/admin" ;
    }

    @RequestMapping("/teacher.do")
    public String teacher(){
        return "putaohelp/admin" ;
    }
}
