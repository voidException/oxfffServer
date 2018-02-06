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
            height: 70px;
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
            height: 46px;
            background-color: #00C29A;
            color: #ffffff;
            font-size: 18px;
            margin-top: 10px;
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
<div  id="addHelpMan">

    <div class="item">
        <div class="itemLeft">姓名：</div>
        <input id="name"  class="common" type="text"  autocomplete="off"  min="0" max="200" placeholder="" />
    </div>
    <div class="item">
        <div class="itemLeft">身份证号：</div>
        <input  id="userIdentity"  class="common"  type="text"  autocomplete="off"  min="0" max="200"placeholder="" />
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
    <div class="item">
        <div class="itemLeft">加入日期：</div>
        <input  id="joinDate"  class="common"  type="date"  autocomplete="off"  min="0" max="200"placeholder="" />
    </div>
    <div class="item">
        <div class="itemLeft">生效日期：</div>
        <input  id="effectDate"  class="common"  type="date"  autocomplete="off"  min="0" max="200"placeholder="" />
    </div>
    <div class="item">
        <div class="itemLeft">所需钱数：</div>
        <input  id="needMoney"  class="common" type="text"  autocomplete="off"  min="0" max="200" placeholder="" />
    </div>
    <div class="itemBig">
        <div class="itemLeft">事件概况：</div>
        <textarea  id="description"  class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">调查过程1：</div>
        <textarea  id="diaoChaProcess1"  class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">调查过程2：</div>
        <textarea  id="diaoChaProcess2"  class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">调查过程3：</div>
        <textarea  id="diaoChaProcess3"  class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">调查过程4：</div>
        <textarea  id="diaoChaProcess4"  class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">互助详情1：</div>
        <textarea  id="helpDetailOne"   class="eltextarea" rows="3"></textarea>
    </div>

    <div class="itemBig">
        <div class="itemLeft">互助详情2：</div>
        <textarea  id="helpDetailTwo"   class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">图片1：</div>
        <textarea  id="img1"   class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">图片2：</div>
        <textarea  id="img2"   class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">图片3：</div>
        <textarea  id="img3" class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">图片4：</div>
        <textarea  id="img4"  class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">图片5：</div>
        <textarea  id="img5"  class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">图片6：</div>
        <textarea  id="img6"  class="eltextarea" rows="3"></textarea>
    </div>

    <div class="itemBig">
        <div class="itemLeft">图片7：</div>
        <textarea  id="img7"  class="eltextarea" rows="3"></textarea>
    </div>
    <div class="itemBig">
        <div class="itemLeft">图片8：</div>
        <textarea  id="img8"  class="eltextarea" rows="3"></textarea>
    </div>
    <div class="submitCon">
        <div  v-on:click="addNews"  class="submit">提交</div>
    </div>

</div>
</body>
<script>


    new Vue({
        el: '#addHelpMan',
        data: {
        },
        mounted: function () {

        },
        methods: {

            addNews:function () {
                //获得文章的信息
                let name=document.getElementById("name").value;
                let userIdentity=document.getElementById("userIdentity").value;
                let categoryType = document.getElementById("ide").value;
                let joinDate=document.getElementById("joinDate").value;
                let effectDate=document.getElementById("effectDate").value;
                console.log(joinDate)
                console.log(effectDate)
                let needMoney=document.getElementById("needMoney").value;
                let description=document.getElementById("description").value;
                let diaoChaProcess1=document.getElementById("diaoChaProcess1").value;
                let diaoChaProcess2=document.getElementById("diaoChaProcess2").value;
                let diaoChaProcess3=document.getElementById("diaoChaProcess3").value;
                let diaoChaProcess4=document.getElementById("diaoChaProcess4").value;
                let helpDetailOne=document.getElementById("helpDetailOne").value;
                let helpDetailTwo=document.getElementById("helpDetailTwo").value;
                let img1=document.getElementById("img1").value;
                let img2=document.getElementById("img2").value;
                let img3=document.getElementById("img3").value;
                let img4=document.getElementById("img4").value;
                let img5=document.getElementById("img5").value;
                let img6=document.getElementById("img6").value;
                let img7=document.getElementById("img7").value;
                let img8=document.getElementById("img8").value;

                let param=new  FormData();
                param.append("name",name);
                param.append("userIdentity",userIdentity);
                param.append("helpType",categoryType);
                param.append("joinDate",joinDate);
                param.append("effectDate",effectDate);
                param.append("description",description);
                param.append("diaoChaProcess1",diaoChaProcess1);
                param.append("diaoChaProcess2",diaoChaProcess2);
                param.append("diaoChaProcess3",diaoChaProcess3);
                param.append("diaoChaProcess4",diaoChaProcess4);
                param.append("img1",img1);
                param.append("img2",img2);
                param.append("img3",img3);
                param.append("img4",img4);
                param.append("img5",img5);
                param.append("img6",img6);
                param.append("img7",img7);
                param.append("img8",img8);

                axios.post('/glove/grapeAdmin/doAddHelpMan.do',param,{
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }}).then(response => {

                    //存储或者改变相应的值
                    if (response.data.retcode==2000){
                        this.deleteTag=response.data.result;
                        alert("发布成功")
                    }else {

                    }
                }, err => {

                });

            },


        },

    });

</script>
</html>
