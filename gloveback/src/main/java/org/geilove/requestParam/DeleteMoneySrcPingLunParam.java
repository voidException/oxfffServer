package org.geilove.requestParam;

/**
 * Created by mfhj-dz-001-424 on 17/2/9.
 */
public class DeleteMoneySrcPingLunParam {

    private String token; //用户的token
    private Long  moneySrcPingluniD; //被删除的评论的id

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getMoneySrcPingluniD() {
        return moneySrcPingluniD;
    }

    public void setMoneySrcPingluniD(Long moneySrcPingluniD) {
        this.moneySrcPingluniD = moneySrcPingluniD;
    }
}
