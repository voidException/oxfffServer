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
<div id="middle"  class="middle">
    <div class="sidebar">
        <div class="wrapper"  style="display: flex;flex-direction: column;align-items: center;height: 150px;width: 200px;background-color: #1F3647;">
            <img  class="wrapImg"  src="<%=contextPath%>/resources/image/admin.png"
                  style="width: 90px;height: 90px;margin-bottom: 10px;margin-top: 10px" />
            <div  class="wrapDiv" style="color: #ffffff;font-size: larger;font-weight: bolder;font-family:tahoma,arial,宋体;">
                管理员
            </div>
        </div>
        <!---模块1-->
         <details>
            <summary>
                <div class="ItemWrapper" >
                        <div  class="ItemWrapperIn">
                             <img  class="ItemWrapperInImg"  src="<%=contextPath%>/resources/image/smallAdmin.png" >
                        </div>
                        <div  class="ItemWrapperInDiv">
                            会员管理
                        </div>
                </div>
            </summary>
            <div  class="ItemBodyWrapper">
                <div style="width: 80px"></div>
                <div   class="ItemBodyInner" >
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/gouserlist.do">
                            <div  id="1"  @click="changeColor" data-index="1">个人列表</div>
                        </a>
                    </div>
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/companylist.do"  >
                            <div  id="2"  @click="changeColor" data-index="2">企业列表</div>
                        </a>
                    </div>
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/shenhelist.do?confirmIf=pass">
                            <div  id="3"  @click="changeColor" data-index="3"> 实名认证</div>
                        </a>
                    </div>
                </div>
            </div>
         </details>
        <!---模块2-->
        <details>
            <summary>
                <div class="ItemWrapper" >
                    <div  class="ItemWrapperIn">
                        <img  class="ItemWrapperInImg"  src="<%=contextPath%>/resources/image/money.png" >
                    </div>
                    <div  class="ItemWrapperInDiv">
                        资金管理
                    </div>
                </div>
            </summary>
            <div  class="ItemBodyWrapper">
                <div style="width: 80px"></div>
                <div   class="ItemBodyInner">
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/zijinMingXi.do">
                            <div  id="4"  @click="changeColor" data-index="4"> 资金明细</div>
                        </a>
                    </div>
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/zijinZouShi.do">
                            <div  id="5"  @click="changeColor" data-index="5"> 资金走势</div>
                        </a>
                    </div>
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/redBaoTongji.do">
                            <div  id="6"  @click="changeColor" data-index="6">
                                红包统计
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </details>

        <!---一个模块-->
        <details>
            <summary>
                <div class="ItemWrapper" >
                    <div  class="ItemWrapperIn">
                        <img  class="ItemWrapperInImg"  src="<%=contextPath%>/resources/image/lovelove.png" >
                    </div>
                    <div  class="ItemWrapperInDiv">
                        项目管理
                    </div>
                </div>
            </summary>
            <div  class="ItemBodyWrapper">
                <div style="width: 80px"></div>
                <div   class="ItemBodyInner" >
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/xiangMuXiangQing.do">
                            <div  id="7"  @click="changeColor" data-index="7"> 项目列表</div>
                        </a>
                    </div>
                </div>
            </div>
        </details>
        <!---模块3-->
        <details>
            <summary>
                <div class="ItemWrapper" >
                    <div  class="ItemWrapperIn">
                        <img  class="ItemWrapperInImg"  src="<%=contextPath%>/resources/image/zixun.png" >
                    </div>
                    <div  class="ItemWrapperInDiv">
                        资讯管理
                    </div>
                </div>
            </summary>
            <div  class="ItemBodyWrapper">
                <div style="width: 80px"></div>
                <div   class="ItemBodyInner" >
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/newsList.do">
                            <div  id="8"  @click="changeColor" data-index="8">资讯列表</div>
                        </a>
                    </div>
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/addNews.do" >
                            <div  id="9"  @click="changeColor" data-index="9">增加资讯</div>
                        </a>
                    </div>
                </div>
            </div>
        </details>
        <!---一个模块-->
        <details>
            <summary>
                <div class="ItemWrapper" >
                    <div  class="ItemWrapperIn">
                        <img  class="ItemWrapperInImg"  src="<%=contextPath%>/resources/image/lovelove.png" >
                    </div>
                    <div  class="ItemWrapperInDiv">
                        互助管理
                    </div>
                </div>
            </summary>
            <div  class="ItemBodyWrapper">
                <div style="width: 80px"></div>
                <div   class="ItemBodyInner" >
                    <%--<div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/beHelpedlist.do">被救助人列表</a></div>--%>
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/addHelpMan.do">
                            <div  id="10"  @click="changeColor" data-index="10"> 增加一个求助人</div>
                        </a>
                    </div>
                    <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/goCostMoney.do"  >
                        <div  id="11"  @click="changeColor" data-index="11">执行扣钱</div>
                    </a></div>
                </div>
            </div>
        </details>
        <!---一个模块-->
        <details>
            <summary>
                <div class="ItemWrapper" >
                    <div  class="ItemWrapperIn">
                        <img  class="ItemWrapperInImg"  src="<%=contextPath%>/resources/image/lovelove.png" >
                    </div>
                    <div  class="ItemWrapperInDiv">
                        权限管理
                    </div>
                </div>
            </summary>
            <div  class="ItemBodyWrapper">
                <div style="width: 80px"></div>
                <div   class="ItemBodyInner" >
                    <%--<div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/beHelpedlist.do">被救助人列表</a></div>--%>
                    <div style="margin-bottom: 10px">
                        <a target="Conframe" href="/glove/grapeAdmin/goPermissionUser.do">
                            <div  id="12"  @click="changeColor" data-index="10">用户</div>
                        </a>
                    </div>
                    <div style="margin-bottom: 10px"><a target="Conframe" href="/glove/grapeAdmin/goPermissionRoler.do"  >
                        <div  id="13"  @click="changeColor" data-index="11">角色</div>
                    </a></div>
                </div>
            </div>
        </details>

    </div>
    <div class="content">
        <iframe class="page-ifream"  name="Conframe" id="Conframe" src="/glove/grapeAdmin/goDefaultMain.do"
                style="display: block;height: 100%; width: 100%; overflow-y:hidden;"
                frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="yes">
        </iframe>
    </div>
</div>
<script>
    new Vue({
        el: '#middle',
        data: {
            nowid:''
        },
        mounted: function () {

        },
        methods: {
            changeColor:function (event) {
                let index =event.target.getAttribute("data-index");
               this.switchColor(index)
            },

            switchColor:function (id) {
                if (this.nowid==''){
                    this.nowid=id;
                    document.getElementById(id).style.color="#237ea7";
                    return
                }else {
                    document.getElementById(id).style.color="#237ea7";
                    //console.log(this.nowid)
                    document.getElementById(this.nowid).style.color="white";
                    this.nowid=id;
                }
            }
        },

    });
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
        font-family:tahoma,arial,宋体;width: 120px;display: flex;flex-direction: row;
        justify-content: flex-start;align-items: center;height: 30px;
        cursor: pointer;
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
        height: 100%;
        border-right: rgba(42,255,48,0.82) 1px dashed;
        background-color: #2A3C4D;
        color: #ffffff;
        overflow:auto
    }
    .content{
        width: 80%;
        height: 90%;
    }
    summary::-webkit-details-marker {
        display: none;
    }
    summary{
        outline: none;
    }
    body{
        overflow-y:hidden;
    }
</style>

</body>
</html>
