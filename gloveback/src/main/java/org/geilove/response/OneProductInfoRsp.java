package org.geilove.response;

import org.geilove.pojo.ProductInfo;

/**
 * Created by aihaitao on 26/3/2017.
 */
public class OneProductInfoRsp {
    private Integer retcode ;
    private String msg;
    private ProductInfo productInfo;

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

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
