<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>资金统计</title>
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
<body>
<div id="zijinTongji">
    <div class="header">
        <div class="headerItemLeft"  @click="showDefault">首页</div>
        <div class="headerItemRight" @click="showDetailTab">详情</div>
    </div>
    <div id="default" style="display: block">

        <div class="headerBody">
            <div class="headerMidItem">计划类型</div>
            <div class="headerSmallItem">总金额</div>
            <div class="headerBodyBigItem">余额</div>
            <div  class="headerSmallItem">加入人数</div>
            <div  class="headerSmallItem">人均</div>
            <div  class="headerSmallItem">互助次数</div>
            <div  class="headerSmallItem">操作</div>
        </div>
    </div>
        <!---
        <div class="contain">
            <div>
                <div>企业员工大病互助</div>
                <div class="item">
                    <div>总金额（元）</div>
                    <div>{{staffDabing.sumMoney}}</div>
                </div>
                <div class="item">
                    <div>加入人数</div>
                    <div>{{staffDabing.sumMan}}</div>
                </div>
                <div class="item">
                    <div>救助均摊金额（元）</div>
                    <div>{{staffDabing.average}}</div>
                </div>
            </div>
            <div>
                <div>企业员工综合意外互助</div>
                <div class="item">
                    <div>总金额（元）</div>
                    <div>{{staffYiwai.sumMoney}}</div>
                </div>
                <div class="item">
                    <div>加入人数</div>
                    <div>{{staffYiwai.sumMan}}</div>
                </div>
                <div class="item">
                    <div>救助均摊金额（元）</div>
                    <div>{{staffYiwai.average}}</div>
                </div>
            </div>
            <div>
                <div>少儿大病互助</div>
                <div class="item">
                    <div>总金额（元）</div>
                    <div>{{littleDabing.sumMoney}}</div>
                </div>
                <div class="item">
                    <div>加入人数</div>
                    <div>{{littleDabing.sumMan}}</div>
                </div>
                <div class="item">
                    <div>救助均摊金额（元）</div>
                    <div>{{littleDabing.average}}</div>
                </div>
            </div>
        </div>

        <div class="contain">
            <div>
                <div>中青年抗癌互助</div>
                <div class="item">
                    <div>总金额（元）</div>
                    <div>{{young.sumMoney}}</div>
                </div>
                <div class="item">
                    <div>加入人数</div>
                    <div>{{young.sumMan}}</div>
                </div>
                <div class="item">
                    <div>救助均摊金额（元）</div>
                    <div>{{young.average}}</div>
                </div>
            </div>
            <div>
                <div>中老年抗癌互助</div>
                <div class="item">
                    <div>总金额（元）</div>
                    <div>{{old.sumMoney}}</div>
                </div>
                <div class="item">
                    <div>加入人数</div>
                    <div>{{old.sumMan}}</div>
                </div>
                <div class="item">
                    <div>救助均摊金额（元）</div>
                    <div>{{old.average}}</div>
                </div>
            </div>
            <div>
                <div>综合意外互助</div>
                <div class="item">
                    <div>总金额（元）</div>
                    <div>{{zongheYiwai.sumMoney}}</div>
                </div>
                <div class="item">
                    <div>加入人数</div>
                    <div>{{zongheYiwai.sumMan}}</div>
                </div>
                <div class="item">
                    <div>救助均摊金额（元）</div>
                    <div>{{zongheYiwai.average}}</div>
                </div>
            </div>
        </div>
        --->
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/zijinTongji.js"></script>
</html>
