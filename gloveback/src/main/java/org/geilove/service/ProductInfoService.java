package org.geilove.service;

import org.geilove.pojo.ProductInfo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mfhj-dz-001-424 on 17/3/22.
 * 用于商业产品的众筹展示，仅仅为了给微信支付审核人员看
 */
public interface ProductInfoService {

     public List<ProductInfo> getProductInfoList(Map<String,Object>  map);

     public  ProductInfo getProductByUUID(String productUUID); //根据商品的uuid获取他
}
