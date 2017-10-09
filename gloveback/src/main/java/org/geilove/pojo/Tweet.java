package org.geilove.pojo;

import java.util.Date;

import org.geilove.vo.WeiBo;

public class Tweet {
    private Long tweetid;

    private Long useridtweet;

    private Long sourcemsgid;

    private Byte tagid;

    private String msgcontent;

    private Long topic;

    private Integer commenttimes;

    private Integer ok;

    private Integer boxtimes;

    private Date publishtime;

    private Integer reportedtimes;

    private Byte publicsee;

    private Byte deletetag;

    private String videoaddress;

    private String tweetbackupone;

    private String tweetbackuptwo;

    private String tweetbackupthree;

    private Integer tweetbackupfour;

    private Long tweetbackupfive; //关联的cash的iD

    private Integer tweetbackupsix;
    
    private String promise;

    private String tweetbackupseven;
    
    private String backupeight;

    private String backupneight; //推文的UUID

    private String backupnine;//usernickname 发布推文的用户昵称

    private String backupten; //用户简介

    private String backupeleven; //用户的头像地址

    private String backuptwelve;

    private Integer tobeuseone; //救助状态

    private Integer tobeusetwo;

    private Integer tobeusethree;

    private String citycode;

    private String cityname;

    private Long cashid;

    private String cashuuid;

    private String useruuidtweet;

    private String helpif;

    private String helptype;

    private String imageurlone;

    private String imageurltwo;

    private String imageurlthree;

    private String zhibourl;


    public Long getTweetid() {
        return tweetid;
    }

    public void setTweetid(Long tweetid) {
        this.tweetid = tweetid;
    }

    public Long getUseridtweet() {
        return useridtweet;
    }

    public void setUseridtweet(Long useridtweet) {
        this.useridtweet = useridtweet;
    }

    public Long getSourcemsgid() {
        return sourcemsgid;
    }

    public void setSourcemsgid(Long sourcemsgid) {
        this.sourcemsgid = sourcemsgid;
    }

    public Byte getTagid() {
        return tagid;
    }

    public void setTagid(Byte tagid) {
        this.tagid = tagid;
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent == null ? null : msgcontent.trim();
    }

    public Long getTopic() {
        return topic;
    }

    public void setTopic(Long topic) {
        this.topic = topic;
    }

    public Integer getCommenttimes() {
        return commenttimes;
    }

    public void setCommenttimes(Integer commenttimes) {
        this.commenttimes = commenttimes;
    }

    public Integer getOk() {
        return ok;
    }

    public void setOk(Integer ok) {
        this.ok = ok;
    }

    public Integer getBoxtimes() {
        return boxtimes;
    }

