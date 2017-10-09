package org.geilove.response;

import org.geilove.pojo.ProductInfo;

import java.util.List;

/**
 * Created by aihaitao on 26/3/2017.
 */
public class ProductListRsp {
    private Integer retcode ;
    private String msg;
    private List<ProductInfo> productInfos;

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }
}
