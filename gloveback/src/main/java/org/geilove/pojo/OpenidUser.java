package org.geilove.pojo;

import java.util.Date;

public class OpenidUser {
    private Long openiduserid;

    private String openiduseruuid;

    private String openid;

    private Integer usertotalhelp;

    private Integer usertotaldonate;

    private Date donatedate;

    private Integer mailtag;

    public Long getOpeniduserid() {
        return openiduserid;
    }

    public void setOpeniduserid(Long openiduserid) {
        this.openiduserid = openiduserid;
    }

    public String getOpeniduseruuid() {
        return openiduseruuid;
    }

    public void setOpeniduseruuid(String openiduseruuid) {
        this.openiduseruuid = openiduseruuid == null ? null : openiduseruuid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getUsertotalhelp() {
        return usertotalhelp;
    }

    public void setUsertotalhelp(Integer usertotalhelp) {
        this.usertotalhelp = usertotalhelp;
    }

    public Integer getUsertotaldonate() {
        return usertotaldonate;
    }

    public void setUsertotaldonate(Integer usertotaldonate) {
        this.usertotaldonate = usertotaldonate;
    }

    public Date getDonatedate() {
        return donatedate;
    }

    public void setDonatedate(Date donatedate) {
        this.donatedate = donatedate;
    }

    public Integer getMailtag() {
        return mailtag;
    }

    public void setMailtag(Integer mailtag) {
        this.mailtag = mailtag;
    }
}