
//let publicurl="http://localhost:8080"
//线上
let publicurl="http://geilove.org";

new Vue({
    el: '#resetPasswd',
    data: {
        stopTag:0, // 防止用户多次点击的标志位
    },
    methods: {
        resetPasswd: function () {
             if(this.stopTag==1){
                 console.log("stopTag")
                 return;
             }
             this.stopTag=1;
            let userToken=localStorage.getItem("backupfour");

            //let token="fcea920f7412b5da7be0cf42b8c937591"
            let originPass=document.getElementById("originPass").value;
            let newPass=document.getElementById("newPass").value;
            let againPass=document.getElementById("againPass").value;


            if(userToken==null){
                this.stopTag=0;
                alert("请登录")
                return;
            }
            if(userToken.length<33){
                this.stopTag=0;
                alert("非法操作")
                return;
            }
            if(originPass==null || newPass==null || againPass==null){
                this.stopTag=0;
                return alert("不得为空")
            }
            if (newPass!=againPass){
                this.stopTag=0;
                return alert("两次密码不一致");
            }

            let regP=/^[0-9|a-z|A-Z]\w{5,17}$/; //6-18w位数字和字母组成的密码


            if(newPass.length<6 ||newPass.length>18|| !regP.test(newPass)){
                //控制'您输入的邮箱或密码有误'  errorTips
                return alert("密码不符合格式");
            }

            let param={
                token:userToken,
                originPass:originPass,
                newPass:newPass,
                againPass:againPass
            };
            this.$http.post(publicurl+'/glove/user/resetpass.do',param,"application/json").then(response => {

                if(response.body.retcode==2000){
                    this.stopTag=0;
                    //接下来，应该清除本地缓存，让用户重新登录，待完成
                    alert("重置密码成功");
                    setTimeout(function(){
                        window.location.href=publicurl+"/glove/path/pages/mobileWo.do" //跳转到首页
                    },100);

                }else {
                    this.stopTag=0;
                    alert("重置密码失败");
                }

            }, err => {
                this.stopTag=0;
                alert("重置密码出现异常")
            });

        }
    }
});