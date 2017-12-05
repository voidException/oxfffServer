package org.geilove.response;

import org.geilove.pojo.UserAccount;

import java.util.List;

/**
 * Created by aihaitao on 17/7/2017.
 */
public class UserAccountRsp {
    private Integer retcode ;
    private String msg;
    private List<UserAccount> result; //员工列表

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

    public List<UserAccount> getResult() {
        return result;
    }

    public void setResult(List<UserAccount> result) {
        this.result = result;
    }
}
