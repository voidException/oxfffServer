
//let publicurl="http://localhost:8080"
//线上
let publicurl="http://geilove.org";
new Vue({
    el: '#register',
    data: {
        "userProfile": {},
    },
    methods: {
        verify:function (obj) {

            //输入完密码，点击return时，校验邮箱和密码是否合法
            //设置3个布尔变量，校验通过为true，否则false
            let phone=obj.phone;
            let verifycode=obj.verifycode;
            let userPassword=obj.userPassword;
            let nickName=obj.nickName;
            let cityName=obj.cityName;

            let regx=/^[1][3,4,5,7,8][0-9]{9}$/;
            let regP=/^[0-9|a-z|A-Z]\w{5,17}$/; //6-18w位数字和字母组成的密码
            if(phone===null ||phone.length!=11 ){
                 alert("手机号长度不对");
                return 0;
            }
            if ( !regx.test(phone)){
                 alert("手机号格式不对");
                return 0;
            }
            if (userPassword!==null && userPassword.length>5 && userPassword.length<19 && !regP.test(userPassword)) {
                alert("密码不对");
                return 0;
            };

            if (nickName.length<2 || nickName.length>10) {
                 alert("昵称长度不对");
                return 0;
            };

            if (verifycode.length!==4) {
                 alert("验证码不对");
                return 0;
            };
            if (cityName.length <1 ||cityName.length>10) {
                 alert("城市名长度不对");
                return 0;
            };

        },

        doregister:function(event) {
            var phone=document.getElementById("phone").value;   //手机号
            let verifycode=document.getElementById("verifycode").value; //验证码
            var userPassword=document.getElementById("userPassword").value;   //密码
            var nickName=document.getElementById("nickName").value; //昵称
            var cityName=document.getElementById("cityName").value;   //城市

            let param={
                phone:phone,
                verifycode:verifycode,
                userPassword:userPassword,
                nickName:nickName,
                cityName:cityName
            };
            if ( this.verify(param)==0){
                return;
            }

            var userAccount = new FormData();
            userAccount.append('phone',phone);
            userAccount.append('userPassword',userPassword);
            userAccount.append("verifycode",verifycode);
            userAccount.append("nickName",nickName);
            userAccount.append("cityName",cityName);

            //接下来发送注册请求,后台接口需要写
            axios.post('/glove/phone/keeperregister.do',userAccount,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {

                if(response.data.retcode==2000){
                    alert("注册成功，请登录");
                    setTimeout(function(){
                        window.location.href="/glove/path/pages/login.do" //跳转到登录
                    },100);
                }else {
                    alert(response.data.msg);
                }

            }, err => {
                console.log(err);
                alert("服务出现异常");
            });


        },
    },

})
