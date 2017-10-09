
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/navigation.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/indexPC.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/footer.css">
    <link rel="shortcut icon" href="http://onejf30n8.bkt.clouddn.com/logo16.png">
    <title>给爱网</title>
</head>
<body>
<div id="mainBody">
    <!--这里是头部部分-->
    <div class="header">
        <div class="headerLeft">
            <a class="logo"  href="http://www.geilove.org" style="text-decoration: none">
                <img src="<%=contextPath%>/resources/image/logo.png" style="width: 40px;height: 40px;border-radius: 20px">
                <div style="color:#00BB3B;cursor: pointer">首页</div>
            </a>
            <a class="logo  appHelp" href="http://www.geilove.org/glove/pages/appDownload" style="text-decoration: none" >
                <span>App下载</span>
            </a>
            <a href="http://www.geilove.org/glove/pages/helpCenter" class="logo appHelp" style="text-decoration: none">
                <span>帮助中心</span>
            </a>
        </div>
        <div style="display: flex;flex-direction: row;align-items: center;justify-content: center">
            <div class="headerRight"  v-if="loginTag==false">
                <a  href="http://www.geilove.org/pages/loginRegister" class="login" style="text-decoration: none">登录</a>
                <a  href="http://www.geilove.org/pages/loginRegister" class="register" style="text-decoration: none">注册</a>
            </div>
            <div class="publishItem" v-if="loginTag==true">
                <a href="http://www.geilove.org/pages/publishItem" class="login" style="text-decoration: none">发布项目</a>
                <img src="<%=contextPath%>/resources/image/logo.png" style="height: 40px;width: 40px;border-radius: 20px;cursor: pointer">
            </div>
        </div>
    </div>
    <!--标签-->
    <div class="label">
        <div class="labelin">农村特产</div>
        <%--<div class="labelin">科技发明</div>--%>
    </div>

    <!--这里是主体部分-->
    <div class="mainBody">
        <!--这是一个item-->
        <template v-for="item in productInfos">
            <a  class="itemWrapper"  :key="item.productid"  :href="'/pages/productDetail/index?id='+item.productuuid"  style="text-decoration: none">
                <img :src="item.imgseven"    style="height: 200px;width: 366px;">
                <div class="userInfo">
                    <div class="user">
                        <img :src="item.photourl" style="height: 40px;width: 40px;border-radius: 20px">
                        <div style="color: #2b3845">{{item.nickname}}</div>
                    </div>
                    <div class="end">{{new Date(item.endtime).toLocaleString().substring(0,10)}}</div>

                </div>
                <!--这里是众筹标题-->
                <div class="title" style="font-size: 17px;font-weight: 600;color: #000;">{{item.maintitleone}}</div>
                <div class="moneyInfo">
                    <div>筹款目标</div>
                    <div class="line"></div>
                    <div>{{item.targetmoney}}元</div>
                </div>
                <div class="moneyInfo">
                    <div>已筹金额</div>
                    <div class="line"></div>
                    <div>{{item.handymoney}}元</div>
                </div>
                <div class="moneyInfo">
                    <div>支持人数</div>
                    <div class="line"></div>
                    <div>{{item.backuptimes}}人</div>
                </div>
            </a>
        </template>
        <div style="width: 100%"></div>
        <!--下一页 上一页-->
        <div class="page">
            <div class="lastPage"   v-on:click="backPage">上一页</div>
            <div class="nextPage"   v-on:click="nextPage">下一页</div>
        </div>
        <!--底部-->
    </div>
</div>

<footer class="footer">
    <div>
        <img src="<%=contextPath%>/resources/image/logo.png" style="width: 100px;width: 100px;border-radius: 50px">
    </div>
    <div class="footerItem">
        <a href="http://www.geilove.org/pages/aboutus" style="text-decoration: none">关于我们</a>
        <a href="http://www.geilove.org/pages/helpCenter" style="text-decoration: none">帮助中心</a>
        <a href="http://www.geilove.org/pages/userProtocol" style="text-decoration: none">用户协议</a>
    </div>
    <div class="footerItem">
        <div>联系方式</div>
        <div>电话:010-80441882</div>
        <div>邮箱:geilovetech@126.com</div>
    </div>
    <div>
        <img src="<%=contextPath%>/resources/image/erweima.png" style="height: 100px;width: 100px">
    </div>
</footer>
<div class="bottom">
    © 2017 给爱网 - 北京给爱科技有限公司   京ICP备17013747号
</div>


<script type="text/javascript" src="<%=contextPath%>/resources/javaScriptPCfront/indexPC.js"></script>
</body>
</html>























