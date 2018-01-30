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
</head>
<body style="height: 100%">
<div class="header" style="height:20px;background-color: #ffffff">
</div>
<!---这个是中间部分-->
<div class="middle">
    <div class="sidebar">
        <div class="wrapper"  style="display: flex;flex-direction: column;align-items: center;height: 150px;width: 200px;background-color: #1F3647;">
            <img  class="wrapImg"  src="<%=contextPath%>/resources/image/admin.png"  style="width: 90px;height: 90px;margin-bottom: 10px;margin-top: 10px" />
            <div  class="wrapDiv" style="color: #ffffff;font-size: larger;font-weight: bolder;font-family:tahoma,arial,宋体;">
                管理员
            </div>
        </div>
        <!---模块1-->
        <div class="ItemWrapper" >
            <div  class="ItemWrapperIn">
                 <img  class="ItemWrapperInImg"  src="<%=contextPath%>/resources/image/smallAdmin.png" >
            </div>
            <div  class="ItemWrapperInDiv">
                会员管理
            </div>
        </div>
        <div  class="ItemBodyWrapper">
            <div style="width: 80px"></div>
            <div   class="ItemBodyInner" >
                <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/gouserlist.do">个人列表</a></div>
                <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/gouserlist.do">个人列表分类</a></div>
                <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/companylist.do"  >企业列表</a></div>
                <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/shenhelist.do?confirmIf=pass">实名认证</a></div>
            </div>
        </div>
        <!---模块2-->
        <div class="ItemWrapper" >
            <div  class="ItemWrapperIn">
                <img  class="ItemWrapperInImg"  src="<%=contextPath%>/resources/image/money.png" >
            </div>
            <div  class="ItemWrapperInDiv">
                资金管理
            </div>
        </div>
        <div  class="ItemBodyWrapper">
            <div style="width: 80px"></div>
            <div   class="ItemBodyInner">
                <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/zijinTongji.do">资金统计</a></div>
                <div style="margin-bottom: 10px"><a target="Conframe" href="#">资金明细</a></div>
                <div style="margin-bottom: 10px"><a target="Conframe" href="#">资金走势</a></div>
                <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/redBaoTongji.do">红包统计</a></div>
            </div>
        </div>
        <!---模块3-->
        <div class="ItemWrapper" >
            <div  class="ItemWrapperIn">
                <img  class="ItemWrapperInImg"  src="<%=contextPath%>/resources/image/zixun.png" >
            </div>
            <div  class="ItemWrapperInDiv">
                资讯管理
            </div>
        </div>
        <div  class="ItemBodyWrapper">
            <div style="width: 80px"></div>
            <div   class="ItemBodyInner" >
                <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/newsList.do">资讯列表</a></div>
                <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/addNews.do" >增加资讯</a></div>
            </div>
        </div>
        <!---一个模块-->
        <div class="ItemWrapper" >
            <div  class="ItemWrapperIn">
                <img  class="ItemWrapperInImg"  src="<%=contextPath%>/resources/image/lovelove.png" >
            </div>
            <div  class="ItemWrapperInDiv">
                互助管理
            </div>
        </div>
        <div  class="ItemBodyWrapper">
            <div style="width: 80px"></div>
            <div   class="ItemBodyInner" >
                <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/addHelpMan.do">增加一个求助人</a></div>
                <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/goCostMoney.do"  >执行扣钱</a></div>
            </div>
        </div>
        <!---一个模块-->

    </div>
    <div class="content">
        <iframe name="Conframe" id="Conframe" href="https://www.baidu.com/index.php?tn=baiduhome_pg"
                class="page-ifream"
                frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no"
                width="100%" height="3000px">
            <p>Your browser does not support iframes.</p>
        </iframe>
    </div>
</div>
<script>

</script>
<%--<shiro:hasRole name="admin">--%>
    <%--这是admin角色登录：<shiro:principal></shiro:principal>--%>
<%--</shiro:hasRole>--%>
<%--<shiro:hasPermission name="user:create">--%>
    <%--有user:create权限信息--%>
<%--</shiro:hasPermission>--%>
<%--<br>--%>
<%--<div>登录成功</div>--%>

<style>

    .ItemWrapper{
        display: flex;flex-direction: row;justify-content: center;width: 200px;height: 30px;margin-bottom: 10px;margin-top: 15px;
    }
    .ItemWrapperIn{
        width: 80px;display: flex;flex-direction: row;justify-content: flex-end;
    }
    .ItemWrapperInImg{
        height: 25px;padding-right: 10px;height: 25px;
    }
    .ItemWrapperInDiv{
        font-family:tahoma,arial,宋体;width: 120px;display: flex;flex-direction: row;justify-content: flex-start;align-items: center;height: 30px
    }
    .ItemBodyWrapper{
        display: flex;flex-direction: row;align-items: center;width: 200px
    }
    .ItemBodyInner{
        width: 120px;display: flex;flex-direction: column;align-items: flex-start;justify-content: flex-start;font-family:tahoma,arial,宋体;
    }

    a{
        text-decoration: none;
        font-size: smaller;color: #ffffff;
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

    .middle{
        display: flex;
        flex-direction: row;

    }
    .sidebar{
        width: 200px;
        height: 700px;
        border-right: rgba(42,255,48,0.82) 1px dashed;
        background-color: #2A3C4D;
        color: #ffffff;
    }
    .content{
        width: 80%;
        height: 90%;
    }
</style>

</body>
</html>
