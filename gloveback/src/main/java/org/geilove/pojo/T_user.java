package org.geilove.pojo;

import java.io.Serializable;

/**
 * 在Shiro下与本表对应的t_role t_permission 没有 mapper和dao
 */
public class T_user implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private Integer roleId ; //这个roleId 被t_role 所约束，与其他无关

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
