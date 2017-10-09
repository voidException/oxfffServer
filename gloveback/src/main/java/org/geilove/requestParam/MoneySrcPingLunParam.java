package org.geilove.requestParam;

/**
 * Created by mfhj-dz-001-424 on 17/2/9.
 */
public class MoneySrcPingLunParam {

    private  String token;  //评论人的token

    private Long moneysourceid;

    private Long useridsender;

    private String sendernickname;

    private Long useridreciver;  //被评论人的iD

    private String recivernickname; //被评论人的昵称

    private String pingluntext;

    private Integer refer;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getMoneysourceid() {
        return moneysourceid;
    }

    public void setMoneysourceid(Long moneysourceid) {
        this.moneysourceid = moneysourceid;
    }

    public Long getUseridsender() {
        return useridsender;
    }

    public void setUseridsender(Long useridsender) {
        this.useridsender = useridsender;
    }

    public String getSendernickname() {
        return sendernickname;
    }

    public void setSendernickname(String sendernickname) {
        this.sendernickname = sendernickname;
    }

    public Long getUseridreciver() {
        return useridreciver;
    }

    public void setUseridreciver(Long useridreciver) {
        this.useridreciver = useridreciver;
    }

    public String getRecivernickname() {
        return recivernickname;
    }

    public void setRecivernickname(String recivernickname) {
        this.recivernickname = recivernickname;
    }

    public String getPingluntext() {
        return pingluntext;
    }

    public void setPingluntext(String pingluntext) {
        this.pingluntext = pingluntext;
    }

    public Integer getRefer() {
        return refer;
    }

    public void setRefer(Integer refer) {
        this.refer = refer;
    }
}
