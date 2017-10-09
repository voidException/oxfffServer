package org.geilove.controller;

/**
 * 提供网商品列表获取，但商品获取，增加等功能
 */
import org.geilove.pojo.ProductInfo;
import org.geilove.requestParam.ProductListParam;
import org.geilove.response.CommonRsp;
import org.geilove.response.OneProductInfoRsp;
import org.geilove.response.ProductListRsp;
import org.geilove.service.ProductInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productInfo")
public class ProductInfoController {
    @Resource
    private ProductInfoService productInfoService;

    /*1.用于获取商品列表*/
    @RequestMapping(value="/listProduct",method=RequestMethod.POST)
    public @ResponseBody Object getMoneySourceList(@RequestBody ProductListParam productListParam,HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> map=new HashMap<>();

        ProductListRsp commonRsp=new ProductListRsp();
        if (productListParam==null){
            commonRsp.setMsg("请求参数为空");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        int page=productListParam.getPage();
        map.put("page",page);
        map.put("pageSize",6);
        List<ProductInfo> productInfos;
        try{
            productInfos=productInfoService.getProductInfoList(map);
            if (productInfos==null){
                commonRsp.setMsg("暂无商品");
                commonRsp.setRetcode(2001);
                return  commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("商品列表请求抛出异常");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        commonRsp.setProductInfos(productInfos);
        commonRsp.setRetcode(2000);
        commonRsp.setMsg("获取商品列表成功");
        return commonRsp;
    }

    /*2.根据productInfo的UUID获取该商品相信信息*/
    @RequestMapping(value = "/info/{productInfoUUID}",method = RequestMethod.GET)
    public @ResponseBody  Object getProductInfoByUUID(@PathVariable("productInfoUUID") String productInfoUUID, HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        OneProductInfoRsp commonRsp=new OneProductInfoRsp();
        ProductInfo productInfo;

        if (productInfoUUID==null){
            commonRsp.setMsg("请求参数为空");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        try{
            productInfo=productInfoService.getProductByUUID(productInfoUUID);
            if (productInfo==null){
                commonRsp.setMsg("请求商品不存在");
                commonRsp.setRetcode(2001);
                return  commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("请求商品信息出现异常");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        commonRsp.setMsg("获取商品详情成功");
        commonRsp.setRetcode(2000);
        commonRsp.setProductInfo(productInfo);
        return  commonRsp;
    }
}















