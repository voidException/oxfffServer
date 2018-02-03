package org.geilove.vo;

/**
 * Created by aihaitao on 3/2/2018.
 */
public class DefaultMain {

    private  String  sumUser;  //会员人数
    private  String  itemNum="6";  // 互助产品
    private  String  remainMoney; //账户余额
    private  String  sumCompany;  // 公司用户

    public String getSumUser() {
        return sumUser;
    }

    public void setSumUser(String sumUser) {
        this.sumUser = sumUser;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(String remainMoney) {
        this.remainMoney = remainMoney;
    }

    public String getSumCompany() {
        return sumCompany;
    }

    public void setSumCompany(String sumCompany) {
        this.sumCompany = sumCompany;
    }
}
