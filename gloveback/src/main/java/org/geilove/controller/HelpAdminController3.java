package org.geilove.controller;

/**
 *  本controller用于，iframe路由传值。
 */
import org.geilove.dao.*;
import org.geilove.pojo.*;
import org.geilove.service.T_userService;
import org.geilove.util.Md5Util;
import org.geilove.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/grapeAdmin")
public class HelpAdminController3 {
//    @RequestMapping(value="/shenhelist.do",method = RequestMethod.GET)
//    public ModelAndView shenhelist( HttpServletRequest request){
//        String confirmIf=request.getParameter("confirmIf");
//
//        Map<String,Object>  map =new HashMap<String,Object>();
//        map.put("confirmIf",confirmIf);
//        map.put("page",0);
//        map.put("pageSize",30);
//
//        List<Putaoauth> putaoauths=null;
//        try{
//            putaoauths=putaoauthMapper.getPutaoauths(map); //获得公司的列表
//        }catch (Exception e){
//
//        }
//        //System.out.print(putaoauths);
//        ModelAndView mav=new ModelAndView("putaohelp/shenhelist","data",putaoauths);
//        return mav ;
//    }
}
