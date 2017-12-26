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
    //米粒下的2017122101030573
    //public static String app_id = "2017122101030573";


    // 商户的私钥，在secret_key_tools_RSA_macosx文件夹下面，智顺下的
    public static String alipay_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCzPgWda/ablHUtPwRwpTYobWCLfbnR5NRlZMfG6YVZ0D1zIt6f4hgq2QA3iMfiUYvr1SIdyAWP1bMrjBElkY6z8sks0wPyrStcEcl5IXb9Xt6LonHGQ8oBafh9Nv2mtigEtdWzI/n1Xn6fWklyFJ4gcl1nlBHF1ZmyVF+Rv+hiR/rwyJaE4TqfHZcDL8PV4gfYEo1W0JiGhyPNYLglVgAXygJfYO/9un0Ob1fi79c+BamSQSwKFvXUBuvJPSlvkSIfhEeY3JWxrQlBlU9wuU+ZDFuBV6kPs04Wz1d0G1smKPK4sFVjmCb7g3AL0VrUNbdjXlSyUIWyCfKwU3v5SDrvAgMBAAECggEAB0tP2S8qkvxApH27BYaDpLOGyUZtiHAigJnQU3TvW09RIN1bZ/BAgYXhL2FemQQuiINc5w9bwpW0u+HwVLIOt536t9tCft6zNAT5SPemqvrwXYhXzvS1RMd2OEpDmkQxbrejoa2mH2kgCAYZlQ3nvXRJ/swUyRM/NPWgCgNgRpeLrI7FiUN7SnJ1yXNDi+njE60IWhosJsxUCncMNjZzvAHA88kh+rewrMrzTkDXJC7R/vNc3ztC+nlxiQO7FNLYm9/udvZ+g4sGdVXG+QPVU0scLs4r3U9PDfcQ045JoMgorDvv7C/cwqPQ4/XH0ycTEY1N2Jny2NVF/z4hKIozEQKBgQD3+5CkQ4MHvZyPAyaQ8hRA2rQHbS7eghJ2PH+XWTBIcAWZcrphCT6IlesvDqtey9b/qm7QknvnJdcpJYH5polmOxBBBadE2uYKzEGe8g1AaefCGMNoUOrwTg+1wyP7NPRLrcMahGduF+fWH2RfjSNeZafCVkxV0abnu6qLrzn2SQKBgQC5CYZ0mooDFe/4jf9P9QpAKyUendjMpFNitxL6Ftu3OjK0OSUX2SjKq20EVYydl1VUZvisbI3jB/2nVcNusz92IssmdAQfyRhkRFm1BrSqYnRqYewEg7vBxwJdAAyqnTyTbGRWBkcwP/0rjw+bGqOOZXDADw2+JtSc52bDr8THdwKBgG3CI7gAxasUaooF+/jlnL9DzbEHy1niumcoydRkPnGhW2kalZHDdLEhofG3DXSsxiFs/xVD8KFTZ1Mn7cpgwqnD9KLC7NEWDgGzfEvsmJ7tAQ4wxbSTNBleg6eQkolqW8JvS06eUeUxoYRtapC8OG5ckg77AVhurfAdaRPgX8g5AoGBAJjeDK3Ciqh39DP1I+trtpdCzbzYjSarz5PrSYtBXS2nsAICf+mO58tgCoWdI7mCL+W12FWirSnWDTG0geuvvIsdvZW8HTghS7xyV2Zd7t4gzqqg0Xr9Vh2GCybIBq7hjg0BbHdBYoizJLZaz3rFbEAVACw7g5AHxnkGF3ktejWLAoGALRe+trwKJ8BBUxCOnSOBgLBVKyuo8nSGE75cZcjnS+ld1alPThfejO33zr4uzYxc2QHM27bk5ayfoY3j/WeTkzTu2FvWg8n5Nv26PE0DpNfeqksc/weHQAlRU+U5BmGVlOvWSrUDyOPMuo+CwNFTaiBKsDtxCKd+0Z+KwY3g5tM=";
    //public static String alipay_private_key ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCYJnE3jjJFlwmqsh9IEYkGGFne5yNihGEwppJmsXsdBwOOFI7DKihvT1sVX2flJv5ep3nieVpGx6h2uNpQzwUvOqE0IPburMtNF7cfcWlnZEH0oXkUA4Jq3Nuj288qPhhxdYqnE7T96OXRjktFzGVJEPBCzg66aR37dbnQ2XGxDIT47upvthDXTaeP9ALWimvVSOgMaurQNdCMTkvq1H8GJFjc/ZqSpFAJK37pohEXnH1fR9we0d2Sff5trSUk+k8F4rhYxJkRwaP6BSqAASt85MAVxQ9kSzPvxPiR4rP2wYIVnj2Zcffcar+J6f07iqQJJjkh6mG+6ArA1x/NfZ7AgMBAAECggEAGgCQMWzEzemm1ClefpaVq3hzd0Nq8ug4ZbkeqKACwBncMu7H2UxdNp25HMfm8TURFHOZsgKZZNES7PX6G/e5jtCi0reOHBPCqlZqJjbQ/NGyOAiqjptC9XoxC+Y2LDgyWen8N2EHjoV/JdpoBacno9rhEW7ty7u7m6t+Zxt1/vu+AqGMJArKoAWnplv6uO0EEzqygAeDHqibSsCSkXisOn+G2Xij8vpz0ni+qYP00w9nsIciDMSgP+XhfxI0JW+yL/SRsw7ywl8Q8zVqlQ5ij25KlsUNkINIF6DgESO0Xj5QDU6DSq3rgHt63E7Q7ykOLQ3EW+O9J+CKV2hsz9QdeQKBgQD0ybnVP6e4VUFaBUmo83GvSdgdmv5eec+TPCmr+KqNP0l+6by0tsoOYfJYhuBYgXdR0Fj5aU/C8XHf0mhfkdSar70vCwFNPfuPsXwizIpC0uEYRkhQZfgDgf/PRly7vfuBj38An/q5ki100CcJVYUR00npojlVtUyfWk8uBhCJhwKBgQDLR8g7OXHFw8KTkR4ROZzvxmHJlVzKfLMsKAXRxrDxfyyIVA/tD3E+xlo/MqV44PF/v8qw2YKQRXm1BQEXNDenGBWDpgHSqPjCZvs1Rmg/qBA6+wKFMNbYGZidlQ/R9lE3GiDVTlpKHyjXQV8p2ddCjKzba4i6FxKZcoKXwzNYbQKBgG8A91NbrVvq1VrUIQYLd1cs3mL2gTkoddfnpWIFvpmUxGhX6Cnu5gvFoNdCOoQIGaqxF1fqyK+3O5Nq38fO4qFO5jRqAiob3HLZ4lgJdv08kbSnUm2a/+/fSLieyLieO2oSP15yLYlvX0ukhL8318MwlEjtNqDGOTveAR/2whMLAoGBAMpbJ5jFI1uh9tTdFdhByNbrXbbEx70OC3I+WSStLMEKPvNq8vuibrH11CqrKQu+qMjpzETmEVG6x2AKnj5SDcB5ehk2LQRejls/Fny/HNGZZKbB7o1Vw9kVCdZ8Z2Y0VD6M9PhB6iF1CTvWaHwJzX4C+aPnVREiHLLByfx3JWq1AoGBANcEGlEY8refZ547ExYYaDyrtF/jtYxkSswHPZ31HAShhtBnOCr7OQCWX9/1iHFbYWzth+kWrikGmWYaxCa/bkCkgxknfbr5N3qf988YSxj+Rw83chqHkDhCmghzlEVMtOGjNLQBvtq/g2FgA1t/2rIXOuPlnyQ8c27NrT7Qt79D";

    public  static  String  format="json";

    public static String charset = "UTF-8";

    // 支付宝的公钥，无需修改该值，智顺下的
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhT0ikGs8LX5iGEZB4GejBgcjxcqslHvovBlZp3K5iXSYmhCC5FpOfmlNAiwlA6n2MXgX526b/FxUfmoUtWVl/SYKBHdDgIhATblXVcsH2S+jBoCLWdL3t4m4a1g1QxhgaPlnI7rJDwnGhsKj0cKmwgAdI2DGjtDn7C9ZP4wLolwb0sxGO4xWHrEdBj49GkbSoH5rzaIR2vfhdz70L7tQlz9TNAUhXWtuSvnlCYnKVBO+K5CZUWwim3ppGQUFRk5+KfpW+bxSg0cmgJ5BRFm1SFjZ1kg6DTr2lD+ndHTOMyX4w+9bZvM1fneD4MIG87iIOWDyxaYhCpj7g4W5XwghwwIDAQAB";
    //public static String alipay_public_key  ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4L6w/J9g2xNPG+UFAu0Dipc9S9dS6LqmxPmAiWfeCE+To0qPZ4CWFkt+JBgXgk8Z/wIZUpuo7Z1qsOxhVmuGj7yhklrMSxs8pZ0uW7hThEdkeCjTmDXHgXUzLqtaiysmEDBZaXPwYaeT+xNIB9bEQlYDTb+GokgEwbwQAX7l3beANLHM/JNR7NG/C41uvZJ48ijR8pY87iit4rGxkhSVOdbG9TeEv1QLZNHiIPlD3KhWma+Uf1IaxeOOfz9trGarGOySHARj4NAfjHjAIGUYgUIaLR6lphh6hml9adDQHPL5d7DfSkKR30bRG0ctK1oP6WgzOl11F19pYFF2qwaNxQIDAQAB";
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
