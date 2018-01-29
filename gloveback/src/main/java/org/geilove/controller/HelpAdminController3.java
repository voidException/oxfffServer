package org.geilove.controller;

/**
 *
 */
import freemarker.ext.beans.HashAdapter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.geilove.dao.*;
import org.geilove.pojo.*;
import org.geilove.service.T_userService;
import org.geilove.util.Arith;
import org.geilove.util.Md5Util;
import org.geilove.util.Response;
import org.geilove.vo.CostMoney;
import org.geilove.vo.PersonAccounts;
import org.geilove.vo.RedBaoInfo;
import org.geilove.vo.UserCompanyputao;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/grapeAdmin")
public class HelpAdminController3 {
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
    @Resource
    private  RedMoneyMapper redMoneyMapper; //
    @Resource
    private  NewsMapper newsMapper;
    @Resource
    private PublicMapper publicMapper;
    @Resource
    private  DeductionMapper deductionMapper;

    //*******获得用户列表及其对应的userAccounts
    @RequestMapping(value="/getUseList2.do",method = RequestMethod.POST)
    @ResponseBody
    public Object getUserList2( HttpServletRequest request) {
        Response<List<PersonAccounts>> resp = new Response<>(); //

        String token=request.getParameter("token");
        String pageStr = request.getParameter("page");
        String pageSizeStr=request.getParameter("pageSize");

        Integer page=Integer.valueOf(pageStr);
        Integer pageSize=Integer.valueOf(pageSizeStr);
        page=(page-1)*pageSize;

        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("pageSize",pageSize);
        List<PersonAccounts> personAccountsList=new ArrayList<>();
        List<User> users=null;
        try {
            users=userMapper.getUserList(map);
            if (users==null ||users.isEmpty()){
                resp.failByNoData();
                return  resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }

        for (User user:users){
            PersonAccounts personAccounts=new PersonAccounts();
            personAccounts.setUserphone(user.getUserphone());
            personAccounts.setUseruuid(user.getUseruuid());
            personAccounts.setRegisterdate(user.getRegisterdate());

            Map<String,Object> map2=new HashMap<>();

            String userUUID=user.getUseruuid();
            //System.out.println(userUUID);
            map2.put("useruuid",userUUID);
            map2.put("page",0);
            map2.put("pageSize",10);
            List<UserAccount> accountList=null;

            try {
                accountList=userAccountMapper.getAccountsByuserUUID(map2);
            }catch (Exception e){
                continue;
            }
            personAccounts.setUserAccountList(accountList);
            personAccountsList.add(personAccounts);
        }
        //统计总共有多少条
        //结果放在resp的
        //resp.setMsg("1000");

        resp.success(personAccountsList);
        return  resp;
    }
    //******* 通过用户的手机号检索出用户和其家人加入的互助计划
    @RequestMapping(value="/searchUserAndUserAccountsbyPhone.do",method = RequestMethod.POST)
    @ResponseBody
    public Object  searchUserAndUserAccountsbyPhone( HttpServletRequest request) {
        Response<List<PersonAccounts>> resp = new Response<>(); //
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
        String useruuid=user.getUseruuid();
        Map<String,Object> map=new HashMap<>();
        map.put("useruuid",useruuid);
        map.put("page",0);
        map.put("pageSize",10);
        List<UserAccount> accountList=null;
        try {
            accountList=userAccountMapper.getAccountsByuserUUID(map);
        }catch (Exception e){
            resp.failByNoData();
            return  resp;
        }
        List<PersonAccounts> personAccountsList=new ArrayList<>();
        PersonAccounts personAccounts=new PersonAccounts();
        personAccounts.setUseruuid(user.getUseruuid());
        personAccounts.setRegisterdate(user.getRegisterdate());
        personAccounts.setUserphone(user.getUserphone());
        personAccounts.setUserAccountList(accountList);
        personAccountsList.add(personAccounts);
        resp.success(personAccountsList);
        return resp;
    }

    //获得公司列表
    @RequestMapping(value="/getCompanyList2.do",method = RequestMethod.POST)
    @ResponseBody
    public Object getCompanyList( HttpServletRequest request) {
        Response<List<UserCompanyputao>> resp = new Response<>();
        String token=request.getParameter("token");
        String pageStr=request.getParameter("page");

        int page=Integer.valueOf(pageStr);
        page=(page-1)*5; //因为一个公司对应2个互助计划，供10条
        List<User> userList=null;
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("userType",(byte)2);
            map.put("page",page);
            map.put("pageSize",5);
            userList=userMapper.getCompanyList(map);
            if (userList==null){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        List<UserCompanyputao>  userCompanyputaoList=new ArrayList<>();
        for (User user:userList){
            Map<String,Object> map=new HashMap<>();
            map.put("uuid",user.getUseruuid());
            map.put("page",page);
            map.put("pageSize",10);
            List<Companyputao> companyputaoList=null;
            try{
                 companyputaoList=companyputaoMapper.getCompanyputao(map);
                if (companyputaoList==null || companyputaoList.isEmpty()){
                    continue;
                }
            }catch (Exception e){
                continue;
            }
            UserCompanyputao userCompanyputao1=new UserCompanyputao();
            userCompanyputao1.setUsername(user.getUsernickname());
            userCompanyputao1.setUseruuid(user.getUseruuid());
            userCompanyputao1.setUserphone(user.getUserphone());
            userCompanyputao1.setRegister(user.getRegisterdate());
            userCompanyputao1.setStaffall(companyputaoList.get(0).getStaffall());
            userCompanyputao1.setAverage(companyputaoList.get(0).getAverage());
            userCompanyputao1.setCompanyuuid(companyputaoList.get(0).getCompanyuuid());
            userCompanyputao1.setHelptype(companyputaoList.get(0).getHelptype());
            userCompanyputao1.setTotalmoenystr(companyputaoList.get(0).getTotalmoenystr());
            userCompanyputao1.setTipstimes(companyputaoList.get(0).getTipstimes()); //
            //加入到列表中
            userCompanyputaoList.add(userCompanyputao1) ;

            UserCompanyputao userCompanyputao2=new UserCompanyputao();
            userCompanyputao2.setUsername(user.getUsernickname());
            userCompanyputao2.setUseruuid(user.getUseruuid());
            userCompanyputao2.setUserphone(user.getUserphone());
            userCompanyputao2.setRegister(user.getRegisterdate());
            userCompanyputao2.setStaffall(companyputaoList.get(1).getStaffall());
            userCompanyputao2.setAverage(companyputaoList.get(1).getAverage());
            userCompanyputao2.setCompanyuuid(companyputaoList.get(1).getCompanyuuid());
            userCompanyputao2.setHelptype(companyputaoList.get(1).getHelptype());
            userCompanyputao2.setTotalmoenystr(companyputaoList.get(1).getTotalmoenystr());
            userCompanyputao2.setTipstimes(companyputaoList.get(1).getTipstimes()); //
            //加入到列表中
            userCompanyputaoList.add(userCompanyputao2); //
        } //for
        resp.success(userCompanyputaoList);
        return  resp;
    }

}





























































