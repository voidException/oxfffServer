package org.geilove.pojo;

public class Category {
    private Integer categoryid;

    private String categoryuuid;

    private String categorytype;

    private Integer jointotal;

    private Long historytotalfee;

    private Long nowfee;

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryuuid() {
        return categoryuuid;
    }

    public void setCategoryuuid(String categoryuuid) {
        this.categoryuuid = categoryuuid == null ? null : categoryuuid.trim();
    }

    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype == null ? null : categorytype.trim();
    }

    public Integer getJointotal() {
        return jointotal;
    }

    public void setJointotal(Integer jointotal) {
        this.jointotal = jointotal;
    }

    public Long getHistorytotalfee() {
        return historytotalfee;
    }

    public void setHistorytotalfee(Long historytotalfee) {
        this.historytotalfee = historytotalfee;
    }

    public Long getNowfee() {
        return nowfee;
    }

    public void setNowfee(Long nowfee) {
        this.nowfee = nowfee;
    }
}