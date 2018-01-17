<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>资金统计</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>
        .contain{
            display: flex;
            flex-direction: row;
            justify-content: space-around;
            flex-wrap: nowrap;
            margin-top: 50px;

        }
        .item{
            display: flex;
            flex-direction: row;
        }
    </style>
</head>
<body>
<div id="zijinTongji">
    <div class="contain">
        <div>
            <div>企业员工大病互助</div>
            <div class="item">
                <div>总金额（元）</div>
                <div>{{staffDabing.sumMoney}}</div>
            </div>
            <div class="item">
                <div>加入人数</div>
                <div>{{staffDabing.sumMan}}</div>
            </div>
            <div class="item">
                <div>救助均摊金额（元）</div>
                <div>{{staffDabing.average}}</div>
            </div>
        </div>
        <div>
            <div>企业员工综合意外互助</div>
            <div class="item">
                <div>总金额（元）</div>
                <div>{{staffYiwai.sumMoney}}</div>
            </div>
            <div class="item">
                <div>加入人数</div>
                <div>{{staffYiwai.sumMan}}</div>
            </div>
            <div class="item">
                <div>救助均摊金额（元）</div>
                <div>{{staffYiwai.average}}</div>
            </div>
        </div>
        <div>
            <div>少儿大病互助</div>
            <div class="item">
                <div>总金额（元）</div>
                <div>{{littleDabing.sumMoney}}</div>
            </div>
            <div class="item">
                <div>加入人数</div>
                <div>{{littleDabing.sumMan}}</div>
            </div>
            <div class="item">
                <div>救助均摊金额（元）</div>
                <div>{{littleDabing.average}}</div>
            </div>
        </div>
    </div>

    <div class="contain">
        <div>
            <div>中青年抗癌互助</div>
            <div class="item">
                <div>总金额（元）</div>
                <div>{{young.sumMoney}}</div>
            </div>
            <div class="item">
                <div>加入人数</div>
                <div>{{young.sumMan}}</div>
            </div>
            <div class="item">
                <div>救助均摊金额（元）</div>
                <div>{{young.average}}</div>
            </div>
        </div>
        <div>
            <div>中老年抗癌互助</div>
            <div class="item">
                <div>总金额（元）</div>
                <div>{{old.sumMoney}}</div>
            </div>
            <div class="item">
                <div>加入人数</div>
                <div>{{old.sumMan}}</div>
            </div>
            <div class="item">
                <div>救助均摊金额（元）</div>
                <div>{{old.average}}</div>
            </div>
        </div>
        <div>
            <div>综合意外互助</div>
            <div class="item">
                <div>总金额（元）</div>
                <div>{{zongheYiwai.sumMoney}}</div>
            </div>
            <div class="item">
                <div>加入人数</div>
                <div>{{zongheYiwai.sumMan}}</div>
            </div>
            <div class="item">
                <div>救助均摊金额（元）</div>
                <div>{{zongheYiwai.average}}</div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/zijinTongji.js"></script>
</html>
