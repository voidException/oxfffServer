package org.geilove.vo;

import org.geilove.pojo.UserAccount;

import java.util.Date;
import java.util.List;

/**
 * 包含部分用户信息，和用户所对应的UserAccount 列表
 */
public class PersonAccounts {

    private String usernickname;

    private String usertype;

    private String certificatetype;

    private String usertag;

    private String country;

    private Date registerdate;

    private String userphoto;

    private String backupfour;

    private String useruuid;

    private String userphone;

    private String wecharbind;

    private String phonebind;

    private String usertoken;

    private String token;

    private String unionid;

    List<UserAccount> userAccountList;


    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getCertificatetype() {
        return certificatetype;
    }

    public void setCertificatetype(String certificatetype) {
        this.certificatetype = certificatetype;
    }

    public String getUsertag() {
        return usertag;
    }

    public void setUsertag(String usertag) {
        this.usertag = usertag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public String getBackupfour() {
        return backupfour;
    }

    public void setBackupfour(String backupfour) {
        this.backupfour = backupfour;
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getWecharbind() {
        return wecharbind;
    }

    public void setWecharbind(String wecharbind) {
        this.wecharbind = wecharbind;
    }

    public String getPhonebind() {
        return phonebind;
    }

    public void setPhonebind(String phonebind) {
        this.phonebind = phonebind;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }




}
