package org.geilove.pojo;

import java.util.Date;

public class UserStaff {
    private Long staffid;

    private String staffuuid;

    private String useruuid;

    private String account;

    private String staffname;

    private String staffphone;

    private String helptype;

    private String affirm;

    private Date joindate;

    private Date effectivedate;

    private Date stopdate;

    private String nowstate;

    private Integer applyhelptimes;

    public Long getStaffid() {
        return staffid;
    }

    public void setStaffid(Long staffid) {
        this.staffid = staffid;
    }

    public String getStaffuuid() {
        return staffuuid;
    }

    public void setStaffuuid(String staffuuid) {
        this.staffuuid = staffuuid == null ? null : staffuuid.trim();
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

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname == null ? null : staffname.trim();
    }

    public String getStaffphone() {
        return staffphone;
    }

    public void setStaffphone(String staffphone) {
        this.staffphone = staffphone == null ? null : staffphone.trim();
    }

    public String getHelptype() {
        return helptype;
    }

    public void setHelptype(String helptype) {
        this.helptype = helptype == null ? null : helptype.trim();
    }

    public String getAffirm() {
        return affirm;
    }

    public void setAffirm(String affirm) {
        this.affirm = affirm == null ? null : affirm.trim();
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

    public Integer getApplyhelptimes() {
        return applyhelptimes;
    }

    public void setApplyhelptimes(Integer applyhelptimes) {
        this.applyhelptimes = applyhelptimes;
    }
}