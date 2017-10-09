package org.geilove.utilAlipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    public  static  String  gateway_url="https://openapi.alipay.com/gateway.do";  //固定值

    // APPID
    public static String app_id = "2017081908269445";

    // 商户的私钥，在secret_key_tools_RSA_macosx文件夹下面
    public static String alipay_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCzPgWda/ablHUtPwRwpTYobWCLfbnR5NRlZMfG6YVZ0D1zIt6f4hgq2QA3iMfiUYvr1SIdyAWP1bMrjBElkY6z8sks0wPyrStcEcl5IXb9Xt6LonHGQ8oBafh9Nv2mtigEtdWzI/n1Xn6fWklyFJ4gcl1nlBHF1ZmyVF+Rv+hiR/rwyJaE4TqfHZcDL8PV4gfYEo1W0JiGhyPNYLglVgAXygJfYO/9un0Ob1fi79c+BamSQSwKFvXUBuvJPSlvkSIfhEeY3JWxrQlBlU9wuU+ZDFuBV6kPs04Wz1d0G1smKPK4sFVjmCb7g3AL0VrUNbdjXlSyUIWyCfKwU3v5SDrvAgMBAAECggEAB0tP2S8qkvxApH27BYaDpLOGyUZtiHAigJnQU3TvW09RIN1bZ/BAgYXhL2FemQQuiINc5w9bwpW0u+HwVLIOt536t9tCft6zNAT5SPemqvrwXYhXzvS1RMd2OEpDmkQxbrejoa2mH2kgCAYZlQ3nvXRJ/swUyRM/NPWgCgNgRpeLrI7FiUN7SnJ1yXNDi+njE60IWhosJsxUCncMNjZzvAHA88kh+rewrMrzTkDXJC7R/vNc3ztC+nlxiQO7FNLYm9/udvZ+g4sGdVXG+QPVU0scLs4r3U9PDfcQ045JoMgorDvv7C/cwqPQ4/XH0ycTEY1N2Jny2NVF/z4hKIozEQKBgQD3+5CkQ4MHvZyPAyaQ8hRA2rQHbS7eghJ2PH+XWTBIcAWZcrphCT6IlesvDqtey9b/qm7QknvnJdcpJYH5polmOxBBBadE2uYKzEGe8g1AaefCGMNoUOrwTg+1wyP7NPRLrcMahGduF+fWH2RfjSNeZafCVkxV0abnu6qLrzn2SQKBgQC5CYZ0mooDFe/4jf9P9QpAKyUendjMpFNitxL6Ftu3OjK0OSUX2SjKq20EVYydl1VUZvisbI3jB/2nVcNusz92IssmdAQfyRhkRFm1BrSqYnRqYewEg7vBxwJdAAyqnTyTbGRWBkcwP/0rjw+bGqOOZXDADw2+JtSc52bDr8THdwKBgG3CI7gAxasUaooF+/jlnL9DzbEHy1niumcoydRkPnGhW2kalZHDdLEhofG3DXSsxiFs/xVD8KFTZ1Mn7cpgwqnD9KLC7NEWDgGzfEvsmJ7tAQ4wxbSTNBleg6eQkolqW8JvS06eUeUxoYRtapC8OG5ckg77AVhurfAdaRPgX8g5AoGBAJjeDK3Ciqh39DP1I+trtpdCzbzYjSarz5PrSYtBXS2nsAICf+mO58tgCoWdI7mCL+W12FWirSnWDTG0geuvvIsdvZW8HTghS7xyV2Zd7t4gzqqg0Xr9Vh2GCybIBq7hjg0BbHdBYoizJLZaz3rFbEAVACw7g5AHxnkGF3ktejWLAoGALRe+trwKJ8BBUxCOnSOBgLBVKyuo8nSGE75cZcjnS+ld1alPThfejO33zr4uzYxc2QHM27bk5ayfoY3j/WeTkzTu2FvWg8n5Nv26PE0DpNfeqksc/weHQAlRU+U5BmGVlOvWSrUDyOPMuo+CwNFTaiBKsDtxCKd+0Z+KwY3g5tM=";

    public  static  String  format="json";

    public static String charset = "UTF-8";

    // 支付宝的公钥，无需修改该值
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhT0ikGs8LX5iGEZB4GejBgcjxcqslHvovBlZp3K5iXSYmhCC5FpOfmlNAiwlA6n2MXgX526b/FxUfmoUtWVl/SYKBHdDgIhATblXVcsH2S+jBoCLWdL3t4m4a1g1QxhgaPlnI7rJDwnGhsKj0cKmwgAdI2DGjtDn7C9ZP4wLolwb0sxGO4xWHrEdBj49GkbSoH5rzaIR2vfhdz70L7tQlz9TNAUhXWtuSvnlCYnKVBO+K5CZUWwim3ppGQUFRk5+KfpW+bxSg0cmgJ5BRFm1SFjZ1kg6DTr2lD+ndHTOMyX4w+9bZvM1fneD4MIG87iIOWDyxaYhCpj7g4W5XwghwwIDAQAB";

    // 签名方式 不需修改
    public static String sign_type = "RSA2";

    //
    public static String product_code="QUICK_MSECURITY_PAY"; //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY

    //订单超时关闭时间
    public  static  String  timeout_express="90m";

    //回调通知地址
    public  static String  notify_url="http://www.putaohuzhu.cn/glove/alipay/notify.do";

    //日志输出地址
    public  static  String  log_path="/";


}
