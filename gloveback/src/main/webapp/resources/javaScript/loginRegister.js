//let publicurl="http://localhost:8080"
//线上
let publicurl="http://geilove.org";
new Vue({
    el: '#loginRegister',
    data: {
        "userProfile": {},
    },
    methods: {
        dologin:function (event) {
            var loginEmailInputValue=document.getElementById("loginEmailInput").value; //登录的email值
            var loginPasswdInputValue=document.getElementById("loginPasswdInput").value; //登录的密码值
            //接下来发送登录请求
            let param={
                userEmail:loginEmailInputValue,
                userPassword:loginPasswdInputValue,
            };
            this.$http.post(publicurl+'/glove/user/login.do',param).then(response => {
                console.log(response.body.data);
                this.userProfile=response.body.data; //存入数据库

                if(response.body.retcode==2000){

                    //设置其他用户信息
                    window.localStorage.setItem("userEmail",response.body.data.useremail);
                    window.localStorage.setItem("usernickname",response.body.data.usernickname);
                    window.localStorage.setItem("userphoto",response.body.data.userphoto);
                    window.localStorage.setItem("backupfour",response.body.data.backupfour);
                    window.location.href="http://www.geilove.org/";
                }else {
                    alert("登录失败");
                }

            }, err => {
                console.log(err);
            });

        },
        doregister:function(event) {
            var registerEmailInput=document.getElementById("registerEmailInput").value;   //邮箱
            var registerPasswdInput=document.getElementById("registerPasswdInput").value;   //密码
            var registerNickNameInput=document.getElementById("registerNickNameInput").value; //昵称
            var registerCityInput=document.getElementById("registerCityInput").value;   //城市

            //接下来发送注册请求
            let param={
                userNickName:registerNickNameInput,
                userEmail:registerEmailInput,
                userPassword:registerPasswdInput,
                cityName:registerCityInput,
            };
            this.$http.post(publicurl+'/glove/user/register.do',param).then(response => {
                console.log(response.body);
                this.userProfile=response.body.data;
                if(response.body.retcode==2000){
                    alert("注册成功，请登录");
                }else {
                    alert("注册失败");
                }

            }, err => {
                console.log(err);
                alert("注册失败")
            });


        },
    },

})
