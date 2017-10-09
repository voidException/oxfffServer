<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>输入邮箱</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/putEmail.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body style="background-color: #E1E5E7">
<div id="putEmial" style=" margin-top: 50px">
    <div class="checkTeam">
        <input  id="userEmail"  class="checkTeamInput"   name="newPass"  type="text" value="" placeholder="请输入注册邮箱"/>
        <div class="checkTeamTxt" v-on:click="putEmail">发送</div>
    </div>
</div>
</body>
<script>

    new Vue({
        el: '#putEmial',
        data: {
            stopTag:0, // 防止用户多次点击的标志位
        },
        methods: {
            putEmail: function () {
                if(this.stopTag==1){
                    //console.log("stopTag")
                    return;
                }
                this.stopTag=1;
                let email=document.getElementById("userEmail").value;
                let regx=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                if (email==null ||email.length<10 || email.length>30  ||!regx.test(email)){
                    this.stopTag=0;
                    return  alert("输入邮箱有误");
                }
                //这里应该加入，对邮箱进行校验的代码
                let param={
                    userEmail:email,
                };
                this.$http.post('http://localhost:8080/glove/user/findpassword.do',param).then(response => {

                    if(response.body.retcode==2000){
                        this.stopTag=0;
                        //接下来，应该清除本地缓存，让用户重新登录，待完成
                        alert("请查看邮箱");
                        setTimeout(function(){
                            window.location.href="www.geilove.org/mobile" //跳转到首页
                        },100);

                    }else {
                        this.stopTag=0;
                        alert("发送失败");
                    }

                }, err => {
                    this.stopTag=0;
                    alert("发送出现异常")
                });

            }
        }
    });
</script>
</html>
