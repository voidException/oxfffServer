<%--
  葡萄互助后台管理系统主页
--%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String contextPath = request.getContextPath();
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>葡萄互助后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
    <%--<link rel="stylesheet" href="<%=contextPath%>/resources/putaohelp/css/index.css">--%>
</head>
<body style="height: 2600px">
<div class="header" style="height:50px;padding-left: 30px;padding-right: 30px">
    <div class="headertxt">葡萄互助</div>
    <%--<div class="headertxt">葡萄众筹</div>--%>
    <div id="userToken"  style="display: none">${userToken}</div>
    <div id="userName" class="headertxt">欢迎您！${userName } </div>
</div>
<!---这个是中间部分-->
<div class="middle">
    <div class="sidebar">
        <ul class="">
            <li>
                <a href="#" style="font-weight: bolder">资料审核</a>
                <ul>
                    <li><a target="Conframe" href="/glove/grapeAdmin/shenhelist.do?confirmIf=unhandle" style="font-size: smaller">待审核</a></li>
                    <li><a target="Conframe" href="/glove/grapeAdmin/shenhelist.do?confirmIf=refused"  style="font-size: smaller">已拒绝</a></li>
                    <li><a target="Conframe" href="/glove/grapeAdmin/shenhelist.do?confirmIf=pass"  style="font-size: smaller">审核通过</a></li>
                    <li><a target="Conframe" href="/glove/grapeAdmin/shenheSearch.do"  style="font-size: smaller">检索</a></li>
                </ul>
            </li>
        </ul>
        <ul class="">
            <li>
                <a href="#" style="font-weight: bolder">家庭用户</a>
                <ul>
                    <li><a target="Conframe"  href="/glove/grapeAdmin/userInfoTongji.do" style="font-size: smaller">用户统计</a></li>
                    <li><a target="Conframe"  href="/glove/grapeAdmin/gouserlist.do" style="font-size: smaller">用户列表</a></li>
                    <li><a target="Conframe"  href="/glove/grapeAdmin/userSearch.do" style="font-size: smaller">检索</a></li>
                </ul>
            </li>
        </ul>
        <ul class="">
            <li>
                <a href="#" style="font-weight: bolder">公司用户</a>
                <ul>
                    <li><a target="Conframe"  href="/glove/grapeAdmin/companyInfoTongji.do" style="font-size: smaller">公司统计</a></li>
                    <li><a target="Conframe"  href="/glove/grapeAdmin/companylist.do" style="font-size: smaller">公司列表</a></li>
                    <li><a target="Conframe"  href="/glove/grapeAdmin/companySearch.do" style="font-size: smaller">检索</a></li>
                </ul>
            </li>
        </ul>

        <ul class="">
            <li>
                <a href="#" style="font-weight: bolder">资金管理</a>
                <ul>
                    <li><a target="Conframe" href="/glove/grapeAdmin/zijinTongji.do" style="font-size: smaller">资金统计</a></li>
                    <li><a target="Conframe" href="/glove/grapeAdmin/zijinTongji.do" style="font-size: smaller">对账</a></li>

                </ul>
            </li>
        </ul>
        <ul class="">
            <li>
                <a href="#" style="font-weight: bolder">红包管理</a>
                <ul>
                    <li><a target="Conframe" href="/glove/grapeAdmin/redBaoTongji.do" style="font-size: smaller">红包金额统计</a></li>
                </ul>
            </li>
        </ul>
        <ul class="">
            <li>
                <a href="#" style="font-weight: bolder">资讯管理</a>
                <ul>
                    <li><a target="Conframe" href="/glove/grapeAdmin/newsList.do" style="font-size: smaller">资讯列表</a></li>
                    <li><a target="Conframe"  href="/glove/grapeAdmin/addNews.do"  style="font-size: smaller">增加资讯</a></li>
                </ul>
            </li>
        </ul>
        <ul class="">
            <li>
                <a href="#" style="font-weight: bolder">互助管理</a>
                <ul>
                    <li><a target="Conframe" href="/glove/grapeAdmin/addHelpMan.do" style="font-size: smaller">增加一个求助人</a></li>
                    <li><a target="Conframe"  href="/glove/grapeAdmin/costMoney.do" style="font-size: smaller">执行扣钱</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="content">
        <iframe name="Conframe" id="Conframe" src="<%=basePath%>resources/putaohelp/html/test.html"
                class="page-ifream"
                frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no"
                width="100%" height="3900">
        </iframe>
    </div>
</div>
<%--<shiro:hasRole name="admin">--%>
    <%--这是admin角色登录：<shiro:principal></shiro:principal>--%>
<%--</shiro:hasRole>--%>
<%--<shiro:hasPermission name="user:create">--%>
    <%--有user:create权限信息--%>
<%--</shiro:hasPermission>--%>
<%--<br>--%>
<%--<div>登录成功</div>--%>

<style>
    a{
        text-decoration: none;
    }
    ul{
        list-style:none
    }
    .header{
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        height: 50px;
        background-color: #4EB160;
    }
    .headertxt{
        color: #FFFFFF;
    }

    .middle{
        display: flex;
        flex-direction: row;

    }
    .sidebar{
        width: 20%;
        height: 700px;
        border-right: rgba(42,255,48,0.82) 1px dashed;
    }
    .content{
        width: 80%;
        height: 90%;
    }
</style>

</body>
</html>
