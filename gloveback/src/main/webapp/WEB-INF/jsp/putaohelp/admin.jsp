<%--
  葡萄互助后台管理系统主页
--%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String contextPath = request.getContextPath();
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>葡萄互助后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
    <link rel="stylesheet" href="<%=contextPath%>/resources/putaohelp/css/index.css">
</head>
<body>
<div class="header" style="height:50px;padding-left: 30px;padding-right: 30px">
    <div class="headertxt">葡萄互助</div>
    <div class="headertxt">葡萄众筹</div>
    <div id="userToken"  style="display: none">${userToken}</div>
    <div id="userName" class="headertxt">欢迎您！${userName } </div>
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
        <ul class="">
            <li>
                <a href="#">认证管理</a>
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
        <iframe name="Conframe" id="Conframe" src="<%=basePath%>resources/putaohelp/test.html"
                class="page-ifream"
                frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no"
                width="100%" height="700">
        </iframe>
    </div>
</div>
<%--<shiro:hasRole name="admin">--%>
    <%--这是admin角色登录：<shiro:principal></shiro:principal>--%>
<%--</shiro:hasRole>--%>
<%--<shiro:hasPermission name="user:create">--%>
    <%--有user:create权限信息--%>
<%--</shiro:hasPermission>--%>
<%--<br>--%>
<%--<div>登录成功</div>--%>



</body>
</html>
