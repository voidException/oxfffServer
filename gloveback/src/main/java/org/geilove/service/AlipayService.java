package org.geilove.service;

import org.geilove.pojo.PayMoney;

import java.util.Map;

/**
 * Created by aihaitao on 28/9/2017.
 */
public interface  AlipayService {

    public  int insertPrePayMoneyRecord(PayMoney payMoney); // 生成订单的时候预先插入充值记录，trade_status

    public  PayMoney  selectByTradeNoandTotalMoney(Map<String,String> map);

    public  void   aliNotify(PayMoney payMoney);

}
