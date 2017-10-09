package org.geilove.service.impl;

import org.geilove.dao.*;
import org.geilove.pojo.*;
import org.geilove.service.AlipayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by aihaitao on 28/9/2017.
 *
 */

@Service("alipay")
public class AlipayServiceImpl implements AlipayService {

    @Resource
    private PayMoneyMapper  payMoneyMapper;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private ShareUserMapper shareUserMapper;
    @Resource
    private RedMoneyMapper redMoneyMapper;
    @Resource
    private AccountMapper accountMapper;



    @Override
    public  int insertPrePayMoneyRecord(PayMoney payMoney){
        int tag=0;
        try {
            tag=payMoneyMapper.insertSelective(payMoney);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return tag;
    }
    //根据tradeNo, totalMoney, sellerid,appid等验证订单是否来自于支付宝
    @Override
    public  PayMoney  selectByTradeNoandTotalMoney(Map<String,String> map){

        PayMoney payMoney= null;
        try {
            payMoney = payMoneyMapper.selectByMapKeys(map);
        } catch (Exception e) {
            System.out.println("查询订单失败"+e.getMessage());
            e.printStackTrace();
        }

        return payMoney;  //如果为空，说明不是来自支付宝的回调通知
    }

    @Override
    public  void   aliNotify(PayMoney payMoney){
        //在这里先查询是否有该订单，如果有就更新PayMoney表，对于UserAccount表如果用户没加入就插入记录，否则就只更新金钱
        PayMoney  payMoney1=new PayMoney();
        if (false){
           //判断app_id 或者seller_id正确
        }

        Map<String,String>  map=new HashMap<String,String>();
        map.put("outTradeNo",payMoney.getOutTradeNo());  //交易订单号
        map.put("totalAmount",payMoney.getTotalAmount()); //金额
        try {
            payMoney1=payMoneyMapper.selectByMapKeys(map);//查询数据库确定是否有此订单
            if (payMoney1==null){
                return;
            }
        }catch (Exception e){
            return;
        }
        //有此订单，更新PayMoney表，
        Long paymoneyid=payMoney1.getPaymoneyid();
        payMoney.setPaymoneyid(paymoneyid);
        try {
            //在生成订单的时候，已经插入了一次的。
            int updateTag=payMoneyMapper.updateByPrimaryKeySelective(payMoney); //更新充值记录表
        }catch (Exception e){
            //记录日志
        }
        //*********更新UserAccount表*********//
        // 1.根据userUUID，accountUUID,categorytype查询是否有此用户参与的该计划，如果没有就加入，如果有就更新
        Map<String,String> map1=new HashMap<>();

        String  userUUID=payMoney.getUseruuid();
        String  accountUUID =payMoney.getAccountuuid();
        String  categorytype=payMoney.getCategorytype();//互助的类别
        String  totalAmount=payMoney.getTotalAmount(); //
        map1.put("userUUID",userUUID);
        map1.put("accountUUID",accountUUID);
        map1.put("categorytype",categorytype);
        UserAccount userAccount=null;
        try {
             userAccount=userAccountMapper.selectByUUID(map1); //如果userAccount==null就插入新的

        }catch (Exception e){

        }
        if (userAccount==null){
            // 必须new
            userAccount=new UserAccount();
            String  useraccountuuid= UUID.randomUUID().toString();  //uuid
            String  useruuid=payMoney.getUseruuid();
            String  accountuuid=payMoney.getAccountuuid();
            String  userName=payMoney.getPassbackParams().substring(18); //用户姓名

            Date effectiveDate=new  Date();//取时间
            System.out.println(effectiveDate.toString());
            Calendar calendar   =   new   GregorianCalendar();
            calendar.setTime(effectiveDate);
            calendar.add(calendar.DAY_OF_MONTH, 180);//日期往后增加180天
            effectiveDate=calendar.getTime();   // 生效时间
            System.out.println(effectiveDate.toString());

            //把所有的字段放入到userAccount
//            userAccount.setAccountuuid(useraccountuuid);

            userAccount.setUseraccountuuid(useraccountuuid); //uuid生成器生成的
            userAccount.setUseruuid(useruuid);
            userAccount.setAccountuuid(accountuuid);//accountUUID 是account的身份证号
            userAccount.setUsername(userName);
            userAccount.setBreakif("no"); //no 代表没有解除关联
            userAccount.setBuildrelationdate(new Date());
            userAccount.setBreakrelationdescription("business"); //需要修改
            userAccount.setCategorytype(payMoney.getCategorytype()); //互助类别
            userAccount.setJoindate(new Date()); //加入
            userAccount.setEffectivedate(effectiveDate); //用户生效时间
            userAccount.setNowstate("normal"); //正常
            userAccount.setPaytotalmoney(totalAmount.toString());
            userAccount.setPaytotalmoney(totalAmount); //账户余额

            try {
                int insertTag=userAccountMapper.insertSelective(userAccount);
                System.out.println(insertTag);
            }catch (Exception e){
                System.out.println("加入计划失败");
            }
            //加入计划成功之后，将用户添加到Account表中
            Account account=new Account();
            account.setAccountuuid(accountuuid);
            account.setUseridentity(accountuuid);
            Account queryAccout=accountMapper.selectByuserIdentity(accountuuid);
            if(queryAccout==null){
                //如果account表中不存在该用户 则进行添加操作
                account.setUsername(userName);
                account.setAccountdate(new Date());
                try {
                    int insertAccount=accountMapper.insertSelective(account);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("account加入失败");
                }
            }

            // 插入userAccount表成功之后，去shareUser表查询有关数据  确认当前充值的用户是不是通过点击分享网页进行注册的
            ShareUser shareUser=shareUserMapper.selectByUserUUID(useruuid);
            if(shareUser!=null){
                //shareUser 不是null 说明当前充值的用户是通过分享注册的
                if(shareUser.getHaspay()==0){

                    try {
                        shareUser.setHaspay(1);
                        int updateShareUser =shareUserMapper.updateByPrimaryKeySelective(shareUser);
                        //改变shareUser的状态 然后更新
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        //更改红包表中 因其生成的红包状态  由waiting 改为 active
                        RedMoney redMoney=redMoneyMapper.selectByUserUUIDClick(useruuid);
                        redMoney.setRedmoneystate("active");
                        int updateRedMoney=redMoneyMapper.updateByPrimaryKeySelective(redMoney);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }else { //这种情况只当用户是充钱了
            // 1.这个是原先数据库的钱
            String  totalAmountStr=userAccount.getPaytotalmoney(); //
            Double  totalAmountDouble=Double.valueOf(totalAmountStr.toString());
            BigDecimal totalAmountBig = new BigDecimal(String.valueOf(totalAmountDouble));
            // 2.这个是新充值的钱数
            String   alipayMoney=payMoney.getTotalAmount();
            Double   alipayMoneyDouble=Double.valueOf(alipayMoney.toString());
            BigDecimal   alipayMoneyBig=new BigDecimal(String.valueOf(alipayMoneyDouble));
            // 3.钱数求和
            BigDecimal finalMoney = alipayMoneyBig.add(totalAmountBig); //新的钱数
            String  finalMoneyStr=finalMoney.toString();
            //只更新钱就可以了
            userAccount.setPaytotalmoney(finalMoneyStr);
            try {
                int  updateTag=userAccountMapper.updateByPrimaryKeySelective(userAccount);
            }catch (Exception e){

            }
            // 插入userAccount表成功之后，去shareUser表查询有关数据  确认当前充值的用户是不是通过点击分享网页进行注册的
            ShareUser shareUser=shareUserMapper.selectByUserUUID(payMoney.getUseruuid());
            if(shareUser!=null){
                //shareUser 不是null 说明当前充值的用户是通过分享注册的
                if(shareUser.getHaspay()==0){

                    try {
                        shareUser.setHaspay(1);
                        int updateShareUser =shareUserMapper.updateByPrimaryKeySelective(shareUser);
                        //改变shareUser的状态 然后更新
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        //更改红包表中 因其生成的红包状态  由waiting 改为 active
                        RedMoney redMoney=redMoneyMapper.selectByUserUUIDClick(payMoney.getUseruuid());
                        redMoney.setRedmoneystate("active");
                        int updateRedMoney=redMoneyMapper.updateByPrimaryKeySelective(redMoney);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return;
    }//aliNotify
}





















