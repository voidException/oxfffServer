<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String contextPath = request.getContextPath();
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println(path);
	System.out.print(basePath);

%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
	<%--<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>--%>
	<title>首页用来测试</title>
</head>

<body>
<div class="p_header navbar navbar-lightblue navbar-fixed-top" role="navigation">
	<div class="row">
		<div class="col-md-3">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">某某市突发预警平台</a>
				<div class="navbar-text">asdasd</div>
			</div>
		</div>
		<div id="navMenu" class="col-md-6">
			<div>好好</div>
			<div>好好</div>
			<div>好好</div>
		</div>

		<div class="col-md-3">
			<div class="con_exit pull-right">
				欢迎您！<a href="#">退出</a>
			</div>
		</div>
	</div>
</div>
<div class="clearfix"></div>
<div class="page-container">
	<div class="page-sidebar">
		<ul class="nav nav-pills nav-stacked">
			<li>
				<a href="#"><i class="fa fa-adn"></i>Java <span class="arrow"></span></a>
				<ul>
					<li><a target="Conframe" href="https://www.baidu.com/">百度</a></li>
					<li><a target="Conframe"  href="/glove/user/putEmail.do">测试</a></li>
					<li><a target="Conframe" href="<%=basePath%>resources/test.html">test.html</a></li>
					<li><a target="Conframe" href="gongsi3.html">分离的链接</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<div class="page-content">
		<iframe name="Conframe" id="Conframe" src="<%=basePath%>resources/test.html" class="page-ifream" frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="100%"></iframe>
	</div>

	<div class="p_footer navbar navbar-lightblue navbar-fixed-bottom">
		<div class="navbar-text">asdasd</div>
	</div>
	<div class="winens winopen"></div>
</body>
</html>