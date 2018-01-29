package org.geilove.vo;

import java.util.Date;

/**
 * 部分user表信息和companyputao信息的结合，适用于"企业列表"
 */
public class UserCompanyputao {

    private  String  username;

    private  String  userphone;

    private  Date register;

    private Integer companyid;

    private String companyuuid;

    private String useruuid;

    private Integer staffall;

    private Integer totalmoney;

    private String totalmoenystr;

    private String average;

    private String helptype;

    private String needtips;

    private Integer tipstimes;

    private Date tipsdate;

    private Date updatedate;

    private Date createdate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getCompanyuuid() {
        return companyuuid;
    }

    public void setCompanyuuid(String companyuuid) {
        this.companyuuid = companyuuid;
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid;
    }

    public Integer getStaffall() {
        return staffall;
    }

    public void setStaffall(Integer staffall) {
        this.staffall = staffall;
    }

    public Integer getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Integer totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getTotalmoenystr() {
        return totalmoenystr;
    }

    public void setTotalmoenystr(String totalmoenystr) {
        this.totalmoenystr = totalmoenystr;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getHelptype() {
        return helptype;
    }

    public void setHelptype(String helptype) {
        this.helptype = helptype;
    }

    public String getNeedtips() {
        return needtips;
    }

    public void setNeedtips(String needtips) {
        this.needtips = needtips;
    }

    public Integer getTipstimes() {
        return tipstimes;
    }

    public void setTipstimes(Integer tipstimes) {
        this.tipstimes = tipstimes;
    }

    public Date getTipsdate() {
        return tipsdate;
    }

    public void setTipsdate(Date tipsdate) {
        this.tipsdate = tipsdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

}
