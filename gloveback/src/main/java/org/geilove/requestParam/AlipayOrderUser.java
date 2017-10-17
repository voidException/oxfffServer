package org.geilove.requestParam;

/**
 * Created by aihaitao on 14/10/2017.
 */

public class  AlipayOrderUser{

    private  String  username;
    private  String  accountuuid;
    private  String  money;    //必须是整数钱数

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountuuid() {
        return accountuuid;
    }

    public void setAccountuuid(String accountuuid) {
        this.accountuuid = accountuuid;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}