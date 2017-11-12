
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

            let regx=/^1(3|4|5|7|8)\d{9}$/;
            let regP=/^[0-9|a-z|A-Z]\w{5,17}$/; //6-18w位数字和字母组成的密码
            if(phone===null ||phone.length!=11 ){
                return alert("手机号长度不对");
            }
            if ( regx.test(phone)){
                return alert("手机号格式不对")
            }
            if (userPassword!==null && userPassword.length>5 && userPassword.length<19 && regP.test(userPassword)) {
               return alert("密码不对")
            };

            if (nickName.length>2 && nickName.length<31) {
                return alert("昵称长度不对")
            };

            if (verifycode.length!==4) {
                return alert("验证码不对")
            };
            if (cityName.length <1 ||cityName.length>10) {
                return alert("城市名长度不对")
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

            this.verify(param);
            var userAccount = new FormData();
            userAccount.append('phone',phone);
            userAccount.append('userPassword',userPassword);
            userAccount.append("verifycode",verifycode);
            userAccount.append("nickName",nickName);
            userAccount.append("cityName",cityName);

            //接下来发送注册请求,后台接口需要写
            this.$http.post(publicurl+'/user/register.do',userAccount,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //console.log(response.body);
                this.userProfile=response.body.data;
                if(response.data.retcode==2000){
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
