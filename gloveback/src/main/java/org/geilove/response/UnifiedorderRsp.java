package org.geilove.response;

/**
 * 这里返回调起H5支付的全部参数值
 */
public class UnifiedorderRsp {

    private  String   msg;
    private  Integer  retcode;

    private  String   orderId;
    private  String   openId;
    private  String   prepay_id;

    // 以下为必须字段
    private  String   payAppId;       //公众号id
    private  String   paytimestamp;   //时间戳
    private  String   paypackage;     //订单详情扩展字符串
    private  String   paynonceStr;     //随机字符串
    private  String   paysignType;     //MD5
    private  String   paySign;         //签名

    public String getPayAppId() {
        return payAppId;
    }

    public void setPayAppId(String payAppId) {
        this.payAppId = payAppId;
    }

    public String getPaytimestamp() {
        return paytimestamp;
    }

    public void setPaytimestamp(String paytimestamp) {
        this.paytimestamp = paytimestamp;
    }

    public String getPaypackage() {
        return paypackage;
    }

    public void setPaypackage(String paypackage) {
        this.paypackage = paypackage;
    }

    public String getPaynonceStr() {
        return paynonceStr;
    }

    public void setPaynonceStr(String paynonceStr) {
        this.paynonceStr = paynonceStr;
    }

    public String getPaysignType() {
        return paysignType;
    }

    public void setPaysignType(String paysignType) {
        this.paysignType = paysignType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }
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

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }
}
