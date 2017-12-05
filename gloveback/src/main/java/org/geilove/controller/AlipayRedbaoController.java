package org.geilove.controller;

/**
 * 带红包的支付宝支付
 */
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.geilove.pojo.PayMoney;
import org.geilove.requestParam.AlipayOrderParam;
import org.geilove.requestParam.AlipayOrderSetParam;
import org.geilove.requestParam.AlipayRedParam;
import org.geilove.response.AlipayOrderRsp;
import org.geilove.service.AlipayService;
import org.geilove.service.AlipayService;
import org.geilove.service.RegisterLoginService;
import org.geilove.utilAlipay.config.AlipayConfig;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.net.URLEncoder;
import java.util.*;
import javax.annotation.Resource;

@Controller
@RequestMapping("/alipay")
public class AlipayRedbaoController {
    /**
     * 支付下订单，生成预订单
     */

    @Resource
    private AlipayService alipayService;

    @Resource
    private RegisterLoginService rlService;

    class OrderThread extends Thread {
        public PayMoney payMoney;

        public OrderThread(PayMoney payMoney) {
            this.payMoney = payMoney;
        }

        public void run() {
            alipayService.insertPrePayMoneyRecord(this.payMoney); //初步入库
        }
    }


    // 适用于红包充值
    @RequestMapping(value = "/getRedOrder.do", method = RequestMethod.POST)
    @ResponseBody
    public Object payOrder(@RequestBody AlipayRedParam httpRequest) {
        AlipayOrderRsp alipayOrderRsp = new AlipayOrderRsp();
        if (httpRequest == null) {
            alipayOrderRsp.setRetcode(2001);
            alipayOrderRsp.setMsg("请求参数为空");
            alipayOrderRsp.setOderStr(null);
            return alipayOrderRsp;
        }


        String token = httpRequest.getToken();  //仅仅用于本地校验，不用于生成订单
        String payType = httpRequest.getPayType();//red--红包充值，company-公司充值，person--个人补充充钱
        String body = httpRequest.getUserUUID();     //对交易或商品的描述。httpequest传输过来 userUUID
        String subject = httpRequest.getCategoryType();  //商品的标题/交易标题/.  httpequest传输过来(互助类别)categoryType
        String total_amount = httpRequest.getAmount(); //App传来的总金额
        String accountName = httpRequest.getAccountName(); //对于红包充值，这个是被充值人的姓名，userName是充值人的姓名
        String userName = httpRequest.getUserName(); //对于公司集体充值，不需要传值
        String accountUUID = httpRequest.getAccountUUID();  //对于个人充值，是身份证号，对于公司是固定值company
        String redMoneyuuid=httpRequest.getRedmoneyuuid();   // 红包的uuid
        String out_trade_no = getOutTradeNo();           // 商户订单号，本地生成 payMoneyUUID
        String seller_id = "2088911776278734";        //合作者账号，PID，非必须

        String passback_params = "";                 //回传参数
        passback_params = "$$$" + accountUUID + '@' + accountName + '@' + userName+'@'+redMoneyuuid;
        // 缴纳充值记录表
        PayMoney payMoney = new PayMoney();
        payMoney.setPaymoneyuuid(out_trade_no);
        payMoney.setAccountuuid(accountUUID); //被充值用户的身份证号
        payMoney.setPassbackParams(passback_params); //冗余 可不要
        payMoney.setUseruuid(body); //userUUID
        payMoney.setCategorytype(subject); //互助类型
        payMoney.setNotifyTime(new Date());
        payMoney.setTotalAmount(total_amount); //充值的金额
        payMoney.setOutTradeNo(out_trade_no); //商户订单号
        payMoney.setTradeStatus("WAIT_BUYER_PAY"); //等待商家付款
        payMoney.setSellerId(seller_id); //卖家支付宝用户号2088开头

        OrderThread orderThread = new OrderThread(payMoney);
        try {
            orderThread.start(); //开启线程,避免出现问题
        } catch (Exception e) {
            alipayOrderRsp.setRetcode(2001);
            alipayOrderRsp.setOderStr("orderThread抛出异常");
            return alipayOrderRsp;
        }
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gateway_url,
                AlipayConfig.app_id, AlipayConfig.alipay_private_key,
                AlipayConfig.format, AlipayConfig.charset,
                AlipayConfig.alipay_public_key, AlipayConfig.sign_type
        );
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(URLEncoder.encode(body));
        model.setSubject(subject); // 商品标题
        model.setOutTradeNo(out_trade_no); // 商家订单编号(13位时间戳+4位认证人员信息表id后四位，不足前面补0)
        model.setTimeoutExpress(AlipayConfig.timeout_express); // 超时关闭该订单时间
        model.setTotalAmount(total_amount); // 订单总金额
        model.setProductCode(AlipayConfig.product_code); // 销售产品码，商家和支付宝签约的产品码，
        model.setPassbackParams(passback_params); // 描述信息 添加附加数据
        model.setSellerId(seller_id);
        request.setBizModel(model); //业务参数.参见文档
        request.setNotifyUrl(AlipayConfig.notify_url); // 回调地址
        String orderStr = "";  //最终加密的字符串
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            orderStr = response.getBody();
            alipayOrderRsp.setOderStr(orderStr);
            alipayOrderRsp.setRetcode(2000);
            alipayOrderRsp.setMsg("下单成功");
            System.out.println(orderStr);//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
            alipayOrderRsp.setRetcode(2001);
            alipayOrderRsp.setMsg("下单抛出异常");
        }
        return alipayOrderRsp;  //返回结果
    }


    private static String getOutTradeNo() {
        String key = "putao" + UUID.randomUUID().toString();
        return key;
    }
}
