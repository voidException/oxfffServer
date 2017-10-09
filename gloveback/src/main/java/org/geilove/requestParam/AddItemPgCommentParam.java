package org.geilove.requestParam;

import java.util.Date;

/**
 * Created by mfhj-dz-001-424 on 17/2/9.
 */
public class AddItemPgCommentParam {

    private  String  token;

    private Long itemprogressid;

    private Long useridsender;

    private String useruuidsender;

    private String sendernickname;

    private String senderphoto;

    private Long useridreciver;

    private String useruuidreciver;

    private String recivernickname;

    private String content;

    private Integer refer;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getItemprogressid() {
        return itemprogressid;
    }

    public void setItemprogressid(Long itemprogressid) {
        this.itemprogressid = itemprogressid;
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
        this.useruuidsender = useruuidsender;
    }

    public String getSendernickname() {
        return sendernickname;
    }

    public void setSendernickname(String sendernickname) {
        this.sendernickname = sendernickname;
    }

    public String getSenderphoto() {
        return senderphoto;
    }

    public void setSenderphoto(String senderphoto) {
        this.senderphoto = senderphoto;
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
        this.useruuidreciver = useruuidreciver;
    }

    public String getRecivernickname() {
        return recivernickname;
    }

    public void setRecivernickname(String recivernickname) {
        this.recivernickname = recivernickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRefer() {
        return refer;
    }

    public void setRefer(Integer refer) {
        this.refer = refer;
    }
}
