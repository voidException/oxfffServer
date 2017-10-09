<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>重置密码</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/resetPasswd.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body style="background-color: #E1E5E7">

<div id="resetPasswd">

    <div class="checkTeam">
        <div class="checkTeamTxt">原始密码</div>
        <input  id="originPass"  class="checkTeamInput"  name="originPass"  type="text" value="" placeholder="用户现密码"/>
    </div>
    <div class="checkTeam">
        <div class="checkTeamTxt">新密码</div>
        <input  id="newPass"  class="checkTeamInput"   name="newPass"  type="text" value="" placeholder=""/>
    </div>
    <div class="checkTeam">
        <div class="checkTeamTxt">重新输入</div>
        <input   id="againPass" class="checkTeamInput"   name="againPass"  type="text" value="" placeholder=""/>
    </div>
    <div class="commitWrapper">
        <div class="commit" v-on:click="resetPasswd">重置</div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/resetPasswd.js"></script>

</html>
