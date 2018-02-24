
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!-- 引入jstl库 -->
<html>
<head>
    <title>登录成功后的界面</title>
</head>
<body>

    <h1>${sessionScope.user.uname }登录成功!!!</h1>
    <h2>欢迎您,${sessionScope.uname }</h2>
    <br/>
    <a href="/glove/learn/getSession.do">获取Session</a><br/>
</body>
</html>
