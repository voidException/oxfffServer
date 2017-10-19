<%@ page import="java.nio.file.Path" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <title>葡萄互助后台登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- CSS -->
    <link rel="stylesheet" href="<%=contextPath%>/resources/putaohelp/css/loginhelp.css">
</head>

<body>
<div class="container" style="width: 100%;height: 600px">

    <h3>葡萄互助后台管理登录</h3>
    <form action="${pageContext.request.contextPath}/loginadmin.do" method="post">
        <div>
            <input type="text" name="userName" class="username" placeholder="输入用户名" autocomplete="off"/>
        </div>
        <div>
            <input type="password" name="password" class="输入密码" placeholder="Password"/>
        </div>
        <button id="submit" type="submit">登录</button>
    </form>

</div>
</body>

</html>
