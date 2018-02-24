package org.geilove.vo;

/**
 * Created by aihaitao on 21/2/2018.
 */
public class LearnUser {
    private String uname;
    private String pwd;

    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String toString() {
        return "User [uname=" + uname + ", pwd=" + pwd + "]";
    }

}
