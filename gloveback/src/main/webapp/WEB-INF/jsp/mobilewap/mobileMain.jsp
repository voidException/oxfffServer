
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
    <title>主页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/mobileWo.css">

    <link rel="stylesheet" href="<%=contextPath%>/resources/css/mobileMain.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body style="background-color: #E1E5E7">
    <div style="display: block; background-color: #E5F8E5;">
        <div style="justify-content: center;align-items: center;flex-direction: column;height: 120px;border-radius: 20px;width: 100%">
            <div style="color:#4EB160;justify-content: center;align-items: center;flex-direction: row;display: flex;margin-left: 70px">越过，</div>
            <div style="color:#4EB160 ;justify-content: center;align-items: center;flex-direction: row;display: flex;margin-left: 40px">千山万水</div>
            <div style="color:#4EB160 ;justify-content: center;align-items: center;flex-direction: row;display: flex;margin-left: 56px">连接每一座城市的</div>
            <div style="color:#4EB160 ;justify-content: center;align-items: center;flex-direction: row;display: flex;margin-left: -10px">每一加爱心社——</div>
            <div style="color:#4EB160 ;justify-content: center;align-items: center;flex-direction: row;display: flex;margin-left: 20px">只为温暖世界的每一处角落。</div>
        </div>
        <img src="http://onejf30n8.bkt.clouddn.com/gongzhongearth.png"  style="position: absolute;left: 15px;top: 25px;width: 70px;height: 70px;border-radius: 25px"/>
        <img src="http://onejf30n8.bkt.clouddn.com/gongzhongwater.png" style="position: absolute;right: 15px;top: 15px; width: 50px;height: 50px">
    </div>
    <div style=" display: block; margin: 0px;background-color: #FFFFFF;border-radius: 8px">
        <%--<div id="openId" style="display: none">${openId}</div>--%>
        <div id="openId" style="display: none">sdkkdslald</div>

        <div class="IpublishWrapper" style="margin-top: 30px">
            <img src="http://onejf30n8.bkt.clouddn.com/gongzhongcollege.png"  style="width: 24px;height: 24px;border-radius: 12px"/>
            <a href="http://localhost:8080/glove/path/pages/helpTweetList.do?helpType=college">支持母校</a>
        </div>
        <div class="IpublishWrapper">
            <img src="http://onejf30n8.bkt.clouddn.com/gongzhongpinkun.png"  style="width: 20px;height: 20px;border-radius: 10px"/>
            <a href="http://localhost:8080/glove/path/pages/helpTweetList.do?helpType=shangxue">资助贫困生</a>
        </div>

        <div class="IpublishWrapper">
            <img src="http://onejf30n8.bkt.clouddn.com/gongzhongbing.png"  style="width: 20px;height: 20px;border-radius: 10px"/>
            <a href="http://localhost:8080/glove/path/pages/helpTweetList.do?helpType=dabing">大病救急</a>
        </div>

    </div>

<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/mobileMain.js"></script>
</body>
</html>

















