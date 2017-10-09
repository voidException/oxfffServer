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
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/register.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body>

<!--这里是注册-->
<div  id="register" class="registerWrapper">
    <div class="Register">
        <div class="email">邮箱:</div>
        <input id="registerEmailInput"  type="email"  value=""  placeholder="邮箱"/>
    </div>
    <div class="Register">
        <div class="email">密码:</div>
        <input  id="registerPasswdInput"  value=""  placeholder="密码"/>
    </div>
    <div class="Register">
        <div class="email">昵称:</div>
        <input id="registerNickNameInput" placeholder="输入昵称"/>
    </div>
    <div class="Register">
        <div class="email">所在城市:</div>
        <input  id="registerCityInput" placeholder="输入所在的城市，如临沂市"/>
    </div>

    <div class="doRegister"    v-on:click="doregister" >注册</div>
</div>


</body>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/register.js"></script>
</html>
