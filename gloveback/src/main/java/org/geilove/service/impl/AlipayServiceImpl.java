package org.geilove.service.impl;

import org.geilove.dao.*;
import org.geilove.pojo.*;
import org.geilove.requestParam.AlipayOrderUser;
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
    private  PayaddsetMapper payaddsetMapper;
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
            return 0;
        }
        return tag;
    }

    @Override
    public  void paySetInsert(PayMoney payMoney,List<AlipayOrderUser> alipayOrderUsers){
        //PayMoney表记录一下，payaddset记录所有的
        // 1.PayMoney表插入该条记录
        try{
            int insertTag=payMoneyMapper.insert(payMoney);
        }catch (Exception e){
             //这个应该记录日志
        }
        // 2.遍历alipayOrderUsers，等数量的Payaddset记录，插入到数据库中
        for(AlipayOrderUser aou: alipayOrderUsers){
            Payaddset payaddset=new Payaddset();

            payaddset.setPayadduuid(payMoney.getOutTradeNo()); //
            payaddset.setAccountuuid(aou.getAccountuuid()); //被充值人的身份证号
            payaddset.setUseruuid(payMoney.getUseruuid()); //注册用户的userUUID
            payaddset.setCategorytype(payMoney.getCategorytype()); //互助计划
            payaddset.setNotifyTime(new Date());
            // payaddset.setTradeNo(payMoney.getTradeNo());
            payaddset.setOutTradeNo(payMoney.getOutTradeNo()); //通过此字段和payoney关联起来
            payaddset.setTotalAmount(aou.getMoney()); //给该用户充值的金额
            payaddset.setTradeStatus("WAIT_BUYER_PAY");
            payaddset.setPassbackParams(aou.getUsername()); //被充值人的姓名
            // 插入这条记录
            try{
                int insertTag=payaddsetMapper.insertSelective(payaddset);
            }catch (Exception e){
                 // 有可能某一个会出错。记录日志
            }

        }
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

}





















