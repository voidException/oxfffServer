package org.geilove.vo;

/**
 * Created by mfhj-dz-001-424 on 17/2/14.
 */
public class UserPartProfile {

    private Long userid;   //用户的iD
    private String  userPhotoUrl; // 用户的头像地址
    private String  nickName;  //用户的昵称

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
