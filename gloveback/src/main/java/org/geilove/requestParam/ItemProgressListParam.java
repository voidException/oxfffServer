package org.geilove.requestParam;

/**
 * Created by mfhj-dz-001-424 on 17/2/7.
 */
public class ItemProgressListParam {

    private  String  userUUID;//该进度更新对应的cash表的iD，无论进度更新还是moneysource表都应该首先跟cash表关联，而不是推文
    private  String  cashUUID;
    private  Integer  page;  //这个是请求的页数
    private  Integer  pageSize ; //这个是请求的每页的大小
    private  String   timeStamp;

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getCashUUID() {
        return cashUUID;
    }

    public void setCashUUID(String cashUUID) {
        this.cashUUID = cashUUID;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
