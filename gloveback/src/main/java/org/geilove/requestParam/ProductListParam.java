package org.geilove.requestParam;

/**
 * Created by aihaitao on 26/3/2017.
 * 用于PC电脑web端展示商品列表
 */
public class ProductListParam {

    private  Integer  page;
    private  Integer  pageSize;

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
