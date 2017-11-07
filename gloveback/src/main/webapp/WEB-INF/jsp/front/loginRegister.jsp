<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />

    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/navigation.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/loginRegister.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/footer.css">
    <title>登录注册</title>

</head>
<body>
<!--PC版本的登录注册-->
<div class="header">
    <div class="headerLeft">
        <a class="logo"  href="http://www.geilove.org" style="text-decoration: none">
            <img src="<%=contextPath%>/resources/image/logo.png" style="width: 40px;height: 40px;border-radius: 20px">
            <div style="color:#00BB3B;cursor: pointer">首页</div>
        </a>
        <a class="logo  appHelp" href="/glove/pages/appDownload.do" style="text-decoration: none" >
            <span>App下载</span>
        </a>
        <a href="/glove/pages/helpCenter.do" class="logo appHelp" style="text-decoration: none">
            <span>帮助中心</span>
        </a>
    </div>
</div>
<div style="display: flex;align-items: center;justify-content: center;margin-bottom: 20px">
    <img src="<%=contextPath%>/resources/image/logo.png" style="width: 80px;height: 80px;border-radius: 40px">
</div>
<%--登录注册--%>
<div  id="loginRegister"    style="display: flex;flex-direction: row;justify-content: center;margin-bottom: 60px">
    <!--这里是登录-->
    <div class="loginWrapper">
        <div class="loginRegister">
            <div>邮箱:</div>l
            <input id="loginEmailInput" type="email" value="" placeholder="邮箱"/>
        </div>
        <div class="loginRegister">
            <div>密码:</div>
            <input  id="loginPasswdInput" type="password"   value=""  placeholder="密码"/>
        </div>
        <div class="doLogin"  v-on:click="dologin">登录</div>
    </div>
    <!--这里是注册-->
    <div class="registerWrapper">
        <div class="loginRegister">
            <div class="email">邮箱:</div>
            <input id="registerEmailInput"  type="email"  value=""  placeholder="邮箱"/>
        </div>
        <div class="loginRegister">
            <div class="email">密码:</div>
            <input  id="registerPasswdInput"  value=""  placeholder="密码"/>
        </div>
        <div class="loginRegister">
            <div class="email">昵称:</div>
            <input id="registerNickNameInput" placeholder="输入昵称"/>
        </div>
        <div class="loginRegister">
            <div class="email">所在城市:</div>
            <input  id="registerCityInput" placeholder="输入所在的城市，如临沂市"/>
        </div>

        <div class="doLogin"  v-on:click="doregister">注册</div>
    </div>
</div>
<%--登录注册--%>
<footer class="footer">
    <div>
        <img src="<%=contextPath%>/resources/image/logo.png" style="width: 100px;width: 100px;border-radius: 50px">
    </div>
    <div class="footerItem">
        <a href="/glove/pages/aboutus.do" style="text-decoration: none">关于我们</a>
        <a href="/glove/pages/helpCenter.do" style="text-decoration: none">帮助中心</a>
        <a href="/glove/pages/userProtocol.do" style="text-decoration: none">用户协议</a>
    </div>
    <div class="footerItem">
        <div>联系方式</div>
        <div>电话:010-80441882</div>
        <div>邮箱:geilovetech@126.com</div>
    </div>
    <div>
        <img src="<%=contextPath%>/resources/image/erweima.png" style="height: 100px;width: 100px">
    </div>
</footer>
<div class="bottom">
    © 2017 给爱网 - 北京给爱科技有限公司   京ICP备17013747号
</div>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScriptPCfront/loginRegister.js"></script>
</body>
</html>
