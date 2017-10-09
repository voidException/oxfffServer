package org.geilove.dao;

import org.geilove.pojo.ProductInfo;

import java.util.List;
import java.util.Map;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Long productid);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Long productid);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);

    List<ProductInfo>  getProductInfoList(Map<String,Object> map); //自定义根据一组参数获取商品网信息列表

    ProductInfo selectByProductUUID(String productUUID); //根据网商品的UUID获得它

}