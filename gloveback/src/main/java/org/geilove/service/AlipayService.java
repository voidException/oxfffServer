package org.geilove.service;

import org.geilove.pojo.PayMoney;
import org.geilove.requestParam.AlipayOrderUser;

import java.util.List;
import java.util.Map;

/**
 * Created by aihaitao on 28/9/2017.
 */
public interface  AlipayService {

   public  int insertPrePayMoneyRecord(PayMoney payMoney); // 生成订单的时候预先插入充值记录，trade_status

    public  PayMoney  selectByTradeNoandTotalMoney(Map<String,String> map);

    //适用于批量支付的
    public  void paySetInsert(PayMoney payMoney,List<AlipayOrderUser> alipayOrderUsers);

}
