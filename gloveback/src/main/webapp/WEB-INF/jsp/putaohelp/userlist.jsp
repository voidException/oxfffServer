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
<div id="userlist" style="display: flex;flex-direction: column">
    <div class="header">
        <div class="headerItemLeft"  @click="showDefault" style="cursor: pointer">首页</div>
        <div class="headerItemRight"  id="xiangqing" @click="showDetailTab" >详情</div>
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

        <template v-for="item in data" >
            <div class="mainBodyContain" :key="item.userphone">
                <div class="headerMidItem" style="color: #000;font-size: smaller">{{item.userphone}}</div>
                <div class="mainBodyContainRight">
                    <template v-for="userAccount in item.userAccountList" >
                    <div class="mainBodyContainRightItem"  :key="userAccount.useraccountid">
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

        <div class="detailHeader">{{detail.categorytype | formHelpType}}</div>
        <div class="detailBody">
            <div class="detailBodyTop">
                <div style="display: flex;flex-direction: row;justify-content: center;align-items: center;height: 100px; width: 246px">
                    <img  class="wrapImg"  src="<%=contextPath%>/resources/image/admin.png"  style="width: 90px;height: 90px;margin-bottom: 10px;margin-top: 10px" />
                    <div style="display: flex;width: 100px;flex-direction: column;justify-content: center;align-items: center;color: #ffffff">
                        <div>{{detail.username}}</div>
                        <div>个人账户</div>
                    </div>
                </div>
                <div style="height: 100px;width: 2px;background-color: #ffffff"></div>
                <div   style="display: flex;flex-direction: column;justify-content: center;align-items: center;height: 100px; width: 246px;color: #ffffff">
                    <div>{{detail.paytotalmoney}}</div>
                    <div>账户余额</div>
                </div>
            </div>
            <%--<div class="profileItem">--%>
                <%--<div>手机号：</div>--%>
                <%--<div>17827478</div>--%>
            <%--</div>--%>
            <div class="profileItem">
                <div>身份证号：</div>
                <div>{{detail.accountuuid}}</div>
            </div>
            <div class="profileItem">
                <div>加入时间：</div>
                <div>{{detail.joindate | formatDate}}</div>
            </div>
            <div class="profileItem">
                <div>互助次数：</div>
                <div>{{detail.remainfee}}</div>
            </div>

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
