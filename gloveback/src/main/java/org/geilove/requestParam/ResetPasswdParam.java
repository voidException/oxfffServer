package org.geilove.requestParam;

/**
 * Created by aihaitao on 22/5/2017.
 */
public class ResetPasswdParam {

    private  String  originPass;
    private  String  newPass;
    private  String  againPass;
    private  String  token;
    public String getOriginPass() {
        return originPass;
    }

    public void setOriginPass(String originPass) {
        this.originPass = originPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getAgainPass() {
        return againPass;
    }

    public void setAgainPass(String againPass) {
        this.againPass = againPass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
