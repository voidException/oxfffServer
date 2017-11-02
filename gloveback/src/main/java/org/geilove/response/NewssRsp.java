package org.geilove.response;

import org.geilove.pojo.News;

import java.util.List;

/**
 * Created by aihaitao on 2/11/2017.
 */
public class NewssRsp {
    private Integer retcode ;
    private String msg;
    private List<News> newss;

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

    public List<News> getNewss() {
        return newss;
    }

    public void setNewss(List<News> newss) {
        this.newss = newss;
    }
}
