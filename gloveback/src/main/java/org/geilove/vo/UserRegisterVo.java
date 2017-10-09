package org.geilove.vo;

public class UserRegisterVo {

    private String userNickName;
    private String userEmail;
    private String userPassword;
    private String cityName;

    private String shareUserUUID;

    public String getShareUserUUID() {
        return shareUserUUID;
    }

    public void setShareUserUUID(String shareUserUUID) {
        this.shareUserUUID = shareUserUUID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
