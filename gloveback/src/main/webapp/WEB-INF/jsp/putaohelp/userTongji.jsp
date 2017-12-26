<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>用户信息统计</title>
    <style>
        .tongjiInfo{
            display: flex;
            flex-direction: row;
            justify-content: space-around;
            align-items: center;

        }
        tongji{

        }
        .tongjiBottom{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }

    </style>
</head>
<body>
<div class="tongjiInfo">
    <div class="tongji">
        <div>少儿互助计划</div>
        <div class="tongjiBottom">
            <div>${little}</div>
            <div>人</div>
        </div>
    </div>
    <div class="tongji">
        <div>中青年抗癌互助计划</div>
        <div class="tongjiBottom">
            <div>${young}</div>
            <div>人</div>
        </div>
    </div>
    <div class="tongji">
        <div>中老年抗癌互助计划</div>
        <div class="tongjiBottom">
            <div>${old}</div>
            <div>人</div>
        </div>
    </div>
    <div class="tongji">
        <div>综合意外互助计划</div>
        <div class="tongjiBottom">
            <div>${zonghe}</div>
            <div>人</div>
        </div>
    </div>

</div>






<%--
${message } <br/>
<p>书籍列表</p>
<c:forEach items="${bookList}" var="node">
    <c:out value="${node}"></c:out>
</c:forEach>
<br/>
<br/>
<c:forEach items="${map}" var="node">
    姓名：<c:out value="${node.key}"></c:out>
    住址：<c:out value="${node.value}"></c:out>
    <br/>
</c:forEach>
--%>
</body>
</html>
