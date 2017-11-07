


//let publicurl="http://localhost:8080"
//线上
let publicurl="http://geilove.org";

new Vue({
    el: '#setting',
    data: {},
    mounted:function(){
        let useremail=localStorage.getItem("useremail");
        if(useremail==null){ //未登录
            let  setBody=document.getElementById("setBody");
            setBody.style.display="none";
            window.location.href=publicurl+"/glove/path/pages/mobileWo.do"

        }
    },
    methods:{
        logOut:function (){
            console.log("logout")
            localStorage.clear();
            window.location.href=publicurl+"/glove/path/pages/mobileWo.do"
        }
    }

});

