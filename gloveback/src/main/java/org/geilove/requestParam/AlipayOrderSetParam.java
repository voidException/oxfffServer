package org.geilove.requestParam;

import java.util.List;

/**
 * Created by aihaitao on 13/10/2017.
 */
public class AlipayOrderSetParam {

    private  String  categorytype; //互助的计划
    private  String  useruuid;
    private  String  token;
    private List<AlipayOrderUser>  alipayorderuserlist;

    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype;
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<AlipayOrderUser> getAlipayorderuserlist() {
        return alipayorderuserlist;
    }

    public void setAlipayorderuserlist(List<AlipayOrderUser> alipayorderuserlist) {
        this.alipayorderuserlist = alipayorderuserlist;
    }

}


