package org.geilove.requestParam;

/**
 * Created by mfhj-dz-001-424 on 17/1/24.
 */
//用于MoneySourceController 的请求参数，获得"支持了"列表

public class MoneySourceParam {

    private  String   cashUUID;
    private  Integer  page;  //这个是请求的页数
    private  Integer  pageSize ; //这个是请求的每页的大小
    private  String   timeStamp; //这个是"支持了" 的时间，要按照时间获取

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
