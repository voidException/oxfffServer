package org.geilove.controller;


import org.geilove.dao.UserMapper;
import org.geilove.dao.UserStaffMapper;
import org.geilove.pojo.*;

import org.geilove.requestParam.ConfirmStuffsParam;
import org.geilove.requestParam.StuffsUserUUID;
import org.geilove.response.CommonRsp;
import org.geilove.service.*;
import org.geilove.util.*;
import org.geilove.vov.CompanyBaseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 葡萄互助有关公司的接口
 */
@Controller
@RequestMapping("/grape")
public class PutaoCompanyController {

    @Resource
    private RegisterLoginService rlService;
    @Resource
    private CompanyputaoService  companyputaoService;
    @Resource
    private AshipService  ashipService;
    @Resource
    private PhoneService phoneService;
    @Resource
    private UserStaffMapper  userStaffMapper;
    @Resource
    private UserMapper userMapper;

    //1.获取公司参与的所有互助计划
    @RequestMapping(value="/getcompanyhelp.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getCompanyHelp(HttpServletRequest request){

        Response<List<Companyputao>> resp = new Response<List<Companyputao>>();
        String token=request.getParameter("token");
        String  uuid=request.getParameter("uuid"); //用户的uuid
        try{
            String userPassword=token.substring(0,32); //token是password和userID拼接成的。
            String useridStr=token.substring(32);
            Long userid=Long.valueOf(useridStr).longValue();
            String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
            if(!userPassword.equals(passwdTrue)){
                resp.failByNoInputData("认证失败，密码不对");
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }

        //使用uuid在companyputao表中取出该公司参与的计划
        Map<String,Object>  map=new HashMap<String,Object>();
        map.put("uuid",uuid);
        map.put("page",0);
        map.put("pageSize",2);
        List<Companyputao> companyputaoList;

        try{
            companyputaoList =companyputaoService.getCompanyputaos(map);
            if (companyputaoList!=null&& !companyputaoList.isEmpty()){
               for (Companyputao companyputao:companyputaoList){
                   int staffInt=0;
                   String helpType=companyputao.getHelptype(); //互助的类型
                   Map<String,Object>  map1=new HashMap<String,Object>();

                   map.put("uuid",uuid);
                   map.put("affirm","yes"); //员工是否确认
                   map.put("helptype",helpType); //互助的类型
                   //map.put("joinDate",joinDate); //加入的时间
                   map.put("page",0);
                   map.put("pageSize",60); //暂定为每页显示60人

                   // 员工总数
                   List<UserStaff> companyputaos=userStaffMapper.getAllStuffs(map);

                   if(companyputaos==null ||companyputaos.isEmpty()){
                       staffInt=0;
                   }else{
                       staffInt=companyputaos.size();
                   }
                   companyputao.setStaffall(staffInt); //员工总数

                   String totalmoenystr=companyputao.getTotalmoenystr();

                   if (staffInt==0){//员工数为0
                       companyputao.setAverage(companyputao.getAverage());
                       continue;
                   }
                   //人均余额
                   Double avager= Arith.div( Double.valueOf(totalmoenystr),Double.valueOf(staffInt),new Integer(3));
                   companyputao.setAverage(avager.toString());
                   //是否需要提示
                   BigDecimal datavager = new BigDecimal(avager);
                   BigDecimal data160=new BigDecimal(160);
                   if (datavager.compareTo(data160)==1){//如果人均余额小于150，就设置needtips
                        companyputao.setNeedtips("no");
                   }else {
                       companyputao.setNeedtips("yes");
                   }

               }
                resp.success(companyputaoList);
                return  resp;
            }
        }catch (Exception e){
             resp.failByException();
             return  resp;
        }
        resp.failByNoData(); // 没有数据
        return resp;
    }
    //2.获取公司所有的员工
    @RequestMapping(value="/getallstuffs.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getAllStuffs(HttpServletRequest request){
        Response<List<UserStaff>> resp = new Response<List<UserStaff>>();
        String  token=request.getParameter("token");
        String  uuid=request.getParameter("uuid"); //用户的uuid
        String  affirm=request.getParameter("affirm"); //是否确认
        String  helptype=request.getParameter("helptype");  //参与的互助类型
        String  joinDate=request.getParameter("joinDate");
        String pageStr=request.getParameter("page");
        int page=Integer.valueOf(pageStr);
        int pageSize=200;
        page=pageSize*(page-1);
        try{
            String userPassword=token.substring(0,32); //token是password和userID拼接成的。
            String useridStr=token.substring(32);
            Long userid=Long.valueOf(useridStr).longValue();
            String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
            if(!userPassword.equals(passwdTrue)){
                resp.failByNoInputData("认证失败，密码不对");
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }

        Map<String,Object>  map=new HashMap<String,Object>();
        map.put("uuid",uuid);
        map.put("affirm",affirm); //员工是否确认
        map.put("helptype",helptype); //互助的类型
        map.put("joinDate",joinDate); //加入的时间
        map.put("page",page);
        map.put("pageSize",pageSize); //暂定为每页显示60人

        List<UserStaff> userStaffs;
        try{
            userStaffs=companyputaoService.getAllStuffs(map);
            if (userStaffs!=null && !userStaffs.isEmpty()){
                resp.success(userStaffs);
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        resp.failByNoData();
        return resp;

    }
    //3.删除一名员工
    @RequestMapping(value="/delAStuff.do",method=RequestMethod.POST)
    @ResponseBody
    public Object delAStuff(HttpServletRequest request){
        Response<CompanyBaseInfo> resp = new Response<CompanyBaseInfo>();

        String  token=request.getParameter("token");
        String  uuid=request.getParameter("uuid"); //用户的uuid
        String  account=request.getParameter("account"); //被删除员工的身份证号
        String  helptyep=request.getParameter("helptype"); //参与的互助的类型
        //String  affirm=request.getParameter("affirm"); //
        try{
            String userPassword=token.substring(0,32); //token是password和userID拼接成的。
            String useridStr=token.substring(32);
            Long userid=Long.valueOf(useridStr).longValue();
            String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
            if(!userPassword.equals(passwdTrue)){
                resp.failByNoInputData("认证失败，密码不对");
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("uuid",uuid);
        map.put("account",account);
        map.put("helptype",helptyep);
       // map.put("affirm",affirm); //del
        CompanyBaseInfo companyBaseInfo=companyputaoService.delResult(map);
        //判断companyBaseInfo 中的code值，
        resp.success(companyBaseInfo);

        return  resp;
    }

    /*4.获取公司的充值详情和支出详情,未使用
    @Deprecated
    @RequestMapping(value="/getpayinfos.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getpayinfos(HttpServletRequest request){
        Response<List<PayMoney>> resp = new Response<List<PayMoney>>();

        String  token=request.getParameter("token");
        String  uuid=request.getParameter("uuid"); //用户的uuid
        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32);
        Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        if(!userPassword.equals(passwdTrue)){
            resp.failByNoInputData("认证失败，密码不对");
            return resp;
        }
        List<PayMoney> payMoneyList;
        try {
            payMoneyList=ashipService.getPayMoneyList(uuid);
            if (payMoneyList!=null){
                resp.success(payMoneyList);
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        resp.failByNoData();
        return resp;
    }*/
    //5.确认一批员工
    @RequestMapping(value="/confirmstuffs.do",method=RequestMethod.POST)
    @ResponseBody
    public Object confirmstuffs( @RequestBody ConfirmStuffsParam confirmStuffsParam) {
        Response<Object> resp = new Response<Object>();

        String token=confirmStuffsParam.getToken();
        String useruuid=confirmStuffsParam.getUseruuid();
        String helptype=confirmStuffsParam.getHelptype();
        List<StuffsUserUUID> stuffsUserUUIDList=confirmStuffsParam.getStuffsUserUUIDList();

        try{
            String userPassword=token.substring(0,32); //token是password和userID拼接成的。
            String useridStr=token.substring(32);
            Long userid=Long.valueOf(useridStr).longValue();
            String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
            if(!userPassword.equals(passwdTrue)){
                resp.failByNoInputData("认证失败，密码不对");
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        //判断confirmStuffsParam 是否为空
        for(StuffsUserUUID stuff:stuffsUserUUIDList){
            //更新userstaff
            //1.为保证安全，应该先查询是否有这个员工，
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("useruuid",useruuid);
            map.put("account",stuff.getAccount());
            map.put("helptype",helptype);
            try{
                UserStaff userStaff=userStaffMapper.selectByparam(map);
                if (userStaff==null){
                    continue;
                }else {
                    userStaff.setAffirm("yes");
                    int updateTag=userStaffMapper.updateByPrimaryKey(userStaff);
                }
            }catch (Exception e){
                //记录日志
                resp.setMsg("有员工未更新成功");
            }
        } //for
        resp.setRetcode(2000);
        return  resp;
    }

    //6.通过html5页面，加入一家公司
    @RequestMapping(value="/staffjoin.do",method = RequestMethod.GET)
    public String staffjoin(){
        String index="front/staffjoin";
        return index;
    }

    //  通过html5界面分享并注册  可以获得红包
    @RequestMapping(value="/joinByShare.do",method = RequestMethod.GET)
    public String staffjoinByHtml(){
        String index="front/joinByShare";
        return index;
    }

    //6.1 ajax提交验证
    @RequestMapping(value="/addstaff.do",method = RequestMethod.POST)
    @ResponseBody
    public Object addstaff(HttpServletRequest request){
        CommonRsp commonRsp=new CommonRsp();
        //token是私有的，不能泄露
        String uuid=request.getParameter("uuid");
        String timeStamp=request.getParameter("timeStamp");//应该是加密的时间戳
        String staffName=request.getParameter("staffName"); //用户姓名
        String account=request.getParameter("account");    //身份证号码
        String phone=request.getParameter("phone");       // 用户的手机号
        String verifycode=request.getParameter("verifyCode");  //验证码
        String helptype=request.getParameter("helptype"); //参与的互助的类型

        try{
            Message message = phoneService.checkPhoneCode(phone,verifycode);
            if (message==null){
                commonRsp.setMsg("验证码和手机号不匹配");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("查询验证码抛出异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        //验证验证码是否正确,查询该手机号对应的最新的验证码，
        //验证时间戳是否正确，时间戳应该用对称加密。
        UserStaff userStaff=new UserStaff();
        userStaff.setStaffuuid(UUID.randomUUID().toString());
        userStaff.setUseruuid(uuid);
        userStaff.setStaffname(staffName);
        userStaff.setAccount(account);
        userStaff.setStaffphone(phone);
        userStaff.setHelptype(helptype);
        userStaff.setAffirm("no");
        userStaff.setJoindate(new Date()); //加入时间
        userStaff.setApplyhelptimes(0); //在本公司申请互助的次数
        Map<String,Object> map=new HashMap<>();

        map.put("useruuid",uuid); //公司的uuid
        map.put("account",account); //员工的身份证号
        map.put("helptype",helptype); //员工参与的互助计划

        try{
            //应该先查询该员工是否已经加入了该公司的该互助计划
            UserStaff userStaff1=companyputaoService.selectUserStaff( map);
            if (userStaff1!=null){
                commonRsp.setRetcode(2001);
                commonRsp.setMsg("用户已经加入");
                return commonRsp;
            }
            int tag=companyputaoService.addStuff(userStaff);
            if (tag!=1){
                commonRsp.setMsg("加入失败");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }

        }catch (Exception e){
            commonRsp.setMsg("出现异常");
            commonRsp.setRetcode(2002);
            return commonRsp;

        }
        commonRsp.setMsg("加入成功");
        commonRsp.setRetcode(2000);
        return commonRsp;
    }

    //7.公司认证
    @RequestMapping(value="/companyforreal.do",method=RequestMethod.POST)
    @ResponseBody
    public Object renZheng( HttpServletRequest request) throws IOException {
        Response<Object> resp = new Response<Object>();
        String ipAndport= ServerIP.getiPPort(); //http://172.16.32.52:8080

        String token=request.getParameter("token");

        String useruuid=request.getParameter("useruuid");
        String name=request.getParameter("name"); //用户名字或者企业名字
        String numberid=request.getParameter("numberid"); //统一征信码或者身份证号码
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");
        String legalPerson=request.getParameter("legalPerson");//法人姓名，
        String authtype=request.getParameter("authtype"); //个人认证还会企业认证
        String  verifyCode=request.getParameter("verifyCode");
        // 1.校验token
        try{
            String userPassword=token.substring(0,32); //token是password和userID拼接成的。
            String useridStr=token.substring(32);
            Long userid=Long.valueOf(useridStr).longValue();
            String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
            if(!userPassword.equals(passwdTrue)){
                resp.failByNoInputData("认证失败，密码不对");
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        // 2. 校验其它字段
        try{
            if (useruuid.length()<32 ||name.length()>30 ||numberid.length()!=18 ||phone.length()!=11  ||
                    email.length()>60 ||legalPerson.length()>10 ||verifyCode.length()!=4){
                resp.failByNoInputData("请检查输入字段");
                return  resp;
            }

        }catch (Exception e){
            resp.failByException();
            return resp;

        }

        List<String> imgPathArray=new ArrayList<String>();

        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            //System.out.println(multiRequest.getParameterNames().nextElement());
            Iterator<String> iter = multiRequest.getFileNames();
            //在本次上传中，这个部分路径是常量
            String constDirectory="/usr/local/nginx/html/staticImage"; //tomcat配置的常量路径+weiboPhoto
            String timeDirectory=new TimeUtil().getNyDay(); //每天创建一个文件夹,时间路径
            String directory=constDirectory+timeDirectory+'/'+token+'/';

            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为"",说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){ //
                        //System.out.println(myFileName);
                        //重命名上传后的文件名
                        String originfileName = file.getOriginalFilename();
                        String millisFileName=new TimeUtil().getMil().toString();
                        String fileName=millisFileName+originfileName; //文件名，包含时间戳与原始文件名，确保不重复
                        String path = directory+fileName+".png"; //需要修改
                        //这里面方法更安全，待测试
                        if(CreateFileUtil.createDirectory(directory)==1){//目录已经存在或创建成功
                            File localFile = new File(path);
                            file.transferTo(localFile);

                            String useful=timeDirectory+'/'+token+'/'+fileName+".png";

                            imgPathArray.add(useful);
                        }else{
                            resp.failByNoInputData("磁盘创建目录失败");
                            return resp;
                        }
                    }//if
                }//if
                //记录上传该文件后的时间
                //int finaltime = (int) System.currentTimeMillis();
            }//while
        }
        /***以下将传递过来的文本信息存入数据库***/
        Putaoauth putaoauth=new Putaoauth();
        putaoauth.setRenzhenguuid(UUID.randomUUID().toString());
        putaoauth.setUseruuid(useruuid);
        putaoauth.setName(name);
        putaoauth.setNumberid(numberid);
        putaoauth.setPhone(phone);
        putaoauth.setEmail(email);
        putaoauth.setConfirmif("unhandle");
        putaoauth.setImgone(imgPathArray.get(0));
        putaoauth.setImgtwo(imgPathArray.get(1));
        putaoauth.setImgthree(imgPathArray.get(2));
        putaoauth.setAuthtype(authtype);
        putaoauth.setLegalperson(legalPerson);
        try{
            //先查询是不是已经认证了
            Putaoauth putaoauth1=companyputaoService.checkIfauth(useruuid);
            if (putaoauth1!=null){
                resp.failByNoInputData("已经认证过了");
                return  resp;
            }
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }
        try{
            //如果没有认证，就插入这条认证
            int tag=companyputaoService.addPutaoauth(putaoauth);
            //将用户类型变为3  即 审核中

            if (tag==1){
                User user=new User();
                user.setUseruuid(useruuid);
                byte usertype=3;
                user.setUsertype(usertype);
                int tagUpdateUser=userMapper.updateByUserUUID(user);
                if(tagUpdateUser==1){
                    resp.success("认证提交成功");
                    return resp;
                }

            }

        }catch (Exception e){
            resp.failByException();
            return  resp;
        }

        resp.failByNoInputData("认证提交失败");
        return resp;
    }



    //8.获得通知消息列表
    @RequestMapping(value="/notification.do",method=RequestMethod.POST)
    @ResponseBody
    public Object notification( HttpServletRequest request) {
        Response<List<Notification>> resp = new Response<List<Notification>>();
        String  token=request.getParameter("token");
        String  uuid=request.getParameter("uuid"); //用户的uuid
        String  pageStr=request.getParameter("page"); //用户的uuid
        int page=Integer.valueOf(pageStr);
        int pageSize=100;
        page=pageSize*(page-1);
        try{
            String userPassword=token.substring(0,32); //token是password和userID拼接成的。
            String useridStr=token.substring(32);
            Long userid=Long.valueOf(useridStr).longValue();
            String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
            if(!userPassword.equals(passwdTrue)){
                resp.failByNoInputData("认证失败，密码不对");
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }

        Map<String,Object> map=new HashMap<>();
        map.put("uuid",uuid);
        map.put("page",page);
        map.put("pageSize",pageSize);
        List<Notification> notificationList;
        try{
             notificationList=companyputaoService.getNotifications(map);
             if(notificationList==null || notificationList.isEmpty()){
                 resp.failByNoData();
                 return resp;
             }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }

        resp.success(notificationList);
        return resp;
    }


}
