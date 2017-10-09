package org.geilove.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.geilove.pojo.WechatLog;
import org.geilove.service.WechatLogService;

import javax.annotation.Resource;

/**
 * @author haoz
 * 
 * @date 2016-8-30
 *
 */
public class WeChatUtils {

	@Resource
	private  static WechatLogService wechatLogService;
	//通过code获取openId
	public static String getOpenId(String code){
		if(StringUtils.isNotEmpty(code)){
			String appid = Configure.getAppid();
			String secret = Configure.getAppSecret();

            try{
				String result = HttpUtil.doGet(HttpConf.get(HttpConf.WECHAT_AUTH_TOKEN_URL)+"?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code", null, "UTF-8", true);
				if(StringUtils.isNotEmpty(result)){
					JSONObject json = JSONObject.parseObject(result);
					if(json.get("openid")!=null){
						return json.get("openid").toString();
					}
				}
			}catch (Exception e){
				System.out.println(e);
				WechatLog wechatLog=new WechatLog();
				wechatLog.setClassname("WeChatUtils");
				wechatLog.setLog(e.getMessage());
				wechatLogService.addlog(wechatLog);
			}
		}
		return "";
	}
	
}
