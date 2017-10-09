package org.geilove.requestParam;

/**
 * Created by aihaitao on 29/5/2017.
 */
public class SubOpenIDEmailParam {
    private  String  Email;
    private  String  token;
    private  String  openId;
    private Integer usertotalhelp=0;
    private Integer usertotaldonate=0;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
}
