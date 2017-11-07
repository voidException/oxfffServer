package org.geilove.pojo;

import java.util.Date;

public class Notification {
    private Long id;

    private String notifyuuid;

    private String useruuid;

    private Date notifytime;

    private String readif;

    private String message;

    private String msgtype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotifyuuid() {
        return notifyuuid;
    }

    public void setNotifyuuid(String notifyuuid) {
        this.notifyuuid = notifyuuid == null ? null : notifyuuid.trim();
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public Date getNotifytime() {
        return notifytime;
    }

    public void setNotifytime(Date notifytime) {
        this.notifytime = notifytime;
    }

    public String getReadif() {
        return readif;
    }

    public void setReadif(String readif) {
        this.readif = readif == null ? null : readif.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype == null ? null : msgtype.trim();
    }
}