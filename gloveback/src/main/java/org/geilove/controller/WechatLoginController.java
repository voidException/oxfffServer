package org.geilove.controller;

/**
 * 微信登录，获取access_token，获取用户信息
 */
import org.geilove.service.MoneySourceService;
import org.geilove.service.RegisterLoginService;
import org.geilove.utils.Configure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import net.sf.json.JSONObject;
@RequestMapping(value="/wechat")
@Controller
public class WechatLoginController {
    @Resource
    private RegisterLoginService registerLoginService;
    //@Autowired
   // private AccessTokenService accessTokenServiceImpl; //待实现

    @RequestMapping(value="/login.do",method= RequestMethod.POST)
    @ResponseBody
    public Object wechatlogin(HttpServletRequest request, HttpServletResponse response){

        String  state=request.getParameter("state"); //如何判断用户的请求是真实的？
        String  code=request.getParameter("code");
        String  appid=request.getParameter("appid");
        //
        String accessToken = null;
        String expiresIn = null;
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+Configure.putaohuzhuappid+"&secret="+Configure.putaohuzhuAppSecret+"&code="+code+"&grant_type=authorization_code";
        try {

            URL urlGet = new URL(url);

            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

            http.setRequestMethod("GET"); // 必须是get方式请求

            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            http.setDoOutput(true);

            http.setDoInput(true);

            http.connect();

            InputStream is = http.getInputStream();

            int size = is.available();

            byte[] jsonBytes = new byte[size];

            is.read(jsonBytes);

            String message = new String(jsonBytes, "UTF-8");

            JSONObject demoJson = JSONObject.fromObject(message);

            accessToken = demoJson.getString("access_token");
            expiresIn = demoJson.getString("expires_in");

            System.out.println("accessToken===="+accessToken);
            System.out.println("expiresIn==="+expiresIn);
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接下来用这个access_token 获取用户的信息，


        return 0;
    }
}
