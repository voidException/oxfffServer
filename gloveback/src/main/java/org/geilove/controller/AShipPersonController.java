package org.geilove.controller;


import org.apache.poi.ss.formula.functions.T;
import org.geilove.pojo.Account;
import org.geilove.pojo.RedMoney;
import org.geilove.pojo.User;
import org.geilove.pojo.UserAccount;
import org.geilove.requestParam.UseRedMoneyParam;
import org.geilove.service.SelRedMoneyService;
import org.geilove.service.UserAndUserAccountService;
import org.geilove.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.jvm.hotspot.oops.ObjectHeap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/person")
public class AShipPersonController {

    @Resource
    private SelRedMoneyService selRedMoneyService;

    @Resource
    private UserAndUserAccountService userAndUserAccountService;

    @RequestMapping(value = "/myCoupon.do", method = RequestMethod.POST)
    @ResponseBody
    public Object checkMyCoupon(@RequestBody RedMoney redMoney, HttpServletRequest request, HttpServletResponse response) {

        Response<List<RedMoney>> resp = new Response<List<RedMoney>>();

        if (redMoney == null) {
            resp.failByNoInputData("传入的用户为空");
            return resp;
        } else if (redMoney.getUseruuid() == null || redMoney.getUseruuid() == "") {
            resp.failByNoInputData("传入的uuid为空");
            return resp;
        }


        List<RedMoney> redMoneyList = null;
        try {
            String useruuid = redMoney.getUseruuid();
            Date redMoneyDate = redMoney.getRedmoneydate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String redMoneyDateString = simpleDateFormat.format(redMoneyDate);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("useruuid", useruuid);
            hashMap.put("redMoneyDate", redMoneyDateString);
            redMoneyList = selRedMoneyService.selAllRedMoney(hashMap);
            //数据库查询数据
        } catch (Exception e) {
            e.printStackTrace();
            resp.failByException();
            return resp;
        }

        if (redMoneyList == null) {
            resp.failByNoData();
        } else {
            resp.success(redMoneyList);

        }
        System.out.println('q');
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
    public Response<String> useMyRedMoney(@RequestBody UseRedMoneyParam useRedMoneyParam, HttpServletRequest request, HttpServletResponse response) {
        Response<String> resp = new Response<>();
        /*
        1根据传入的红包信息 判断红包是不是失效了
        2.去useraccount表中查询相关的数据
        3.使用 红包
         */
        String useruuid = useRedMoneyParam.getUseruuid();
        Long redmoneyid = useRedMoneyParam.getRedmoneyid();
        String username = useRedMoneyParam.getUsername();
        String useridentity = useRedMoneyParam.getUseridentity();
        String categorytype = useRedMoneyParam.getCategorytype();
        if (redmoneyid == null || username == null || useridentity == null || categorytype == null) {
            resp.failByNoInputData("对不起，请您检查您的输入是否有误");
        }

        //首先查询 红包数据 确定红包是否失效
        RedMoney redMoney = selRedMoneyService.selectByPrimaryKey(redmoneyid);
        if ("used".equals(redMoney.getRedmoneystate())) {
            //失效直接返回失效信息
            resp.setMsg("这个红包已经使用了哦");
            resp.setRetcode(2002);
            return resp;
        }

        //确定红包尚未失效，则去判断要充值的人的信息是够正确存在
        Account account = userAndUserAccountService.selectByuserIdentity(useridentity);
        if (account == null) {
            resp.setMsg("对不起，您要充值的人不存在");
            resp.setRetcode(2002);
            return resp;
        }


        String accountUUID = account.getAccountuuid();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("accountUUID", accountUUID);
        hashMap.put("categoryType", categorytype);
        UserAccount userAccount = userAndUserAccountService.selectByUserAndUserAccount(hashMap);
        //已经获取到了对应的userAccount数据信息
        if (userAccount == null) {
            resp.setMsg("对不起，请您检查您的输入和所选类别");
            resp.setRetcode(2002);
            return resp;
        }

        //先改变红包的使用状态
        redMoney.setRedmoneystate("used");
        int redmoneyHasUsed = selRedMoneyService.updateByPrimaryKeySelective(redMoney);
        if (redmoneyHasUsed == 0) {
            resp.failByException();
            //红包数据更新没有成功
            return resp;
        }
        else{
            Long remainFee = userAccount.getRemainfee();
            userAccount.setRemainfee(remainFee + 500);
            //当红包状态更新成功之后，更新userAccount表中的余额信息
            int hasUpdated = userAndUserAccountService.updateByPrimaryKey(userAccount);


            if (hasUpdated == 1) {
                //使用红包成功之后，要去更新红包表
                resp.setMsg("红包使用成功");
                resp.setRetcode(2000);
                return resp;
            }

        }
        resp.failByException();
        return resp;
    }
}
