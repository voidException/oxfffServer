package org.geilove.response;

import org.geilove.pojo.RedMoney;

import java.util.List;

/**
 * Created by aihaitao on 26/7/2017.
 */
public class AwardListRsp {
    private List<RedMoney> lp;
    private Integer retcode ;
    private String msg;

    public List<RedMoney> getLp() {
        return lp;
    }

    public void setLp(List<RedMoney> lp) {
        this.lp = lp;
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
