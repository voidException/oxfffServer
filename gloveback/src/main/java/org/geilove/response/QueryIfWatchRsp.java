package org.geilove.response;

/**
 * Created by mfhj-dz-001-424 on 17/1/4.
 */
public class QueryIfWatchRsp {

    private Byte paytag;   //关注标识，1主动关注，2被动关注，
    private Byte doublefans;  //是否相互关注
    private  Integer retcode ;
    private  String msg;

    public Byte getPaytag() {
        return paytag;
    }

    public void setPaytag(Byte paytag) {
        this.paytag = paytag;
    }

    public Byte getDoublefans() {
        return doublefans;
    }

    public void setDoublefans(Byte doublefans) {
        this.doublefans = doublefans;
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
