<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/putEmail.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/register.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div style="display: flex;flex-direction: row;align-items: center;justify-content: center;margin-top: 60px">
    <img src="<%=contextPath%>/resources/image/xiong.png" style="width: 100px;height: 100px;border-radius:50px ">
</div>

<!--这里是注册-->
<div  id="register" class="registerWrapper">

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
        <div style="width: 20%;padding-left: 10px">密码</div>
        <input  id="userPassword" class="checkTeamInput" placeholder="密码"/>
    </div>

    <div style="display: flex;flex-direction: row;width: 100%;height: 40px;align-items: center;background-color:#ffffff">
        <div style="width: 20%;padding-left: 10px">用户的昵称</div>
        <input  id="nickName" class="checkTeamInput" placeholder="昵称"/>
    </div>

    <div style="display: flex;flex-direction: row;width: 100%;height: 40px;align-items: center;background-color:#ffffff">
        <div style="width: 20%;padding-left: 10px">所在城市</div>
        <input  id="cityName" class="checkTeamInput" placeholder="输入所在的城市，如临沂市"/>
    </div>


    <div class="doRegister"    v-on:click="doregister" >注册</div>
</div>


</body>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/register.js"></script>
</html>
