package org.geilove.response;

import java.util.List;

/**
 * Created by mfhj-dz-001-424 on 17/2/7.
 */
public class ProgressUpdateRsp {
    private List<ProgressUpdate> lp; //返回的数据域
    private  String msg;           //返回的提示信息
    private Integer  retcode;      //返回码

    public List<ProgressUpdate> getLp() {
        return lp;
    }

    public void setLp(List<ProgressUpdate> lp) {
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
