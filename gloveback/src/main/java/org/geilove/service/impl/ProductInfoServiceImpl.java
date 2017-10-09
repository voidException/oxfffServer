package org.geilove.service.impl;

import org.geilove.dao.ProductInfoMapper;
import org.geilove.pojo.ProductInfo;
import org.geilove.service.ProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mfhj-dz-001-424 on 17/3/22.
 */
@Service("productInfo")
public class ProductInfoServiceImpl implements ProductInfoService {
    @Resource
    private ProductInfoMapper  productInfoMapper;

    @Override
    public List<ProductInfo> getProductInfoList(Map<String,Object> map){

        List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();

        productInfoList=productInfoMapper.getProductInfoList(map);

        return  productInfoList;
    }
    @Override
    public  ProductInfo getProductByUUID(String productUUID){
        ProductInfo productInfo;
        productInfo=productInfoMapper.selectByProductUUID(productUUID);
        return  productInfo;
    }
}
