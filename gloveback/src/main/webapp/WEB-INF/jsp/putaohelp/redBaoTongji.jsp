<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>红包金额统计</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>
        .contain{
            display: flex;
            flex-direction: row;
            justify-content: space-around;

        }
        .item{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 160px;
            width: 160px;
            background-color:#F8742E;
            color: #ffffff;
        }
        .kongxi{
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div id="redBaoTongji">
<div class="contain">
    <div class="item">
        <div class="kongxi">未激活红包金额</div>
        <div>1000（元）</div>
    </div>
    <div class="item">
        <div class="kongxi">已激活红包金额</div>
        <div>1000（元）</div>
    </div>
    <div class="item">
        <div class="kongxi">已使用红包金额</div>
        <div>1000（元）</div>
    </div>
</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/redBaoTongji.js"></script>
</body>
</html>
