
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
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/productDetail.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/footer.css">
    <link rel="shortcut icon" href="http://onejf30n8.bkt.clouddn.com/logo16.png">
    <title>给爱网</title>
</head>
<body>
<!--这里是头部部分-->
<div class="header">
    <div class="headerLeft">
        <a class="logo"  href="http://www.geilove.org" style="text-decoration: none">
            <img src="<%=contextPath%>/resources/image/logo.png" style="width: 40px;height: 40px;border-radius: 20px">
            <div style="color:#00BB3B;cursor: pointer">首页</div>
        </a>
        <a class="logo  appHelp" href="http://www.geilove.org/pages/appDownload" style="text-decoration: none" >
            <span>App下载</span>
        </a>
        <a href="http://www.geilove.org/pages/helpCenter" class="logo appHelp" style="text-decoration: none">
            <span>帮助中心</span>
        </a>
    </div>
</div>
<div id="productDetail">
    <!--项目标题-->
    <h2 style="padding-left: 80px;width: 70%;text-align: center">{{productDetail.maintitleone}}</h2>
    <!--bodyMain-->
    <div class="bodyMain">
        <!--左侧布局-->
        <div class="bodyLeft">
            <!--项目详情-->
            <div class="detailShare">
                <div class="detail">项目详情</div>
                <div class="share" style="margin-left: 10px">分享到</div>
                <img src="<%=contextPath%>/resources/image/weichatlogo.png" style="width: 40px;height: 40px;border-radius: 20px;margin-left: 3px ">
            </div>
            <!--项目详细介绍-->
            <div style=" margin-bottom: 30px">
                <!--第一章-->
                <div>
                    <!--viceTitleOne-->
                    <div class="viceTitle">
                        <span>{{productDetail.vicetitleone}}</span>
                    </div>
                    <div class="imgDetail">
                        <img :src="productDetail.imgone">
                    </div>
                </div>
                <!--第二章-->
                <div>
                    <div class="mainTile">
                        <strong>{{productDetail.maintitletwo}}</strong>
                    </div>
                    <!--viceTitleOne-->
                    <div class="viceTitle">
                        <span>{{productDetail.vicetitletwo}}</span>
                    </div>
                    <div class="imgDetail">
                        <img :src="productDetail.imgtwo">
                    </div>
                </div>
                <!--第3章-->
                <div>

                    <div class="mainTile">
                        <strong>{{productDetail.maintitlethree}}</strong>
                    </div>

                    <div class="viceTitle">
                        <span>{{productDetail.vicetitlethree}}</span>
                    </div>
                    <div class="imgDetail">
                        <img :src="productDetail.imgthree">
                    </div>
                </div>
                <!--第4章-->
                <div>

                    <div class="mainTile">
                        <strong>{{productDetail.maintitlefour}}</strong>
                    </div>

                    <div class="viceTitle">
                        <span>{{productDetail.vicetitlefour}}</span>
                    </div>
                    <div class="imgDetail">
                        <img :src="productDetail.imgfour">
                    </div>
                </div>
                <!--第5章-->
                <div>
                    <div class="mainTile">
                        <strong>{{productDetail.maintitlefive}}</strong>
                    </div>
                    <div class="viceTitle">
                        <span>{{productDetail.vicetitlefive}}</span>
                    </div>
                    <div class="imgDetail">
                        <img :src="productDetail.imgfive">
                    </div>
                </div>
                <!--第6章-->
                <div>
                    <div class="mainTile">
                        <strong>{{productDetail.maintitlesix}}</strong>
                    </div>
                    <div class="viceTitle">
                        <span>{{productDetail.vicetitlesix}}</span>
                    </div>
                    <div class="imgDetail">
                        <img :src="productDetail.imgsix">
                    </div>
                </div>
                <!--第7章-->
                <div>
                    <div class="mainTile">
                        <strong>{{productDetail.maintitleseven}}</strong>
                    </div>
                    <div class="viceTitle">
                        <span>{{productDetail.vicetitleseven}}</span>
                    </div>
                    <div class="imgDetail">
                        <img :src="productDetail.imgseven">
                    </div>
                </div>
            </div>
            <!--项目支持者-->
            <div style="text-align: center;font-size: 20px;font-family: 'Heiti SC'">Ta的支持者</div>
            <template v-for="item in backupList">
            <div style="padding-left: 20px">
                <!--具体的支持人-->
                <div class="backUp">
                    <img :src="item.photoUrl" style="width: 50px;height: 50px;border-radius: 25px">
                    <div style="margin-left: 10px;padding-top: 3px">
                        <div class="backupProfile">
                            <div>{{item.nickname}}</div>
                            <div style="margin-left: 3px;margin-right: 3px;color: #00BB3B">支持了</div>
                            <div style="color: #F25B4B">{{item.money}}<span style="color: #6f7a7e">元</span></div>

                        </div>
                        <div style="height: 30px;color: #999">{{item.timeStamp}}</div>
                    </div>
                </div>
            </div>
            </template>
        </div>
        <%--左侧布局结束--%>
        <!--右侧布局-->
        <div class="bodyRight">
            <div class="money">
                <div class="moneyNeed">
                    <div>{{productDetail.targetmoney}}<span class="yuan">元</span></div>
                    <div class="target">目标金额</div>
                </div>
                <div class="moneyNeed">
                    <div>{{productDetail.handymoney}}<span class="yuan">元</span></div>
                    <div class="target">已付款</div>
                </div>
            </div>
            <div class="money">
                <div class="moneyNeed">
                    <div>{{productDetail.backuptimes}}<span class="yuan">人</span></div>
                    <div class="target">购买人数</div>
                </div>
                <div class="moneyNeed">
                    <%--<div>{{productDetail.endtime}}</div>--%>
                    <div>{{new Date(productDetail.endtime).toLocaleString().substring(0,10)}}</div>
                    <div class="target">截止日期</div>
                </div>
            </div>
            <div class="wechatPay">
                <img src="<%=contextPath%>/resources/image/erweima.png" style="width: 120px;height: 120px; margin-left: 20px;">
                <div style="margin-left: 30px;font-size: larger; font-family: STHeiti;font-weight: 700;color: #47AE47">微信支付</div>
            </div>
            <!--项目发起人-->
            <div class="itemStarter">
                <div style="font-weight: 400;font-size: 18px;font-family: STHeiti; color: #6f7a7e;margin-bottom: 10px">项目发起人</div>
                <div class="userProfile">
                    <img :src="productDetail.photourl" style="width: 60px;height: 60px;border-radius: 30px">
                    <div style="margin-left: 10px">
                        <div style="margin-bottom: 5px;font-weight: 400;font-size: 16px;font-family: STHeiti;color: #6f7a7e;">{{productDetail.nickname}}</div>
                        <div style="margin-top: 5px;font-size: 14px;color: #6f7a7e;">{{new Date(productDetail.starttime).toLocaleString().substring(0,10)}}</div>
                    </div>
                </div>
            </div>
            <!--运费和发货时间-->
            <div class="itemStarter">
                <div style="font-weight: 400;font-size: 18px;font-family: STHeiti; color: #6f7a7e;margin-bottom: 10px">
                    运费和发货时间
                </div>
                <div class="express">
                    <div style="width: 80px;color: #999;">运费:</div>
                    <div>{{productDetail.trafficmoney}}</div>
                </div>
                <div class="express">
                    <div style="width: 80px;color: #999">发货时间:</div>
                    <div>{{productDetail.trafficdate}}</div>
                </div>
            </div>
            <!--产品回报-->
            <div style="background: #FFFFFF">
                <div class="productFeedBack">
                    产品和回报
                </div>
                <div class="feed">
                    <div>支持<span style="color:#F25B4B;font-weight: 400;font-family: STHeiTi">{{productDetail.paysampleone}}</span>元</div>
                    <div class="feedback">
                        <img src="<%=contextPath%>/resources/image/money1.png" style="width: 40px;height: 40px">
                        <div class="feedbackDesp">{{productDetail.sampleonedsp}}</div>
                    </div>
                </div>
                <div class="feed">
                    <div>支持<span style="color:#F25B4B;font-weight: 400;font-family: STHeiTi">{{productDetail.paysampletwo}}</span>元</div>
                    <div class="feedback">
                        <img src="<%=contextPath%>/resources/image/money2.png" style="width: 40px;height: 40px">
                        <div class="feedbackDesp">{{productDetail.sampletwodsp}}</div>
                    </div>
                </div>
                <div class="feed">
                    <div>支持<span style="color:#F25B4B;font-weight: 400;font-family: STHeiTi">{{productDetail.paysamplethree}}</span>元</div>
                    <div class="feedback">
                        <img src="<%=contextPath%>/resources/image/money3.png" style="width: 40px;height: 40px">
                        <div class="feedbackDesp">{{productDetail.samplethreedsp}}</div>
                    </div>
                </div>
                <div class="feed">
                    <div>支持<span style="color:#F25B4B;font-weight: 400;font-family: STHeiTi">{{productDetail.paysamplefour}}</span>元</div>
                    <div class="feedback">
                        <img src="<%=contextPath%>/resources/image/money4.png" style="width: 40px;height: 40px">
                        <div class="feedbackDesp">{{productDetail.samplefourdsp}}</div>
                    </div>
                </div>
                <div class="feed">
                    <div>支持<span style="color:#F25B4B;font-weight: 400;font-family: STHeiTi">{{productDetail.paysamplefive}}</span>元</div>
                    <div class="feedback">
                        <img src="<%=contextPath%>/resources/image/money5.png" style="width: 40px;height: 40px">
                        <div class="feedbackDesp">{{productDetail.samplefivedsp}}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%----%>
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

<script type="text/javascript" src="<%=contextPath%>/resources/javaScriptPCfront/productDetail.js"></script>
</body>
</html>























