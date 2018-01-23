package org.geilove.pojo;

public class Resource {
    private Integer resourceid;

    private Integer resourcenum;

    private String resourcename;

    public Integer getResourceid() {
        return resourceid;
    }

    public void setResourceid(Integer resourceid) {
        this.resourceid = resourceid;
    }

    public Integer getResourcenum() {
        return resourcenum;
    }

    public void setResourcenum(Integer resourcenum) {
        this.resourcenum = resourcenum;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename == null ? null : resourcename.trim();
    }
}