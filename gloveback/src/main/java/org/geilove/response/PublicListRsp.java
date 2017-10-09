package org.geilove.response;

import org.geilove.pojo.Public;

import java.util.List;

/**
 * Created by aihaitao on 18/7/2017.
 */
public class PublicListRsp {
    private Integer retcode ;
    private String msg;
    private List<Public> lp;

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

    public List<Public> getLp() {
        return lp;
    }

    public void setLp(List<Public> lp) {
        this.lp = lp;
    }

}
