package org.geilove.controller;

import org.geilove.dao.NotificationMapper;
import org.geilove.dao.UserAccountMapper;
import org.geilove.pojo.*;
import org.geilove.service.RegisterLoginService;
import org.geilove.service.SelRedMoneyService;
import org.geilove.service.UserAndUserAccountService;
import org.geilove.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/person")
public class AShipPersonController {

    @Resource
    private SelRedMoneyService selRedMoneyService;

    @Resource
    private UserAndUserAccountService userAndUserAccountService;

    @Resource
    private RegisterLoginService rlService;

    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private NotificationMapper notificationMapper;

    @RequestMapping(value = "/myCoupon.do", method = RequestMethod.POST)
    @ResponseBody
    public Object checkMyCoupon(HttpServletRequest request) {
        Response<List<RedMoney>> resp = new Response<List<RedMoney>>();
        if (request==null || "".equals(request)){
            resp.failByNoInputData("请求数据为空");
            return resp;
        }
        String token = request.getParameter("token");
        String useruuid = request.getParameter("useruuid");
        String pageStr=request.getParameter("page");
        int page=Integer.valueOf(pageStr);
        page=100*(page-1);

        List<RedMoney> redMoneyList = null;
        try {

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("useruuid", useruuid);
            hashMap.put("page", page);
            hashMap.put("state","unactive");

            redMoneyList = selRedMoneyService.selAllRedMoney(hashMap);
            //数据库查询数据
        } catch (Exception e) {
            e.printStackTrace();
            resp.failByException();
            return resp;
        }

        if (redMoneyList == null || redMoneyList.isEmpty()) {
            resp.failByNoData();
        } else {
            resp.success(redMoneyList);
        }
        return resp;
    }

