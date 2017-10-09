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
    <!-- 这里要求用户输入邮箱，是为了防止此链接发送到别人那里，也能修改，增强安全，此邮箱要与url中加密的邮箱进行比对-->
    <div id="findPasswd">
        <div style="display: none">${result}</div>
        <div class="checkTeam">
            <div class="checkTeamTxt">新密码</div>
            <input  id="originPass"  class="checkTeamInput"   name="newPass"  type="text" value="" placeholder=""/>
        </div>
        <div class="checkTeam">
            <div class="checkTeamTxt">重新输入</div>
            <input   id="againPass" class="checkTeamInput"   name="againPass"  type="text" value="" placeholder=""/>
        </div>
        <div class="commitWrapper">
            <div class="commit" v-on:click="findtPasswd">重置</div>
        </div>

        <div style="margin-top: 40px">
            <div style="font-size: 13px">提示:</div>
            <div style="font-size: 14px">修改密码后，立即删除邮箱中的此链接，不可外泄。</div>
        </div>
    </div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScriptPCback/findPasswd.js"></script>

</html>
