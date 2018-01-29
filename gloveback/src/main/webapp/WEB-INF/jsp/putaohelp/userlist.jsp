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
            border-bottom: 1px solid #4a4a4a;
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
    </style>
</head>
<body style="background-color: #F2F2F2">
<div id="userlist" style="display: flex;flex-direction: column">
    <div class="header">
        <div class="headerItemLeft"  @click="showDefault">首页</div>
        <div class="headerItemRight" @click="showDetailTab">详情</div>
    </div>
    <!---默认板块--->
    <div id="default" style="display: block">
        <div class="header">
            <div class="inputWrap">
                <input  id="joinDate"  class="common"  @keyup="dosearch($event)"     type="text"  autocomplete="off"  min="0" max="200"placeholder="输入手机号搜索" />
            </div>
        </div>
        <div class="headerBody">
            <div class="headerMidItem">手机号</div>
            <div class="headerSmallItem">计划人</div>
            <div class="headerBodyBigItem">参与计划</div>
            <div  class="headerSmallItem">等待期（天）</div>
            <div  class="headerSmallItem">账户余额</div>
            <div  class="headerSmallItem">互助次数</div>
            <div  class="headerSmallItem">操作</div>
        </div>

        <template v-for="item in data">
            <div class="mainBodyContain">
                <div class="headerMidItem" style="color: #000;font-size: smaller">{{item.userphone}}</div>
                <div class="mainBodyContainRight">
                    <template v-for="userAccount in item.userAccountList">
                    <div class="mainBodyContainRightItem">
                        <div class="headerSmallItem" style="color: #000; font-size: smaller">{{userAccount.username}}</div>
                        <div class="headerBodyBigItem" style="color: #000;font-size: smaller">{{userAccount.categorytype |formHelpType}}</div>
                        <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{userAccount.joindate | formatDate}}</div>
                        <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{userAccount.paytotalmoney}}</div>
                        <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{userAccount.remainFee}}</div>
                        <div  class="headerSmallItem">
                            <div class="detail"
                                 @click="showDetail"
                                 v-bind:data-useruuid="item.useruuid"
                                 v-bind:data-accountuuid="userAccount.accountuuid"
                                 v-bind:data-breakif="userAccount.breakif"
                                 v-bind:data-paytotalmoney="userAccount.paytotalmoney"
                                 v-bind:data-username="userAccount.username"
                                 v-bind:data-categorytype="userAccount.categorytype"
                                 v-bind:data-joindate="userAccount.joindate"
                                 v-bind:data-remainfee="userAccount.remainfee">
                                详情
                            </div>
                        </div>
                    </div>
                    </template>
                </div>
            </div>
        </template>
    </div>
    <!--以下是详情-->
    <div id="detail" class="detailTab">
        <div class="detailItem">
            <div>身份证号：</div>
            <div>{{detail.accountuuid}}</div>
        </div>

        <div class="detailItem">
            <div>姓名：</div>
            <div>{{detail.username}}</div>
        </div>

        <div class="detailItem">
            <div>互助类型：</div>
            <div>{{detail.categorytype}}</div>
        </div>
        <div class="detailItem">
            <div>加入时间：</div>
            <div>{{detail.joindate}}</div>
        </div>

        <div class="detailItem">
            <div>余额：</div>
            <div>{{detail.paytotalmoney}}</div>
        </div>

        <div class="detailItem">
            <div>互助次数：</div>
            <div>{{detail.remainfee}}</div>
        </div>
    </div>
    <!--分页组件-->
    <div id="app">
        <ul class="pagination" >
            <li v-show="current != 1" @click="current-- && goto(current)" >
                <div style="cursor: pointer">上一页</div>
            </li>
            <li v-for="index in pages" @click="goto(index)" :class="{'active':current == index}" :key="index">
                <div  @click="getPageIndex"  v-bind:data-index="index" class="index">{{index}}</div>
            </li>
            <li v-show="allpage != current && allpage != 0 " @click="current++ && goto(current++)">
                <div style="cursor: pointer">下一页</div>
            </li>
        </ul>
    </div>
</div>
</body>
<script src="<%=contextPath%>/resources/putaohelp/js/userlist2.js"></script>
</html>
