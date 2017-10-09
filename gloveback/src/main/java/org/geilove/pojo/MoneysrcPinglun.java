package org.geilove.pojo;

import java.util.Date;

public class MoneysrcPinglun {
    private Long moneysrcpinglunid;

    private Long moneysourceid;

    private Long useridsender;

    private String useruuidsender;

    private String sendernickname;

    private String senderphoto;

    private Long useridreciver;

    private String useruuidreciver;

    private String recivernickname;

    private String reciverphoto;

    private Date pingluntime;

    private String pingluntext;

    private Integer refer;

    public Long getMoneysrcpinglunid() {
        return moneysrcpinglunid;
    }

    public void setMoneysrcpinglunid(Long moneysrcpinglunid) {
        this.moneysrcpinglunid = moneysrcpinglunid;
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

    public String getUseruuidsender() {
        return useruuidsender;
    }

    public void setUseruuidsender(String useruuidsender) {
        this.useruuidsender = useruuidsender == null ? null : useruuidsender.trim();
    }

    public String getSendernickname() {
        return sendernickname;
    }

    public void setSendernickname(String sendernickname) {
        this.sendernickname = sendernickname == null ? null : sendernickname.trim();
    }

    public String getSenderphoto() {
        return senderphoto;
    }

    public void setSenderphoto(String senderphoto) {
        this.senderphoto = senderphoto == null ? null : senderphoto.trim();
    }

    public Long getUseridreciver() {
        return useridreciver;
    }

    public void setUseridreciver(Long useridreciver) {
        this.useridreciver = useridreciver;
    }

    public String getUseruuidreciver() {
        return useruuidreciver;
    }

    public void setUseruuidreciver(String useruuidreciver) {
        this.useruuidreciver = useruuidreciver == null ? null : useruuidreciver.trim();
    }

    public String getRecivernickname() {
        return recivernickname;
    }

    public void setRecivernickname(String recivernickname) {
        this.recivernickname = recivernickname == null ? null : recivernickname.trim();
    }

    public String getReciverphoto() {
        return reciverphoto;
    }

    public void setReciverphoto(String reciverphoto) {
        this.reciverphoto = reciverphoto == null ? null : reciverphoto.trim();
    }

    public Date getPingluntime() {
        return pingluntime;
    }

    public void setPingluntime(Date pingluntime) {
        this.pingluntime = pingluntime;
    }

    public String getPingluntext() {
        return pingluntext;
    }

    public void setPingluntext(String pingluntext) {
        this.pingluntext = pingluntext == null ? null : pingluntext.trim();
    }

    public Integer getRefer() {
        return refer;
    }

    public void setRefer(Integer refer) {
        this.refer = refer;
    }
}