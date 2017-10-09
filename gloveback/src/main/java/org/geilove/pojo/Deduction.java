package org.geilove.pojo;

import java.util.Date;

public class Deduction {
    private Long deductionid;

    private String deductionuuid;

    private String categorytype;

    private String userneedmoneyuuid;

    private String userspendmoneyuuid;

    private Long moneyspend;

    private Date userspendmoneydate;

    private Long theorymoneyspend;

    private String other;

    public Long getDeductionid() {
        return deductionid;
    }

    public void setDeductionid(Long deductionid) {
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

    public Long getMoneyspend() {
        return moneyspend;
    }

    public void setMoneyspend(Long moneyspend) {
        this.moneyspend = moneyspend;
    }

    public Date getUserspendmoneydate() {
        return userspendmoneydate;
    }

    public void setUserspendmoneydate(Date userspendmoneydate) {
        this.userspendmoneydate = userspendmoneydate;
    }

    public Long getTheorymoneyspend() {
        return theorymoneyspend;
    }

    public void setTheorymoneyspend(Long theorymoneyspend) {
        this.theorymoneyspend = theorymoneyspend;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}