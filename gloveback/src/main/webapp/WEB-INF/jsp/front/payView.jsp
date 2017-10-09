
<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<html>
<head>
   <title>包含有openId的payView页面</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
   <meta name="apple-mobile-web-app-capable" content="yes">
   <meta name="apple-mobile-web-app-status-bar-style" content="black">
   <meta name="format-detection" content="telephone=no" />
   <link rel="stylesheet" href="<%=contextPath%>/resources/css/payView.css">
   <%--<link rel="stylesheet" href="<%=contextPath%>/resources/css/modalCommonInput.css">--%>
   <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
   <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>

</head>
<body style="background-color:#F6F7F9;margin: 0px ">
<%--4种方式获取ModelAndView 传输来的数据--%>
<%--<div><%=request.getAttribute("orderId")%></div>--%>
<%--<div>${requestScope.openId}</div>--%>
<%--<div><c:out value="${orderId}" /></div>--%>
<div id="payView">
   <div style="display: none">
      <div id="orderId">${orderId}</div>
      <div id="openId">${openId}</div>
   </div>
   <div class="logo">
      <img class="logoImg" src="<%=contextPath%>/resources/image/xiong.png">
      <div>首页</div>
   </div>
   <div>
      <div class="backupMoney">
         <div style="font-size: 14px ;margin-left:20px;">支持金额</div>
         <input  id="moneyInput"   v-on:blur="losePoint()"   class="moneyInput" type="number" placeholder="输入金额" />
      </div>
      <div id="resdata" style="margin-left: 20px;font-size: 13px;margin-top: 20px;color:#3C3F41 ">
         <div>提示:</div>
         <div>支持的金额须为整数。</div>
         <div>如有需要，您将获得优先帮助。</div>
         <div>欢迎加入对应的监督小组。</div>
      </div>
      <div class="clickPay">
         <div>合计：<span id="moneyNum" style="color: red">0</span>元</div>
         <div class="doClick" v-on:click="getPrepayiD">点击支付</div>
      </div>
   </div>
   <!--这个"modal 初始时是要隐藏的"-->
   <div id="modal" class="modal">
      <div id="tips" class="tips">
        loading
      </div>
   </div>
</div>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/payView1.js"></script>

</body>
</html>















