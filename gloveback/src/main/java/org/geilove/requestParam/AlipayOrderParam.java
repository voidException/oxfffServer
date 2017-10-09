package org.geilove.requestParam;

/**
 * Created by aihaitao on 8/9/2017.
 */
public class AlipayOrderParam {

    private  String  amount;  //钱数，单位元
    private  String  userUUID;   //userUUID
    private  String  accountUUID;    // 也即身份证号
    private  String  userName;      //用户姓名
    private  String  categoryType;   //互助的类型，如中青年互助计划

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getAccountUUID() {
        return accountUUID;
    }

    public void setAccountUUID(String accountUUID) {
        this.accountUUID = accountUUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }


}
