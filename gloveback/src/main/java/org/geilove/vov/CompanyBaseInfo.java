package org.geilove.vov;

/**
 * 公司员工人数，总资金数和提醒充值
 */
public class CompanyBaseInfo {
    private  String  stuff;
    private  String  totalmoney;
    private  String  average;
    private String   message; //提示哪一步出了问题
    private int      code; //提示哪一出了问题，

    public String getStuff() {
        return stuff;
    }

    public void setStuff(String stuff) {
        this.stuff = stuff;
    }

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
