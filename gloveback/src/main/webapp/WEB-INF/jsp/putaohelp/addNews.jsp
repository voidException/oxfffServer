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
            height: 50px;
        }
        .itemBig{
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-items: center;
            padding-left: 10px;
            height: 60px;
        }
        .itemLeft{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            float: left;
            font-size: 14px;
            color: #48576a;
            line-height: 1;
            padding: 11px 12px 11px 0;
            box-sizing: border-box;
            width: 100px;
        }
        .common{
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            background-color: #fff;
            border-radius: 4px;
            border: 1px solid #bfcbd9;
            box-sizing: border-box;
            color: #1f2d3d;
            display: block;
            font-size: inherit;
            height: 36px;
            line-height: 1;
            outline: 0;
            padding: 3px 10px;
            font-size: 12px;
            transition: border-color .2s cubic-bezier(.645,.045,.355,1);
            width: 200px;
        }

        .eltextarea {
            display: block;
            resize: vertical;
            padding: 5px 7px;
            line-height: 1.5;
            color: #1f2d3d;
            background-color: #fff;
            width: 400px;
            border: 1px solid #bfcbd9;
            border-radius: 4px;
            transition: border-color .2s cubic-bezier(.645,.045,.355,1);
        }
        .submit{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            width: 400px;
            height: 30px;
            background-color: #00BB3B;
            color: #ffffff;
            font-size: 18px;
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

    <div class="itemBig">
        <div class="itemLeft">新闻标题：</div>
        <textarea  id="title"  class="common" type="text"  autocomplete="off"  min="0" max="200" placeholder=""></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">副标题：</div>
        <textarea  id="vicetitle" class="common" type="text"  autocomplete="off"  min="0" max="200"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">作者：</div>
        <textarea  id="author" class="common" type="text"  autocomplete="off"  min="0" max="200"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">来源：</div>
        <textarea  id="source" class="common" type="text"  autocomplete="off"  min="0" max="200" placeholder=""></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">图片地址：</div>
        <textarea  id="imageone" class="eltextarea" type="text"  autocomplete="off"  min="0" max="200" placeholder="七牛云图片url粘贴在这里"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">对应公众号的Url：</div>
        <textarea  id="newsurl"  class="eltextarea" type="text"  autocomplete="off"  min="0" max="200" placeholder="文章的url粘贴在这里"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">类型：</div>
        <textarea  id="newstype"  class="common" type="text"  autocomplete="off"  min="0" max="200" placeholder="1或者2"></textarea>
    </div>
    <div class="submitCon">
        <div  v-on:click="addNews"  class="submit">提交</div>
    </div>

</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/addNews.js"></script>
</html>
