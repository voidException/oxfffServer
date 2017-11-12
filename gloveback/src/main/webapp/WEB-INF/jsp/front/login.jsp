
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>

	<title>用户登录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no" />
	<link rel="stylesheet" href="<%=contextPath%>/resources/css/login.css">
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body id="root" style="margin: 0px">
<!--这里是登录-->
<form action="/glove/phone/login.do">
	<div  id="wechatLogin">
		<div class="loginWrapper">
			<div class="loginEmail">
				<div>手机号</div>
				<input id="emailInput" name="phone" type="phone" value="" placeholder="用户手机号"/>
			</div>
			<div class="loginPass">
				<div>密码</div>
				<input  id="passwordInput" name="userPass" type="password"   value=""  placeholder="数字字母组成的密码"/>
			</div>
			<div class="doLogin"   v-on:click="loginIn" >登录</div>
		</div>
		<div class="forget"><a href="/glove/user/putEmail.do">忘记密码?</a></div>

		<!-- 模态弹出框-->
		<!--这个"modal 初始时是要隐藏的"-->
		<div id="modalReport" class="modalReport"></div>
</div>
</form>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/login.js"></script>

</html>



























