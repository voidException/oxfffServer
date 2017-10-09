package org.geilove.response;

import org.geilove.pojo.Tweet;

/**
 * Created by mfhj-dz-001-424 on 17/2/23.
 */
public class TweetRsp {
    private  Tweet  lp;
    private  String  msg;
    private  Integer  retcode;

    public Tweet getLp() {
        return lp;
    }

    public void setLp(Tweet lp) {
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
