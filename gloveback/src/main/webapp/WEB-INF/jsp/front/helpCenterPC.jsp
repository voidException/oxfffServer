
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <style>

    </style>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/navigation.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/indexPC.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/footer.css">
    <link rel="shortcut icon" href="http://onejf30n8.bkt.clouddn.com/logo16.png">
    <title>用户帮助中心</title>
</head>
<body>
<!--这里是头部部分-->
<div class="header">
    <div class="headerLeft">
        <a class="logo"  href="http://www.geilove.org" style="text-decoration: none">
            <img src="http://onejf30n8.bkt.clouddn.com/gongzhonglogo.png" style="width: 40px;height: 40px;border-radius: 20px">
            <div style="color:#00BB3B;cursor: pointer">首页</div>
        </a>
        <a class="logo  appHelp" href="http://www.geilove.org/pages/appDownload" style="text-decoration: none" >
            <span>App下载</span>
        </a>
        <a href="http://www.geilove.org/pages/helpCenter" class="logo appHelp" style="text-decoration: none">
            <span>帮助中心</span>
        </a>
    </div>
</div>

<div style="display: flex;flex-direction: column;align-items:center;justify-content: center;height: 100%;width: 100%">
    <div style="width: 1000px;height: 400px;border: #8aff45 1px solid;">
        <div style="width: 100%;height: 40px;text-align: center;line-height: 40px;border-bottom:  #8aff45 1px solid">如有疑问，您可以通过以下方式联系到我们的人员:</div>
        <div style="margin-left: 50px;height: 40px;line-height: 40px">电话：15652649121</div>
        <div style="margin-left: 50px;height: 40px;line-height: 40px">Q Q： 625873066</div>
        <div style="margin-left: 50px;height: 40px;line-height: 40px">QQ群：769864231</div>
        <div style="margin-left: 50px;height: 40px;line-height: 40px">邮箱： geilovetech@126.com</div>
    </div>
</div>

<footer class="footer">
    <div>
        <img src="http://onejf30n8.bkt.clouddn.com/gongzhonglogo.png" style="width: 100px;width: 100px;border-radius: 50px">
    </div>
    <div class="footerItem">
        <a href="http://www.geilove.org/pages/aboutus" style="text-decoration: none">关于我们</a>
        <a href="http://www.geilove.org/pages/helpCenter" style="text-decoration: none">帮助中心</a>
        <a href="http://www.geilove.org/pages/userProtocol" style="text-decoration: none">用户协议</a>
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
</body>
</html>
