<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>资金走势</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <script src="https://cdn.bootcss.com/echarts/4.0.2/echarts.min.js"></script>
    <style>
        .header{
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-items: center;
            height: 60px;
            width: 100%;
            background-color: #FAFAFA;
            border-bottom: #1f2d3d 1px solid;
        }
        .headerItemLeft{
            display: flex;
            flex-direction: row;
            width: 120px;
            height: 60px;
            justify-content: center;
            align-items: center;
            background-color: #ffffff;
            color: #000000;
            font-family:tahoma,arial,宋体;
            margin-left: 10px;
            cursor: pointer;
        }
        .headerItemRight{
            display: flex;
            flex-direction: row;
            width: 120px;
            height: 60px;
            justify-content: center;
            align-items: center;
            background-color: #2C3E51;
            color: #ffffff;
            font-family:tahoma,arial,宋体;
            cursor: pointer;
        }
        .mainWrapper{
            display: flex;
            flex-direction: row;
            justify-content: center;
            width: 100%;
            height: 700px;
            margin-top: 20px;
        }
        .main{
            margin-top: 30px;
            width: 90%;
            height:500px;
        }
    </style>

</head>
<body>
<div id="zijinZouShi">

    <div class="header">
        <div class="headerItemLeft"  @click="showDefault">首页</div>
        <div class="headerItemRight" @click="showDetailTab">详情</div>
    </div>
    <div id="default" class="mainWrapper" >
        <div id="main" class="main"></div>
    </div>
    <div class="mainWrapper">
        <div id="detail" class="main"></div>
    </div>
</div>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<script src="<%=contextPath%>/resources/putaohelp/js/zijinZouShi.js"></script>
</body>
</html>
