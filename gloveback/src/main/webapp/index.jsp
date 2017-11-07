<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String contextPath = request.getContextPath();
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
<body id="root">
<div style="display: flex;flex-direction: column">
	<div style="display: flex;flex-direction: row">
		<div style="width: 40%;height: 100px">
			<div style="font-size: larger;color: red">本地一级页面</div>
			<a href="/glove/path/pages/header2.do">公共头部</a><br/>
			<a href="/glove/path/pages/jianduchu.do">监督处</a><br/>
			<a href="/glove/path/pages/mobileMain.do">公众号首页</a><br/>
		</div>
		<div style="width: 40%;height: 100px">
			<div style="font-size: larger;color: red">远程一级页面</div>
			<a href="http://geilove.org/glove/path/pages/jianduchu.do">监督处</a><br/>
			<a href="http://geilove.org/glove/path/pages/mobileWo.do">公众号我的</a><br/>
		</div>
	</div>
	<div style="display: flex;flex-direction: row">
		<div style="width: 40%;height: 500px">
			<div style="font-size: larger;color: red">本地二级页面</div>
			<a href="/glove/path/pages/aboutus.do?code=021zmCGf2HGaCC0BpKFf26SKGf2zmCGo&state=20170415">关于我们</a><br/>

			<a href="/glove/path/pages/helpApp.do">资助我们</a><br/>

			<a href="/glove/path/pages/helpAixinshe.do">赞助爱心社</a><br/>

			<a href="/glove/path/pages/shareTotimeline.do?userUUID=94111BD33D3F474590C535C0BE24905B&tweetUUID=5eedb509-f9d5-41eb-acc5-e51d25275607&cashUUID=5fedb509-f9d5-41eb-acc5-e51d25275607">分享到朋友圈</a><br/>

			<a href="/glove/path/pages/loginRegister.do">登录PC</a><br/>

			<a href="/glove/path/pages/login.do">公众号登录</a><br/>

			<a href="/glove/path/pages/register.do">公众号注册</a><br/>

			<a href="/glove/wechatpay/toPay/20170415/code.do">点我去支付</a><br/>

			<a href="/glove/path/pages/helpTweetList.do?helpType=dabing">大病求助</a><br/>

			<a href="/glove/path/pages/helpTweetList.do?helpType=shangxue">资助贫困生</a><br/>

			<a href="/glove/path/pages/helpTweetList.do?helpType=college">支持母校</a><br/>

			<a href="/glove/path/pages/userSelfHelp.do">我的求助</a><br/>

			<a href="/glove/photo/setting.do">设置</a><br/>

			<a href="/glove/profile/goAddProfile.do">完善资料</a><br/>

			<a href="/glove/user/resetPassword.do">修改密码</a><br/>

			<a href="/glove/user/putEmail.do">找回密码（输入邮箱）</a><br/>

			<a href="/glove/publishInfo/upload/addman.do">发布求助信息</a><br/>

			<a href="/glove/path/pages/aboutwe.do">关于我们公众号版本</a><br/>

			<a href="/glove/user/H0Am8jdfLzK3ChuW78NnaQ==/findPasswordUrl.do">找回密码（发送到用户邮箱的超链接）</a><br/>

			<a href="/glove/path/pages/faq.do">常见问题</a><br/>

			<a href="/glove/pages/feedback.do">意见反馈</a><br/>
		</div>
		<div style="width: 40%;height: 500px">
			<div style="font-size: larger;color: red">远程二级页面</div>
			<a href="http://geilove.org/glove/wechatpay/toPay/20170415/code.do">点我去支付</a><br/>
		</div>
	</div>
</div>


</body>
</html>