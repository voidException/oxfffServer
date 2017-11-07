
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>

    <title>我</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/mobileWo.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/index.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body style="background-color: #E1E5E7">

<div id="mobileMainDiv" >
    <div>
        <%--2选择1--%>
        <div id="notLogin" class="userPart" style="display: block">
            <div class="logoWrapper">
                <img src="http://onejf30n8.bkt.clouddn.com/gongzhongicon108x108.png"  style="width: 60px;height: 60px;border-radius: 30px"/>
            </div>
            <div class="loginReg">
                <a class="login"  href="/glove/path/pages/register.do">注册</a>
                <a class="login"  href="/glove/path/pages/login.do">登录</a>
            </div>
        </div>

        <div   id="hasLogin" style=" display: none; margin: 10px;background-color: #FFFFFF;border-radius: 4px">
            <div class="userPhoto">
                <img src="http://onejf30n8.bkt.clouddn.com/gongzhongicon108x108.png"  style="width: 50px;height: 50px;border-radius: 25px"/>
                <div style="font-weight: bold;font-family: STHeiti Light ;margin-left: 5px">{{usernickname}}</div>
            </div>
            <div class="helpcommon">
                <div style="margin-left: 5px;font-size: 14px;color: #6f7a7e">我帮助</div>
                <div  style="font-weight: bold;font-family: STHeiti Light ;">{{userhelpsman}}<span style="font-size: 13px;font-weight: normal">人</span></div>
                <div  style="font-weight: bold;font-family: STHeiti Light ;">{{userdonate}}<span style="font-size: 13px;font-weight: normal">元</span></div>
            </div>
            <div class="helpcommon">
                <div style="margin-left: 5px;font-size: 14px;color: #6f7a7e">帮助我</div>
                <div style="font-weight: bold;font-family: STHeiti Light ;">{{amountaccept}}<span style="font-size: 13px;font-weight: normal">人</span></div>
                <div style="font-weight: bold;font-family: STHeiti Light ;">{{acceptmoney}}<span style="font-size: 13px;font-weight: normal">元</span></div>
            </div>
            <div class="IpublishWrapper">
                <img src="http://onejf30n8.bkt.clouddn.com/gongzhongmyhelp.png"  style="width: 24px;height: 24px;border-radius: 12px"/>
                <a href="/glove/path/pages/userSelfHelp.do">我的求助</a>
            </div>
            <div class="IpublishWrapper">
                <img src="http://onejf30n8.bkt.clouddn.com/gongzhongsetting.png"  style="width: 20px;height: 20px;border-radius: 10px"/>
                <a href="/glove/photo/setting.do">设置</a>
            </div>

        </div>
    </div>

    <div class="publishWrapper">
        <img src="http://onejf30n8.bkt.clouddn.com/gongzhongaddHelpMan.png"  style="width: 24px;height: 24px;border-radius: 12px"/>
        <a href="/glove/publishInfo/upload/addman.do">发布求助信息</a>
    </div>
    <div class="zizhu">
        <img src="http://onejf30n8.bkt.clouddn.com/gongzhonghelpMe.png"  style="width: 24px;height: 24px;border-radius: 12px;position: absolute;left: 20px"/>
        <div class="donate"><a href="/glove/path/pages/helpApp.do">资助我们</a></div>
        <div class="donatelove"><a href="/glove/path/pages/helpAixinshe.do">赞助爱心社</a></div>
    </div>
    <div class="aboutus">
        <img src="http://onejf30n8.bkt.clouddn.com/gongzhongaboutUs.png"  style="width: 24px;height: 24px;border-radius: 12px"/>
        <div><a href="/glove/path/pages/aboutwe.do">关于我们</a></div>
    </div>
    <div class="publishWrapper">
        <img src="http://onejf30n8.bkt.clouddn.com/gongzhongfaq.png"  style="width: 24px;height: 24px;border-radius: 12px"/>
        <div><a href="/glove/path/pages/faq.do">常见问题</a></div>
    </div>
</div>

<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/mobileWo.js"></script>
</body>
</html>

















