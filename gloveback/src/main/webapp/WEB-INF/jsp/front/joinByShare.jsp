<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户分享注册</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <script src="https://unpkg.com/vue"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

</head>
<body>
<div style="display: flex;flex-direction: row;align-items: center;justify-content: center;margin-top: 60px">
    <img src="http://oztdsemro.bkt.clouddn.com/putaohuzhu/grapelogo.png" style="width: 100px;height: 100px;border-radius:50px ">
</div>

<!--这里是注册-->
<div  id="joinByShare" class="MaxDiv">
    <div class="peopleMessage">
        <div  style="width: 30%;font-size: 13px;text-align: center">手   机   号</div>
        <input  id="phone"  class="MessageInput"   name="phone"  type="text" value="" placeholder="输入11位手机号"/>
    </div>

    <div class="peopleMessage">
        <div style="width: 30%;font-size: 13px;text-align: center">验   证    码</div>
        <input  id="verifycode"  class="MessageInput" style="width: 35%"   name="newPass"  type="text" value="" placeholder="输入验证码"/>
        <button onclick="getCode()" id="timeId"  style="font-size: 12px;width: 35%;color:#1296db" >
            获取验证码
        </button>
    </div>

    <div class="peopleMessage">
        <div  style="width: 30%;font-size: 13px;text-align: center">密   码</div>
        <input  id="userPass" class="MessageInput"  placeholder="请输入您的密码"/>
    </div>


    <button class="MessageButton"    v-on:click="doregister" >注  册</button>
</div>


</body>
<style type="text/css">
    .MaxDiv {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 350px;
    }

    .peopleMessage {
        display: flex;
        flex-direction: row;
        align-items: center;
        margin-top: 10px;
        width: 90%;
        height: 40px;
        border-bottom: #ccc solid 1px;
    }

    .MessageInput {
        outline: 0;
        border: white solid 1px;
        margin-left: 5px;
        width: 70%;
        height: 25px;
        border: 0;
        color: #4a4a4a;

    }

    .MessageButton {
        border: #1296db solid 1px;
        width: 100%;
        height: 40px;
        margin-top: 30px;
        background-color: #1296db;
        color: white;
    }

    .checkTeamInput{
        height: 40px;
        width: 80%;
        border-radius: 4px;
        border:0px solid #FFFFFF ;
        font-size: 13px;
        margin-left: 10px;
        text-align: left;
        color:#CCCCCC ;
    }
    .checkTeamInput2{
        height: 40px;
        width: 50%;
        border-radius: 4px;
        border:0px solid #FFFFFF ;
        font-size: 13px;
        margin-left: 10px;
        text-align: left;
        color:#CCCCCC ;
    }
    .registerWrapper{
        display: flex;
        flex-direction: column;
        border-radius: 5px;
        justify-content: center;
        align-items: center;
    }

    input{
        height: 34px;
        width: 180px;
        border-radius: 4px;
        border:0px solid #FFFFFF ;
        font-size: 13px;
    }
    .doRegister{
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
        width: 100%;
        background:#00BB3B ;
        border-radius: 5px;
        height: 40px;
        margin-top: 20px;
        cursor: pointer;
        color: #ffffff;
    }
</style>
<script>
    /**
     获取验证码服务
     */

    var maxTime = 60;

    function timeDown() {

        var nowTime;
        var timer = setInterval(() => {
            nowTime=--maxTime;
            document.getElementById('timeId').innerHTML=nowTime+'秒再次获取';
            if (nowTime === 0) {
                clearInterval(timer);
                maxTime=60;
                document.getElementById('timeId').innerHTML='获取验证码'
                document.getElementById('timeId').disabled=false
                // document.getElementById('timeId').style.backgroundColor='#00BB3B'

            }
        }, 1000)
    }
    function getCode() {
        let phone= document.getElementById('phone').value;
        if(phone==null||phone==''){
            alert("手机号格式不正确")
            return
        }
        timeDown();//首先调用定时器，开始倒计时
        document.getElementById('timeId').disabled=true
        // document.getElementById('timeId').style.backgroundColor='#CCCCCC'
        let userAccount = new FormData();

        userAccount.append('phone',phone);
        //接下来发送注册请求,后台接口需要写
        axios.post('/glove/phone/getCode.do',userAccount,{
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }}).then(response => {

            if(response.data.retcode==2000){

            }else {
                alert(response.data.msg);
            }

        }, err => {
            console.log(err);
            alert("服务出现异常");
        });

    }
</script>
<script>
    /**
     * Created by songshifeng on 20/11/2017.
     */

    new Vue({
        el: '#joinByShare',
        data: {
            "userProfile": {},
        },
        methods: {
            verify: function (obj) {

                //输入完密码，点击return时，校验邮箱和密码是否合法
                //设置3个布尔变量，校验通过为true，否则false
                let phone = obj.phone;
                let verifycode = obj.verifycode;
                let userPass = obj.userPass;
                let useruuid = obj.useruuid;

                let regx = /^[1][3,4,5,7,8][0-9]{9}$/;
                let regP = /^[0-9|a-z|A-Z]\w{5,17}$/; //6-18w位数字和字母组成的密码
                if (phone === null || phone.length != 11) {
                    alert("手机号长度不对");
                    return 0;
                }
                if (!regx.test(phone)) {
                    alert("手机号格式不对");
                    return 0;
                }


                if (userPass!==null && userPass.length>5 && userPass.length<19 && !regP.test(userPass)) {
                    alert("密码不对");
                    return 0;
                };

                if (verifycode.length !== 4) {
                    alert("验证码不对");
                    return 0;
                }
                ;

            },

            GetQueryString: function (name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null)
                    return unescape(r[2]);
                return null;
            },
            doregister: function (event) {
                var phone = document.getElementById("phone").value;   //手机号
                let verifycode = document.getElementById("verifycode").value; //验证码
                var userPass = document.getElementById("userPass").value;   //密码
                var useruuid=this.GetQueryString("useruuid")
                let param = {
                    phone: phone,
                    verifycode: verifycode,
                    userPass: userPass,
                    useruuid:useruuid,
                };
                if (this.verify(param) == 0) {
                    return;
                }

                var userAccount = new FormData();
                userAccount.append('phone', phone);
                userAccount.append('userPassword', userPass);
                userAccount.append("verifycode", verifycode);
                userAccount.append("shareUserUUID", useruuid);

                //接下来发送注册请求,后台接口需要写
                axios.post('/glove/phone/register.do', userAccount, {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(response => {

                    if (response.data.retcode == 2000) {
                        alert("注册成功");
                    } else {
                        alert(response.data.msg);
                    }

                }, err => {
                    console.log(err);
                    alert("服务出现异常");
                });


            }
            ,
        },

    })

</script>
</html>
