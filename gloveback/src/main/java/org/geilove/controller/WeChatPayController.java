package org.geilove.controller;

import com.alibaba.fastjson.JSONObject;
import org.geilove.pojo.MoneySource;

import org.geilove.service.MoneySourceService;
import org.geilove.utils.WeChatUtils;
import org.geilove.utils.WxHttpClientUtils;
import org.geilove.utils.WxUrlUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import net.glxn.qrgen.javase.QRCode;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 微信支付相关功能 公众号支付  扫码支付
 */					   
@RequestMapping(value="/wechatpay")
@Controller
public class WeChatPayController {
	Logger logger= Logger.getLogger(this.getClass());
	@Resource
	private MoneySourceService moneySourceService;

	//内部类
	class NotifyThread  extends  Thread {

		public MoneySource  moneySource;

		public NotifyThread(MoneySource  moneySource){  //构造方法

			this.moneySource=moneySource;
		}
		public  void  run(){
			moneySourceService.wxNotify(this.moneySource);
			System.out.print(moneySource.getMoneynum());
		}
	}

	/**
	 * 跳转到微信支付
	 * 这里的orderId 可以看做是MoneySource的一个记录的UUID
	 */
	@RequestMapping(value="/toPay/{orderId}/code.do")
	public String toPay(@PathVariable String orderId, HttpServletRequest request, HttpServletResponse response){

		//这个应该是授权回调页面域名geilove.org/glove/ 下面的
		String orginUrl = "http://geilove.org/glove/authorize/userOpeniD/"+orderId+"/preOrder.do";
		String encodeUrl = URLEncoder.encode(orginUrl);
		String resultUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx13eeb70a6cad4d76&redirect_uri="+encodeUrl+"&response_type=code&scope=snsapi_base&state="+orderId+"#wechat_redirect";

		logger.info(resultUrl);
		System.out.println(orginUrl);

		return "redirect:"+resultUrl;
	}

	//只有这里显示支付成功了才是真的成功，才能入库
	//先到user表查看，是否关联了账号，如果否，就简单的记录下（新建表）
	//如果是，则直接进行一系列的表更新
	//当用户关联的时候就从这个新建表里查找，然后更新相应的表
	@RequestMapping(value="/wxNotifyUrl")
	@ResponseBody
	public String wxNotify(@RequestBody String wxData, HttpServletRequest request, HttpServletResponse response){
		logger.info("微信支付完成回调,返回的参数：");
		try {
			String return_code = "FAIL";
			String return_msg = "";
			Map<String, String> map = new HashMap<String, String>();

			MoneySource moneySource=new MoneySource(); //所有微信返回的数据应该放入这里面

			Document document = DocumentHelper.parseText(wxData);
			Element root = document.getRootElement();
			for(Iterator i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				map.put(element.getName(), element.getText());
			}
			if(WxUrlUtils.checkSign(map)){
				logger.info(JSONObject.toJSON(map));
				String appid=map.get("appid");
				String mch_id=map.get("mch_id");
				String device_info=map.get("device_info");
				String nonce_str=map.get("nonce_str");
				String sign=map.get("sign");
				String sign_type=map.get("sign_type");
				String result_code=map.get("result_code");
				String err_code=map.get("err_code");
				String err_code_des=map.get("err_code_des");
				String openid=map.get("openid");
				String is_subscribe=map.get("is_subscribe");
				String trade_type=map.get("trade_type");
				String bank_type=map.get("bank_type");
				String total_fee=(String)map.get("total_fee"); //需要转换为int 类型
			    String fee_type=map.get("fee_type");
				String transaction_id = map.get("transaction_id");//交易号
				String out_trade_no = map.get("out_trade_no");//订单号
				String attach=map.get("attach");
				String time_end=map.get("time_end");

				moneySource.setAppid(appid);
				moneySource.setMchId(mch_id);
				moneySource.setDeviceInfo(device_info);
				moneySource.setNonceStr(nonce_str);
				moneySource.setSign(sign);
				moneySource.setSignType(sign_type);
				moneySource.setResultCode(result_code);
				moneySource.setErrCode(err_code);
				moneySource.setErrCodeDes(err_code_des);
				moneySource.setOpenid(openid);
				moneySource.setIsSubscribe(is_subscribe);
				moneySource.setTradeType(trade_type);
				moneySource.setBankType(bank_type);
				moneySource.setTotalFee(Integer.parseInt(total_fee)); //这个强制转换不知道行不行
				moneySource.setBackupthree(total_fee); //这个得测试下，究竟是什么
				moneySource.setFeeType(fee_type);
				moneySource.setTransactionId(transaction_id);
				moneySource.setOutTradeNo(out_trade_no);
				moneySource.setAttach(attach);
				moneySource.setTimeEnd(time_end);

				//开启异步线程
				NotifyThread notifyThread=new NotifyThread(moneySource);
				notifyThread.start();

                //尽早返回数据给微信，避免多次重复通知
				if(map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")){
					logger.info("=============支付成功-更新业务状态start=============");
					return_code = "SUCCESS";
					return_msg = "ok";
					logger.info("=============支付成功-更新业务状态end=============");
				}
			} else {
				logger.error("支付成功返回数据，签名不合法");
			}
			String xml = "<xml><return_code>"+return_code+"</return_code><return_msg>"+return_msg+"</return_msg></xml>";
			response.getWriter().write(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/createWeChatOrder/{money}", method= RequestMethod.GET)
	public void createWeChatOrder(Model model, @PathVariable Float money, HttpServletResponse reponse){
		try {
			logger.info("提交微信订单");
				//保存订单
			String generateWxQRCode = WxUrlUtils.generateWxQRCode("2016083000001");
			ByteArrayOutputStream stream = QRCode.from(generateWxQRCode).withSize(160, 160).stream();
			ServletOutputStream outputStream = reponse.getOutputStream();
			stream.writeTo(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="wxPayBack")
	public ModelAndView wxPayBack(@RequestBody String wxData, HttpServletResponse response){
		logger.info("微信支付过程回调,返回的参数：");
		try {
			String return_code = "FAIL";
			String return_msg = "无效";
			String err_code_des = "此商品无效";
			String result_code = "FAIL";
			String prePayId = null;
			Map<String, String> map = new HashMap<String, String>();
			Document document = DocumentHelper.parseText(wxData);
			Element root = document.getRootElement();
			for(Iterator i = root.elementIterator(); i.hasNext();) {
			    Element element = (Element) i.next();
			    map.put(element.getName(), element.getText());
			}
            logger.info("payBack params:" + map);
			if(WxUrlUtils.checkSign(map)){
				logger.info("签名验证通过! 调用【统一下单API】提交支付交易");
				map.put("body", "测试body");
				map.put("total_fee", "1");//测试 1分
				prePayId = WxHttpClientUtils.getPrePayId(map);
				if(StringUtils.isNotBlank(prePayId)){
					return_code = "SUCCESS";
					result_code = "SUCCESS";
					return_msg = "";
					err_code_des = "";
				}
			} else {
				return_msg = "签名失败";
				err_code_des = "签名验证没有通过";
			}
			String xml = WxHttpClientUtils.getReturnXml(prePayId, return_code, return_msg, err_code_des, result_code);
			response.getWriter().write(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}