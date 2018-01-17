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
import org.geilove.vo.RedBaoInfo;
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
    private  PutaoauthMapper putaoauthMapper;
    @Resource
    private  UserMapper  userMapper;
    @Resource
    private  CompanyputaoMapper companyputaoMapper;
    @Resource
    private  UserAccountMapper userAccountMapper;
    @Resource
    private  UserStaffMapper userStaffMapper ;


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
    // 资料审核搜索页面
    @RequestMapping(value="/shenheSearch.do",method = RequestMethod.GET)
    public ModelAndView shenheSearch( HttpServletRequest request) {
        ModelAndView mav=new ModelAndView("putaohelp/shenheSearch","data","");
        return mav;
    }
    //根据给定的公司名，搜索出企业信息。--模糊查询
    @RequestMapping(value="/doshenheSearch.do",method = RequestMethod.POST)
    @ResponseBody
    public Object doshenheSearch( HttpServletRequest request) {
        Response<List<Putaoauth>> resp = new Response<>(); //返回的是一个列表
        String  name=request.getParameter("name"); // 企业的名字
        List<Putaoauth> putaoauths=null;
        Map<String,Object> map=new HashMap<>();

        map.put("name",name); //企业的名字
        map.put("page",0);
        map.put("pageSize",5); //
        try{
            putaoauths=putaoauthMapper.getPutaoauthSearch(map); //map
            if (putaoauths==null || putaoauths.isEmpty() ){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        resp.success(putaoauths);
        return resp;
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



    //*********检索用户，路由
    @RequestMapping(value="/userSearch.do",method = RequestMethod.GET)
    public String userSearch( HttpServletRequest request) {
        return "putaohelp/userSearch" ;
    }

    //*********检索用户，返回用的基本信息和其家人加入的互助信息
    @RequestMapping(value="/douserSearch.do",method = RequestMethod.POST)
    @ResponseBody
    public Object douserSearch( HttpServletRequest request) {
        Response<User> resp = new Response<>();
        String token=request.getParameter("token"); //
        String phone=request.getParameter("phone"); //根据这个查找用户
        User user=null;

        try{
            user=userMapper.getUserByPhone(phone); //获取注册用户
            if (user==null){
                resp.failByNoData();
                return  resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(user);
        return  resp;
    }

    //*********根据useruuid检索其互助家人
    @RequestMapping(value="/doAccountListSearch.do",method = RequestMethod.POST)
    @ResponseBody
    public Object doAccountListSearch( HttpServletRequest request) {
        Response<List<UserAccount>> resp = new Response<>();

        String useruuid=request.getParameter("useruuid");
        Map<String,Object> map=new HashMap<>();
        map.put("usruuid",useruuid);
        map.put("page",0);
        map.put("pageSize",50);
        List<UserAccount> accountList=null;
        try {
            accountList=userAccountMapper.getAccountsByuserUUID(map);
            if (accountList==null ||accountList.isEmpty()){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByNoData();
            return  resp;
        }
        resp.success(accountList);
        return  resp;
    }

    //*********检索用户，根据身份证号返回有关的互助信息
    @RequestMapping(value="/doAccountSearch.do",method = RequestMethod.POST)
    @ResponseBody
    public Object doAccountSearch( HttpServletRequest request) {
        Response<List<UserAccount>> resp = new Response<>();
        String account=request.getParameter("account"); //
        List<UserAccount> userAccounts=null;
        Map<String,Object> map=new HashMap<>();
        map.put("account",account);
        try{
            userAccounts=userAccountMapper.selectByAccount(map);
            if(userAccounts==null){
                resp.failByNoData();
                return  resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(userAccounts);
        return  resp;
    }

    //*******************--公司有关的信息-*********************************//
    @RequestMapping(value="/companyInfoTongji.do",method = RequestMethod.GET)
    public ModelAndView companyInfoTongji( HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("putaohelp/companyTongji"); //返回的文件名

        try{
            int companyTotal=companyputaoMapper.getTotalCompany(); //公司总数
            mav.addObject("companyTotal",companyTotal);

        }catch (Exception e){

        }
        try{
            //所有参与公司大病互助的员工总数
            Map<String,Object> map=new HashMap<>();
            map.put("helpType","staff");
            int totalDaBingInfo=userStaffMapper.getTotalInfo(map);
            mav.addObject("staff",totalDaBingInfo);
        }catch (Exception e){

        }
        try{
            //所有参与公司大病互助的员工总数
            Map<String,Object> map=new HashMap<>();
            map.put("helpType","employee");
            int totalDaBingInfo=userStaffMapper.getTotalInfo(map);
            mav.addObject("employee",totalDaBingInfo);
        }catch (Exception e){

        }

        return  mav;
    }

    // 路由，跳转到公司列表
    @RequestMapping(value="/companylist.do",method = RequestMethod.GET)
    public ModelAndView companylist( HttpServletRequest request){

        ModelAndView mav=new ModelAndView("putaohelp/companylist");
        return mav ;
    }
    //获得公司列表
    @RequestMapping(value="/getCompanyList.do",method = RequestMethod.POST)
    @ResponseBody
    public Object getCompanyList( HttpServletRequest request) {
        Response<List<User>> resp = new Response<>();

        String pageStr=request.getParameter("page");
        //获得公司的
        List<User> userList=null;
        try{
            int page=Integer.valueOf(pageStr);
            page=(page-1)*30;

            Map<String,Object> map=new HashMap<>();
            map.put("userType",(byte)2);
            map.put("page",page);
            map.put("pageSize",30);

            userList=userMapper.getCompanyList(map);
            if (userList==null){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }

        resp.success(userList);
        return resp;
    }

    // 根据公司的uuid获取员工列表
    @RequestMapping(value="/getUserStaffList.do",method = RequestMethod.POST)
    @ResponseBody
    public Object getUserStaffList( HttpServletRequest request) {
        Response<List<UserStaff>> resp = new Response<>();
        String  useruuid=request.getParameter("useruuid"); // 公司的useruuid
        String  pageStr=request.getParameter("page"); //
        List<UserStaff> userStaffs=null;
        try{
            int page=Integer.valueOf(pageStr);
            page=(page-1)*30;
            Map<String,Object> map=new HashMap<>();
            // 暂不区分互助的种类
            map.put("useruuid",useruuid);
            map.put("page",page);
            map.put("pageSize",30);
            userStaffs=userStaffMapper.getUserStaffByUserUUID(map);
            if (userStaffs==null || userStaffs.isEmpty()){
                resp.failByNoData();
                return resp;
            }

        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        resp.success(userStaffs);
        return resp;
    }
    @RequestMapping(value="/aadetail.do",method = RequestMethod.POST)
    @ResponseBody
    public Object detail( HttpServletRequest request) {
        Response<List<Companyputao>> resp = new Response<>();
        //
        String useruuid=request.getParameter("useruuid"); //获得用户的useruuid
        List<Companyputao>  companyputaos=null;
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("uuid",useruuid);
            map.put("page",0);
            map.put("pageSize",2);
            companyputaos=companyputaoMapper.getCompanyputao(map);
            if (companyputaos==null){
                resp.failByNoData();
                return  resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;

        }
        resp.success(companyputaos);
        return  resp;
    }
    // 路由，跳转到公司检索
    @RequestMapping(value="/companySearch.do",method = RequestMethod.GET)
    public ModelAndView companySearch( HttpServletRequest request){

        ModelAndView mav=new ModelAndView("putaohelp/companySearch");
        return mav ;
    }

    // 根据手机号检索这个公司的互助情况
    @RequestMapping(value="/doCompanySearch.do",method = RequestMethod.POST)
    @ResponseBody
    public Object doCompanySearch( HttpServletRequest request) {
        Response<List<Companyputao>> resp = new Response<>();
        String  phone=request.getParameter("phone"); // 根据手机号进行检索
        String  useruuid="";

        List<Companyputao> companyputaos=null;
        //1. 先到User表用手机号搜索user信息
        try{
            User user=userMapper.getUserByPhone(phone);
            if (user==null){
                resp.failByNoInputData("此用户没有注册");
                return  resp ;
            }
            useruuid=user.getUseruuid();
        }catch (Exception e){
            resp.failByException();
            return resp;
        }

        try{
            Map<String,Object> map=new HashMap<>();
            map.put("uuid",useruuid); //用户的useruuid
            map.put("page",0);
            map.put("pageSize",2);
            companyputaos=companyputaoMapper.getCompanyputao(map);
            if (companyputaos==null || companyputaos.size()==0){
                resp.failByNoInputData("没有数据");
                return  resp;
            }

        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        resp.success(companyputaos);
        return resp;
    }

    // 根据公司的uuid获取员工列表
    @RequestMapping(value="/getUserStaffListHelpType.do",method = RequestMethod.POST)
    @ResponseBody
    public Object getUserStaffListHelpType( HttpServletRequest request) {
        Response<List<UserStaff>> resp = new Response<>();
        String  useruuid=request.getParameter("useruuid"); // 公司的useruuid
        String  pageStr=request.getParameter("page"); //
        String  helptype=request.getParameter("helptype");

        List<UserStaff> userStaffs=null;
        try{
            int page=Integer.valueOf(pageStr);
            page=(page-1)*30;
            Map<String,Object> map=new HashMap<>();
            // 暂不区分互助的种类
            map.put("uuid",useruuid);
            map.put("helptype",helptype);
            map.put("affirm","yes");
            map.put("page",page);
            map.put("pageSize",30);
            userStaffs=userStaffMapper.selectTotalStaff(map);
            if (userStaffs==null || userStaffs.isEmpty()){
                resp.failByNoData();
                return resp;
            }

        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        resp.success(userStaffs);
        return resp;
    }
    // 路由，跳转资金统计
    @RequestMapping(value="/zijinTongji.do",method = RequestMethod.GET)
    public ModelAndView zijinTongji( HttpServletRequest request){
        ModelAndView mav=new ModelAndView("putaohelp/zijinTongji");
        return mav ;
    }
    // 路由，红包金额统计
    @RequestMapping(value="/redBaoTongji.do",method = RequestMethod.GET)
    public ModelAndView redBaoTongji( HttpServletRequest request){
        ModelAndView mav=new ModelAndView("putaohelp/redBaoTongji");
        return mav ;
    }
    // 根据公司的uuid获取员工列表
    @RequestMapping(value="/getRedBaoInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public Object getRedBaoInfo( HttpServletRequest request) {
        Response<RedBaoInfo> resp = new Response<>();
        RedBaoInfo redBaoInfo=new RedBaoInfo();
        try{

        }catch (Exception e){

        }

        return resp;
    }


}



























