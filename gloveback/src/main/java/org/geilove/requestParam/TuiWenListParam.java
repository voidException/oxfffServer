package org.geilove.requestParam;

/**
 * Created by mfhj-dz-001-424 on 17/2/28.
 */
public class TuiWenListParam {

    private  Long 	   userID; 		//用户的ID
    private  String    lastUpdate;  //如果是刷新，这个时间就是无限大，如果是点击"下一页"，就是上次刷新后获取数据的最早发布的时间
    private  Integer   page;		//开始的页
    private  Integer   pageSize;    //每页的大小


    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
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
}
