package org.geilove.pojo;

import java.util.Date;

public class ItemProgress {
    private Long itemprogressid;

    private Long userid;

    private String userphoto;

    private String usernickname;

    private String uuid;

    private String itemuuid;

    private Long itemid;

    private Date updatetime;

    private String content;

    private String imgaddressone;

    private String imgaddresstwo;

    private String imgaddressthree;

    private String imgaddressfour;

    private String imgaddressfive;

    private String imgaddresssix;

    private String backupone;  //项目更新、项目结束、项目提前结束等

    private String backuptwo;

    private String backupthree;

    private String backupfour;

    private Integer backupfive;

    private Integer backupsix;

    private Long cashid;

    private String cashuuid;

    public Long getItemprogressid() {
        return itemprogressid;
    }

    public void setItemprogressid(Long itemprogressid) {
        this.itemprogressid = itemprogressid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto == null ? null : userphoto.trim();
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname == null ? null : usernickname.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getItemuuid() {
        return itemuuid;
    }

    public void setItemuuid(String itemuuid) {
        this.itemuuid = itemuuid == null ? null : itemuuid.trim();
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImgaddressone() {
        return imgaddressone;
    }

    public void setImgaddressone(String imgaddressone) {
        this.imgaddressone = imgaddressone == null ? null : imgaddressone.trim();
    }

    public String getImgaddresstwo() {
        return imgaddresstwo;
    }

    public void setImgaddresstwo(String imgaddresstwo) {
        this.imgaddresstwo = imgaddresstwo == null ? null : imgaddresstwo.trim();
    }

    public String getImgaddressthree() {
        return imgaddressthree;
    }

    public void setImgaddressthree(String imgaddressthree) {
        this.imgaddressthree = imgaddressthree == null ? null : imgaddressthree.trim();
    }

    public String getImgaddressfour() {
        return imgaddressfour;
    }

    public void setImgaddressfour(String imgaddressfour) {
        this.imgaddressfour = imgaddressfour == null ? null : imgaddressfour.trim();
    }

    public String getImgaddressfive() {
        return imgaddressfive;
    }

    public void setImgaddressfive(String imgaddressfive) {
        this.imgaddressfive = imgaddressfive == null ? null : imgaddressfive.trim();
    }

    public String getImgaddresssix() {
        return imgaddresssix;
    }

    public void setImgaddresssix(String imgaddresssix) {
        this.imgaddresssix = imgaddresssix == null ? null : imgaddresssix.trim();
    }

    public String getBackupone() {
        return backupone;
    }

    public void setBackupone(String backupone) {
        this.backupone = backupone == null ? null : backupone.trim();
    }

    public String getBackuptwo() {
        return backuptwo;
    }

    public void setBackuptwo(String backuptwo) {
        this.backuptwo = backuptwo == null ? null : backuptwo.trim();
    }

    public String getBackupthree() {
        return backupthree;
    }

    public void setBackupthree(String backupthree) {
        this.backupthree = backupthree == null ? null : backupthree.trim();
    }

    public String getBackupfour() {
        return backupfour;
    }

    public void setBackupfour(String backupfour) {
        this.backupfour = backupfour == null ? null : backupfour.trim();
    }

    public Integer getBackupfive() {
        return backupfive;
    }

    public void setBackupfive(Integer backupfive) {
        this.backupfive = backupfive;
    }

    public Integer getBackupsix() {
        return backupsix;
    }

    public void setBackupsix(Integer backupsix) {
        this.backupsix = backupsix;
    }
    public Long getCashid() {
        return cashid;
    }

    public void setCashid(Long cashid) {
        this.cashid = cashid;
    }

    public String getCashuuid() {
        return cashuuid;
    }

    public void setCashuuid(String cashuuid) {
        this.cashuuid = cashuuid == null ? null : cashuuid.trim();
    }
}