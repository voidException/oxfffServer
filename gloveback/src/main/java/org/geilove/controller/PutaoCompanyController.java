package org.geilove.controller;


import org.geilove.pojo.*;

import org.geilove.requestParam.ConfirmStuffsParam;
import org.geilove.requestParam.StuffsUserUUID;
import org.geilove.response.CommonRsp;
import org.geilove.service.AshipService;
import org.geilove.service.CompanyputaoService;
import org.geilove.service.RegisterLoginService;
import org.geilove.service.SelRedMoneyService;
import org.geilove.util.CreateFileUtil;
import org.geilove.util.Response;
import org.geilove.util.ServerIP;
import org.geilove.util.TimeUtil;
import org.geilove.vov.CompanyBaseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    //1.获取公司参与的所有互助计划
    @RequestMapping(value="/getcompanyhelp.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getCompanyHelp(HttpServletRequest request){

        Response<List<Companyputao>> resp = new Response<List<Companyputao>>();

        String token=request.getParameter("token");
        String  uuid=request.getParameter("uuid"); //用户的uuid

        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32);
        Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        if(!userPassword.equals(passwdTrue)){
            resp.failByNoInputData("认证失败，密码不对");
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
                resp.success(companyputaoList);
                return  resp;
            }
        }catch (Exception e){
             resp.failByException();
             return  resp;
        }
        resp.failByNoData();
        return resp;
    }
    //2.获取公司所有的员工
    @RequestMapping(value="/getallstuffs.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getAllStuffs(HttpServletRequest request){
        Response<List<UserStaff>> resp = new Response<List<UserStaff>>();

        String token=request.getParameter("token");
        String  uuid=request.getParameter("uuid"); //用户的uuid
        String  affirm=request.getParameter("affirm");
        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32);
        Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        if(!userPassword.equals(passwdTrue)){
            resp.failByNoInputData("认证失败，密码不对");
            return resp;
        }
        //使用uuid在companyputao表中取出该公司参与的计划
        Map<String,Object>  map=new HashMap<String,Object>();
        map.put("uuid",uuid);
        map.put("affirm",affirm);
        map.put("page",0);
        map.put("pageSize",10);
        List<UserStaff> userStaffs;
        try{
            userStaffs=companyputaoService.getAllStuffs(map);
            if (userStaffs!=null){
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
        String  helptyep=request.getParameter("helptype");
        String  affirm=request.getParameter("affirm"); //

        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32);
        Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        if(!userPassword.equals(passwdTrue)){
            resp.failByNoInputData("认证失败，密码不对");
            return resp;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("uuid",uuid);
        map.put("account",account);
        map.put("helptype",helptyep);
        map.put("affirm",affirm); //del
        CompanyBaseInfo companyBaseInfo=companyputaoService.delResult(map);
        //判断companyBaseInfo 中的code值，
        resp.success(companyBaseInfo);

        return  resp;
    }

    //4.获取公司的充值详情和支出详情
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
    }
    //5.确认一批员工
    @RequestMapping(value="/confirmstuffs.do",method=RequestMethod.POST)
    @ResponseBody
    public Object confirmstuffs( @RequestBody ConfirmStuffsParam confirmStuffsParam) {
        CommonRsp commonRsp=new CommonRsp();

        String token=confirmStuffsParam.getToken();
        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32);
        Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        if(!userPassword.equals(passwdTrue)){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("账号或密码有误");
            return commonRsp;
        }
        //判断confirmStuffsParam 是否为空
        List<StuffsUserUUID> stuffsUserUUIDList=confirmStuffsParam.getStuffsUserUUIDList();
        for(StuffsUserUUID stuff:stuffsUserUUIDList){
            //更新userstaff
            try {
                //把员工的待确认状态改成正式员工状态
                int updateTag=companyputaoService.updateConfirmStuff(stuff);
            }catch (Exception e){
                commonRsp.setMsg("如有员工确认失败，请与客服联系");
            }
        }

        commonRsp.setRetcode(2000);
        return  commonRsp;
    }

    //6.通过html5页面，加入一家公司
    @RequestMapping(value="/staffjoin.do",method = RequestMethod.GET)
    public String staffjoin(){
        String index="front/staffjoin";
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
        String authCode=request.getParameter("authCode");  //验证码
        String helptype=request.getParameter("helptype"); //参与的互助的类型

        //验证验证码是否正确,查询该手机号对应的最新的验证码，
        //验证时间戳是否正确，时间戳应该用对称加密。
        UserStaff userStaff=new UserStaff();
        userStaff.setStaffuuid(uuid);
        userStaff.setStaffname(staffName);
        userStaff.setAccount(account);
        userStaff.setStaffphone(phone);
        userStaff.setHelptype(helptype);
        userStaff.setAffirm("no");
        userStaff.setJoindate(new Date()); //加入时间
        userStaff.setApplyhelptimes(0); //在本公司申请互助的次数
        try{
            //应该先查询该员工是否已经加入了，
            int tag=companyputaoService.addStuff(userStaff);
        }catch (Exception e){

        }


        return commonRsp;
    }

    //7.公司认证
    @RequestMapping(value="/companyforreal.do",method=RequestMethod.POST)
    @ResponseBody
    public Object renZheng( HttpServletRequest request) throws IOException {
        CommonRsp commonRsp=new CommonRsp();
        String ipAndport= ServerIP.getiPPort(); //http://172.16.32.52:8080
        String token=request.getParameter("token");
        String useruuid=request.getParameter("useruuid");
        String name=request.getParameter("name"); //用户名字或者企业名字
        String numberid=request.getParameter("numberid"); //统一征信码或者身份证号码
        String phone=request.getParameter("phone");
        String address=request.getParameter("address");
        String email=request.getParameter("email");
        String legalPerson=request.getParameter("legalPerson");//法人姓名，
        String authtype=request.getParameter("authtype"); //个人认证还会企业认证
        String  verifyCode=request.getParameter("verifyCode");
        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32);
        Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=null;
        try{
            passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        }catch (Exception e){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("发布推文出错");
            return commonRsp;
        }

        if(!userPassword.equals(passwdTrue)){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户密码不对，非法");
            return commonRsp;
        }
        List<String> imgPathArray=new ArrayList<String>();  //这个是图片的URL地址。http://wwww.geilove.org/path/weiboPhoto/.../88.png

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
            String constDirectory="/huzhuguanjia/weiboPhoto"; //tomcat配置的常量路径+weiboPhoto
            String timeDirectory=new TimeUtil().getNyDay(); //每天创建一个文件夹,时间路径
            String directory=constDirectory+timeDirectory+'/'+useridStr+'/';

            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为"",说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        //System.out.println(myFileName);
                        //重命名上传后的文件名
                        String originfileName = file.getOriginalFilename();
                        String millisFileName=new TimeUtil().getMil().toString();
                        String fileName=millisFileName+originfileName; //文件名，包含时间戳与原始文件名，确保不重复
                        String path = directory+fileName+".png";
                        //这里面方法更安全，待测试
                        if(CreateFileUtil.createDirectory(directory)==1){//目录已经存在或创建成功
                            File localFile = new File(path);
                            file.transferTo(localFile);
                            String needPath=ipAndport+"path/weiboPhoto"+timeDirectory+'/'+useridStr+'/'+fileName+".png"; //测试使用的
                            // String needPath="http://www.geilove.org/path/weiboPhoto"+timeDirectory+'/'+useridStr+'/'+fileName+".png";
                            imgPathArray.add(needPath);
                        }else{
                            commonRsp.setMsg("创建磁盘目录失败");
                            commonRsp.setRetcode(2005);
                            return commonRsp;
                        }
                    }//if
                }//if
                //记录上传该文件后的时间
                //int finaltime = (int) System.currentTimeMillis();
            }//while
        }
        /***以下将传递过来的文本信息存入数据库***/
        Putaoauth putaoauth=new Putaoauth();
        putaoauth.setUseruuid(useruuid);
        putaoauth.setName(name);
        putaoauth.setNumberid(numberid);
        putaoauth.setPhone(phone);
        putaoauth.setAddress(address);
        putaoauth.setEmail(email);
        putaoauth.setConfirmif("no");
        putaoauth.setImgone(imgPathArray.get(0));
        putaoauth.setImgtwo(imgPathArray.get(1));
        putaoauth.setImgthree(imgPathArray.get(2));
        putaoauth.setAuthtype(authtype);
        putaoauth.setLegalperson(legalPerson);
        try{
            //先查询是不是已经认证了
            Putaoauth putaoauth1=companyputaoService.checkIfauth(useruuid);
            if (putaoauth1!=null){
                commonRsp.setMsg("已经认证过了");
                commonRsp.setRetcode(2001);
                return  commonRsp;
            }

        }catch (Exception e){
            commonRsp.setMsg("查询是否认证出现异常");
            commonRsp.setRetcode(2001);
            return  commonRsp;

        }
        try{
            //如果没有认证，就插入这条认证
            int tag=companyputaoService.addPutaoauth(putaoauth);
            if (tag==1){
                commonRsp.setMsg("认证提交成功");
                commonRsp.setRetcode(2000);
                return  commonRsp;
            }

        }catch (Exception e){
            commonRsp.setMsg("认证提交出现异常");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        commonRsp.setMsg("认证提交失败");
        commonRsp.setRetcode(2001);
        return  commonRsp;
    }




    //8.获得通知消息列表
    @RequestMapping(value="/notification.do",method=RequestMethod.POST)
    @ResponseBody
    public Object notification( HttpServletRequest request) {
        Response<List<Notification>> resp = new Response<List<Notification>>();
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
        Map<String,Object> map=new HashMap<>();
        map.put("uuid",uuid);
        map.put("page",1);
        map.put("pageSize",10);
        List<Notification> notificationList;
        try{
             notificationList=companyputaoService.getNotifications(map);
        }catch (Exception e){

        }


        return 0;
    }


}
