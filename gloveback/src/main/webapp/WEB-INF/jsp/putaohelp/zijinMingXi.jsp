<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>资金明细</title>
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

<div  id="chongzhilist"  style="display: flex;flex-direction: column">
    <div class="header">
        <div class="headerItemLeft"  @click="showDefault">充值</div>
        <div class="headerItemRight" @click="showDetailTab">扣费</div>
    </div>
    <!---充值--->
    <div id="default" style="display: block">
        <div class="header">
            <div class="inputWrap">
                <input id="accountInput" class="common" @keyup="doSearch($event)"
                       type="text"  autocomplete="off"  min="0" max="200"placeholder="输入身份证号搜索" />
            </div>
        </div>
        <div class="headerBody">
            <div  class="headerMidItem">userUUID</div>
            <div class="headerSmallItem">身份证号</div>
            <div class="headerBodyBigItem">互助类型</div>
            <div  class="headerSmallItem">交易订单号</div>
            <div  class="headerSmallItem">充值金额</div>
            <div  class="headerSmallItem">交易状态</div>
            <div class="headerSmallItem">时间</div>
        </div>

        <template v-for="item in data">
            <div class="mainBodyContain">
                <div class="headerMidItem" style="color: #000;font-size: smaller">{{item.useruuid}}</div>
                <div class="headerSmallItem" style="color: #000; font-size: smaller">{{item.accountuuid}}</div>
                <div class="headerBodyBigItem" style="color: #000;font-size: smaller">{{item.categorytype |formHelpType}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.outTradeNo}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.totalAmount}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.tradeStatus}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.notifyTime |formatDate}}</div>
            </div>
        </template>

        <div id="appdefault">
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
    <!---扣费--->
    <div id="detail" style="display: none">
        <%--<div class="header">--%>
            <%--<div class="inputWrap">--%>
                <%--<input   id="koufeiInput"  class="common"  @keyup="doKoufeiSearch()"--%>
                         <%--type="text"  autocomplete="off"  min="0" max="200"placeholder="输入身份证号搜索" />--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="headerBody">
            <div  class="headerMidItem">互助类别</div>
            <div class="headerSmallItem">扣钱时间</div>
            <div class="headerSmallItem">被扣钱人</div>
            <div  class="headerSmallItem">受助人</div>
            <div  class="headerSmallItem">理论口钱数</div>
            <div  class="headerSmallItem">实际扣钱数</div>
            <div class="headerSmallItem">说明</div>
        </div>

        <template v-for="item in dataDeduction">
            <div class="mainBodyContain">
                <div class="headerMidItem" style="color: #000;font-size: smaller">{{item.categorytype |formHelpType}}</div>
                <div class="headerSmallItem" style="color: #000; font-size: smaller">{{item.userspendmoneydate |formatDate}}</div>
                <div class="headerSmallItem" style="color: #000;font-size: smaller">{{item.userspendmoneyuuid }}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.userneedmoneyuuid}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.theorymoneyspend}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.moneyspend}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.other}}</div>
            </div>
        </template>

        <div id="appdetail">
            <ul class="pagination" >
                <li v-show="currentDetail != 1" @click="currentDetail-- && gotoDetail(currentDetail)" >
                    <div style="cursor: pointer">上一页</div>
                </li>
                <li v-for="index in pagesDetail" @click="gotoDetail(index)" :class="{'active':current == index}" :key="index">
                    <div  @click="getPageIndexDetail"  v-bind:data-index="index" class="index">{{index}}</div>
                </li>
                <li v-show="allpageDetail != currentDetail && allpageDetail != 0 " @click="currentDetail++ && gotoDetail(currentDetail++)">
                    <div style="cursor: pointer">下一页</div>
                </li>
            </ul>
        </div>

    </div>

</div>
<script src="<%=contextPath%>/resources/putaohelp/js/zijinMingXi.js"></script>
</body>
</html>





