    public void setBoxtimes(Integer boxtimes) {
        this.boxtimes = boxtimes;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getReportedtimes() {
        return reportedtimes;
    }

    public void setReportedtimes(Integer reportedtimes) {
        this.reportedtimes = reportedtimes;
    }

    public Byte getPublicsee() {
        return publicsee;
    }

    public void setPublicsee(Byte publicsee) {
        this.publicsee = publicsee;
    }

    public Byte getDeletetag() {
        return deletetag;
    }

    public void setDeletetag(Byte deletetag) {
        this.deletetag = deletetag;
    }

    public String getVideoaddress() {
        return videoaddress;
    }

    public void setVideoaddress(String videoaddress) {
        this.videoaddress = videoaddress == null ? null : videoaddress.trim();
    }

    public String getTweetbackupone() {
        return tweetbackupone;
    }

    public void setTweetbackupone(String tweetbackupone) {
        this.tweetbackupone = tweetbackupone == null ? null : tweetbackupone.trim();
    }

    public String getTweetbackuptwo() {
        return tweetbackuptwo;
    }

    public void setTweetbackuptwo(String tweetbackuptwo) {
        this.tweetbackuptwo = tweetbackuptwo == null ? null : tweetbackuptwo.trim();
    }

    public String getTweetbackupthree() {
        return tweetbackupthree;
    }

    public void setTweetbackupthree(String tweetbackupthree) {
        this.tweetbackupthree = tweetbackupthree == null ? null : tweetbackupthree.trim();
    }

    public Integer getTweetbackupfour() {
        return tweetbackupfour;
    }

    public void setTweetbackupfour(Integer tweetbackupfour) {
        this.tweetbackupfour = tweetbackupfour;
    }

    public Long getTweetbackupfive() {
        return tweetbackupfive;
    }

    public void setTweetbackupfive(Long tweetbackupfive) {
        this.tweetbackupfive = tweetbackupfive;
    }

    public Integer getTweetbackupsix() {
        return tweetbackupsix;
    }

    public void setTweetbackupsix(Integer tweetbackupsix) {
        this.tweetbackupsix = tweetbackupsix;
    }
    public String getPromise() {
        return promise;
    }

    public void setPromise(String promise) {
        this.promise = promise == null ? null : promise.trim();
    }

    public String getTweetbackupseven() {
        return tweetbackupseven;
    }

    public void setTweetbackupseven(String tweetbackupseven) {
        this.tweetbackupseven = tweetbackupseven == null ? null : tweetbackupseven.trim();
    }
    public String getBackupeight() {
        return backupeight;
    }

    public void setBackupeight(String backupeight) {
        this.backupeight = backupeight == null ? null : backupeight.trim();
    }

    public String getBackupneight() {
        return backupneight;
    }

    public void setBackupneight(String backupneight) {
        this.backupneight = backupneight == null ? null : backupneight.trim();
    }

    public String getBackupnine() {
        return backupnine;
    }

    public void setBackupnine(String backupnine) {
        this.backupnine = backupnine == null ? null : backupnine.trim();
    }

    public String getBackupten() {
        return backupten;
    }

    public void setBackupten(String backupten) {
        this.backupten = backupten == null ? null : backupten.trim();
    }

    public String getBackupeleven() {
        return backupeleven;
    }

    public void setBackupeleven(String backupeleven) {
        this.backupeleven = backupeleven == null ? null : backupeleven.trim();
    }

    public String getBackuptwelve() {
        return backuptwelve;
    }

    public void setBackuptwelve(String backuptwelve) {
        this.backuptwelve = backuptwelve == null ? null : backuptwelve.trim();
    }

    public Integer getTobeuseone() {
        return tobeuseone;
    }

    public void setTobeuseone(Integer tobeuseone) {
        this.tobeuseone = tobeuseone;
    }

    public Integer getTobeusetwo() {
        return tobeusetwo;
    }

    public void setTobeusetwo(Integer tobeusetwo) {
        this.tobeusetwo = tobeusetwo;
    }

    public Integer getTobeusethree() {
        return tobeusethree;
    }

    public void setTobeusethree(Integer tobeusethree) {
        this.tobeusethree = tobeusethree;
    }
    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
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

    public String getUseruuidtweet() {
        return useruuidtweet;
    }

    public void setUseruuidtweet(String useruuidtweet) {
        this.useruuidtweet = useruuidtweet == null ? null : useruuidtweet.trim();
    }

    public String getHelpif() {
        return helpif;
    }

    public void setHelpif(String helpif) {
        this.helpif = helpif == null ? null : helpif.trim();
    }

    public String getHelptype() {
        return helptype;
    }

    public void setHelptype(String helptype) {
        this.helptype = helptype == null ? null : helptype.trim();
    }

    public String getImageurlone() {
        return imageurlone;
    }

    public void setImageurlone(String imageurlone) {
        this.imageurlone = imageurlone == null ? null : imageurlone.trim();
    }

    public String getImageurltwo() {
        return imageurltwo;
    }

    public void setImageurltwo(String imageurltwo) {
        this.imageurltwo = imageurltwo == null ? null : imageurltwo.trim();
    }

    public String getImageurlthree() {
        return imageurlthree;
    }

    public void setImageurlthree(String imageurlthree) {
        this.imageurlthree = imageurlthree == null ? null : imageurlthree.trim();
    }

    public String getZhibourl() {
        return zhibourl;
    }

    public void setZhibourl(String zhibourl) {
        this.zhibourl = zhibourl == null ? null : zhibourl.trim();
    }
}