<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>权限模块用户</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/putaohelp/css/shenhelist.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
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
        .headerBody{
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-items: center;
            height: 60px;
            width: 100%;
            background-color: #00BA97;
        }
        .headerBodyBigItem{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            height: 50px;
            width: 250px;
            color: #ffffff;
            font-family:tahoma,arial,宋体;
        }
        .mainBodyContain{
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-items: center;
            /*height: 60px;*/
            width: 100%;
            border-bottom: 1px solid #ffffff;
        }

        li{
            list-style:none;
        }
        .pagination li{
            display: inline-block;
            margin:0 5px;
        }
        .pagination li a{
            padding:.5rem 1rem;
            display:inline-block;
            border:1px solid #ddd;
            background:#fff;
            color:#0E90D2;
        }
        .pagination li a:hover{
            background:#eee;
        }
        .pagination li.active a{
            background:#0E90D2;
            color:#fff;
        }
    </style>
</head>
<body style="background-color: #F2F2F2">
<div id="permissionUser" style="display: flex;flex-direction: column">
    <div class="header">
        <div class="headerItemLeft"  @click="showDefault" style="cursor: pointer">用户列表</div>
        <div class="headerItemRight"  id="xiangqing" @click="showDetailTab" >角色</div>
    </div>
    <div id="default">
        <div class="headerBody">
            <div  class="headerBodyBigItem">用户名</div>
            <div  class="headerBodyBigItem">操作</div>
        </div>

        <template v-for="item in data">
            <div class="mainBodyContain">
                <div class="headerBodyBigItem"  style="color: #000;font-size: smaller">{{item.userName}}</div>
                <div  class="headerBodyBigItem"  @click="getRoles" style="color: #000; font-size: smaller;cursor: pointer">查看</div>
            </div>
        </template>
    </div>
    <div id="detail">
        <div class="headerBody">
            <div  class="headerBodyBigItem">角色名</div>
            <div  class="headerBodyBigItem">是否拥有</div>
        </div>
        <template v-for="item in dataRole">
            <div class="mainBodyContain">
                <div class="headerBodyBigItem"  style="color: #000;font-size: smaller">{{item.rolename}}</div>
                <div class="headerBodyBigItem"  style="color: #000;font-size: smaller">
                    <div v-if="item.t_userID!=0">
                        <input  v-bind:data-tuserid="item.t_userID"  type="checkbox" checked="checkbox">
                    </div>
                    <div v-else>
                        <input   v-bind:data-tuserid="item.t_userID"  type="checkbox">
                    </div>
                </div>
            </div>
        </template>

    </div>


</div>
</body>
<script src="<%=contextPath%>/resources/putaohelp/js/permissionUser.js"></script>
</html>
