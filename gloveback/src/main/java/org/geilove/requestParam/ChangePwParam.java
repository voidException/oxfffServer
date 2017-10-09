package org.geilove.requestParam;

/**
 * Created by aihaitao on 22/5/2017.
 */
public class ChangePwParam {
    private  String originPass;
    private  String againPass;
    private  String encryptEmail;

    public String getOriginPass() {
        return originPass;
    }

    public void setOriginPass(String originPass) {
        this.originPass = originPass;
    }

    public String getAgainPass() {
        return againPass;
    }

    public void setAgainPass(String againPass) {
        this.againPass = againPass;
    }

    public String getEncryptEmail() {
        return encryptEmail;
    }

    public void setEncryptEmail(String encryptEmail) {
        this.encryptEmail = encryptEmail;
    }
}
