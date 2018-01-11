package org.geilove.controller;

/**
 * 葡萄互助后台管理系统
 */
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
public class HelpAdminController2 {

    @Resource
    private PutaoauthMapper putaoauthMapper;
    @Resource
    private UserMapper  userMapper;
    @Resource
    private CompanyputaoMapper companyputaoMapper;
    @Resource
    private UserAccountMapper userAccountMapper;


    // 1.获得用户提交的认证的资料列表
    @RequestMapping("/getPutaoauths.do")
    @ResponseBody
    public Object getPutaoauths(HttpServletRequest request ){
        Response<List<Putaoauth>> resp = new Response<>(); //获得公司认证列表

        String  confirmIf=request.getParameter("confirmIf");

        Map<String,Object>  map =new HashMap<String,Object>();
        map.put("confirmIf",confirmIf);
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

    // 资料审核列表，路由和传递参数
    @RequestMapping(value="/shenhelist.do",method = RequestMethod.GET)
    public ModelAndView shenhelist( HttpServletRequest request){
        String confirmIf=request.getParameter("confirmIf");

        ModelAndView mav=new ModelAndView("putaohelp/shenhelist","data",confirmIf);
        return mav ;
    }

    @RequestMapping(value="/shenhelistdata.do",method = RequestMethod.POST)
    @ResponseBody
    public Object shenhelistdata( HttpServletRequest request){

        Response< List<Putaoauth>> resp = new Response<>();
        String confirmIf=request.getParameter("confirmIf");
        String page=request.getParameter("page");
        int pageInt=0;
        try {
            pageInt = Integer.valueOf(page).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.failByException();
            return resp;
        }
        pageInt=(pageInt-1)*30;

        Map<String,Object>  map =new HashMap<String,Object>();
        map.put("confirmIf",confirmIf);
        map.put("page",pageInt);
        map.put("pageSize",30);
        List<Putaoauth> putaoauths=null;

        try{
            putaoauths=putaoauthMapper.getPutaoauths(map); //获得公司的列表
            if (putaoauths==null){
                resp.failByNoInputData("数据为空");
            }
        }catch (Exception e){
            resp.failByException(); //抛出异常
        }
        resp.success(putaoauths);
        return resp ;
    }

    // 这个是点击审核列表的详情
    @RequestMapping(value="/detail.do",method = RequestMethod.GET)
    public ModelAndView shenheDetail( HttpServletRequest request){
        String useruuid=request.getParameter("useruuid");
        Putaoauth putaoauth=null;
        try {
             putaoauth=putaoauthMapper.selectByUserUUID(useruuid);
        }catch (Exception e){
        }
        ModelAndView mav=new ModelAndView("putaohelp/shenheDetail","data",putaoauth);
        return mav;
    }

    // 1.对用户提交的资料进行认证，pass  refused
    @RequestMapping("/shenhe.do")
    @ResponseBody
    public Object confirmIf(HttpServletRequest request ){

        String  token=request.getParameter("token"); //暂时没用
        String  useruuid=request.getParameter("useruuid"); //企业或个人的userUUID
        String  confirmif=request.getParameter("confirmif"); //是否通过
        String  comment=request.getParameter("comment") ; // 拒绝或者通过的原因

        Response<String> resp = new Response<>();
        Putaoauth putaoauth=null;
        try {
             putaoauth=putaoauthMapper.selectByUserUUID(useruuid);
             if (putaoauth==null){
                 resp.failByNoInputData("没有此企业认证信息");
                 return  resp;
             }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        putaoauth.setConfirmif(confirmif);
        putaoauth.setComment(comment);
        if ("refused".equals(confirmif)){ //拒绝了就只更新putaoauth
            try{
                int tag=putaoauthMapper.updateByPrimaryKeySelective(putaoauth);
                if (tag!=1){
                    resp.failByNoInputData("更细认证信息失败");
                    return resp;
                }
            }catch (Exception e){
                resp.failByException();
                return resp;
            }
        }
        //认证通过，更改putaoauth表，更改user表，生成互助计划
        if ("pass".equals(confirmif)){
            //更改putaoauth表
            try{
                int tag=putaoauthMapper.updateByPrimaryKeySelective(putaoauth);
                if (tag!=1){
                    resp.failByNoInputData("更新认证信息失败");
                    return resp;
                }
            }catch (Exception e){
                resp.failByException();
                return resp;
            }
            //更改user表的
            User user=new User();
            user.setUseruuid(useruuid);
            user.setUsertype( (byte)2);
            try{
               int tag= userMapper.updateByUserUUID(user);
                if (tag!=1){
                    resp.failByNoInputData("更新认证信息用户表失败");
                    return resp;
                }
            }catch (Exception e){
                resp.failByException();
                return resp;
            }
            //生成互助计划，先查询是否有此互助计划，用户可能重复提交
            List<Companyputao> companyputaos=null;
            Map<String,Object> map=new HashMap<>();
            map.put("uuid",useruuid);
            try{
                companyputaos=companyputaoMapper.getCompanyputao(map);

            }catch (Exception e){
                resp.failByException();
                return resp;
            }
            if (companyputaos==null || companyputaos.size()!=2){
                //之前没有计划，那就生成2个互助计划
                //员工大病互助计划
                Companyputao companyputaoDaBing= new Companyputao(); //
                companyputaoDaBing.setCompanyuuid(UUID.randomUUID().toString());
                companyputaoDaBing.setUseruuid(useruuid);
                companyputaoDaBing.setStaffall(0);
                companyputaoDaBing.setAverage("0");
                companyputaoDaBing.setHelptype("staff");
                companyputaoDaBing.setNeedtips("no");
                companyputaoDaBing.setUpdatedate(new Date());

                // 员工意外伤害互助计划
                Companyputao companyputaoYiWai=new Companyputao();
                companyputaoYiWai.setCompanyuuid(UUID.randomUUID().toString());
                companyputaoYiWai.setUseruuid(useruuid);
                companyputaoYiWai.setStaffall(0);
                companyputaoYiWai.setAverage("0");
                companyputaoYiWai.setHelptype("employee");
                companyputaoYiWai.setNeedtips("no");
                companyputaoYiWai.setUpdatedate(new Date());
                try{
                    int tagDabing=companyputaoMapper.insertSelective(companyputaoDaBing);

                    int tagYiWai=companyputaoMapper.insertSelective(companyputaoYiWai);
                }catch (Exception e){
                    resp.failByException();
                    return resp;
                }

            }

        }
        resp.success("提交成功");
        return  resp;
    }

    //***********************用户列表*******************************
    @RequestMapping(value="/userlist.do",method = RequestMethod.GET)
    public ModelAndView userlist( HttpServletRequest request) {
        String pagestr = request.getParameter("page");

        Integer page=Integer.valueOf(pagestr);
        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("pageSize",200);
        List<User> users=null;
        try {
            users=userMapper.getUserList(map);
        }catch (Exception e){
          System.out.print(e.getMessage());
        }

        ModelAndView mav=new ModelAndView("putaohelp/userlist","data",users);
        return mav;
    }
    //*************用户列表下的accountlist列表
    @RequestMapping(value="/accountlist.do",method = RequestMethod.GET)
    public ModelAndView accountlist( HttpServletRequest request) {
        String useruuid = request.getParameter("useruuid");

        Map<String,Object> map=new HashMap<>();
        map.put("usruuid",useruuid);
        map.put("page",0);
        map.put("pageSize",50);
        List<UserAccount> accountList=null;
        try {
           //
            accountList=userAccountMapper.getAccountsByuserUUID(map);
        }catch (Exception e){
            System.out.print(e.getMessage());
        }

        ModelAndView mav=new ModelAndView("putaohelp/useraccountlist","data",accountList);
        return mav;
    }
    //*********用户信息统计
    @RequestMapping(value="/userInfoTongji.do",method = RequestMethod.GET)
    public ModelAndView userInfoTongji( HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("putaohelp/userTongji"); //返回的文件名
        /***  这个是样板
        //字符串信息
        mav.addObject("message","hello kitty"); //加入到mav中
        //List信息
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("c++");
        list.add("oracle");
        mav.addObject("bookList", list); //加入到mav中
        //Map信息
        Map<String,String> map = new HashMap<String,String>();
        map.put("zhangsan", "北京");
        map.put("lisi", "上海");
        map.put("wangwu", "深圳");
        mav.addObject("map",map); //加入到mav中
       */
        // 在userAccount表中选择出各种统计信息，然后返回
        try{//参与少儿大病互助计划的
            Map<String,Object> map=new HashMap<>();
            map.put("helpType","little");
            int little=userAccountMapper.getCountByhelpType(map);
            mav.addObject("little",little);
        }catch (Exception e){

        }
        try{//参与中青年互助计划的
            Map<String,Object> map=new HashMap<>();
            map.put("helpType","young");
            int young=userAccountMapper.getCountByhelpType(map);
            mav.addObject("young",young);
        }catch (Exception e){

        }
        try{//参与中老年
            Map<String,Object> map=new HashMap<>();
            map.put("helpType","old");
            int old=userAccountMapper.getCountByhelpType(map);
            mav.addObject("old",old);
        }catch (Exception e){

        }
        try{//参与综合意外互助
            Map<String,Object> map=new HashMap<>();
            map.put("helpType","zonghe");
            int zonghe=userAccountMapper.getCountByhelpType(map);
            mav.addObject("zonghe",zonghe);
        }catch (Exception e){

        }
        try{//参与综合意外互助
            Map<String,Object> map=new HashMap<>();
            map.put("helpType","zonghe");
            int zonghe=userAccountMapper.getCountByhelpType(map);
            mav.addObject("zonghe",zonghe);
        }catch (Exception e){

        }
        return mav;
    }



    //*********检索用户
    @RequestMapping(value="/userSearch.do",method = RequestMethod.GET)
    public String userSearch( HttpServletRequest request) {
        return "putaohelp/userSearch" ;
    }

    //*********检索用户
    @RequestMapping(value="/douserSearch.do",method = RequestMethod.POST)
    @ResponseBody
    public Object douserSearch( HttpServletRequest request) {
        Response<User> resp = new Response<>();
        String info=request.getParameter("info"); //这个是身份证号或者手机号
        String typeTag=request.getParameter("typeTag"); //这个是区分标志。phone 或者id



        return  resp;
    }

}



























