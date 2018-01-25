package org.geilove.pojo;

import java.util.Date;

public class Deduction {
    private Integer deductionid;

    private String deductionuuid;

    private String categorytype;

    private String userneedmoneyuuid;

    private String userspendmoneyuuid;

    private String moneyspend;

    private Date userspendmoneydate;

    private String theorymoneyspend;

    private String other;

    private String userneedmoneyaccount;

    private String userspendmoneyaccount;

    public Integer getDeductionid() {
        return deductionid;
    }

    public void setDeductionid(Integer deductionid) {
        this.deductionid = deductionid;
    }

    public String getDeductionuuid() {
        return deductionuuid;
    }

    public void setDeductionuuid(String deductionuuid) {
        this.deductionuuid = deductionuuid == null ? null : deductionuuid.trim();
    }

    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype == null ? null : categorytype.trim();
    }

    public String getUserneedmoneyuuid() {
        return userneedmoneyuuid;
    }

    public void setUserneedmoneyuuid(String userneedmoneyuuid) {
        this.userneedmoneyuuid = userneedmoneyuuid == null ? null : userneedmoneyuuid.trim();
    }

    public String getUserspendmoneyuuid() {
        return userspendmoneyuuid;
    }

    public void setUserspendmoneyuuid(String userspendmoneyuuid) {
        this.userspendmoneyuuid = userspendmoneyuuid == null ? null : userspendmoneyuuid.trim();
    }

    public String getMoneyspend() {
        return moneyspend;
    }

    public void setMoneyspend(String moneyspend) {
        this.moneyspend = moneyspend == null ? null : moneyspend.trim();
    }

    public Date getUserspendmoneydate() {
        return userspendmoneydate;
    }

    public void setUserspendmoneydate(Date userspendmoneydate) {
        this.userspendmoneydate = userspendmoneydate;
    }

    public String getTheorymoneyspend() {
        return theorymoneyspend;
    }

    public void setTheorymoneyspend(String theorymoneyspend) {
        this.theorymoneyspend = theorymoneyspend == null ? null : theorymoneyspend.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getUserneedmoneyaccount() {
        return userneedmoneyaccount;
    }

    public void setUserneedmoneyaccount(String userneedmoneyaccount) {
        this.userneedmoneyaccount = userneedmoneyaccount == null ? null : userneedmoneyaccount.trim();
    }

    public String getUserspendmoneyaccount() {
        return userspendmoneyaccount;
    }

    public void setUserspendmoneyaccount(String userspendmoneyaccount) {
        this.userspendmoneyaccount = userspendmoneyaccount == null ? null : userspendmoneyaccount.trim();
    }
}