
//let publicurl="http://localhost:8080"
//线上
let publicurl="http://geilove.org";

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
            this.$http.post(publicurl+'/glove/user/findpassword.do',param).then(response => {

                if(response.body.retcode==2000){
                    this.stopTag=0;
                    //接下来，应该清除本地缓存，让用户重新登录，待完成
                    alert("请查看邮箱");
                    setTimeout(function(){
                        window.location.href=publicurl+"/glove/mobile.do" //跳转到首页
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