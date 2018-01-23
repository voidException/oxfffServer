package org.geilove.pojo;

public class RoleResource {
    private Integer roleresourceid;

    private Integer roleid;

    private Integer resourceid;

    private String ok;

    public Integer getRoleresourceid() {
        return roleresourceid;
    }

    public void setRoleresourceid(Integer roleresourceid) {
        this.roleresourceid = roleresourceid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getResourceid() {
        return resourceid;
    }

    public void setResourceid(Integer resourceid) {
        this.resourceid = resourceid;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok == null ? null : ok.trim();
    }
}