<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>用户家人列表</title>
</head>
<body>

<div class="itemDetail">
    <c:forEach items="${data}" var="arr">
        <div  class="item">
            <div>
                <div class="zoo">
                    <div>用户的姓名：</div>
                    <c:out value="${arr.username}"></c:out>
                </div>
                <div class="zoo">
                    <div>加入的时间：</div>
                    <div><fmt:formatDate value="${arr.buildrelationdate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                </div>
                <div class="zoo">
                    <div>参与的计划：</div>
                    <div><c:out value="${arr.categorytype}"></c:out></div>
                </div>

                <div class="zoo">
                    <div>生效的时间：</div>
                    <div><fmt:formatDate value="${arr.effectivedate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                </div>

                <div class="zoo">
                    <div>当前的状态：</div>
                    <div><c:out value="${arr.nowstate}"></c:out></div>
                </div>
                <div class="zoo">
                    <div>账户余额：</div>
                    <div><c:out value="${arr.paytotalmoney}"></c:out></div>
                </div>

            </div>
        </div>
    </c:forEach>
</div>
</body>
<style>
    .item{
        display: flex;
        flex-direction: row;
        width: 350px;
        justify-content: space-between;
        padding-right: 20px;
        padding-left: 10px;
        border-bottom: #00BB3B 1px solid;
        border-left: red 5px solid;
        margin-left: 10px;
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

</style>
</html>
