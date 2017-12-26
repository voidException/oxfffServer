<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>用户列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/putaohelp/css/shenhelist.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
</head>
<body>
<div class="wrapper">
<div class="itemDetail">
    <c:forEach items="${data}" var="arr">
        <div  class="item">

            <div>
                <div class="zoo">
                    <div>姓名：</div>
                    <div><c:out value="${arr.usernickname}"></c:out></div>
                </div>
                <div class="zoo">
                    <div>手机号：</div>
                    <div><c:out  value="${arr.userphone}"></c:out></div>
                </div>

                <div class="zoo">
                    <div>注册时间：</div>
                    <div><fmt:formatDate value="${arr.registerdate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                </div>
            </div>
            <a target="accountlist" href="/glove/grapeAdmin/accountlist.do?useruuid=<c:out value="${arr.useruuid}"></c:out>" >详情>></a>
        </div>
    </c:forEach>
</div>
<iframe  class="page-ifream" name="accountlist" id="accountlist"
         src="<%=contextPath%>/resources/putaohelp/html/test.html"
         frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no"
         width="100%" height="2500">
</iframe>
</div>
</body>
<style>
    a{
        text-decoration: none;
        color: #00BB3B;
    }
    .waitCheck{
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        height: 30px;
        width: 100px;
        background-color: #0000FF;
        color: white;
        border-radius: 5px;
    }
    .wrapper{
        display: flex;
        flex-direction: row;
    }
    .temp{
        display: flex;
        flex-direction: column;
    }
    .item{
        display: flex;
        flex-direction: row;
        width: 350px;
        justify-content: space-between;
        padding-right: 20px;
        padding-left: 10px;
        border-bottom: #00BB3B 1px solid;
        //height: 30px;
    }
    .zoo{
        display: flex;
        flex-direction: row;
        height: 30px;
        justify-content: flex-start;
        align-items: center;
        margin-top: 1px;
        //background-color: #999999;
    }
    .itemDetail{
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
    }

</style>
</html>
