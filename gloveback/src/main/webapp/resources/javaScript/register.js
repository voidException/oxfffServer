/**
 * Created by aihaitao on 17/5/2017.
 */
/**
 * Created by aihaitao on 29/3/2017.
 */

new Vue({
    el: '#register',
    data: {
        "userProfile": {},
    },
    methods: {
        verify:function (obj) {

            //输入完密码，点击return时，校验邮箱和密码是否合法
            //设置3个布尔变量，校验通过为true，否则false
            let email=obj.userEmail;
            let password=obj.userPassword;
            let nickName=obj.userNickName;

            let regx=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            let regP=/^[0-9|a-z|A-Z]\w{5,17}$/; //6-18w位数字和字母组成的密码
            //let testEm='@567890qwertyui';
            //console.log(regP.test(testEm));
            let Vemail=false;
            let Vpass=false;
            let VnickName=false;

            if(email!==null && email.length<31 && email.length>9 && regx.test(email)){
                Vemail=true;
            }
            if (password!==null && password.length>5 && password.length<19 && regP.test(password)) {
                Vpass=true;
            };
            if (nickName.length>2 && nickName.length<31) {
                VnickName=true;
            };
            if (Vpass && Vemail && VnickName) {
                return true;
            }else{
                return false;
            }
        },

        doregister:function(event) {
            var registerEmailInput=document.getElementById("registerEmailInput").value;   //邮箱
            var registerPasswdInput=document.getElementById("registerPasswdInput").value;   //密码
            var registerNickNameInput=document.getElementById("registerNickNameInput").value; //昵称
            var registerCityInput=document.getElementById("registerCityInput").value;   //城市


            if (!this.verify(param)){
                return alert("输入有误");
            }
            if (registerCityInput.length>8 ||registerCityInput==null){
                return alert("城市名字有误")
            }
            //接下来发送注册请求
            let param={
                userNickName:registerNickNameInput,
                userEmail:registerEmailInput,
                userPassword:registerPasswdInput,
                cityName:registerCityInput,
            };

            this.$http.post('/user/register.do',param).then(response => {
                //console.log(response.body);
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
