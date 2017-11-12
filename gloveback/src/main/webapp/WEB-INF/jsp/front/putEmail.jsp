<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>找回密码</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/putEmail.css">
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
</head>
<body style="background-color: #E1E5E7;margin: 0px">
<div style="display: flex;flex-direction: row;align-items: center;justify-content: center;margin-top: 60px">
    <img src="<%=contextPath%>/resources/image/xiong.png" style="width: 100px;height: 100px;border-radius:50px ">
</div>
<div id="putEmial" style=" margin-top: 50px">

    <div style="display: flex;flex-direction: row;width: 100%;height: 40px;align-items: center;background-color:#ffffff">
        <div style="width: 20%;padding-left: 10px">手机号</div>
        <input  id="phone"  class="checkTeamInput"   name="newPass"  type="text" value="" placeholder="输入11位手机号"/>
    </div>

    <div style="display: flex; height: 40px;flex-direction: row;width: 100%;height: 44px;align-items: center;background-color:#ffffff">
        <div  style="width: 20%;padding-left: 10px">验证码</div>
        <input  id="verifycode"  class="checkTeamInput2"   name="newPass"  type="text" value="" placeholder="验证码"/>
        <button style="width: 20%; padding-right: 2px;background-color: #00BB3B ; border-radius: 4px;height: 40px " >
            获取验证码
        </button>
    </div>
    <div style="display: flex;flex-direction: row;width: 100%;height: 40px;align-items: center;background-color:#ffffff">
        <div style="width: 20%;padding-left: 10px">新密码</div>
        <input  id="userPassword" class="checkTeamInput" placeholder="新的密码"/>
    </div>
    <div style="display: flex;flex-direction: row;align-items: center;margin-top: 30px;width: 100%;height: 44px;justify-content: center">
        <button  v-on:click="doSubmit"  style="display: flex; font-size: 20px;color:white;flex-direction: row;align-items: center;justify-content: center;height: 44px;width: 100%;background-color: #00BB3B">
            提交
        </button>
    </div>

</div>
</body>

<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/putEmail.js"></script>

</html>
