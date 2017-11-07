<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>设置</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/setting.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/index.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body  style="background-color: #E1E5E7">
<div id="setBody">
    <div  style=" background-color: #00BB3B;  display: flex;flex-direction: row;align-items: center;justify-content: space-between;border-bottom: 1px solid #00BB3B;width: 100%  ">
        <a href="/glove/path/pages/mobileWo.do" style="width: 40px; margin-left: 10px;text-decoration: none;font-size: 18px;color: white">首页</a>
        <img src="http://onejf30n8.bkt.clouddn.com/gongzhongicon108x108.png"  style="width: 30px;height: 30px;border-radius: 15px"/>
        <div style="width: 40px"></div>
    </div>
    <form  class="photo"   method="post" action="jspupload.do"  enctype="multipart/form-data" class="form-horizontal" role="form">
        <input  id="token"  name="token" type="text" style="display: none" value="e10adc3949ba59abbe56e057f20f883e1">
        <div  class="upload">
            <div style="margin-left: 10px">
                <div style="margin-bottom: 3px">上传头像</div>
                <input type="file"  name="photo" value="浏览"/>
            </div>
        </div>
        <div style="width: 70px">
            <button  class="button" type="submit">提交</button>
        </div>
    </form>
    <div class="publishWrapper">
        <img src="http://onejf30n8.bkt.clouddn.com/gongzhongwanShanprofile.png" style="width: 24px;height: 24px;border-radius: 12px"/>
        <div>	<a href="/glove/profile/goAddProfile.do">完善资料</a></div>
    </div>
    <div class="publishWrapper">
        <img src="http://onejf30n8.bkt.clouddn.com/gongzhongresetPasswd.png" style="width: 24px;height: 24px;border-radius: 12px"/>
        <div><a href="/glove/user/resetPassword.do">修改密码</a></div>
    </div>
    <div id="setting" class="loginout">
        <div class="dologinOut"  v-on:click="logOut">退出</div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/setting.js"></script>

</html>
