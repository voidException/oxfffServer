/**
 * 到本页面，首先检查是否已经登录
 */

new Vue({
    el: '#mobileMainDiv',
    data: {
        userhelpsman:0,
        userdonate:0,
        amountaccept:0,
        acceptmoney:0,
        usernickname:"abc"
    },
    mounted:function(){//检查本地localStorage 是否登录，如果登录
        let useremail=localStorage.getItem("useremail");
        if(useremail==null){ //未登录
            let  notLogin=document.getElementById("notLogin");
            notLogin.style.display="block";

            let  hasLogin=document.getElementById("hasLogin");
            hasLogin.style.display="none";

        }else {
            let  hasLogin=document.getElementById("hasLogin");
            hasLogin.style.display="block";
            let  notLogin=document.getElementById("notLogin");
            notLogin.style.display="none";
            this.userhelpsman=localStorage.getItem("userhelpsman");
            this.userdonate=localStorage.getItem("userdonate");
            this.amountaccept=localStorage.getItem("amountaccept");
            this.acceptmoney=localStorage.getItem("acceptmoney");
            this.usernickname=localStorage.getItem("usernickname");
        }

    },
    methods: {

    }
});