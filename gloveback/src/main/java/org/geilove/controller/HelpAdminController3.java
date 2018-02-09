package org.geilove.controller;

/**
 *
 */
import freemarker.ext.beans.HashAdapter;
import net.sf.ehcache.search.aggregator.Sum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.geilove.dao.*;
import org.geilove.pojo.*;
import org.geilove.response.PublicListRsp;
import org.geilove.service.AshipService;
import org.geilove.service.SumInfoService;
import org.geilove.service.T_userService;
import org.geilove.util.Arith;
import org.geilove.util.Md5Util;
import org.geilove.util.Response;
import org.geilove.vo.*;
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
    @Resource
    private SumInfoService sumInfoService;
    @Resource
    private  PayMoneyMapper payMoneyMapper;
    @Resource
    private  TongjiMapper tongjiMapper;
    @Resource
    private AshipService ashipService;
    @Resource
    private T_userDao tUserDao;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private  UserRoleMapper userRoleMapper;
    @Resource
    private  ResourceMapper resourceMapper;
    @Resource
    private  RoleResourceMapper roleResourceMapper;

    // 路由，跳转到对账--废弃
    @RequestMapping(value="/goDefaultMain.do",method = RequestMethod.GET)
    public ModelAndView goDefaultMain( HttpServletRequest request){
        ModelAndView mav=new ModelAndView("putaohelp/defaultMain");
        return mav ;
    }

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
        try{
            int num=userMapper.getPersonCount();
            resp.setMsg(String.valueOf(num));
        }catch (Exception e){
            resp.setMsg("0");
        }
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
        try{
            int count=companyputaoMapper.getCompanyputaoCount();
            resp.setMsg(String.valueOf(count)); //总条数
        }catch (Exception e){
            resp.setMsg("0");
        }
        return  resp;
    }

    @RequestMapping(value="/searchUserStaffByPhone.do",method = RequestMethod.POST)
    @ResponseBody
    public Object searchUserStaffByPhone( HttpServletRequest request) {
        Response< List<UserStaff> > resp=new Response<>();
        String phone=request.getParameter("phone");
        List<UserStaff> userStaffs;
        try {
            userStaffs=userStaffMapper.selectByPhone(phone);
            if (userStaffs==null ||userStaffs.isEmpty()){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        resp.success(userStaffs);
        return  resp;
    }
    //项目列表
    @RequestMapping(value="/getSumInfo2.do",method = RequestMethod.POST)
    @ResponseBody
    public Object getSumInfo2( HttpServletRequest request) {
        Response<List<SumInfo>> resp = new Response<>();
        String token = request.getParameter("token");
        List<SumInfo> sumInfoList=new ArrayList<>();

        SumInfo sumInfolittle=new SumInfo();
        try{
            sumInfolittle=sumInfoService.getSumInfo("little");
            sumInfoList.add(sumInfolittle);
        }catch (Exception e){
           resp.failByException();
           return resp;
        }
        SumInfo sumInfoyoung=new SumInfo();
        try{
            sumInfoyoung=sumInfoService.getSumInfo("young");
            sumInfoList.add(sumInfoyoung);
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        SumInfo sumInfoold=new SumInfo();
        try{
            sumInfoold=sumInfoService.getSumInfo("old");
            sumInfoList.add(sumInfoold);
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        SumInfo sumInfozonghe=new SumInfo();
        try{
            sumInfozonghe=sumInfoService.getSumInfo("zonghe");
            sumInfoList.add(sumInfozonghe);
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        SumInfo sumInfostaff=new SumInfo();
        try{
            sumInfostaff=sumInfoService.getSumInfo("staff");
            sumInfoList.add(sumInfostaff);
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        SumInfo sumInfoemployee=new SumInfo();
        try{
            sumInfoemployee=sumInfoService.getSumInfo("employee");
            sumInfoList.add(sumInfoemployee);
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        resp.success(sumInfoList);
        return resp;
    }

    // 资金明细--充值记录表
    @RequestMapping(value="/chongzhiList.do",method = RequestMethod.POST)
    @ResponseBody
    public Object chongzhiList( HttpServletRequest request) {
        Response<List<PayMoney>> resp = new Response<>();
        String token = request.getParameter("token");
        String pageStr = request.getParameter("page");
        String pageSizeStr=request.getParameter("pageSize");

        int page,pageSize;
        page=Integer.valueOf(pageStr);
        pageSize=Integer.valueOf(pageSizeStr);
        page=(page-1)*pageSize;

        List<PayMoney> payMoneyList;
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("page",page);
            map.put("pageSize",pageSize);
            payMoneyList=payMoneyMapper.selectPayMoney(map);
            if (payMoneyList==null || payMoneyList.isEmpty()){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(payMoneyList);
        try{
            int count=payMoneyMapper.countAll();
            resp.setMsg(String.valueOf(count));
        }catch (Exception e){
            resp.setMsg("0");
        }
        return  resp;
    }
    // 资金明细--搜索
    @RequestMapping(value="/searchChongzhiList.do",method = RequestMethod.POST)
    @ResponseBody
    public Object searchChongzhiList( HttpServletRequest request) {
        Response<List<PayMoney>> resp = new Response<>();
        String token = request.getParameter("token");
        String account=request.getParameter("account");
        String pageStr = request.getParameter("page");
        String pageSizeStr=request.getParameter("pageSize");

        int page,pageSize;
        page=Integer.valueOf(pageStr);
        pageSize=Integer.valueOf(pageSizeStr);
        page=(page-1)*pageSize;

        List<PayMoney> payMoneyList;
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("page",page);
            map.put("pageSize",pageSize);
            map.put("account",account);
            payMoneyList=payMoneyMapper.searchByAccount(map);
            if (payMoneyList==null || payMoneyList.isEmpty()){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(payMoneyList);
        try{
            int count=payMoneyMapper.countAllLimitAcount(account);
            resp.setMsg(String.valueOf(count));
        }catch (Exception e){
            resp.setMsg("0");
        }
        return  resp;
    }

    // 资金明细--扣费记录表
    @RequestMapping(value="/koufeiList.do",method = RequestMethod.POST)
    @ResponseBody
    public Object koufeiList( HttpServletRequest request) {
        Response<List<Deduction>> resp = new Response<>();
        String token = request.getParameter("token");
        String pageStr = request.getParameter("page");
        String pageSizeStr=request.getParameter("pageSize");

        int page,pageSize;
        page=Integer.valueOf(pageStr);
        pageSize=Integer.valueOf(pageSizeStr);
        page=(page-1)*pageSize;

        List<Deduction> deductionList;
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("page",page);
            map.put("pageSize",pageSize);
            deductionList=deductionMapper.getDeductionList(map);

            if (deductionList==null || deductionList.isEmpty()){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(deductionList);
        try{
            int count=deductionMapper.countAll();
            resp.setMsg(String.valueOf(count));
        }catch (Exception e){
            resp.setMsg("0");
        }
        return  resp;
    }

    // 资金明细--扣费搜索
    @RequestMapping(value="/searchKoufeiList.do",method = RequestMethod.POST)
    @ResponseBody
    public Object searchKoufeiList( HttpServletRequest request) {
        Response<List<Deduction>> resp = new Response<>();
        String token = request.getParameter("token");

        String account=request.getParameter("account");
        String pageStr = request.getParameter("page");
        String pageSizeStr=request.getParameter("pageSize");

        int page,pageSize;
        page=Integer.valueOf(pageStr);
        pageSize=Integer.valueOf(pageSizeStr);
        page=(page-1)*pageSize;

        List<Deduction> deductionList;
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("account",account);
            map.put("page",page);
            map.put("pageSize",pageSize);
            deductionList=deductionMapper.searchKoufeiList(map);

            if (deductionList==null || deductionList.isEmpty()){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(deductionList);
        try{
            int count=deductionMapper.countAllLimitAccount(account);
            resp.setMsg(String.valueOf(count));
        }catch (Exception e){
            resp.setMsg("0");
        }
        return  resp;
    }


    // 资金走势--最近10天的资金余额变化
    @RequestMapping(value="/zijinzoushi.do",method = RequestMethod.POST)
    @ResponseBody
    public Object zijinzoushi( HttpServletRequest request) {
        Response<List<Tongji>> resp=new Response<>();

        String  pageStr=request.getParameter("page");
        String  pageSizeStr=request.getParameter("pageSize");
        String  helpType=request.getParameter("helpType");

        int page,pageSize;
        page=Integer.valueOf(pageStr);
        pageSize=Integer.valueOf(pageSizeStr);
        page=(page-1)*pageSize;
        List<Tongji> tongjiList=null;
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("helpType",helpType);
            map.put("page",page);
            map.put("pageSize",pageSize);
            tongjiList=tongjiMapper.selectByType(map);
            if (tongjiList==null ||tongjiList.isEmpty()){
                resp.failByNoData();
                return  resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(tongjiList);
        return  resp;
    } //zijintongji
    @RequestMapping(value="/bingTu.do",method = RequestMethod.POST)
    @ResponseBody
    public Object bingTu( HttpServletRequest request) {
        Response<List<BingTu>> resp=new Response<>();
        List<BingTu> bingTuList=new ArrayList<>();

        BingTu bingTuLittle=new BingTu();
        BingTu bingTuYoung=new BingTu();
        BingTu bingTuOld=new BingTu();
        BingTu bingTuZonghe=new BingTu();
        BingTu bingTuStaff=new BingTu();
        BingTu bingTuEmployee=new BingTu();

        Double totalMoneyLittle=0.0;
        Double totalMoneyYoung=0.0;
        Double totalMoneyOld=0.0;
        Double totalMoneyZonghe=0.0;
        Double totalMoneyStaff=0.0;
        Double totalMoneyEmployee=0.0;

        List<UserAccount> userAccountList=new ArrayList<>();
        try{
            userAccountList=userAccountMapper.getSumInfo("little");
            if (userAccountList==null || userAccountList.isEmpty()){
                bingTuLittle.setHelpName("少儿互助计划");
                bingTuLittle.setMoney("0.0");
                bingTuList.add(bingTuLittle);
            }else{
                for (UserAccount userAccount:userAccountList){
                    totalMoneyLittle=Arith.add(totalMoneyLittle, Double.valueOf(userAccount.getPaytotalmoney()));
                }
                bingTuLittle.setHelpName("少儿大病互助");
                bingTuLittle.setMoney(totalMoneyLittle.toString());
                bingTuList.add(bingTuLittle);
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        try{
            userAccountList=userAccountMapper.getSumInfo("young");
            if (userAccountList==null || userAccountList.isEmpty()){
                bingTuYoung.setHelpName("中青年抗癌互助");
                bingTuYoung.setMoney("0.0");
                bingTuList.add(bingTuYoung);
            }else{
                for (UserAccount userAccount:userAccountList){
                    totalMoneyYoung=Arith.add(totalMoneyYoung, Double.valueOf(userAccount.getPaytotalmoney()));
                }
                bingTuYoung.setHelpName("中青年抗癌互助");
                bingTuYoung.setMoney(totalMoneyYoung.toString());
                bingTuList.add(bingTuYoung);
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        try{
            userAccountList=userAccountMapper.getSumInfo("old");
            if (userAccountList==null || userAccountList.isEmpty()){
                bingTuOld.setHelpName("中老年抗癌互助");
                bingTuOld.setMoney("0.0");
                bingTuList.add(bingTuOld);
            }else{
                for (UserAccount userAccount:userAccountList){
                    totalMoneyOld=Arith.add(totalMoneyOld, Double.valueOf(userAccount.getPaytotalmoney()));
                }
                bingTuOld.setHelpName("中老年抗癌互助");
                bingTuOld.setMoney(totalMoneyOld.toString());
                bingTuList.add(bingTuOld);
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        try{
            userAccountList=userAccountMapper.getSumInfo("zonghe");
            if (userAccountList==null || userAccountList.isEmpty()){
                bingTuZonghe.setHelpName("综合意外互助");
                bingTuZonghe.setMoney("0.0");
                bingTuList.add(bingTuZonghe);
            }else{
                for (UserAccount userAccount:userAccountList){
                    totalMoneyZonghe=Arith.add(totalMoneyZonghe, Double.valueOf(userAccount.getPaytotalmoney()));
                }
                bingTuZonghe.setHelpName("综合意外互助");
                bingTuZonghe.setMoney(totalMoneyZonghe.toString());
                bingTuList.add(bingTuZonghe);
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        // 从companyPutao中选择
        List<Companyputao> companyputaoList;
        try{
            companyputaoList=companyputaoMapper.getSumInfo("staff");
            if (companyputaoList==null || companyputaoList.isEmpty()){
                bingTuStaff.setHelpName("企业员工大病互助");
                bingTuStaff.setMoney("0.0");
                bingTuList.add(bingTuStaff);
            }else {
                for (Companyputao companyputao:companyputaoList){
                    totalMoneyStaff=Arith.add(totalMoneyStaff,companyputao.getTotalmoney());
                }
                bingTuStaff.setHelpName("企业员工大病互助");
                bingTuStaff.setMoney(totalMoneyStaff.toString());
                bingTuList.add(bingTuStaff);
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        try{
            companyputaoList=companyputaoMapper.getSumInfo("employee");
            if (companyputaoList==null || companyputaoList.isEmpty()){
                bingTuEmployee.setHelpName("企业员工综合意外互助");
                bingTuEmployee.setMoney("0.0");
                bingTuList.add(bingTuEmployee);
            }else {
                for (Companyputao companyputao:companyputaoList){
                    totalMoneyEmployee=Arith.add(totalMoneyStaff,companyputao.getTotalmoney());
                }
                bingTuEmployee.setHelpName("企业员工综合意外互助");
                bingTuEmployee.setMoney(totalMoneyEmployee.toString());
                bingTuList.add(bingTuEmployee);
            }
        }catch (Exception e){
            resp.failByException();
            return resp;

        }
        resp.success(bingTuList);
        return  resp;
    }

    //获取公示列表
    @RequestMapping(value="/getPublicList.do",method=RequestMethod.POST)
    @ResponseBody
    public Object  getPublicList(){
        Response<List<Public>> resp=new Response<>();
        List<Public> publicList=null;
        Map<String,Object> map=new HashMap<>();
        map.put("page",0);
        map.put("pageSize",30);
        try{
            publicList=ashipService.getPublicList(map);
            if (publicList==null ||publicList.isEmpty()){
                resp.failByNoData();
                return  resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(publicList);
        return resp;
    }

    @RequestMapping(value="/getDefaultMain.do",method=RequestMethod.POST)
    @ResponseBody
    public Object  getDefaultMain(){
        Response<DefaultMain> resp=new Response<>();
        DefaultMain defaultMain=new DefaultMain();

        List<Tongji>  tongjis;
        try{
            int sumUser=userMapper.getPersonCount();
            defaultMain.setSumUser(String.valueOf(sumUser));
            int sumCompany=userMapper.countLimitType((byte)2); //公司总数

            defaultMain.setSumCompany(String.valueOf(sumCompany));
            tongjis=tongjiMapper.selectNewest();
            if (tongjis==null ||tongjis.isEmpty()){
                defaultMain.setRemainMoney("0");
            }else {
                defaultMain.setRemainMoney(tongjis.get(0).getAllmoney());
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        resp.success(defaultMain);
        return  resp;
    }

    // 路由，跳转到权限管理的用户
    @RequestMapping(value="/goPermissionUser.do",method = RequestMethod.GET)
    public ModelAndView goPermissionUser( HttpServletRequest request){
        ModelAndView mav=new ModelAndView("putaohelp/permissionUser");
        return mav ;
    }
    // 路由，跳转到权限管理的角色
    @RequestMapping(value="/goPermissionRoler.do",method = RequestMethod.GET)
    public ModelAndView goPermissionRole( HttpServletRequest request){
        ModelAndView mav=new ModelAndView("putaohelp/permissionRole");
        return mav ;
    }

    // 获得权限用户列表
    @RequestMapping(value="/getPermissionUsers.do",method=RequestMethod.POST)
    @ResponseBody
    public Object  getPermissionUsers(HttpServletRequest request){
        String token=request.getParameter("token");
        Response<List<T_user>> resp=new Response<>();
        List<T_user> userList;
        try{
            userList=tUserDao.getuserList();
            if (userList==null | userList.isEmpty()){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(userList);
        return  resp;
    }

    // 获得角色列表，角色会被标记是否属于该用户
    @RequestMapping(value="/getPermissionRoles.do",method=RequestMethod.POST)
    @ResponseBody
    public Object  getPermissionRoles(HttpServletRequest request){
        Response<List<Role>> resp=new Response<>(); //要返回的是 Role 角色表

        String token=request.getParameter("token");
        String  tuseridStr=request.getParameter("tuserid");
        int tuserid=Integer.valueOf(tuseridStr);
        List<Role> roleList;
        try{
            roleList=roleMapper.getRoles();
            if (roleList==null | roleList.isEmpty()){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        // 到UserRole表 查找该
        List<UserRole> userRoleList;
        try{
            userRoleList=userRoleMapper.getUserRolesByid(tuserid);
        }catch (Exception e){
           resp.failByException();
           return  resp;
        }

        for (UserRole userRole:userRoleList){
           if (userRole.getTuserid()==tuserid){ //如果用户角色表，里的用户等于本次查找的用户
               int roleid=userRole.getRoleid(); // 那么就获得本次角色的id
               for (int i=0; i<roleList.size();i++){
                    if (roleid==roleList.get(i).getRoleid()){
                        roleList.get(i).setT_userID(tuserid);
                    }
                    continue;
               } //for
           }
        }// for1
        resp.success(roleList);
        return  resp;
    }

    // 获得纯粹角色列表
    @RequestMapping(value="/getRoles.do",method=RequestMethod.POST)
    @ResponseBody
    public Object  getRoles(HttpServletRequest request){
        Response<List<Role>> resp=new Response<>(); //要返回的是 Role 角色表
        String token=request.getParameter("token");
        List<Role> roleList;
        try{
            roleList=roleMapper.getRoles();
            if (roleList==null | roleList.isEmpty()){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        resp.success(roleList);
        return  resp;
    }


    // 获得资源列表，资源会被标记是否属于该角色
    @RequestMapping(value="/getResource.do",method=RequestMethod.POST)
    @ResponseBody
    public Object  getResource(HttpServletRequest request) {
        Response<List<org.geilove.pojo.Resource>> resp = new Response<>();
        String token=request.getParameter("token");

        String  roleidStr=request.getParameter("roleid");
        int roleid=Integer.valueOf(roleidStr);
        List<org.geilove.pojo.Resource> resourceList;
        try{
            resourceList=resourceMapper.getResources();
            if (resourceList==null | resourceList.isEmpty()){
                resp.failByNoData();
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        //
        List<RoleResource> roleResourceList;
        try{
            roleResourceList=roleResourceMapper.getRoleResourceList(roleid);
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        for (int i=0;i<roleResourceList.size(); i++){ //角色资源映射

                for (int j=0; j<resourceList.size();j++){
                    if (roleResourceList.get(i).getResourceid()==resourceList.get(j).getResourceid()){
                        resourceList.get(j).setRoleID(roleid);
                    }
                    continue;
                } //for
        }// for
        resp.success(resourceList); //返回资源列表
        return  resp;
    }

}





























































