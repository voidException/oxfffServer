package org.geilove.response;

/**
 * Created by aihaitao on 19/9/2017.
 */
public class AlipayOrderRsp {

    private   String  oderStr;
    private   Integer retcode ;
    private   String msg;

    public String getOderStr() {
        return oderStr;
    }

    public void setOderStr(String oderStr) {
        this.oderStr = oderStr;
    }

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
