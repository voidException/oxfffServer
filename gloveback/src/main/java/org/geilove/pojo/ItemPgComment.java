package org.geilove.pojo;

import java.util.Date;

public class ItemPgComment {

    private Long itempgcommentid;

    private Long itemprogressid;

    private Long useridsender;

    private String useruuidsender;

    private String sendernickname;

    private String senderphoto;

    private Long useridreciver;

    private String useruuidreciver;

    private String recivernickname;

    private String reciverphoto;

    private Date commenttime;

    private String content;

    private Integer refer;

    private String backupone;

    private String backuptwo;

    private String backupthree;

    private String backupfour;

    private Integer backupfive;

    private Integer backupsix;

    public Long getItempgcommentid() {
        return itempgcommentid;
    }

    public void setItempgcommentid(Long itempgcommentid) {
        this.itempgcommentid = itempgcommentid;
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

    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getRefer() {
        return refer;
    }

    public void setRefer(Integer refer) {
        this.refer = refer;
    }

    public String getBackupone() {
        return backupone;
    }

    public void setBackupone(String backupone) {
        this.backupone = backupone == null ? null : backupone.trim();
    }

    public String getBackuptwo() {
        return backuptwo;
    }

    public void setBackuptwo(String backuptwo) {
        this.backuptwo = backuptwo == null ? null : backuptwo.trim();
    }

    public String getBackupthree() {
        return backupthree;
    }

    public void setBackupthree(String backupthree) {
        this.backupthree = backupthree == null ? null : backupthree.trim();
    }

    public String getBackupfour() {
        return backupfour;
    }

    public void setBackupfour(String backupfour) {
        this.backupfour = backupfour == null ? null : backupfour.trim();
    }

    public Integer getBackupfive() {
        return backupfive;
    }

    public void setBackupfive(Integer backupfive) {
        this.backupfive = backupfive;
    }

    public Integer getBackupsix() {
        return backupsix;
    }

    public void setBackupsix(Integer backupsix) {
        this.backupsix = backupsix;
    }
}