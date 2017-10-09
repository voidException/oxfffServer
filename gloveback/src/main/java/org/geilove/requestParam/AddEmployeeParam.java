package org.geilove.requestParam;

/**
 * Created by aihaitao on 14/7/2017.
 */
public class AddEmployeeParam {

    private  String token;        //token值
    private  String userUUID;     //注册用户的useruuid
    private  String accountuuid;  //员工或者家人的身份证号
    private  String userName;     //员工的姓名
    private  String buildrelationdescription;   //home或者business

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getAccountuuid() {
        return accountuuid;
    }

    public void setAccountuuid(String accountuuid) {
        this.accountuuid = accountuuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBuildrelationdescription() {
        return buildrelationdescription;
    }

    public void setBuildrelationdescription(String buildrelationdescription) {
        this.buildrelationdescription = buildrelationdescription;
    }
}
