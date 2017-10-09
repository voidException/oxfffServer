package org.geilove.pojo;

import java.util.Date;

public class UserAccount {
    private Long useraccountid;

    private String useraccountuuid;

    private String useruuid;

    private String accountuuid;

    private String breakif;

    private Date buildrelationdate;

    private Date breakrelationdate;

    private String buildrelationdescription;

    private String breakrelationdescription;

    private Integer paytimes;

    private String paytotalmoney;

    private String username;

    private String categorytype;

    private Date joindate;

    private Date effectivedate;

    private Date stopdate;

    private String nowstate;

    private Long remainfee;

    public Long getUseraccountid() {
        return useraccountid;
    }

    public void setUseraccountid(Long useraccountid) {
        this.useraccountid = useraccountid;
    }

    public String getUseraccountuuid() {
        return useraccountuuid;
    }

    public void setUseraccountuuid(String useraccountuuid) {
        this.useraccountuuid = useraccountuuid == null ? null : useraccountuuid.trim();
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public String getAccountuuid() {
        return accountuuid;
    }

    public void setAccountuuid(String accountuuid) {
        this.accountuuid = accountuuid == null ? null : accountuuid.trim();
    }

    public String getBreakif() {
        return breakif;
    }

    public void setBreakif(String breakif) {
        this.breakif = breakif == null ? null : breakif.trim();
    }

    public Date getBuildrelationdate() {
        return buildrelationdate;
    }

    public void setBuildrelationdate(Date buildrelationdate) {
        this.buildrelationdate = buildrelationdate;
    }

    public Date getBreakrelationdate() {
        return breakrelationdate;
    }

    public void setBreakrelationdate(Date breakrelationdate) {
        this.breakrelationdate = breakrelationdate;
    }

    public String getBuildrelationdescription() {
        return buildrelationdescription;
    }

    public void setBuildrelationdescription(String buildrelationdescription) {
        this.buildrelationdescription = buildrelationdescription == null ? null : buildrelationdescription.trim();
    }

    public String getBreakrelationdescription() {
        return breakrelationdescription;
    }

    public void setBreakrelationdescription(String breakrelationdescription) {
        this.breakrelationdescription = breakrelationdescription == null ? null : breakrelationdescription.trim();
    }

    public Integer getPaytimes() {
        return paytimes;
    }

    public void setPaytimes(Integer paytimes) {
        this.paytimes = paytimes;
    }

    public String getPaytotalmoney() {
        return paytotalmoney;
    }

    public void setPaytotalmoney(String paytotalmoney) {
        this.paytotalmoney = paytotalmoney == null ? null : paytotalmoney.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype == null ? null : categorytype.trim();
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Date getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(Date effectivedate) {
        this.effectivedate = effectivedate;
    }

    public Date getStopdate() {
        return stopdate;
    }

    public void setStopdate(Date stopdate) {
        this.stopdate = stopdate;
    }

    public String getNowstate() {
        return nowstate;
    }

    public void setNowstate(String nowstate) {
        this.nowstate = nowstate == null ? null : nowstate.trim();
    }

    public Long getRemainfee() {
        return remainfee;
    }

    public void setRemainfee(Long remainfee) {
        this.remainfee = remainfee;
    }
}