    /**
     * 使用红包的功能
     *
     * @param
     * @return
     */
    @RequestMapping(value = "useMyRedMoney.do", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> useMyRedMoney( HttpServletRequest request) {
        Response<String> resp = new Response<>();
        /*
        1根据传入的红包信息 判断红包是不是失效了
        2.去useraccount表中查询相关的数据
        3.使用 红包
         */
        String token=request.getParameter("token");
        String useruuid = request.getParameter("useruuid");
        String userName=request.getParameter("userName");
        String redmoneyuuid=request.getParameter("redmoneyuuid");
        String account=request.getParameter("account");  //用户身份证号
        String name=request.getParameter("name"); //用户姓名
        String categorytype=request.getParameter("categorytype");

        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32);

        // 1.验证token
        Long userid=Long.valueOf(useridStr).longValue();
        try{
            String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
            if(!userPassword.equals(passwdTrue)){
                resp.failByNoInputData("认证失败，密码不对");
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        // 1. 首先查询红包，确定是否有效
        RedMoney redMoney=null;
        try{
            redMoney = selRedMoneyService.selectByRedUUID(redmoneyuuid);
            if ("used".equals(redMoney.getRedmoneystate())) {
                //失效直接返回失效信息
                resp.setMsg("这个红包已经使用了哦");
                resp.setRetcode(2002);
                return resp;
            }
            //按道理应该是充值成功后失效，这里先失效
            redMoney.setRedmoneystate("used");
            int redMoneyUpdateTag=selRedMoneyService.updateByPrimaryKeySelective(redMoney); //红包失效
            if (redMoneyUpdateTag!=1){
                resp.setMsg("红包状态更新出现异常");
                resp.setRetcode(2002);
                return resp;
            }

        }catch (Exception e){
            resp.failByException();
            return  resp;

        }
        // 2.查询 用户是否加入此互助计划
        UserAccount userAccount=null;
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("account",account);
            map.put("helptype",categorytype);
            userAccount=userAndUserAccountService.selectByuserIdentity(map);
        }catch (Exception e){
           resp.failByException();
           return resp;
        }
        if (userAccount==null){
            //这个用户没有加入任何非企业互助计划，加入互助计划，本账户发message
            Date effectiveDate=new  Date();//取时间
            //System.out.println(effectiveDate.toString());
            Calendar calendar   =   new   GregorianCalendar();
            calendar.setTime(effectiveDate);
            calendar.add(calendar.DAY_OF_MONTH, 180);//日期往后增加180天
            effectiveDate=calendar.getTime();   // 生效时间
            //System.out.println(effectiveDate.toString());

            userAccount=new UserAccount();
            userAccount.setUseraccountuuid(UUID.randomUUID().toString());
            userAccount.setUseruuid(useruuid); //用户的账号
            userAccount.setAccountuuid(account); //用户身份证号
            userAccount.setUsername(name); //用户姓名
            userAccount.setBreakif("no");
            userAccount.setBuildrelationdate(new Date());
            userAccount.setCategorytype(categorytype);
            userAccount.setJoindate(new Date());
            userAccount.setEffectivedate(effectiveDate);
            userAccount.setNowstate("nostart");
            userAccount.setPaytotalmoney("5");
            userAccount.setBuildrelationdescription("home");
            try {
                int inTag=userAccountMapper.insert(userAccount);
                if (inTag!=1){
                    resp.failByNoInputData("加入互助计划失败");
                    return resp;
                }else {
                    // 用户加入互助计划成功，notification
                    Notification notification=new Notification();
                    notification.setNotifyuuid(UUID.randomUUID().toString());
                    notification.setUseruuid(useruuid);
                    notification.setMsgtype("red"); //红包消息
                    notification.setNotifytime(new Date());
                    notification.setMessage("给用户"+name+"使用红包5元");
                    notificationMapper.insert(notification);
                    resp.success("红包充值成功");
                    return resp; //返回
                }

            }catch (Exception e){
                resp.failByException();
                return resp;
            }

        }
        if (userAccount.getCategorytype().equals(categorytype) && userAccount.getUseruuid().equals(useruuid)){
            //用户加入了计划且在该账户下面，充钱，然后本账户发message
            String moneyStr=userAccount.getPaytotalmoney();
            BigDecimal oldMoney=new BigDecimal(moneyStr);
            BigDecimal redmoney = new BigDecimal("5");
            oldMoney= oldMoney.add(redmoney);
            userAccount.setPaytotalmoney(oldMoney.toString());
            try {
                int upTag=userAccountMapper.updateByPrimaryKey(userAccount);
                if (upTag!=1){
                    resp.failByNoInputData("红包充值失败");
                    return  resp;
                }

                //充值成功，发送通知
                // 用户加入互助计划成功，notification
                Notification notification=new Notification();
                notification.setNotifyuuid(UUID.randomUUID().toString());
                notification.setUseruuid(useruuid);
                notification.setMsgtype("red"); //红包消息
                notification.setNotifytime(new Date());
                notification.setMessage("给用户"+name+"使用红包5元");
                notificationMapper.insert(notification);
                resp.success("红包充值成功");
                return resp; //返回

            }catch (Exception e){
                resp.failByException();
                return  resp;

            }

        }
        if (!useruuid.equals(userAccount.getUseruuid())){
            //用户加入了计划，但在别人账户下面。充钱后给别人发message
            String moneyStr=userAccount.getPaytotalmoney();
            BigDecimal oldMoney=new BigDecimal(moneyStr);
            BigDecimal redmoney = new BigDecimal("5");
            oldMoney=oldMoney.add(redmoney);
            userAccount.setPaytotalmoney(oldMoney.toString());
            try {
                int upTag=userAccountMapper.updateByPrimaryKey(userAccount);
                if (upTag!=1){
                    resp.failByNoInputData("红包充值失败");
                    return  resp;
                }

                //充值成功，发送通知
                // 用户加入互助计划成功，notification
                Notification notification=new Notification();
                notification.setNotifyuuid(UUID.randomUUID().toString());
                notification.setMsgtype("red"); //红包消息
                notification.setNotifytime(new Date());
                notification.setMessage(userName+"给用户"+name+"使用红包5元");
                notificationMapper.insert(notification);

                resp.success("红包充值成功");
                return resp; //返回

            }catch (Exception e){
                resp.failByException();
                return  resp;

            }
        }

        resp.success("充值成功");
        return resp;
    }
}
