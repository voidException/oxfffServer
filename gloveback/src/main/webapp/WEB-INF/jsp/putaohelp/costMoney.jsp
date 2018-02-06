<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>执行扣钱</title>
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
        .submit{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            width: 400px;
            height: 46px;
            background-color: #00C29A;
            color: #ffffff;
            font-size: 18px;

        }
        .submitCon{
            display: flex;
            width: 460px;
            justify-content: center;
            align-items: center;
            height: 50px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div id="costMoney">
        <div class="itemBig">
            <div class="itemLeft">姓名：</div>
            <input  id="name"  class="common" type="text"  autocomplete="off"  min="0" max="200" placeholder="">
        </div>
        <div class="itemBig">
            <div class="itemLeft">身份证号：</div>
            <input  id="account"  class="common" type="text"  autocomplete="off"  min="0" max="200" placeholder="">
        </div>
        <div class="itemBig">
            <div class="itemLeft">手机号：</div>
            <input  id="phone"  class="common" type="text"  autocomplete="off"  min="0" max="200" placeholder="用户的手机号">
        </div>
        <div class="item">
            <div class="itemLeft">互助计划：</div>
            <select id="ide" style="width: 200px">
                <option value="staff" >企业员工大病互助计划</option>
                <option value="employee">企业员工意外伤害互助计划</option>
                <option value="little">少儿健康互助</option>
                <option value="young">中青年抗癌互助计划</option>
                <option value="old">中老年抗癌互助计划</option>
                <option value="zonghe">综合意外互助计划</option>
            </select>
        </div>
        <div class="itemBig">
            <div class="itemLeft">所需要钱数：</div>
            <input  id="money"  class="common" type="text"  autocomplete="off"  min="0" max="200" placeholder="必须为整数">
        </div>
        <div class="submitCon">
            <div  v-on:click="addNews"  class="submit">提交</div>
        </div>
</div>
<script>
    new Vue({
        el: '#costMoney',
        data: {
        },
        mounted: function () {

        },
        methods: {

            costMoney:function () {
                //获得文章的信息
                let name=document.getElementById("name").value;
                let account=document.getElementById("account").value;
                let phone=document.getElementById("phone").value;
                let helpType=document.getElementById("ide").value;
                let money=document.getElementById("money").value;

                let param=new  FormData();
                param.append("name",name);
                param.append("account",account);
                param.append("phone",phone);
                param.append("helpType",helpType);
                param.append("money",money);

                if (helpType=="staff" || helpType=="employee"){ //企业互助扣钱
                    axios.post('/glove/grapeAdmin/costMoneyStaff.do',param,{
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }}).then(response => {

                        //存储或者改变相应的值
                        if (response.data.retcode==2000){
                            alert("扣钱成功")
                        }else {

                        }
                    }, err => {

                    });
                }else {
                    axios.post('/glove/grapeAdmin/costMoney.do',param,{
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }}).then(response => {

                        //存储或者改变相应的值
                        if (response.data.retcode==2000){
                            alert("扣钱成功")
                        }else {

                        }
                    }, err => {

                    });
                } //else
            }, //costMoney
        }, //method

    });
</script>
</body>
</html>
