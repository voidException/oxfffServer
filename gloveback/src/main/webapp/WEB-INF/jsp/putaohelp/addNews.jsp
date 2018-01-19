<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>增加一篇新闻</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>
        .item{
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-items: center;
            padding-left: 10px;
            width: 460px;
            border-bottom: #00BB3B 1px solid;
        }
        .itemLeft{
            width: 160px;
            height: 60px;
        }

        textarea{
            width: 300px;
            height: 60px;
            border-top: #ffffff ;
            border-bottom: #ffffff;
            border-left:#00BB3B 1px solid;
            border-right: #00BB3B 1px solid;
        }
        .submit{
            width: 300px;
            height: 30px;
            text-align: center;
            vertical-align:middle;
            background-color: #00BB3B;
            color: #ffffff;
        }
        .submitCon{
            display: flex;
            width: 460px;
            justify-content: center;
            align-items: center;
            height: 50px;
        }
    </style>
</head>
<body>
<div  id="addNews">

    <div class="item">
        <div class="itemLeft">新闻的标题：</div>
        <textarea  id="title" placeholder=""></textarea>
    </div>
    <div class="item">
        <div class="itemLeft">副标题：</div>
        <textarea  id="vicetitle" placeholder=""></textarea>
    </div>
    <div class="item">
        <div class="itemLeft">作者：</div>
        <textarea  id="author" placeholder=""></textarea>
    </div>
    <div class="item">
        <div class="itemLeft">来源：</div>
        <textarea  id="source" placeholder=""></textarea>
    </div>
    <div class="item">
        <div class="itemLeft">图片地址：</div>
        <textarea  id="imageone" placeholder="七牛云图片url粘贴在这里"></textarea>
    </div>
    <div class="item">
        <div class="itemLeft">对应公众号的Url：</div>
        <textarea  id="newsurl"  placeholder="文章的url粘贴在这里"></textarea>
    </div>
    <div class="item">
        <div class="itemLeft">类型：</div>
        <textarea  id="newstype"  placeholder="1或者2"></textarea>
    </div>
    <div class="submitCon">
        <div  v-on:click="addNews"  class="submit">提交</div>
    </div>

</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/addNews.js"></script>
</html>
