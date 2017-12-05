package org.geilove.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.geilove.pojo.PayMoney;
import org.geilove.service.AlipayNotifyService;
import org.geilove.service.AlipayService;
import org.geilove.utilAlipay.config.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝支付异步通知
 */

@Controller
@RequestMapping("/alipay")
public class AlipayNotifyController {

    @Resource
    private AlipayService alipayService;
    @Resource
    private AlipayNotifyService alipayNotifyService;

    //内部类，用于异步通知处理业务逻辑
    class AlipayNotifyThread  extends  Thread {

        public PayMoney payMoney;

        public AlipayNotifyThread(PayMoney  payMoney){  //构造方法

            this.payMoney=payMoney;
        }
        public  void  run(){
            // aliNotify这里面完成所有逻辑
            System.out.println("新线程开启成功---------------------------------------");
            alipayNotifyService.aliNotify(this.payMoney);//这个是获得通知后进一步处理业务
            // System.out.print(moneySource.getMoneynum());
        }
    }
    /*alipay 支付异步通知  http://www.putaohuzhu.cn/glove/alipay/notify.do */

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value="/notify.do",method= RequestMethod.POST)

    public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {
        response.setContentType("text/plain; charset=UT");
        PrintWriter out = response.getWriter();
        //  获取支付宝POST过来反馈信息
        //logger.debug("支付宝异步回调");
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                //logger.debug(valueStr);
            }
            // 乱码解决，这段代码在出现乱码时使用。
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }


        boolean flag = AlipaySignature.rsaCheckV1(params,
                AlipayConfig.alipay_public_key,
                AlipayConfig.charset,
                AlipayConfig.sign_type);

         if (flag) {
            if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
                //trade_status
                String trade_status="TRADE_SUCCESS"; //交易状态号
                //notify_timeStr 通知时间
                String  notify_timeStr=params.get("notify_time");
                //notify_time
                //Date notify_time=new Date();
                //app_id
                String  app_id=params.get("app_id");
                // 订单金额
                String total_amount = params.get("total_amount");
                // 商户订单号
                String out_trade_no = params.get("out_trade_no");
                // 支付宝交易号
                String trade_no = params.get("trade_no");
                // 互助类别
                String  categorytype=params.get("subject");
                //userUUID
                String  userUUID=params.get("body");
                //passback_params 包含accountUUID和userName
                String  passback_params=params.get("passback_params");
                //seller_id
                String  seller_id=params.get("seller_id");
                // 买家支付宝用户号
                String buyer_id = params.get("buyer_id");

                // 交易创建时间 格式为yyyy-MM-dd HH:mm:ss
                String gmt_create = params.get("gmt_create");

                PayMoney  payMoney=new PayMoney();
                payMoney.setTradeStatus(trade_status);
                payMoney.setNotifyTime(new Date());
                payMoney.setAppId(app_id);
                payMoney.setTotalAmount(total_amount); //总金额
                payMoney.setOutTradeNo(out_trade_no); //本地生成的订单
                payMoney.setTradeNo(trade_no); //支付宝自己生成的交易单
                payMoney.setCategorytype(categorytype); //互助的类别
                payMoney.setUseruuid(userUUID); //用户的uuid
                payMoney.setPassbackParams(passback_params);
                //payMoney.setAccountuuid(accountUUID); //
                payMoney.setSellerId(seller_id); //卖家的id
                payMoney.setBuyerId(buyer_id); //买家的支付宝账号

                //此处应该先查询订单是否处理过，如果处理过，不开线程
                //用线程的方法更新,先查询数据库确认是否有此次交易，校验通过后，更新相应的数据库表
                try{
                    AlipayNotifyThread  alipayNotifyThread=new AlipayNotifyThread(payMoney);
                    alipayNotifyThread.start();
                }catch (Exception e){
                    out.print("failure");
                    out.close();
                }
                out.print("success");
                out.close();


                //logger.debug("-----支付宝异步通知成功----");
            } else {
                //logger.debug("-----支付宝异步通知，订单未成功付款----");
                out.print("failure");
                out.close();
            }
        }
        //logger.debug("-----支付宝异步通知，订单验证错误----");
        out.print("failure");
        out.close();

    } //notify
}
