package org.geilove.utils;

/**
 * @author haoz
 * 
 * @date 2016-8-30
 *
 */
public class Configure {

	//公众账号ID
	private static String appid = "wx13eeb70a6cad4d76"; //互助管家的
	//公众账号AppSecret
	private static String appSecret = "a751a981b4584205e609650f9a5a7058";  //互助管家的
	//API密钥
	private static String key = "qazwsxedcrfvtgbyhnujmikolp123456"; //互助管家
	//商户号
	private static String mch_id = "1444279102";   //互助管家的

	//葡萄互助的Appid
	public static String  putaohuzhuappid="wxbdcf30c9232401a4";
	//葡萄互助的AppSecret
	public static  String putaohuzhuAppSecret="68b8ef8f7c8e84e0d427e838d69a16c9";

	//Android支付AppID
	private static String androidAppID = "wcnmdwxndapishityy";
	//Android应用 AppSecret(应用密钥
	private static String androidAppSecret = "1326123jhkjsdkfalsdjflkk32jbbmsa";

	//Android商户号
	private static String android_mch_id = "8888888888";

	//androidkey
	private static String androidkey = "skfK3JL2K4323KLJjlkFLKLSKFL23K4L";
	
	//SuccessBackUrl
	private static String successBackUrl = "http://www.geilove.org/glove/wechatpay/wxNotifyUrl";//这个在统一下单处设置
	
	
	//AppSuccessBackUrl
	private static String successBackUrlApp = "http://www.fuckbaidu.com/xxxx/xxxx";
	
	//payBackUrl
    //private static String payBackUrl = "http://www.xxgj365.com/home/wechatpay/wxPayBack";

	protected static String getAppid() {
		return appid;
	}
	
	public static String getAndroidAppID() {
		return androidAppID;
	}

	protected static String getMch_id() {
		return mch_id;
	}

	protected static String getKey() {
		return key;
	}

	public static String getAppSecret() {
		return appSecret;
	}
	
	public static String getAndroidAppSecret() {
		return androidAppSecret;
	}

	public static String getSuccessBackUrl() {
		return successBackUrl;
	}

	public static String getAndroid_mch_id() {
		return android_mch_id;
	}

	public static String getAndroidkey() {
		return androidkey;
	}

	public static String getSuccessBackUrlApp() {
		return successBackUrlApp;
	}
	public static String getPutaohuzhuappid() {
		return putaohuzhuappid;
	}

	public static void setPutaohuzhuappid(String putaohuzhuappid) {
		Configure.putaohuzhuappid = putaohuzhuappid;
	}

	public static String getPutaohuzhuAppSecret() {
		return putaohuzhuAppSecret;
	}

	public static void setPutaohuzhuAppSecret(String putaohuzhuAppSecret) {
		Configure.putaohuzhuAppSecret = putaohuzhuAppSecret;
	}


}
