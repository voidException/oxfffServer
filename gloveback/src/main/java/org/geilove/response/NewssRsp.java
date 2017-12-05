package org.geilove.response;

import org.geilove.pojo.News;

import java.util.List;

/**
 * Created by aihaitao on 2/11/2017.
 */
public class NewssRsp {
    private Integer retcode ;
    private String msg;

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

    public List<News> getResult() {
        return result;
    }

    public void setResult(List<News> result) {
        this.result = result;
    }

    private List<News> result;


}
