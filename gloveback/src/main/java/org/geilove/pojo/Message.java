package org.geilove.pojo;

import java.util.Date;

public class Message {
    private Long id;

    private String msguuid;

    private String phonenumbers;

    private String templatecode;

    private String templateparam;

    private Date senddate;

    private String requestid;

    private String code;

    private String bizid;

    private String yewu;

    private String useruuid;

    private String account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsguuid() {
        return msguuid;
    }

    public void setMsguuid(String msguuid) {
        this.msguuid = msguuid == null ? null : msguuid.trim();
    }

    public String getPhonenumbers() {
        return phonenumbers;
    }

    public void setPhonenumbers(String phonenumbers) {
        this.phonenumbers = phonenumbers == null ? null : phonenumbers.trim();
    }

    public String getTemplatecode() {
        return templatecode;
    }

    public void setTemplatecode(String templatecode) {
        this.templatecode = templatecode == null ? null : templatecode.trim();
    }

    public String getTemplateparam() {
        return templateparam;
    }

    public void setTemplateparam(String templateparam) {
        this.templateparam = templateparam == null ? null : templateparam.trim();
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid == null ? null : requestid.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBizid() {
        return bizid;
    }

    public void setBizid(String bizid) {
        this.bizid = bizid == null ? null : bizid.trim();
    }

    public String getYewu() {
        return yewu;
    }

    public void setYewu(String yewu) {
        this.yewu = yewu == null ? null : yewu.trim();
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }
}