<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String contextPath = request.getContextPath();
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>

    <title>首页用来测试</title>
</head>

<body style=" background-color: #1ab394;">
<div class="header" style="display: flex;justify-content: center;flex-direction: column;
                align-items: center; height: 400px;width: 100%; color: #ffffff;">
    <div class="headertxt" style="font-size: 30px">欢迎您！</div>
    <div><a  style="color: #000; text-decoration:none;font-size: 30px" href="/glove/admin/login.do">登录</a></div>
</div>

</body>
</html>