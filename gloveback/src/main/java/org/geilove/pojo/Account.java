package org.geilove.pojo;

import java.util.Date;

public class Account {
    private Long accountid;

    private String accountuuid;

    private String useridentity;

    private String username;

    private String ifhelp;

    private Date accountdate;

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public String getAccountuuid() {
        return accountuuid;
    }

    public void setAccountuuid(String accountuuid) {
        this.accountuuid = accountuuid == null ? null : accountuuid.trim();
    }

    public String getUseridentity() {
        return useridentity;
    }

    public void setUseridentity(String useridentity) {
        this.useridentity = useridentity == null ? null : useridentity.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getIfhelp() {
        return ifhelp;
    }

    public void setIfhelp(String ifhelp) {
        this.ifhelp = ifhelp == null ? null : ifhelp.trim();
    }

    public Date getAccountdate() {
        return accountdate;
    }

    public void setAccountdate(Date accountdate) {
        this.accountdate = accountdate;
    }
}