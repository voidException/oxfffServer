package org.geilove.pojo;

public class Role {
    private Integer roleid;

    private String  rolename;

    private Integer t_userID=0; //如果非0，则该角色就是用户的所属角色

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }
    public Integer getT_userID() {
        return t_userID;
    }

    public void setT_userID(Integer t_userID) {
        this.t_userID = t_userID;
    }

}