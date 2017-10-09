package org.geilove.pojo;

import java.util.Date;

public class Cash {
    private Long cashid;

    private String cashuuid;

    private Long useridcash;

    private Long behelpuserid;

    private String behelpusernickname;

    private String behelpusername;

    private Long dutyuserid;

    private String dutyusernickname;

    private String dutyusername;

    private Long supuserid;

    private String superusernickname;

    private String superusername;

    private Integer useridcircle;

    private String circleusername;

    private String circleusernickname;

    private Integer promisetype;

    private String chengnuotype;

    private String promisemiaoshu;

    private Integer proveone;

    private Integer provetwo;

    private Integer provethree;

    private Integer provefour;

    private Integer provefive;

    private Byte countstate;

    private Integer targetcash;

    private Integer realcash;

    private Date opentime;

    private Date closetime;

    private Byte getmoneytag;

    private Byte cashok;

    private Integer spendmoney;

    private Integer sumaffirm;

    private Integer sumzhuanfa;

    private Integer sumattention;

    private Integer sumbackup;

    private Integer summanbackup;

    private Integer sumdays;

    private Integer progressstate;

    private Date createdate;

    private Date offlinepassdate;

    private Date moneyuseupdate;

    private Date lastend;

    private String acceptmoneyman;

    private String acceptmoneymanid;

    private Long checkuserid;

    private String checkuseruuid;

    private String checkusernickname;

    private String backupone; //受助人身份证号

    private String backuptwo; //资金接收人联系电话

    private String backupthree;

    private String backupfour;

    private Integer backupfive;

    private Integer backupsix;

    private Integer promisedsp;

    private Long cashtweetid;

    private String cashtweetuuid;

    private String cashcityname;

    private String cashcitycode;

    private String useruuid;

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

    public Long getUseridcash() {
        return useridcash;
    }

    public void setUseridcash(Long useridcash) {
        this.useridcash = useridcash;
    }

    public Long getBehelpuserid() {
        return behelpuserid;
    }

    public void setBehelpuserid(Long behelpuserid) {
        this.behelpuserid = behelpuserid;
    }

    public String getBehelpusernickname() {
        return behelpusernickname;
    }

    public void setBehelpusernickname(String behelpusernickname) {
        this.behelpusernickname = behelpusernickname == null ? null : behelpusernickname.trim();
    }

    public String getBehelpusername() {
        return behelpusername;
    }

    public void setBehelpusername(String behelpusername) {
        this.behelpusername = behelpusername == null ? null : behelpusername.trim();
    }

    public Long getDutyuserid() {
        return dutyuserid;
    }

    public void setDutyuserid(Long dutyuserid) {
        this.dutyuserid = dutyuserid;
    }

    public String getDutyusernickname() {
        return dutyusernickname;
    }

    public void setDutyusernickname(String dutyusernickname) {
        this.dutyusernickname = dutyusernickname == null ? null : dutyusernickname.trim();
    }

    public String getDutyusername() {
        return dutyusername;
    }

    public void setDutyusername(String dutyusername) {
        this.dutyusername = dutyusername == null ? null : dutyusername.trim();
    }

    public Long getSupuserid() {
        return supuserid;
    }

    public void setSupuserid(Long supuserid) {
        this.supuserid = supuserid;
    }

    public String getSuperusernickname() {
        return superusernickname;
    }

    public void setSuperusernickname(String superusernickname) {
        this.superusernickname = superusernickname == null ? null : superusernickname.trim();
    }

    public String getSuperusername() {
        return superusername;
    }

    public void setSuperusername(String superusername) {
        this.superusername = superusername == null ? null : superusername.trim();
    }

    public Integer getUseridcircle() {
        return useridcircle;
    }

    public void setUseridcircle(Integer useridcircle) {
        this.useridcircle = useridcircle;
    }

    public String getCircleusername() {
        return circleusername;
    }

    public void setCircleusername(String circleusername) {
        this.circleusername = circleusername == null ? null : circleusername.trim();
    }

    public String getCircleusernickname() {
        return circleusernickname;
    }

    public void setCircleusernickname(String circleusernickname) {
        this.circleusernickname = circleusernickname == null ? null : circleusernickname.trim();
    }

    public Integer getPromisetype() {
        return promisetype;
    }

    public void setPromisetype(Integer promisetype) {
        this.promisetype = promisetype;
    }

    public String getChengnuotype() {
        return chengnuotype;
    }

    public void setChengnuotype(String chengnuotype) {
        this.chengnuotype = chengnuotype == null ? null : chengnuotype.trim();
    }

    public String getPromisemiaoshu() {
        return promisemiaoshu;
    }

    public void setPromisemiaoshu(String promisemiaoshu) {
        this.promisemiaoshu = promisemiaoshu == null ? null : promisemiaoshu.trim();
    }

    public Integer getProveone() {
        return proveone;
    }

    public void setProveone(Integer proveone) {
        this.proveone = proveone;
    }

    public Integer getProvetwo() {
        return provetwo;
    }

    public void setProvetwo(Integer provetwo) {
        this.provetwo = provetwo;
    }

    public Integer getProvethree() {
        return provethree;
    }

    public void setProvethree(Integer provethree) {
        this.provethree = provethree;
    }

