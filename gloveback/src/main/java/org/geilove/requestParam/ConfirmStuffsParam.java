package org.geilove.requestParam;

import java.util.List;

/**
 * Created by aihaitao on 4/11/2017.
 */
public class ConfirmStuffsParam {

    private String  token ;
    private String  useruuid;
    private List<StuffsUserUUID> stuffsUserUUIDList;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid;
    }

    public List<StuffsUserUUID> getStuffsUserUUIDList() {
        return stuffsUserUUIDList;
    }

    public void setStuffsUserUUIDList(List<StuffsUserUUID> stuffsUserUUIDList) {
        this.stuffsUserUUIDList = stuffsUserUUIDList;
    }
}
