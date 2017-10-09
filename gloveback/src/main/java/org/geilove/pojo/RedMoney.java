package org.geilove.pojo;

import java.util.Date;

public class RedMoney {
    private Long redmoneyid;

    private String redmoneyuuid;

    private String useruuid;

    private Long redmoney;

    private Date redmoneydate;

    private String useruuidclick;

    private String redmoneystate;

    private Date losefuncdate;

    public Long getRedmoneyid() {
        return redmoneyid;
    }

    public void setRedmoneyid(Long redmoneyid) {
        this.redmoneyid = redmoneyid;
    }

    public String getRedmoneyuuid() {
        return redmoneyuuid;
    }

    public void setRedmoneyuuid(String redmoneyuuid) {
        this.redmoneyuuid = redmoneyuuid == null ? null : redmoneyuuid.trim();
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public Long getRedmoney() {
        return redmoney;
    }

    public void setRedmoney(Long redmoney) {
        this.redmoney = redmoney;
    }

    public Date getRedmoneydate() {
        return redmoneydate;
    }

    public void setRedmoneydate(Date redmoneydate) {
        this.redmoneydate = redmoneydate;
    }

    public String getUseruuidclick() {
        return useruuidclick;
    }

    public void setUseruuidclick(String useruuidclick) {
        this.useruuidclick = useruuidclick == null ? null : useruuidclick.trim();
    }

    public String getRedmoneystate() {
        return redmoneystate;
    }

    public void setRedmoneystate(String redmoneystate) {
        this.redmoneystate = redmoneystate == null ? null : redmoneystate.trim();
    }

    public Date getLosefuncdate() {
        return losefuncdate;
    }

    public void setLosefuncdate(Date losefuncdate) {
        this.losefuncdate = losefuncdate;
    }
}