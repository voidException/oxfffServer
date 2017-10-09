package org.geilove.response;

import org.geilove.vo.TimeLine;

/**
 * Created by mfhj-dz-001-424 on 17/2/14.
 * 由于该接口很重要，返回的数据由 tweet表 cash表 证实表组成，故返回的状态码有多个
 * cash表
 */
public class TimeLineRsp {
    private  TimeLine  lp;
    private  String    msg;
    private  Integer   retcode;      //tweet和cash获取成功就是2000

    private  Integer   tweetCode;    //这个是代表tweet的查找情况
    private  String    tweetMsg;
    private  Integer   cashCode;     //这个是代表的cash表的查找情况
    private  String    cashMsg;
    private  Integer   confirmCode;  // 这个是代表取得cash表的证实情况,

    public Integer getTweetCode() {
        return tweetCode;
    }

    public void setTweetCode(Integer tweetCode) {
        this.tweetCode = tweetCode;
    }

    public String getTweetMsg() {
        return tweetMsg;
    }

    public void setTweetMsg(String tweetMsg) {
        this.tweetMsg = tweetMsg;
    }

    public Integer getCashCode() {
        return cashCode;
    }

    public void setCashCode(Integer cashCode) {
        this.cashCode = cashCode;
    }

    public String getCashMsg() {
        return cashMsg;
    }

    public void setCashMsg(String cashMsg) {
        this.cashMsg = cashMsg;
    }

    public Integer getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(Integer confirmCode) {
        this.confirmCode = confirmCode;
    }

    public String getConfirmMsg() {
        return confirmMsg;
    }

    public void setConfirmMsg(String confirmMsg) {
        this.confirmMsg = confirmMsg;
    }

    private  String    confirmMsg;



    public TimeLine getLp() {
        return lp;
    }

    public void setLp(TimeLine lp) {
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

