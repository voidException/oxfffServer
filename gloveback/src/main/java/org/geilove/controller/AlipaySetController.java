package org.geilove.controller;
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
import org.geilove.requestParam.AlipayOrderUser;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 支付宝批量支付---一次性加入多个互助人员
 */
@Controller
@RequestMapping("/alipay")
public class AlipaySetController {
    @Resource
    private AlipayService alipayService;

    @Resource
    private RegisterLoginService rlService;

    /*alipay 支付，批量加入预付订单生成*/
    @RequestMapping(value = "/getOrderSet.do",method=RequestMethod.POST)
    @ResponseBody
    public Object  getOrderSet(@RequestBody AlipayOrderSetParam alipayOrderSetParam )throws AlipayApiException, IOException{
        System.out.print("aaa");
        AlipayOrderRsp alipayOrderRsp =new AlipayOrderRsp();
        if (alipayOrderSetParam==null){
            alipayOrderRsp.setRetcode(2001);
            alipayOrderRsp.setMsg("请求参数为空");
        }
        String  body=alipayOrderSetParam.getUseruuid(); //注册用户的userUUID
        String  subject=alipayOrderSetParam.getCategorytype(); //参与的互助类型
        String  out_trade_no=getOutTradeNo();           // 商户订单号，本地生成 payMoneyUUID
        List<AlipayOrderUser> alipayOrderUserList=alipayOrderSetParam.getAlipayorderuserlist();
        String  total_amount="" ;// 这个需要计算出来
        String  seller_id="2088911776278734";//合作者账号，PID，非必须
        String  passback_params="multy"; //这个代表多用户充值

        String token=alipayOrderSetParam.getToken();
        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32);
        Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        // 校验通过
        if(!userPassword.equals(passwdTrue)){
            alipayOrderRsp.setRetcode(2001);
            alipayOrderRsp.setMsg("用户密码不对，非法");
            return alipayOrderRsp;
        }
        // 0.计算总的金额
        BigDecimal totalMoney=new BigDecimal(0);
        for( AlipayOrderUser alipayOrderUser: alipayOrderUserList){
             String singleMoneyStr=alipayOrderUser.getMoney();
             BigDecimal singleMoneyBd=new BigDecimal(singleMoneyStr);
             totalMoney.add(singleMoneyBd);
        }
        total_amount=totalMoney.toString(); //
        PayMoney payMoney=new PayMoney();
        payMoney.setTradeStatus("WAIT_BUYER_PAY"); //等待商家付款
        payMoney.setUseruuid(body); //userUUID
       //payMoney.setAccountuuid(accountUUID); //被充值用户的身份证号
        payMoney.setCategorytype(subject); //互助类型
        payMoney.setOutTradeNo(out_trade_no); //商户订单号
        payMoney.setTotalAmount(total_amount); //充值的金额
        payMoney.setSellerId(seller_id); //卖家支付宝用户号2088开头

        // 1. 开启新线程 操作 缴纳充值记录表，

        // 2.生成预付订单并返回，这个回调地址是不是可以用不同的？
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
        return  alipayOrderRsp;
    }

    //辅助方法，订单号
    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        key="putao"+key;
        return key;
    }


}
