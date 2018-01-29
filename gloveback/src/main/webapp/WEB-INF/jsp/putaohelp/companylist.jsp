<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>公司列表</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>
        #companylist{
            display: flex;
            flex-direction: row;
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
            border-bottom: 1px solid #fff;
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
<div id="companylist" style="display: flex;flex-direction: column">

    <div class="header">
        <div class="headerItemLeft"  @click="showDefault">首页</div>
        <div class="headerItemRight" @click="showDetailTab">详情</div>
    </div>
    <div id="default" style="display: block">
        <div class="header">
            <div class="inputWrap">
                <input  id="joinDate"  class="common"  @keyup="dosearch($event)"     type="text"  autocomplete="off"  min="0" max="200"placeholder="输入手机号搜索" />
            </div>
        </div>
        <div class="headerBody">
            <div class="headerMidItem">公司名</div>
            <div class="headerBodyBigItem">计划类型</div>
            <div class="headerBodyBigItem">总人数</div>
            <div  class="headerSmallItem">总金额</div>
            <div  class="headerSmallItem">平均余额</div>
            <div  class="headerSmallItem">互助次数</div>
            <div  class="headerSmallItem">操作</div>
        </div>
        <template v-for="item in data">
            <div class="mainBodyContain">
                <div class="headerMidItem" style="color: #000;font-size: smaller">{{item.username}}</div>
                <div  class="headerBodyBigItem" style="color: #000; font-size: smaller">{{item.helptype |formHelpType}}</div>
                <div  class="headerBodyBigItem" style="color: #000;font-size: smaller">{{item.staffall}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.totalmoenystr }}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.average}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.tipstimes}}</div>
                <div  class="headerSmallItem">
                      <div class="detail"   @click="getStafflist"
                           v-bind:data-helptype="item.helptype"
                           v-bind:data-useruuid="item.useruuid">
                          详情
                      </div>
                </div>
            </div>
        </template>
    </div>
    <!--以下是详情-->
    <div id="detail" style="display: none">
        <div class="header">
            <div class="inputWrap">
                <input  id="staffPhone"  class="common"  @keyup="dosearch($event)"     type="text"  autocomplete="off"  min="0" max="200"placeholder="输入手机号搜索" />
            </div>
        </div>
        <div class="headerBody">
            <div class="headerMidItem">手机号</div>
            <div class="headerBodyBigItem">身份证号</div>
            <div class="headerBodyBigItem">姓名</div>
            <div  class="headerSmallItem">加入时间</div>
            <div  class="headerSmallItem">互助次数</div>
            <div  class="headerSmallItem">当前状态</div>
        </div>
        <template v-for="item in staffList">
            <div class="mainBodyContain">
                <div class="headerMidItem"  style="color: #000;font-size: smaller">{{item.staffphone}}</div>
                <div  class="headerBodyBigItem" style="color: #000; font-size: smaller">{{item.account }}</div>
                <div  class="headerBodyBigItem" style="color: #000;font-size: smaller">{{item.staffname}}</div>
                <div  class="headerSmallItem"  style="color: #000;font-size: smaller">{{item.joindate |formatDate}}</div>
                <div  class="headerSmallItem"  style="color: #000;font-size: smaller">{{item.applyhelptimes}}</div>
                <div  class="headerSmallItem"  style="color: #000;font-size: smaller">{{item.nowstate}}</div>
            </div>
        </template>
    </div>
</div>
<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/companylist.js"></script>
</body>
</html>
