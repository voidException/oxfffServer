<%   String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>
    <title>审核列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/putaohelp/css/shenhelist.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
</head>
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
        width: 60%;
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
        width: 60%;
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
    .select{
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        width: 100px;
        height: 40px;
    }
    .puding{
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        width: 100%;
        height: 50px;
        background-color: #F3F2F5;
        margin-top: 1px;
        border-bottom: solid 1px #ffffff;
    }
    .companyName{
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        padding-left: 20px;
        width: 100px;
    }
    .passrefused{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items:center;
        margin-top: 20px;
    }
    .imagelist{
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        flex-wrap: wrap;
    }
    .text-area{
        width: 98%;
        height: 100px;
    }
    .dopassRefused{
        display: flex;
        flex-direction: row;
        justify-content: center;
        width: 100%;
    }
    .pass{
        display: flex;
        width: 40%;
        height: 50px;
        background-color: #1ab394;
        color: white;
        margin: 10px;
        justify-content:center ;
        align-items: center;
        border-radius: 3px;
    }
</style>
<body style="background-color: #F2F2F2">
<div id="shenhelist" style="display: flex;flex-direction: column">
    <div class="header">
        <div class="headerItemLeft"  @click="showDefault">首页</div>
        <div class="headerItemRight" @click="showDetailTab">详情</div>
    </div>
    <div id="default" style="display: block">
        <div class="header">
            <div class="inputWrap">
                <input  id="joinDate"  class="common"  @keyup="dosearch($event)"     type="text"  autocomplete="off"  min="0" max="200"placeholder="输入手机号搜索" />
            </div>
            <div class="select">
                <input  @click="selectByType" data-type="all"  type="radio" name="radio3" value="all"/>
                <div>全部</div>
            </div>
            <div class="select">
                <input  @click="selectByType" data-type="unhandle"  type="radio" name="radio3" value="unhandle"/>
                <div>待审核</div>
            </div>
            <div class="select">
                <input  @click="selectByType" data-type="pass"  type="radio" name="radio3" value="pass"/>
                <div>已通过</div>
            </div>
            <div class="select">
                <input  @click="selectByType" data-type="refused"  type="radio" name="radio3" value="refused"/>
                <div>已拒绝</div>
            </div>
        </div>
        <div class="headerBody">
            <div class="headerMidItem">公司名</div>
            <div class="headerBodyBigItem">征信代码</div>
            <div class="headerBodyBigItem">手机号</div>
            <div  class="headerSmallItem">地址</div>
            <div  class="headerSmallItem">邮箱</div>
            <div  class="headerSmallItem">是否确认</div>
            <div  class="headerSmallItem">操作</div>
        </div>
        <template v-for="item in data">
            <div class="mainBodyContain">
                <div class="headerMidItem" style="color: #000;font-size: smaller">{{item.name}}</div>
                <div  class="headerBodyBigItem" style="color: #000; font-size: smaller">{{item.numberid}}</div>
                <div  class="headerBodyBigItem" style="color: #000;font-size: smaller">{{item.phone}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.address }}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.email}}</div>
                <div  class="headerSmallItem" style="color: #000;font-size: smaller">{{item.confirmif}}</div>
                <div  class="headerSmallItem">
                    <div class="detail"   @click="getDetail"
                         v-bind:data-helptype="item.helptype"
                         v-bind:data-useruuid="item.useruuid">
                        详情
                    </div>
                </div>
            </div>
        </template>
    </div>
    <div id="detail" style="display: none">

        <div style="display: flex;align-items:center;justify-content:center; width: 100%;height: 60px;">
            <div style="display: flex;justify-content:center;align-items:center;width: 400px;height: 50px;
            color: #ffffff;font-weight: bolder;font-size: 20px; background-color: #00B997;border-radius: 4px">
                企业信息
            </div>
        </div>
        <div id="useruuid" style="display: none">{{detail.useruuid}}</div>
        <div class="puding">
            <div class="companyName">状态：</div>
            <div  id="confirmif">{{detail.confirmif}}</div>
        </div>

        <div class="puding">
            <div class="companyName">公司名字：</div>
            <div>{{detail.name}}</div>
        </div>

        <div class="puding">
            <div class="companyName">手机号：</div>
            <div>{{detail.phone}}</div>
        </div>
        <div class="puding">
            <div class="companyName">地址：</div>
            <div>{{detail.address}}</div>
        </div>
        <div class="puding">
            <div class="companyName">邮箱：</div>
            <div>{{detail.email}}</div>
        </div>
        <div class="puding">
            <div class="companyName">通过/拒绝原因：</div>
            <div>{{detail.comment}}</div>
        </div>
        <div class="puding">
            <div class="companyName">法人姓名：</div>
            <div>{{detail.legalperson}}</div>
        </div>
        <div class="imagelist">
            <img  v-bind:src="'http://101.200.33.138/staticImage/'+ detail.imgtwo "  style="height: 300px;width: 300px" />
            <img  v-bind:src="'http://101.200.33.138/staticImage/'+ detail.imgone "  style="height: 300px;width: 300px" />
            <img  v-bind:src="'http://101.200.33.138/staticImage/'+ detail.imgthree " style="height: 300px;width: 300px" />
        </div>
        <div id="passrefused"  class="passrefused">
                <textarea  id="comment" class="text-area" rows="10" cols="60" placeholder="请输入通过或拒绝的理由"></textarea>
                <div class="dopassRefused" >
                    <div  v-on:click="pass"  class="pass" >通过</div>
                    <div  v-on:click="refused"  class="pass" >拒绝</div>
                </div>
        </div>
    </div>

</div>
<script src="<%=contextPath%>/resources/putaohelp/js/shenhelist.js"></script>
<style>

</style>
</body>
</html>
