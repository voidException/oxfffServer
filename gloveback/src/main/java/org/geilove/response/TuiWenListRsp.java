package org.geilove.response;

import org.geilove.vov.WeiBo;

import java.util.List;

/**
 * Created by mfhj-dz-001-424 on 17/2/28.
 */
public class TuiWenListRsp {

    private List<WeiBo> data;
    private Integer retcode ;
    private String msg;

    public List<WeiBo> getData() {
        return data;
    }

    public void setData(List<WeiBo> data) {
        this.data = data;
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
