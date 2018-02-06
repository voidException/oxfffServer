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
            flex-direction: column;
        }
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
        .inputWrap{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            height: 50px;
            width: 90%;
        }
        .common{
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            background-color: #fff;
            border-radius: 4px;
            border: 0px solid #bfcbd9;
            box-sizing: border-box;
            color: #1f2d3d;
            display: block;
            font-size: inherit;
            height: 46px;
            line-height: 1;
            outline: 0;
            padding: 3px 10px;
            transition: border-color .2s cubic-bezier(.645,.045,.355,1);
            width: 80%;
            text-align: center;
            margin-left: 20px;
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
        .headerMidItem{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            height: 50px;
            width: 200px;
            color: #ffffff;
            font-family:tahoma,arial,宋体;
        }
        .headerSmallItem{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            height: 50px;
            width: 150px;
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
        .mainBodyContainRight{

        }
        .mainBodyContainRightItem{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            height: 60px;
            width: 950px;
        }
        .detail{
            width: 100px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            background-color: #00BA97;
            cursor: pointer;
        }
        .detailTab{
            display: none;
            flex-direction: column;
            width: 100%;
            height: 100%;
            align-items: center;
            justify-content: flex-start;
            margin-top: 30px;

        }
        .detailItem{
            display: flex;
            flex-direction: row;
        }

        li{
            list-style:none;
        }
        .pagination {
            position: relative;

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
        .index{
            width: 80px;
            height: 60px;
            line-height: 60px;
            text-align: center;
            background-color: #ffffff;
            cursor: pointer;
        }
        .detailHeader{
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            width: 500px;
            height: 44px;
            background-color: #00B997;
            border-radius: 4px;
            color: #ffffff;
        }
        .detailBody{
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            width: 500px;
            height: 660px;
            background-color: #ffffff;
            border-radius: 4px;
            margin-top: 10px;
        }
        .detailBodyTop{
            display: flex;
            flex-direction: row;
            width: 500px;
            height: 200px;
            justify-content: center;
            background-color: #00B997;
            align-items: center;
        }
        .profileItem{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            height: 100px;
            width: 500px;
            border-bottom: #6f7a7e dashed 1px;
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
