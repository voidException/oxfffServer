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
    <link rel="stylesheet" href="<%=contextPath%>/resources/putaohelp/css/index.css">
    <%--<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>--%>
    <title>首页用来测试</title>
</head>

<body>
<div class="header" style="height:50px">
    <div class="headertxt">葡萄互助</div>
    <div class="headertxt">葡萄众筹</div>
    <div class="headertxt">欢迎您！<a href="/glove/admin/login.do">登录</a></div>
</div>
<!---这个是中间部分-->
<div class="middle">
    <div class="sidebar">
        <ul class="">
            <li>
                <a href="#">用户管理</a>
                <ul>
                    <li><a target="Conframe" href="https://www.baidu.com/">测试外部链接</a></li>
                    <li><a target="Conframe"  href="/glove/user/putEmail.do">测试Servlet跳转</a></li>
                    <li><a target="Conframe" href="<%=basePath%>resources/putaohelp/test.html">本地test.html</a></li>
                    <li><a target="Conframe" href="gon.html">分离的链接</a></li>
                </ul>
            </li>
        </ul>
        <ul class="">
            <li>
                <a href="#">资金管理</a>
                <ul>
                    <li><a target="Conframe" href="https://www.baidu.com/">测试外部链接</a></li>
                    <li><a target="Conframe"  href="/glove/user/putEmail.do">测试Servlet跳转</a></li>
                    <li><a target="Conframe" href="<%=basePath%>resources/putaohelp/test.html">本地test.html</a></li>
                    <li><a target="Conframe" href="gon.html">分离的链接</a></li>
                </ul>
            </li>
        </ul>
        <ul class="">
            <li>
                <a href="#">红包管理</a>
                <ul>
                    <li><a target="Conframe" href="https://www.baidu.com/">测试外部链接</a></li>
                    <li><a target="Conframe"  href="/glove/user/putEmail.do">测试Servlet跳转</a></li>
                    <li><a target="Conframe" href="<%=basePath%>resources/test.html">本地test.html</a></li>
                    <li><a target="Conframe" href="gon.html">分离的链接</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="content">
        <iframe name="Conframe" id="Conframe" src="<%=basePath%>resources/test.html"
                class="page-ifream"
                frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no"
                width="100%" height="700">
        </iframe>
    </div>
</div>

</body>
</html>