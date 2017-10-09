package org.geilove.pojo;

import java.util.Date;

public class ShareUser {
    private Long shareid;

    private String useruuid;

    private String shareuseruuid;

    private Integer haspay;

    private Date createddate;

    public Long getShareid() {
        return shareid;
    }

    public void setShareid(Long shareid) {
        this.shareid = shareid;
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public String getShareuseruuid() {
        return shareuseruuid;
    }

    public void setShareuseruuid(String shareuseruuid) {
        this.shareuseruuid = shareuseruuid == null ? null : shareuseruuid.trim();
    }

    public Integer getHaspay() {
        return haspay;
    }

    public void setHaspay(Integer haspay) {
        this.haspay = haspay;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }
}