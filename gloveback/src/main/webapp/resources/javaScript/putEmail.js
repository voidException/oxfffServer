
//let publicurl="http://localhost:8080"
//线上
//let publicurl="http://geilove.org";

new Vue({
    el: '#putEmial',
    data: {
        stopTag:0, // 防止用户多次点击的标志位
    },
    methods: {
        doSubmit: function () {
            // if(this.stopTag==1){
            //     //console.log("stopTag")
            //     return;
            // }
            // this.stopTag=1;
            let email=document.getElementById("phone").value;
            let verifycode=document.getElementById("verifycode").value;
            let password=document.getElementById("userPassword").value;

            let regx=/^1(3|4|5|7|8)\d{9}$/;
            let regP=/^[0-9|a-z|A-Z]\w{5,17}$/; //6-18w位数字和字母组成的密码

            if(email===null ||password===null ||email.length!=11 ||
                password.length<6  ||verifycode==null ||verifycode.length!=4 ||password.length>18|| !regx.test(email) || !regP.test(password)){
                //控制'您输入的邮箱或密码有误'  errorTips
                return ;
            }
            //这里应该加入，对邮箱进行校验的代码

            var userAccount = new FormData();
            userAccount.append('phone',email);
            userAccount.append('userPassword',password);
            userAccount.append("verifycode",verifycode);

            axios.post('/glove/phone/resetpass.do',userAccount,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                    console.log(response)

                if(response.data.retcode==2000){
                    this.stopTag=0;
                    //接下来，应该清除本地缓存，让用户重新登录，待完成
                    alert("密码修改成功");
                    setTimeout(function(){
                        window.location.href="/glove//path/pages/mobileMain.do" //跳转到首页
                    },100);

                }else {
                    this.stopTag=0;
                    alert(response.data);
                }

            }, err => {
                this.stopTag=0;
                alert(err)
            });

        },
        getVerifyCode: function () {
            //这个是获取验证码的
        }
    }
});