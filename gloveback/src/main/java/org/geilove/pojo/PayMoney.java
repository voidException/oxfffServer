package org.geilove.pojo;

import java.util.Date;

public class PayMoney {
    private Long paymoneyid;

    private String paymoneyuuid;

    private String accountuuid;

    private String useruuid;

    private String categorytype;

    private Long wechatpay;

    private Date notifyTime;

    private String appId;

    private String signType;

    private String tradeNo;

    private String outTradeNo;

    private String buyerId;

    private String sellerId;

    private String totalAmount;

    private String tradeStatus;

    private String passbackParams;

    public Long getPaymoneyid() {
        return paymoneyid;
    }

    public void setPaymoneyid(Long paymoneyid) {
        this.paymoneyid = paymoneyid;
    }

    public String getPaymoneyuuid() {
        return paymoneyuuid;
    }

    public void setPaymoneyuuid(String paymoneyuuid) {
        this.paymoneyuuid = paymoneyuuid == null ? null : paymoneyuuid.trim();
    }

    public String getAccountuuid() {
        return accountuuid;
    }

    public void setAccountuuid(String accountuuid) {
        this.accountuuid = accountuuid == null ? null : accountuuid.trim();
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype == null ? null : categorytype.trim();
    }

    public Long getWechatpay() {
        return wechatpay;
    }

    public void setWechatpay(Long wechatpay) {
        this.wechatpay = wechatpay;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType == null ? null : signType.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId == null ? null : buyerId.trim();
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount == null ? null : totalAmount.trim();
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    public String getPassbackParams() {
        return passbackParams;
    }

    public void setPassbackParams(String passbackParams) {
        this.passbackParams = passbackParams == null ? null : passbackParams.trim();
    }
}