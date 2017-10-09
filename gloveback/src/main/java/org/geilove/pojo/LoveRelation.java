package org.geilove.pojo;

import java.util.Date;

public class LoveRelation {
    private Long loverelationid;

    private String loverelationuuid;

    private Long userid;

    private String useruuid;

    private Long loveuserid;

    private String loveuseruuid;

    private Date joindate;

    private String citylocation;

    private String citylocationcode;

    public Long getLoverelationid() {
        return loverelationid;
    }

    public void setLoverelationid(Long loverelationid) {
        this.loverelationid = loverelationid;
    }

    public String getLoverelationuuid() {
        return loverelationuuid;
    }

    public void setLoverelationuuid(String loverelationuuid) {
        this.loverelationuuid = loverelationuuid == null ? null : loverelationuuid.trim();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public Long getLoveuserid() {
        return loveuserid;
    }

    public void setLoveuserid(Long loveuserid) {
        this.loveuserid = loveuserid;
    }

    public String getLoveuseruuid() {
        return loveuseruuid;
    }

    public void setLoveuseruuid(String loveuseruuid) {
        this.loveuseruuid = loveuseruuid == null ? null : loveuseruuid.trim();
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public String getCitylocation() {
        return citylocation;
    }

    public void setCitylocation(String citylocation) {
        this.citylocation = citylocation == null ? null : citylocation.trim();
    }

    public String getCitylocationcode() {
        return citylocationcode;
    }

    public void setCitylocationcode(String citylocationcode) {
        this.citylocationcode = citylocationcode == null ? null : citylocationcode.trim();
    }
}