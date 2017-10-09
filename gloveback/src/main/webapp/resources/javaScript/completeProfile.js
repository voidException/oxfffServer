/**
 * Created by aihaitao on 20/5/2017.
 */

new Vue({
    el: '#completeProfile',
    data: {
        "userProfile": {},
    },
    methods: {
        fullFillProfile: function () {

            let token=localStorage.getItem("backupfour"); //
            let realName=document.getElementById("realName").value;
            let identity=document.getElementById("identity").value;
            let address=document.getElementById("address").value;
            let college=document.getElementById("college").value;
            let mobilePhone=document.getElementById("mobilePhone").value;
            let selfIntroduce=document.getElementById("selfIntroduce").value;
            if (token==null){
                alert("请登录");
               setTimeout(function () {
                   window.location.href="http://localhost:8080/glove/path/pages/mobileWo.do";
               },500)
                return;
            }
            if(realName=="" && identity==""  && address=="" && college=="" && mobilePhone==""){
                return alert("所有字段不得为空")
            }

            let param={
                token:token,
                realName:realName,
                identity:identity,
                address:address,
                college:college,
                mobilePhone:mobilePhone,
                selfIntroduce:selfIntroduce
            };
            this.$http.post('http://localhost:8080/glove/profile/completeProfileJSP.do',param).then(response => {
                //console.log(response.body);
                if(response.body.retcode==2000){
                    alert("更新成功");
                    window.location.href="http://localhost:8080/glove/path/pages/mobileWo.do";
                    return;
                }else {
                    alert("更新失败");
                }

            }, err => {
                //console.log(err);
                alert("更新出现异常")
            });

        }
    }
});