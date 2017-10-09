package org.geilove.controller;

/**
 * Created by aihaitao on 14/7/2017.
 */
import org.geilove.pojo.Account;
import org.geilove.pojo.Public;
import org.geilove.pojo.UserAccount;
import org.geilove.requestParam.AddEmployeeParam;
import org.geilove.requestParam.MyEmployeeParam;
import org.geilove.response.CommonRsp;
import org.geilove.response.PublicListRsp;
import org.geilove.response.UserAccountRsp;
import org.geilove.service.AshipService;
import org.geilove.service.RegisterLoginService;
import org.geilove.util.ServerIP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ship")
public class AShipNormalController {
    @Resource
    private AshipService ashipService;
    @Resource
    private RegisterLoginService rlService;

    //添加员工，如果员工此前未加入企业互助，则添加失败；如果未解除与前公司的关联，也失败
    @RequestMapping(value="/addEmployee.do",method=RequestMethod.POST)
    @ResponseBody
    public CommonRsp addEmployee(@RequestBody AddEmployeeParam addEmployeeParam, HttpServletRequest  request){
        CommonRsp commonRsp=new CommonRsp();
        if (addEmployeeParam==null){
            commonRsp.setMsg("请求参数为空");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }

        String token=addEmployeeParam.getToken();
        String userUUID=addEmployeeParam.getUserUUID();
        String accountuuid=addEmployeeParam.getAccountuuid();
        String userName=addEmployeeParam.getUserName();
        String buildrelationdescription=addEmployeeParam.getBuildrelationdescription();

        if(token.length()<33){
            commonRsp.setMsg("凭证不合法");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }

        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32); //取得userid部分
        Long userid=Long.valueOf(useridStr).longValue();  //转换成long类型

        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        if(!userPassword.equals(passwdTrue)){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户密码不对，非法");
            return commonRsp;
        }

        Account account=ashipService.getAccount(accountuuid);
        if(account==null){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("该员工尚未加入企业互助类型");
            return commonRsp;
        }


        //1.查看UserAccount表，确定该用户是否有已经关联的企业，无论是否解除关联，都只能有一条
         Map<String,Object> map=new HashMap<String,Object>();
         map.put("userIdentity",accountuuid);
         map.put("buildRelationDescription",buildrelationdescription); //代表与企业关联
         map.put("breakIf","no"); //代表没有断开关联

        UserAccount userAccount=null;
        try{
            //查询出来的应该是list  要改
            userAccount= ashipService.getUserAccountBreakIf(map);
            if (userAccount==null){
                commonRsp.setMsg("员工尚未和原公司解除关联");
                commonRsp.setRetcode(2001);
                return  commonRsp;
            }
        }catch (Exception e){
             commonRsp.setMsg("增加员工出现异常");
             commonRsp.setRetcode(2001);
             return  commonRsp;
        }
        //2.如果breakIf 为yes，就提示用户取消与先前企业的关联
        if (userAccount.getBreakif().equals("yes")){
            commonRsp.setMsg("未取消与前企业的关联");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        //3.用户取消了与前东家的关联，就更新userAccount表的记录为新东家
        userAccount=new UserAccount();
        userAccount.setUseruuid(userUUID);
        userAccount.setBuildrelationdate(new Date());
        userAccount.setUsername(userName); //
        userAccount.setAccountuuid(accountuuid);
        try { //根据accountuuid 进行更新，
            int upDateTag=ashipService.updateByPrimaryKeySelectiveAndBusiness(userAccount);
            if (upDateTag==0){
                commonRsp.setMsg("添加失败");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("抛出异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        commonRsp.setMsg("添加成功");
        commonRsp.setRetcode(2000);
        return  commonRsp;
    }

    //获得我的员工列表
    @RequestMapping(value="/getMyEmployee.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getMyEmployee(@RequestBody MyEmployeeParam myEmployeeParam, HttpServletRequest  getMyEmployeeParam){//这个是获取我的员工列表
       /*   userUUID:用户的uuid
       *    userType:person 或者business
       */
        UserAccountRsp  userAccountRsp=new UserAccountRsp();
        //1.直接从userAccount表根据userUUID 和breakIf 和buildRelationDescription 来查看我的员工

//        String  userUUID=getMyEmployeeParam.getParameter("userUUID");
//        String buildrelationdescription= getMyEmployeeParam.getParameter("buildrelationdescription");
        String userUUID=myEmployeeParam.getUserUUID();
        String buildrelationdescription=myEmployeeParam.getBuildrelationdescription();

        Map<String,Object> map=new HashMap<>();
        map.put("userUUID",userUUID);
        map.put("breakIf","no");
        map.put("buildrelationdescription",buildrelationdescription); //用户和公司建立关联的时间
        map.put("page",0);
        map.put("pageSize",10);
        //map.put("和buildRelationDescription",buildRelationDescription);
        List<UserAccount> userAccountList=null;
        try{
            userAccountList=ashipService.getUserAccountList(map);
            if (userAccountList==null || userAccountList.size()==0){
                userAccountRsp.setMsg("没有员工");
                userAccountRsp.setRetcode(2001);
                return  userAccountRsp;
            }
        }catch (Exception e){
            userAccountRsp.setMsg("抛出异常");
            userAccountRsp.setRetcode(2001);
            return  userAccountRsp;
        }
        //userAccountList不为空，有员工或者家人
        userAccountRsp.setLp(userAccountList);
        userAccountRsp.setMsg("成功");
        userAccountRsp.setRetcode(2000);
        return userAccountRsp;
    }
    //获取公示列表
    @RequestMapping(value="/getPublicList.do",method=RequestMethod.GET)
    @ResponseBody
    public Object  getPublicList(){
        PublicListRsp publicListRsp=new PublicListRsp();
        List<Public> publicList=null;
        Map<String,Object> map=new HashMap<>();
        map.put("page",0);
        map.put("pageSize",10);
        try{
            publicList=ashipService.getPublicList(map);
            if (publicList==null){
                publicListRsp.setRetcode(2001);
                publicListRsp.setMsg("还未有人申请救助");
                return  publicListRsp;
            }
        }catch (Exception e){
            publicListRsp.setRetcode(2001);
            publicListRsp.setMsg("抛出异常");
            return  publicListRsp;
        }
        publicListRsp.setLp(publicList);
        publicListRsp.setRetcode(2000);
        publicListRsp.setMsg("成功");
        return publicListRsp;
    }
    // 公司认证接口
    @RequestMapping(value="/multiUpload",method=RequestMethod.POST)
    @ResponseBody
    public CommonRsp multiUpload(HttpServletRequest request)throws IllegalStateException, IOException {
        CommonRsp commonRsp = new CommonRsp();
        String ipAndport = ServerIP.getiPPort(); //http://172.16.32.52:8080
        String token = request.getParameter("token");
        String userPassword = token.substring(0, 32); //token是password和userID拼接成的。
        String useridStr = token.substring(32);
        Long userid = Long.valueOf(useridStr).longValue();
        String passwdTrue = null;
        try {
            passwdTrue = rlService.selectMD5Password(Long.valueOf(userid));
        } catch (Exception e) {
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("发布推文出错");
            return commonRsp;
        }
        return  commonRsp;
    }

}
















