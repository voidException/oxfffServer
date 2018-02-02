
<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>admin 默认页面</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
    <style>
        .showItemWrap{
            display: flex;flex-direction: row; justify-content:flex-start;width: 610px;flex-wrap: wrap
        }
        .shoItem{
            width: 300px;
            height: 400px;
            background-color: red;
            margin: 1px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div style="display: flex;flex-direction: row;justify-content: center;align-items:flex-start;">
    <div  class="showItemWrap" >
        <div class="shoItem"></div>
        <div class="shoItem"></div>
        <div class="shoItem"></div>
        <div class="shoItem"></div>
    </div>

</div>
</body>
</html>
