
<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>公司统计</title>
    <style>
        .item{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            width: 200px;
            height: 200px;
            background-color: #00BB3B;
            color: #ffffff;
        }
        .contain{
            display: flex;
            flex-direction: row;
            justify-content: space-around;
        }
    </style>
</head>
<body>
<div class="contain">
    <div class="item">
      <div>公司总数：</div>
      <div>${companyTotal}</div>
    </div>
    <div class="item">
        <div>员工大病互助总人数：</div>
        <div>${staff}</div>
    </div>
    <div class="item">
        <div>员工意外互助总人数：</div>
        <div>${employee}</div>
    </div>
</div>
</body>
</html>
