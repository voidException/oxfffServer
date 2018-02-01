package org.geilove.vo;

/**
 * Created by aihaitao on 27/11/2017.
 */
public class SumInfo {

    private  String sumMan;   //参与该计划的人数
    private  String sumMoney;  //参与该计划的 历史总金额,后产品要求改为最低互助金额，0或者150
    private  String average;   //当前人均
    private  String sumMoneyRemain ;  //余额
    private  String  helpTimes ;  //互助次数
    private  String  helpType; //互助类型

    public String getHelpType() {
        return helpType;
    }

    public void setHelpType(String helpType) {
        this.helpType = helpType;
    }

    public String getSumMan() {
        return sumMan;
    }

    public void setSumMan(String sumMan) {
        this.sumMan = sumMan;
    }

    public String getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getSumMoneyRemain() {
        return sumMoneyRemain;
    }

    public void setSumMoneyRemain(String sumMoneyRemain) {
        this.sumMoneyRemain = sumMoneyRemain;
    }

    public String getHelpTimes() {
        return helpTimes;
    }

    public void setHelpTimes(String helpTimes) {
        this.helpTimes = helpTimes;
    }
}
