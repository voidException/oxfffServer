
<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>admin 默认页面</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>
        .showItemWrap{
            display: flex;flex-direction: row; justify-content:flex-start;width: 860px;flex-wrap: wrap
        }
        .shoItem{
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            align-items: center;
            width: 400px;
            height: 300px;
            background-color: #ed5466;
            margin: 10px;
            border-radius: 20px;
        }
        .innerItem{
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            font-size: 28px;
            color: #ffffff;
            font-family:tahoma,arial,宋体;
        }
    </style>
</head>
<body>
<div  id="defaultMain" style="display: flex;flex-direction: row;justify-content: center;align-items:flex-start;">
    <div  class="showItemWrap" >
        <div class="shoItem">
            <img src="<%=contextPath%>/resources/image/rena.png"  style="width: 80px;height: 80px">
            <div class="innerItem">
                <div>{{defaultMain.sumUser}}</div>
                <div>会员人数</div>
            </div>
        </div>
        <div class="shoItem">
            <img src="<%=contextPath%>/resources/image/huzhu.png"  style="width: 80px;height: 80px">
            <div class="innerItem">
                <div>6</div>
                <div>互助产品</div>
            </div>
        </div>
        <div class="shoItem">
            <img src="<%=contextPath%>/resources/image/qiandai.png"  style="width: 80px;height: 80px">
            <div class="innerItem">
                <div>{{defaultMain.remainMoney}}</div>
                <div>账户余额</div>
            </div>
        </div>
        <div class="shoItem">
            <img src="<%=contextPath%>/resources/image/renzhengde.png"  style="width: 80px;height: 80px">
            <div class="innerItem">
                <div>{{defaultMain.sumCompany}}</div>
                <div>实名认证</div>
            </div>
        </div>
    </div>

</div>
<script src="<%=contextPath%>/resources/putaohelp/js/defaultMain.js"></script>
</body>
</html>
