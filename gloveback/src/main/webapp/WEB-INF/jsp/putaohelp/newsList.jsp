<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <title>新闻列表</title>
    <style>
         .itemWrapper{
             display: flex;
             flex-direction: row;
             justify-content: space-between;
             align-items: center;
             width: 700px;
             border-bottom: #00BB3B  1px  solid;
             padding-left: 10px;
         }
         .item{
             display: flex;
             flex-direction: row;
             justify-content: flex-start;
             align-items: center;
             width: 600px;
         }
         .delete{
              cursor: pointer;
             color: #0070FF;
         }
        .itemLeft{
            margin-right: 20px;
        }
    </style>
</head>
<body>
<div id="newsList" class="newsList">
    <template  class="temp" v-for="item in newsList">
        <div class="itemWrapper">
            <div>
                <div class="item">
                    <div class="itemLeft">作者:</div>
                    <div>{{item.author}}</div>
                </div>
                <div class="item">
                    <div class="itemLeft">标题:</div>
                    <div>{{item.title}}</div>
                </div>
                <div class="item">
                    <div class="itemLeft">副标题:</div>
                    <div>{{item.vicetitle}}</div>
                </div>
            </div>
            <div    v-on:click="deleteNews"  v-bind:data-uuid="item.newsuuid"  class="delete">删除</div>
        </div>
    </template>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/newsList.js"></script>
</html>
