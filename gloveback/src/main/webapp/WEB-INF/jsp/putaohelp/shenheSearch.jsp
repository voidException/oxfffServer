<%   String contextPath = request.getContextPath(); %>
<%@  page contentType="text/html;charset=UTF-8" language="java" %>
<%@  taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>
    <title>检索企业认证审核资料</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>
        .search{
            display: flex;
            flex-direction: row;
            width: 100%;
            justify-content: flex-start;
            align-items: center;
            height: 50px;
        }
        .searchInput{
            display: flex;
            width: 300px;
            height: 40px;
            justify-content: center;
            align-items: center;
        }
        .searchSubmit{
            display: flex;
            justify-content: center;
            align-items: center;
            height: 40px;
            width: 80px;
            background-color: #00BB3B;
            color: #ffffff;
        }
        .putaoauth{
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            width: 600px;
            height: 50px;
        }
    </style>
</head>
<body>
<div id="shenheSearch">
    <div class="search">
        <input   id="searchInput"  class="searchInput"  placeholder="根据企业名字进行搜索">
        <div   v-on:click="dogetPutaoauthSearch"  class="searchSubmit">确定</div>
    </div>
    <div id="putaoauth" class="putaoauth">
        <div>公司名字：</div>
        <div>{{oneData.name}}</div>
    </div>
    <div  class="putaoauth">
        <div>营业执照：</div>
        <div>{{oneData.numberid}}</div>
    </div>
    <div  class="putaoauth">
        <div>手机号：</div>
        <div>{{oneData.phone}}</div>
    </div>
    <div  class="putaoauth">
        <div>公司地址：</div>
        <div>{{oneData.address}}</div>
    </div>
    <div  class="putaoauth">
        <div>邮箱：</div>
        <div>{{oneData.email}}</div>
    </div>
    <div  class="putaoauth">
        <div>认证状态：</div>
        <div>{{oneData.confirmif}}</div>
    </div>
    <div  class="putaoauth">
        <div>通过/拒绝原因：</div>
        <div>{{oneData.comment}}</div>
    </div>
    <div  class="putaoauth">
        <div>法人姓名：</div>
        <div>{{oneData.legalperson}}</div>
    </div>

</div>
</body>
<script>

    new Vue({
        el: '#shenheSearch',
        data: {
            data:[],
            oneData:{},
        },
        mounted: function () {

        },
        methods: {

            getPutaoauthSearch:function (param) {

                //发送请求前先，隐藏弹出框，避免多次点击
                //发送网络请求
                axios.post('/glove/grapeAdmin/doshenheSearch.do',param,{
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }}).then(response => {
                    //console.log(response.data);
                    //存储或者改变相应的值
                    if (response.data.retcode==2000){
                        this.data=response.data.result;
                        this.oneData=response.data.result[0];
                    }

                }, err => {

                });
            }, //
            dogetPutaoauthSearch:function () {
                let name=document.getElementById("searchInput").value
                var param = new FormData();
                param.append('name',name);

                this.getPutaoauthSearch(param);
            },
        },

    });
</script>

</html>
