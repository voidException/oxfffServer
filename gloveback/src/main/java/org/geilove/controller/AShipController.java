package org.geilove.controller;

/**
 * 这里是专门葡萄互助App开发的接口.
 * http请求路径必须是包含 /ship
 */
import org.geilove.dao.CompanyputaoMapper;
import org.geilove.dao.PayMoneyMapper;
import org.geilove.dao.UserAccountMapper;
import org.geilove.dao.UserStaffMapper;
import org.geilove.pojo.*;
import org.geilove.response.AwardListRsp;
import org.geilove.service.AshipService;
import org.geilove.util.Arith;
import org.geilove.util.Response;
import org.geilove.vo.MainSumInfo;
import org.geilove.vo.SumInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ship")
public class AShipController {

    @Resource
    private AshipService ashipService;
    @Resource
    private UserStaffMapper userStaffMapper;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private CompanyputaoMapper companyputaoMapper;
    @Resource
    private PayMoneyMapper  payMoneyMapper;

    @RequestMapping(value="/getMainSumInfo.do",method=RequestMethod.GET)
    @ResponseBody
    public Object getMainSumInfo(HttpServletRequest request){
        Response<MainSumInfo> resp = new Response<>();
        MainSumInfo mainSumInfo=new MainSumInfo(); //公司总数量/员工总数量。 //总个人互助

        try{
            int totalCompany=companyputaoMapper.getTotalCompany();
            int totalStaff=userStaffMapper.getTotalStaff();
            int totalPerson=userAccountMapper.getTotalUserAccount();

            mainSumInfo.setSumQiye(String.valueOf(totalCompany));
            mainSumInfo.setSumStaff(String.valueOf(totalStaff));
            mainSumInfo.setSumMan(String.valueOf(totalPerson));
        }catch (Exception e){
            resp.failByException();
            return  resp;
        }

        resp.success(mainSumInfo);
        return  resp;

    }

    @RequestMapping(value="/getSumInfo.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getSumInfo( HttpServletRequest request){

        Response<SumInfo> resp = new Response<>();

        String helptype=request.getParameter("helptype");

          if ("staff".equals(helptype) || "employee".equals(helptype)){//公司的互助计划
           List<UserStaff> userStaffs;
           List<Companyputao> companyputaos;
           try{
               Map<String,Object> map=new HashMap<>();
               map.put("helptype",helptype);
               map.put("affirm","yes");
               userStaffs=userStaffMapper.getSumInfo(map);

               if (userStaffs==null ||userStaffs.isEmpty()){
                   resp.failByNoData();
                   return resp;
               }
               companyputaos=companyputaoMapper.getSumInfo(helptype);
               if (companyputaos==null ||companyputaos.isEmpty()){
                   resp.failByNoData();
                   return resp;
               }
               Double sumMoney=0.0;
               for (Companyputao companyputao:companyputaos){
                   String singleMoney=companyputao.getTotalmoenystr();
                   sumMoney=Arith.add(sumMoney,Double.valueOf(singleMoney));//总金额
               }
                   Integer sumRen=userStaffs.size(); //员工总数
                  Double avaMoney=Arith.div(sumMoney,Double.valueOf(sumRen),3);
                   SumInfo sumInfo=new SumInfo();
                   sumInfo.setSumMoney(String.valueOf(sumMoney));
                   sumInfo.setSumMan(String.valueOf(sumRen));
                   sumInfo.setAverage(avaMoney.toString());
                   resp.success(sumInfo);
                   return resp;

              }catch (Exception e){
                    resp.failByException();
              }
             return  resp;
           }
           else{//这个是个人的互助计划
              List<UserAccount> userAccounts;
              try {
                  userAccounts=userAccountMapper.getSumInfo(helptype);
                  if (userAccounts==null ||userAccounts.isEmpty()){
                      resp.failByNoData();
                      return resp;
                  }
                  // 根据获取的userAccounts，获取参与该计划的总人数，计算出总金额，最后计算出平均数，BigDecimal计算
                  Integer sumRen=userAccounts.size(); //参与该计划的人数。一定不是0
                  Double sumMoney=0.0;
                  for (UserAccount userAccount:userAccounts){
                      String payTotalMoney=userAccount.getPaytotalmoney(); //该账户的金额
                      sumMoney= Arith.add(sumMoney,Double.valueOf(payTotalMoney));
                  }
                  // 计算人均
                  Double avaMoney=Arith.div(sumMoney,Double.valueOf(sumRen),3);

                  SumInfo sumInfo=new SumInfo();
                  sumInfo.setSumMoney(String.valueOf(sumMoney));
                  sumInfo.setSumMan(String.valueOf(sumRen));
                  sumInfo.setAverage(String.valueOf(avaMoney));

                  resp.success(sumInfo);
                  return resp;

              }catch (Exception e){
                  resp.failByException();
                  return resp;
              }
         }
    }

    //检查是否是第一次充值
    @RequestMapping(value="/checkifpay.do",method=RequestMethod.POST)
    @ResponseBody
    public Object  checkifpay(HttpServletRequest request ){
        Response<Object> resp = new Response<Object>();
          //获取useruuid 和helptype，然后查充值记录表
        String  useruuid=request.getParameter("useruuid");
        String  helptype=request.getParameter("helptype"); //
        Map<String,Object> map=new HashMap<>();

        map.put("useruuid",useruuid);
        map.put("helptype",helptype);
        map.put("paystatus","TRADE_SUCCESS");
        List<PayMoney> payMoneys=null;
        try {
            payMoneys=payMoneyMapper.getPaymoneys(map);


           if (payMoneys==null || payMoneys.isEmpty()){
               resp.success(true);
               return resp;
           }

        }catch (Exception e){
             //System.out.print(e.getMessage());
             resp.failByException();
             return resp;
        }
        resp.failByNoInputData("不是第一次充值");
        return resp;
    }

    //获取公示列表
    @RequestMapping(value="/getAwardList.do",method=RequestMethod.GET)
    @ResponseBody
    public Object  getAwardList(){
        AwardListRsp awardListRsp=new AwardListRsp();
        List<RedMoney> redMoneyList=null;
        Map<String,Object> map=new HashMap<>();
        map.put("page",0);
        map.put("pageSize",10);
        try{
           // redMoneyList=ashipService.getPublicList(map);
            if (redMoneyList==null){
                awardListRsp.setRetcode(2001);
                awardListRsp.setMsg("您还没有奖励，邀请吧");
                return  awardListRsp;
            }
        }catch (Exception e){
            awardListRsp.setRetcode(2001);
            awardListRsp.setMsg("抛出异常");
            return  awardListRsp;
        }
        awardListRsp.setLp(redMoneyList);
        awardListRsp.setRetcode(2000);
        awardListRsp.setMsg("成功");
        return awardListRsp;
    }


}
