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
<body style="height: 1600px">
<script>
</script>
<div id="shenhelist">
    <div id="confirmIf" style="display: none">${data}</div>

    <div class="pageNext" style="display: flex;flex-direction: row;width: 200px;justify-content: space-around">
        <!--  上一页下一下，这个用ajax实现，点击下一页时隐藏<div class="itemDetail">，-->
        <div   v-on:click="goUpPage" >上一页</div>
        <div  v-on:click="goNextPage">下一页</div>
    </div>

    <div class="wrapper">

        <div class="itemDetail">
            <template  class="temp" v-for="item in data">
                <div class="item">
                    <div style="margin-right: 10px ;color: red">{{item.name}}</div>
                    <a target="shenheDetail" :href=" '/glove/grapeAdmin/detail.do?useruuid='+item.useruuid" >详情>></a>
                </div>
            </template>
        </div>

        <iframe  class="page-ifream" name="shenheDetail" id="shenheDetail"
                 src="<%=contextPath%>/resources/putaohelp/html/test.html"
                frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no"
                width="100%" height="2500">
        </iframe>
    </div>

</div>
<script src="<%=contextPath%>/resources/putaohelp/js/shenhelist.js"></script>

</body>
<style>
    a{
        text-decoration: none;
        color: #00BB3B;
    }
    .waitCheck{
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        height: 30px;
        width: 100px;
        background-color: #0000FF;
        color: white;
        border-radius: 5px;
    }
    .wrapper{
        display: flex;
        flex-direction: row;
    }
    .temp{
        display: flex;
        flex-direction: column;
    }
    .item{
        display: flex;
        flex-direction: row;
        width: 350px;
        justify-content: space-between;
        padding-right: 20px;
        padding-left: 10px;
        border-bottom: #00BB3B 1px solid;
        height: 30px;
    }
    .itemDetail{
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
    }

</style>
</html>
