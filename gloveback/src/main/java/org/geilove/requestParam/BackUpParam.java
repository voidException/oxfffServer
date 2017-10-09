package org.geilove.requestParam;

import java.util.Date;

/**
 * Created by mfhj-dz-001-424 on 17/2/8.
 */
public class BackUpParam {

    private Long useridbehelped; //受助人的userID

    private String wechatTokenOfbehelped ; //受助人的微信token，待定

    private Long useridgoodguy; //帮助人的userID

    private  String wechatTokenOfgoodguy ;//帮助人的微信token，待定

    private  Long cashiD; //这个是cash表的主键，这个必须传过来

    private Integer moneynum;  //帮助的钱数，必须是整数，否则转成整数

    private String goodGuyNickName;  //帮助人昵称

    private String goodGuyPhotoUrl; //帮助人头像地址，需要去掉ip地址部分

    private String goodGuyUUID; //帮助人的uuid

    private String beHelpedUUID; //受助人的uuid


    public Long getUseridbehelped() {
        return useridbehelped;
    }

    public void setUseridbehelped(Long useridbehelped) {
        this.useridbehelped = useridbehelped;
    }

    public String getWechatTokenOfbehelped() {
        return wechatTokenOfbehelped;
    }

    public void setWechatTokenOfbehelped(String wechatTokenOfbehelped) {
        this.wechatTokenOfbehelped = wechatTokenOfbehelped;
    }

    public Long getUseridgoodguy() {
        return useridgoodguy;
    }

    public void setUseridgoodguy(Long useridgoodguy) {
        this.useridgoodguy = useridgoodguy;
    }

    public String getWechatTokenOfgoodguy() {
        return wechatTokenOfgoodguy;
    }

    public void setWechatTokenOfgoodguy(String wechatTokenOfgoodguy) {
        this.wechatTokenOfgoodguy = wechatTokenOfgoodguy;
    }

    public Integer getMoneynum() {
        return moneynum;
    }

    public void setMoneynum(Integer moneynum) {
        this.moneynum = moneynum;
    }

    public String getGoodGuyNickName() {
        return goodGuyNickName;
    }

    public void setGoodGuyNickName(String goodGuyNickName) {
        this.goodGuyNickName = goodGuyNickName;
    }

    public String getGoodGuyPhotoUrl() {
        return goodGuyPhotoUrl;
    }

    public void setGoodGuyPhotoUrl(String goodGuyPhotoUrl) {
        this.goodGuyPhotoUrl = goodGuyPhotoUrl;
    }

    public String getGoodGuyUUID() {
        return goodGuyUUID;
    }

    public void setGoodGuyUUID(String goodGuyUUID) {
        this.goodGuyUUID = goodGuyUUID;
    }

    public String getBeHelpedUUID() {
        return beHelpedUUID;
    }

    public void setBeHelpedUUID(String beHelpedUUID) {
        this.beHelpedUUID = beHelpedUUID;
    }
}
