
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
	<title>关于我们</title>
</head>
<body id="root">
<!--这里是头部部分-->
<div class="header">
	<div class="headerLeft">
		<a class="logo"  href="http://www.geilove.org/" style="text-decoration: none">
			<img src="<%=contextPath%>/resources/image/logo.png" style="width: 40px;height: 40px;border-radius: 20px">
			<div style="color:#00BB3B;cursor: pointer">首页</div>
		</a>
		<a class="logo  appHelp" href="http://www.geilove.org/pages/appDownload" style="text-decoration: none" >
			<span>App下载</span>
		</a>
		<a href="http://www.geilove.org/pages/helpCenter" class="logo appHelp" style="text-decoration: none">
			<span>帮助中心</span>
		</a>
	</div>
</div>
<div id="app" style="margin-bottom: 100px">
	<div style="height: 40px;line-height: 60px;text-align: center;font-size: 18px">给爱科技是几个来自北京大学的研究生创立的的公司，希望为广大用户提供优质可靠的农产品！</div>
	<div style="display: flex;flex-direction: row;align-items: center;width: 70%;margin-left: 80px ;margin-top: 100px;">
		<img src="<%=contextPath%>/resources/image/aihaitao.jpg" style="width: 100px;height: 100px;border-radius: 50px" >
		<div style="margin-left: 30px">艾海涛，北京大学软件与微电子学院2015级研究生，北京给爱科技有限公司创始人CEO，主要负责产品设计，服务端开发以及其它事项</div>
	</div>
	<%--<div style="display: flex;flex-direction: row;align-items: center;width: 70%;margin-left: 80px ;margin-top: 30px;">--%>
		<%--<img src="<%=contextPath%>/resources/image/erweima.png" style="width: 100px;height: 100px;border-radius: 50px" >--%>
		<%--<div>胡玉，北京大学软件与微电子学院2015级研究生，主要负责android开发。</div>--%>
	<%--</div>--%>
	<div style="display: flex;flex-direction: row;align-items: center;width: 70%;margin-left: 80px ;margin-top: 30px;">
		<img src="<%=contextPath%>/resources/image/huanjian.jpg" style="width: 100px;height: 100px;border-radius: 50px" >
		<div style="margin-left: 30px">黄健，北京大学软件与微电子学院2015级研究生，主要负责iOS的开发。</div>
	</div>
</div>
<footer class="footer">
	<div>
		<img src="<%=contextPath%>/resources/image/logo.png" style="width: 100px;width: 100px;border-radius: 50px">
	</div>
	<div class="footerItem">
		<a href="http://www.geilove.org/pages/aboutus" style="text-decoration: none">关于我们</a>
		<a href="http://www.geilove.org/pages/helpCenter" style="text-decoration: none">帮助中心</a>
		<a href="http://www.geilove.org/pages/userProtocol" style="text-decoration: none">用户协议</a>
	</div>
	<div class="footerItem">
		<div>联系方式</div>
		<div>电话:010-80441882</div>
		<div>邮箱:geilovetech@126.com</div>
	</div>
	<div>
		<img src="<%=contextPath%>/resources/image/erweima.png" style="height: 100px;width: 100px">
	</div>
</footer>
<div class="bottom">
	© 2017 给爱网 - 北京给爱科技有限公司   京ICP备17013747号
</div>
</body>
</html>



























