
package org.geilove.response;

import org.geilove.pojo.Tweet;

import java.util.List;

/**
 * Created by mfhj-dz-001-424 on 17/2/23.
 */
public class TweetsRsp {

    private List<Tweet>  lp;
    private  String  msg;
    private  Integer  retcode;

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

    public List<Tweet> getLp() {
        return lp;
    }

    public void setLp(List<Tweet> lp) {
        this.lp = lp;
    }
}
