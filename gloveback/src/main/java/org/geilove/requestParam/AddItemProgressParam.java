package org.geilove.requestParam;

/**
 * Created by mfhj-dz-001-424 on 17/2/9.
 */
public class AddItemProgressParam {

    private String token; //用户的token,用于权限校验

    private Long userid;  //用户的iD

    private String  useruuid;    //用户的uuid

    private String userphoto;  //用户的头像Url

    private String usernickname;  //用户的昵称

    private  String cashUUID;  //对应的资金表的cashUUID


    private String content;  //更新的内容

    private String imgaddressone;

    private String imgaddresstwo;

    private String imgaddressthree;

    private String imgaddressfour;

    private String imgaddressfive;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public String getCashUUID() {
        return cashUUID;
    }

    public void setCashUUID(String cashUUID) {
        this.cashUUID = cashUUID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgaddressone() {
        return imgaddressone;
    }

    public void setImgaddressone(String imgaddressone) {
        this.imgaddressone = imgaddressone;
    }

    public String getImgaddresstwo() {
        return imgaddresstwo;
    }

    public void setImgaddresstwo(String imgaddresstwo) {
        this.imgaddresstwo = imgaddresstwo;
    }

    public String getImgaddressthree() {
        return imgaddressthree;
    }

    public void setImgaddressthree(String imgaddressthree) {
        this.imgaddressthree = imgaddressthree;
    }

    public String getImgaddressfour() {
        return imgaddressfour;
    }

    public void setImgaddressfour(String imgaddressfour) {
        this.imgaddressfour = imgaddressfour;
    }

    public String getImgaddressfive() {
        return imgaddressfive;
    }

    public void setImgaddressfive(String imgaddressfive) {
        this.imgaddressfive = imgaddressfive;
    }

    public String getImgaddresssix() {
        return imgaddresssix;
    }

    public void setImgaddresssix(String imgaddresssix) {
        this.imgaddresssix = imgaddresssix;
    }

    public String getUpDateType() {
        return upDateType;
    }

    public void setUpDateType(String upDateType) {
        this.upDateType = upDateType;
    }

    private String imgaddresssix;

    private String upDateType;  //项目更新、项目结束、项目提前结束等



}
