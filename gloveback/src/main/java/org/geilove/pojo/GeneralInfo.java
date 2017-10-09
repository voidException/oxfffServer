package org.geilove.pojo;

import java.util.Date;

public class GeneralInfo {
    private Long generalinfoid;

    private String generaluuid;

    private Integer msgpubcount;

    private Integer msgboxcount;

    private Integer msgokcount;

    private Integer fanscount;

    private Integer followcount;

    private Integer notsay;

    private Date notsaytime;

    private Date cansaytime;

    private String notsayreason;

    private Integer totalnotsay;

    public Long getGeneralinfoid() {
        return generalinfoid;
    }

    public void setGeneralinfoid(Long generalinfoid) {
        this.generalinfoid = generalinfoid;
    }

    public String getGeneraluuid() {
        return generaluuid;
    }

    public void setGeneraluuid(String generaluuid) {
        this.generaluuid = generaluuid == null ? null : generaluuid.trim();
    }

    public Integer getMsgpubcount() {
        return msgpubcount;
    }

    public void setMsgpubcount(Integer msgpubcount) {
        this.msgpubcount = msgpubcount;
    }

    public Integer getMsgboxcount() {
        return msgboxcount;
    }

    public void setMsgboxcount(Integer msgboxcount) {
        this.msgboxcount = msgboxcount;
    }

    public Integer getMsgokcount() {
        return msgokcount;
    }

    public void setMsgokcount(Integer msgokcount) {
        this.msgokcount = msgokcount;
    }

    public Integer getFanscount() {
        return fanscount;
    }

    public void setFanscount(Integer fanscount) {
        this.fanscount = fanscount;
    }

    public Integer getFollowcount() {
        return followcount;
    }

    public void setFollowcount(Integer followcount) {
        this.followcount = followcount;
    }

    public Integer getNotsay() {
        return notsay;
    }

    public void setNotsay(Integer notsay) {
        this.notsay = notsay;
    }

    public Date getNotsaytime() {
        return notsaytime;
    }

    public void setNotsaytime(Date notsaytime) {
        this.notsaytime = notsaytime;
    }

    public Date getCansaytime() {
        return cansaytime;
    }

    public void setCansaytime(Date cansaytime) {
        this.cansaytime = cansaytime;
    }

    public String getNotsayreason() {
        return notsayreason;
    }

    public void setNotsayreason(String notsayreason) {
        this.notsayreason = notsayreason == null ? null : notsayreason.trim();
    }

    public Integer getTotalnotsay() {
        return totalnotsay;
    }

    public void setTotalnotsay(Integer totalnotsay) {
        this.totalnotsay = totalnotsay;
    }
}