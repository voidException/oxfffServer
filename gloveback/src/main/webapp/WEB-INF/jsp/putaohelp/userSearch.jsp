<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>检索用户</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>

        .hhtop{
            display: flex;
            flex-direction: row;
            justify-content: space-around;

        }
        .seachUser{
            display: flex;
            flex-direction: row;

        }
    </style>
</head>
<body>
<div id="userSearch">
    <div class="hhtop">
        <div class="seachUser">
            <input id="phone" name="phone" type="text" placeholder="请输入手机号"/>
            <button v-on:click="doSearchUser">检索注册用户</button>
        </div>

        <div class="seachUser">
            <input id="identity" name="identity" type="text" placeholder="请输入身份证号"/>
            <button>检索注册用户</button>
        </div>
    </div>
    <%--下面展示用户--%>
    <div>
        <div>这里是注册用户</div>
        <div>这里加入互助的人</div>
    </div>


</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/userSearch.js"></script>
</html>
