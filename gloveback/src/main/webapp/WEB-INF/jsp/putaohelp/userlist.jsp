<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>用户列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/putaohelp/css/shenhelist.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>
        .page{
            display: flex;
            flex-direction: row;
            width: 260px;
            justify-content: space-around;
        }
        .pageUp{
            cursor: pointer;
            color: #0070FF;
        }
        .item{
            display: flex;
            flex-direction: row;
            width: 260px;
            justify-content: flex-start;
        }
        .itemWrapper{
            display: flex;
            flex-direction: row;
            border-bottom: #00BB3B  1px solid;
            border-right: #00BB3B  1px solid;
            width: 300px;
            padding: 10px;
        }
        #userlist{
            display: flex;
            flex-direction: row;
        }
    </style>
</head>
<body>
<div id="userlist">

    <div class="left">
        <div class="page">
            <div v-on:click="goUpPage"    class="pageUp">上一页</div>
            <div v-on:click="goNextPage"  class="pageUp">下一页</div>
        </div>
        <template  class="temp" v-for="item in data">
            <div class="itemWrapper">
                <div>
                    <div class="item">
                        <div>用户昵称：</div>
                        <div style="margin-right: 10px ;color: red">{{item.usernickname}}</div>
                    </div>
                    <div class="item">
                        <div>手机号：</div>
                        <div style="margin-right: 10px ;color: red">{{item.userphone}}</div>
                    </div>
                    <div class="item">
                        <div>注册时间：</div>
                        <div style="margin-right: 10px ;color: red">{{item.registerdate}}</div>
                    </div>
                </div>
                <div   v-on:click="getAccounts"  v-bind:data-uuid="item.useruuid"    style="cursor: pointer;color: #00BB3B">成员</div>
            </div>
        </template>
    </div>
    <div class="right">
        <div class="page">
            <div v-on:click="goUpPageAccount"    class="pageUp">上一页</div>
            <div v-on:click="goNextPageAccount"  class="pageUp">下一页</div>
        </div>
        <template  class="temp" v-for="item in dataAccount">
            <div class="itemWrapper">
                <div>
                    <div class="item">
                        <div>用户昵称：</div>
                        <div style="margin-right: 10px ;color: red">{{item.username}}</div>
                    </div>
                    <div class="item">
                        <div>手机号：</div>
                        <div style="margin-right: 10px ;color: red">{{item.categorytype}}</div>
                    </div>
            </div>
        </template>
    </div>

</div>
</body>
<script src="<%=contextPath%>/resources/putaohelp/js/userlist.js"></script>
</html>
