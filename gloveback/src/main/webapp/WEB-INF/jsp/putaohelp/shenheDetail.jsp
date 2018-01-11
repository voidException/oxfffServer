<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审核详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
</head>
<body style="height: 2000px">
<div   id="shenheDetail" class="shenheDetail"  style="padding: 20px">

    <div id="useruuid" style="display: none">${data.useruuid}</div>

    <div class="puding">
        <div class="companyName">状态：</div>
        <div  id="confirmif">${data.confirmif}</div>
    </div>


    <div class="puding">
        <div class="companyName">公司名字：</div>
        <div>${data.name}</div>
    </div>

    <div class="puding">
       <div class="companyName">手机号：</div>
       <div>${data.phone}</div>
    </div>
    <div class="puding">
        <div class="companyName">地址：</div>
        <div>${data.address}</div>
    </div>
    <div class="puding">
        <div class="companyName">邮箱：</div>
        <div>${data.email}</div>
    </div>
    <div class="puding">
        <div class="companyName">通过/拒绝原因：</div>
        <div>${data.comment}</div>
    </div>
    <div class="puding">
        <div class="companyName">法人姓名：</div>
        <div>${data.legalperson}</div>
    </div>
    <div class="imagelist">
        <img src="http://101.200.33.138/staticImage/${data.imgtwo}"  style="height: 300px;width: 300px" />
        <img src="http://101.200.33.138/staticImage/${data.imgone}"  style="height: 300px;width: 300px" />
        <img src="http://101.200.33.138/staticImage/${data.imgthree}" style="height: 300px;width: 300px" />
    </div>

    <div id="passrefused"  class="passrefused">
        <textarea  id="comment" class="text-area" rows="10" cols="60" placeholder="请输入通过或拒绝的理由"></textarea>
        <div class="dopassRefused" >
            <div  v-on:click="pass"  class="pass" >通过</div>
            <div  v-on:click="refused"  class="pass" >拒绝</div>
        </div>

    </div>
</div>
<script src="<%=contextPath%>/resources/putaohelp/js/shenheDetail.js"></script>

</body>
<style>
    .puding{
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        width: 100%;
        height: 50px;
        background-color: #F3F2F5;
        margin-top: 1px;
    }
    .companyName{
        display: flex;
        flex-direction: row;
        padding-left: 20px;
    }
    .passutton{
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        width: 100%;
        height: 50px;
        background-color: #F3F2F5;
        margin-top: 1px;
    }
    button{
        width: 100px;
        height: 65px;
    }

    .passrefused{
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items:flex-end;

    }
    .imagelist{
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
    }
    .text-area{
        width: 100%;
        height: 100px;
    }
    .dopassRefused{
        display: flex;
        flex-direction: row;
        justify-content: center;
        width: 100%;
    }
    .pass{
        display: flex;
        width: 49%;
        height: 50px;
        background-color: #1ab394;
        color: white;
        margin: 10px;
        justify-content:center ;
        align-items: center;
    }

</style>

</html>