    public Integer getProvefour() {
        return provefour;
    }

    public void setProvefour(Integer provefour) {
        this.provefour = provefour;
    }

    public Integer getProvefive() {
        return provefive;
    }

    public void setProvefive(Integer provefive) {
        this.provefive = provefive;
    }

    public Byte getCountstate() {
        return countstate;
    }

    public void setCountstate(Byte countstate) {
        this.countstate = countstate;
    }

    public Integer getTargetcash() {
        return targetcash;
    }

    public void setTargetcash(Integer targetcash) {
        this.targetcash = targetcash;
    }

    public Integer getRealcash() {
        return realcash;
    }

    public void setRealcash(Integer realcash) {
        this.realcash = realcash;
    }

    public Date getOpentime() {
        return opentime;
    }

    public void setOpentime(Date opentime) {
        this.opentime = opentime;
    }

    public Date getClosetime() {
        return closetime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
    }

    public Byte getGetmoneytag() {
        return getmoneytag;
    }

    public void setGetmoneytag(Byte getmoneytag) {
        this.getmoneytag = getmoneytag;
    }

    public Byte getCashok() {
        return cashok;
    }

    public void setCashok(Byte cashok) {
        this.cashok = cashok;
    }

    public Integer getSpendmoney() {
        return spendmoney;
    }

    public void setSpendmoney(Integer spendmoney) {
        this.spendmoney = spendmoney;
    }

    public Integer getSumaffirm() {
        return sumaffirm;
    }

    public void setSumaffirm(Integer sumaffirm) {
        this.sumaffirm = sumaffirm;
    }

    public Integer getSumzhuanfa() {
        return sumzhuanfa;
    }

    public void setSumzhuanfa(Integer sumzhuanfa) {
        this.sumzhuanfa = sumzhuanfa;
    }

    public Integer getSumattention() {
        return sumattention;
    }

    public void setSumattention(Integer sumattention) {
        this.sumattention = sumattention;
    }

    public Integer getSumbackup() {
        return sumbackup;
    }

    public void setSumbackup(Integer sumbackup) {
        this.sumbackup = sumbackup;
    }

    public Integer getSummanbackup() {
        return summanbackup;
    }

    public void setSummanbackup(Integer summanbackup) {
        this.summanbackup = summanbackup;
    }

    public Integer getSumdays() {
        return sumdays;
    }

    public void setSumdays(Integer sumdays) {
        this.sumdays = sumdays;
    }

    public Integer getProgressstate() {
        return progressstate;
    }

    public void setProgressstate(Integer progressstate) {
        this.progressstate = progressstate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getOfflinepassdate() {
        return offlinepassdate;
    }

    public void setOfflinepassdate(Date offlinepassdate) {
        this.offlinepassdate = offlinepassdate;
    }

    public Date getMoneyuseupdate() {
        return moneyuseupdate;
    }

    public void setMoneyuseupdate(Date moneyuseupdate) {
        this.moneyuseupdate = moneyuseupdate;
    }

    public Date getLastend() {
        return lastend;
    }

    public void setLastend(Date lastend) {
        this.lastend = lastend;
    }

    public String getAcceptmoneyman() {
        return acceptmoneyman;
    }

    public void setAcceptmoneyman(String acceptmoneyman) {
        this.acceptmoneyman = acceptmoneyman == null ? null : acceptmoneyman.trim();
    }

    public String getAcceptmoneymanid() {
        return acceptmoneymanid;
    }

    public void setAcceptmoneymanid(String acceptmoneymanid) {
        this.acceptmoneymanid = acceptmoneymanid == null ? null : acceptmoneymanid.trim();
    }

    public Long getCheckuserid() {
        return checkuserid;
    }

    public void setCheckuserid(Long checkuserid) {
        this.checkuserid = checkuserid;
    }

    public String getCheckuseruuid() {
        return checkuseruuid;
    }

    public void setCheckuseruuid(String checkuseruuid) {
        this.checkuseruuid = checkuseruuid == null ? null : checkuseruuid.trim();
    }

    public String getCheckusernickname() {
        return checkusernickname;
    }

    public void setCheckusernickname(String checkusernickname) {
        this.checkusernickname = checkusernickname == null ? null : checkusernickname.trim();
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

    public Integer getPromisedsp() {
        return promisedsp;
    }

    public void setPromisedsp(Integer promisedsp) {
        this.promisedsp = promisedsp;
    }

    public Long getCashtweetid() {
        return cashtweetid;
    }

    public void setCashtweetid(Long cashtweetid) {
        this.cashtweetid = cashtweetid;
    }

    public String getCashtweetuuid() {
        return cashtweetuuid;
    }

    public void setCashtweetuuid(String cashtweetuuid) {
        this.cashtweetuuid = cashtweetuuid == null ? null : cashtweetuuid.trim();
    }

    public String getCashcityname() {
        return cashcityname;
    }

    public void setCashcityname(String cashcityname) {
        this.cashcityname = cashcityname == null ? null : cashcityname.trim();
    }

    public String getCashcitycode() {
        return cashcitycode;
    }

    public void setCashcitycode(String cashcitycode) {
        this.cashcitycode = cashcitycode == null ? null : cashcitycode.trim();
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }
}