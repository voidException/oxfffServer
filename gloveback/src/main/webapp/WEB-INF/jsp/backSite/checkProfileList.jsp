<%--
  Created by IntelliJ IDEA.
  User: mfhj-dz-001-424
  Date: 17/2/18
  Time: 下午8:04
  登录后跳转到此页面，所需要的初始数据，由登录页面存入cookie或者本地数据库中，在这个页面的获取
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
//    String path = request.getContextPath();
//    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
    <title>这个是待审核列表页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/checkProfileList.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body>
    <%--这里是头部--%>
    <nav style="display: flex;flex-direction: row;align-items:center;justify-content: space-around;height: 50px;background: #F5F6F7;position: fixed;left: 0px;top: 0px;width: 100%">
        <img src="<%=contextPath%>/resources/image/xiong.png" style="height: 30px;width: 30px">
        <div>
            <a href="#" style="margin-right: 20px">求助人员信息列表</a>
            <a href="#">添加社团成员</a>
            <input  style="height: 35px;width: 150px"/>
        </div>

        <a href="#">我的资料</a>
    </nav>
    <div style="height: 60px"></div>
    <%--这里是主题，整体三七分左右布局--%>
    <div style="display: flex;flex-direction: row;background: #D4D5E0;">
        <div style="position: fixed;left: 0px;top: 50px; margin-left:30px;display: flex;flex-direction:column;align-items: flex-start;justify-content: flex-start; width: 13%;">
            <div style="height: 32px;margin-top: 100px">待审核</div>
            <div style="height: 32px">线上初审通过</div>
            <div style="height: 32px">线下实地调查通过</div>
            <div style="height: 32px">已结束</div>
        </div>
        <div style="display: flex;width: 70%;background: #2FAC4C;height: 3000px;margin-left: 15%">

        </div>
        <div style="display: flex;flex-direction: row;justify-content: center;position: fixed;left: 47%;bottom: 15px;">
            <div style="margin-right: 20px">上一页</div>
            <div>下一页</div>
        </div>
    </div>

</body>
</html>
