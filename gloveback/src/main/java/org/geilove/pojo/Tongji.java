package org.geilove.pojo;

import java.util.Date;

public class Tongji {
    private Integer tongjiid;

    private String tongjiuuid;

    private String allmoney;

    private String moneytype;

    private Date tongjidate;

    public Integer getTongjiid() {
        return tongjiid;
    }

    public void setTongjiid(Integer tongjiid) {
        this.tongjiid = tongjiid;
    }

    public String getTongjiuuid() {
        return tongjiuuid;
    }

    public void setTongjiuuid(String tongjiuuid) {
        this.tongjiuuid = tongjiuuid == null ? null : tongjiuuid.trim();
    }

    public String getAllmoney() {
        return allmoney;
    }

    public void setAllmoney(String allmoney) {
        this.allmoney = allmoney == null ? null : allmoney.trim();
    }

    public String getMoneytype() {
        return moneytype;
    }

    public void setMoneytype(String moneytype) {
        this.moneytype = moneytype == null ? null : moneytype.trim();
    }

    public Date getTongjidate() {
        return tongjidate;
    }

    public void setTongjidate(Date tongjidate) {
        this.tongjidate = tongjidate;
    }
}