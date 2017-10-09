
package org.geilove.controller;
import org.geilove.pojo.WechatLog;
import org.geilove.requestParam.UnifiedPartParam;
import org.geilove.response.UnifiedorderRsp;
import org.geilove.service.WechatLogService;
import org.geilove.utils.WeChatUtils;
import org.geilove.utils.WxHttpClientUtils;
import org.geilove.utils.WxUrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

//这个主要是为关于我们，常见问题，意见反馈等提供路由
@Controller
@RequestMapping(value="/authorize")
public class WeiXinShouQuanController {

    @Resource
    private WechatLogService wechatLogService;

    @RequestMapping(value="/userOpeniD/{orderId}/preOrder.do",method = RequestMethod.GET)
    public ModelAndView getUserOpenId(@PathVariable String orderId, HttpServletRequest request, HttpServletResponse response){
        System.out.println("start");
        String openId = "";

        if (request.getParameter("code")!=null) {  //拿到code
            String code = request.getParameter("code").toString();
            try{
                /* 1. 关键点,使用code获取openId */
                openId = WeChatUtils.getOpenId(code);
                WechatLog wechatLog=new WechatLog();
                wechatLog.setClassname("WeiXinShouQuanController_Exception");
                wechatLog.setLog(openId);
                //wechatLog.setOther(e.getMessage());
                wechatLogService.addlog(wechatLog);
            }catch (Exception e){
                System.out.println(e);
            }
        }
         // 测试数据，后续删掉 ，这里根据orderId 重定向到不同的网页，一是支付，二是关联用户邮箱
        //openId="openId";
        orderId="orderId";
        Map<String,String> model =new HashMap();
        model.put("openId", openId);
        model.put("orderId", orderId);
        ModelAndView modelAndView=new ModelAndView("front/payView",model);
        return  modelAndView;
    }

    // 用户点击支付，先发送请求到这里获取必须的参数。prepayid，这里调用统一下单接口
    // 可以获得受助人的UUID  和 对应的cashUUID，以及moneySourceUUID，放在attach等里面。(非常重要)
    // https://api.mch.weixin.qq.com/pay/unifiedorder
    @RequestMapping(value="/weixinJSBridge/invoke.do",method = RequestMethod.POST)
    public @ResponseBody  Object getCallH5Param(@RequestBody UnifiedPartParam unifiedPartParam, HttpServletRequest request, HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        UnifiedorderRsp unifiedorderRsp=new UnifiedorderRsp();

        System.out.println("getCallH5Param");

        String openId=unifiedPartParam.getOpenId(); //前端传输过来
        String orderId=unifiedPartParam.getOrderId(); // 前端传输过来
        Integer money=unifiedPartParam.getMoney(); //这个单位应该是元，转化成分
        String attach = "测试订单attach";
        String body = "测试数据";
        //以下是部分参数，另一部分在WxHttpClientUtils 里面
        Map<String, String> orderParam = new HashMap<String, String>();
        orderParam.put("attach", attach);
        orderParam.put("body", body);
        orderParam.put("openid", openId); //openId
        orderParam.put("out_trade_no", orderId.toString());   //商户订单号，在这里应该生成作为Moneysource的uuid，然后和受助人uuid，cashUUD存入资金来源表

       // orderParam.put("ip", request.getRemoteAddr());        //终端IP
        orderParam.put("ip", "192.168.0.106");
        orderParam.put("total_fee", "1");  //FIXME 测试数据 一分钱。这个文档上是Integer类型，但是不知道为何要转化成字符串
        String prePayId=null;
        try{
             //重要
             prePayId = WxHttpClientUtils.getPrePayIdH5(orderParam); //获得预支付号
        }catch (Exception e){
          System.out.println(e.getMessage());
        }
        //System.out.println("unifiedorderRsp");
        Map<String, String> paySignParams =new HashMap<>();
        try {
            paySignParams = WxUrlUtils.generatePaySign(prePayId);
        }catch (Exception e){

        }
        //以下3个字段在吊起微信支付时，用不到
        unifiedorderRsp.setOrderId(orderId);       //订单号
        unifiedorderRsp.setOpenId(openId);         //用户对公众号的唯一标识
        unifiedorderRsp.setPrepay_id(prePayId);    //预支付号
        // 必备字段
        unifiedorderRsp.setPayAppId(paySignParams.get("appId"));
        unifiedorderRsp.setPaytimestamp(paySignParams.get("timeStamp")); //时间戳
        unifiedorderRsp.setPaypackage("prepay_id=" + prePayId); //订单详情扩展字符串
        unifiedorderRsp.setPaynonceStr(paySignParams.get("nonceStr")); //20位随机字符串
        unifiedorderRsp.setPaysignType("MD5");   //签名方式
        unifiedorderRsp.setPaySign(paySignParams.get("sign"));    //签名

        unifiedorderRsp.setRetcode(2000);
        unifiedorderRsp.setMsg("成功");
        //加上其它参数一起返回给前端，然后就可以调用了

        return unifiedorderRsp;
    }


}

