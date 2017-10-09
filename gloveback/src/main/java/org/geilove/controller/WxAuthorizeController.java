package org.geilove.controller;
//import org.geilove.pojo.WechatLog;
//import org.geilove.requestParam.UnifiedPartParam;
//import org.geilove.response.UnifiedorderRsp;
//import org.geilove.service.WechatLogService;
//import org.geilove.utils.WeChatUtils;
//import org.geilove.utils.WxHttpClientUtils;
//import org.geilove.utils.WxUrlUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 微信授权回调页面url
// *
// * 在WeChatayController 里面，发起授权，微信会跳转到此页面，在这里用code获取了openId 还有access_token（用不着）
// *
// */
//
//@RequestMapping(value="/authorize")
//@Controller
@Deprecated
public class WxAuthorizeController {
//
//    @Resource
//    private WechatLogService wechatLogService;
//    //该方法主要是返回输入金额页面，页面中包含了openId，
//
//   // @RequestMapping(value="/userOpeniD/{orderId}/preorder.do")
//    @RequestMapping(value="/order.do")
//    public ModelAndView getOpenId(@PathVariable String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception{
//
//        String openId = "";
//        if (request.getParameter("code")!=null) {  //拿到code
//            String code = request.getParameter("code").toString();
//
//            /* 1. 关键点,使用code获取openId */
//            openId = WeChatUtils.getOpenId(code);
//
//            //记录日志到数据库
////            WechatLog wechatLog=new WechatLog();
////            wechatLog.setClassname("WxAuthorize");
////            wechatLog.setLog(openId);
////            wechatLogService.addlog(wechatLog);
//
//        }
//         // 测试数据，后续删掉
//        openId="openId";
//        orderId="orderId";
//
//        Map <String,String>model =new HashMap();
//        model.put("openId", openId);
//        model.put("orderId", orderId);
//        ModelAndView modelAndView=new ModelAndView("front/payView",model);
//        return  modelAndView;
//    }
//
//    // 用户点击支付，先发送请求到这里获取必须的参数。prepayid，这里调用统一下单接口
//    // https://api.mch.weixin.qq.com/pay/unifiedorder
//    @RequestMapping(value="/weixinJSBridge/invoke.do",method = RequestMethod.POST)
//    public Object getCallH5Param(UnifiedPartParam unifiedPartParam, HttpServletRequest request, HttpServletResponse response) throws Exception{
//        UnifiedorderRsp unifiedorderRsp=new UnifiedorderRsp();
//
//        String openId=unifiedPartParam.getOpenId(); //前端传输过来
//        String orderId=unifiedPartParam.getOrderId(); // 前端传输过来
//        Integer money=unifiedPartParam.getMoney(); //这个单位应该是元，转化成分
//        String attach = "测试订单attach";
//        String body = "测试数据";
//        //以下是部分参数，另一部分在WxHttpClientUtils 里面
//        Map<String, String> orderParam = new HashMap<String, String>();
//        orderParam.put("attach", attach);
//        orderParam.put("body", body);
//        orderParam.put("openid", openId); //openId
//        orderParam.put("out_trade_no", orderId.toString());   //商户订单号
//        orderParam.put("ip", request.getRemoteAddr());        //终端IP
//        orderParam.put("total_fee", "1");  //FIXME 测试数据 一分钱。这个文档上是Integer类型，但是不知道为何要转化成字符串
//
//        String prePayId = WxHttpClientUtils.getPrePayIdH5(orderParam);
//        unifiedorderRsp.setPrepay_id(prePayId);
//        unifiedorderRsp.setRetcode(2000);
//        unifiedorderRsp.setMsg("成功");
//        //加上其它参数一起返回给前端，然后就可以调用了
//
//        return unifiedorderRsp;
//    }
//
}
