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
        .itemWrapper{

        }
        .item{
            display: flex;
            flex-direction: row;
            width: 400px;
            justify-content: flex-start;
        }
        .detail{
            cursor:pointer;
            color: red;
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
            <input id="account" name="identity" type="text" placeholder="请输入身份证号"/>
            <button   v-on:click="doAccountSearch">检索互助信息</button>
        </div>
    </div>
    <%--下面展示用户--%>
    <div id="left">
        <div class="itemWrapper">
            <div class="item">
                <div>用户ID：</div>
                <div id="useruuid">{{dataUser.useruuid}}</div>
            </div>
            <div class="item">
                <div>用户姓名：</div>
                <div>{{dataUser.usernickname}}</div>
            </div>
            <div class="item">
                <div>用户手机号：</div>
                <div>{{dataUser.userphone}}</div>
            </div>
        </div>
        <div v-on:click="getAccountList" class="detail"> 详情>> </div>
        <div>
            <template v-for="item in accountList">
                <div class="item">
                    <div>用户姓名：</div>
                    <div style="margin-right: 10px ;color: red">{{item.username}}</div>
                </div>
                <div class="item">
                    <div>用户身份证号：</div>
                    <div style="margin-right: 10px ;color: red">{{item.accountuuid}}</div>
                </div>
            </template>
        </div>
    </div>
    <!---根据身份证号直接搜索--->
    <div id="right">
        <template v-for="item in userAccount">
            <div class="item">
                <div>用户身份证号：</div>
                <div style="margin-right: 10px ;color: red">{{item.accountuuid}}</div>
            </div>
            <div class="item">
                <div>用户姓名：</div>
                <div style="margin-right: 10px ;color: red">{{item.username}}</div>
            </div>
        </template>
    </div>


</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/userSearch.js"></script>
</html>
