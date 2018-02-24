
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no" />
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
	<link rel="stylesheet" href="<%=contextPath%>/resources/css/navigation.css">
	<link rel="stylesheet" href="<%=contextPath%>/resources/css/indexPC.css">
	<link rel="stylesheet" href="<%=contextPath%>/resources/css/footer.css">
	<link rel="shortcut icon" href="http://onejf30n8.bkt.clouddn.com/logo16.png">
	<title>用于学习cookie，session，shiro</title>
</head>
<body>
<!--这里是头部部分-->
<div class="header">
	<div class="headerLeft">
		<a href="/glove/learn/goLoginPage.do">去登录界面</a><br/>
	</div>
</div>
</body>
</html>
