package org.geilove.requestParam;

/**
 * Created by aihaitao on 1/12/2017.
 */
public class AlipayRedParam {

    private  String  token; //
    private  String  amount;  //钱数，单位元
    private  String  userUUID;   //userUUID
    private  String  accountUUID;    // 也即身份证号
    private  String  userName;      //用户姓名
    private  String  categoryType;   //互助的类型，如中青年互助计划
    private  String  accountName;  //对于红包支付，这个是被充钱人姓名，userName就是充钱人姓名吧
    private  String  payType; //这个是充值的类型，red--红包充值，company-公司充值，person--个人补充充钱
    private  String  redmoneyuuid; //红包的uuuid

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRedmoneyuuid() {
        return redmoneyuuid;
    }

    public void setRedmoneyuuid(String redmoneyuuid) {
        this.redmoneyuuid = redmoneyuuid;
    }

}
