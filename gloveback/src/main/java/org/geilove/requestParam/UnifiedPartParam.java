package org.geilove.requestParam;

import org.jaxen.function.StringFunction;

/**
 * 在调用起H5支付前，需要用支付的金额，openId 发送请求，获取预支付交易prepay_id
 *
 */
public class UnifiedPartParam {

    private String    orderId;
    private String    openId;
    private Integer   money;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
