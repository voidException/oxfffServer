<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>输入邮箱</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/putEmail.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body style="background-color: #E1E5E7">
<div id="putEmial" style=" margin-top: 50px">
    <div class="checkTeam">
        <input  id="userEmail"  class="checkTeamInput"   name="newPass"  type="text" value="" placeholder="请输入注册邮箱"/>
        <div class="checkTeamTxt" v-on:click="putEmail">发送</div>
    </div>
</div>
</body>

<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/putEmail.js"></script>

</html>
