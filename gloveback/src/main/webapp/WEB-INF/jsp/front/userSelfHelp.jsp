<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的求助</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/userSelfHelp.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body  style="background-color: #d9d9d9">

<div id="noData" class="noData">
    您还未发布求助信息。
</div>
<div  id="userSelfHelp" class="userSelfHelp">
    <template v-for="item in lp">
    <div  v-on:click="goTimeline" style="cursor:pointer ;">
        <div  class="photoName">
            <img :src="item.backupeleven" style="width: 40px;height: 40px;border-radius: 20px ">
            <div class="userName">{{item.backupnine}}</div>
        </div>

        <div class="userHelpMain">
            <div class="title">{{item.backuptwelve}}</div>
            <div class="imageArray">
                <img :src="item.tweetbackupone" style="width: 90px;height: 80px;">
                <img :src="item.tweetbackuptwo" style="width: 90px;height: 80px;">
                <img :src="item.tweetbackupthree" style="width: 90px;height: 80px;">
            </div>
            <div class="cityFrom">
                <div>来自：</div>
                <div>{{item.cityname}}</div>
            </div>
        </div>
    </div>
    </template>
</div>

</body>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/userSelfHelp.js"></script>
</html>
