package org.geilove.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.geilove.pojo.T_user;
import org.geilove.service.T_userService;
import org.geilove.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * 葡萄互助的后台管理系统接口
 */
@Controller
@RequestMapping("/")
public class HelpAdminController {

    @Autowired
    private T_userService t_userService ;

    //1. 后台管理员登录
    @RequestMapping("/loginadmin.do")
    public ModelAndView login(T_user user, Model model){
        ModelAndView mav=new ModelAndView();

        String passmd5 = Md5Util.getMd5(user.getPassword()); //对密码进行加密
        String usermd5=Md5Util.getMd5(user.getUserName()); //对用户名进行加密
        String userToken=passmd5.concat(usermd5);
        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),passmd5) ;
        try {
            //1.用加密后的密码 和 用户名 查询admin表，二次验证

            subject.login(token);
            String userName= (String) subject.getPrincipal();
            mav.addObject("userName",userName);
            mav.addObject("userToken",userToken); // 以后就用这个作为凭证，可用django获取数据了
           // mav.addObject("token","token"); //用于ajax请求携带
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
        //Subject subject = SecurityUtils.getSubject();
        //String userName= (String) subject.getPrincipal();
        //System.out.println(userName);
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
