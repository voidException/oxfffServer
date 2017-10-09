package org.geilove.response;

import org.geilove.vo.CashConfirm;

/**
 * Created by mfhj-dz-001-424 on 17/2/23.
 */
public class CashConfirmRsp {

    private CashConfirm lp;
    private  String    msg;
    private  Integer   retcode;

    public CashConfirm getLp() {
        return lp;
    }

    public void setLp(CashConfirm lp) {
        this.lp = lp;
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
}
