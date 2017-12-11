package org.geilove.controller;

/**
 * 葡萄互助后台管理系统
 */
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.geilove.dao.PutaoauthMapper;
import org.geilove.pojo.Putaoauth;
import org.geilove.pojo.T_user;
import org.geilove.service.T_userService;
import org.geilove.util.Md5Util;
import org.geilove.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/grapeAdmin")
public class HelpAdminController2 {

    @Resource
    private PutaoauthMapper putaoauthMapper;

    // 1.获得用户提交的认证的资料列表
    @RequestMapping("/getPutaoauths.do")
    @ResponseBody
    public Object getPutaoauths(HttpServletRequest request ){
        Response<List<Putaoauth>> resp = new Response<>(); //获得公司认证列表

        String  confirmIf=request.getParameter("confirmIf");
        Map<String,Object>  map =new HashMap<String,Object>();
        map.put("page",0);
        map.put("pageSize",1000);

        List<Putaoauth> putaoauths=null;
        try{
            putaoauths=putaoauthMapper.getPutaoauths(map); //获得公司的列表
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        if (putaoauths==null || putaoauths.isEmpty()){
            resp.failByNoInputData("数据为空");
            return  resp;
        }else {
            resp.success(putaoauths);
            return  resp;
        }
    }
    // 1.对用户提交的资料进行认证，pass  refused
    @RequestMapping("/confirmIf.do")
    @ResponseBody
    public Object confirmIf(HttpServletRequest request ){
        Response<String> resp = new Response<>();
        // token companyuuid  comment
        //



        return  resp;
    }

}



























