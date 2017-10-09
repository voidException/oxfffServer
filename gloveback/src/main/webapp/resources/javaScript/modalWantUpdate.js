/**
 * Created by aihaitao on 12/4/2017.
 * 对评论或者回复进行发送用的
 */
new Vue({
    el: '#wantUpdate',
    data: {},
    methods:{
        showDialog2:function showDialog(msg){
            /*1.先隐藏模态框*/
            let  modal=document.getElementById("wantUpdate");
            let  tips=document.getElementById("needUpdate");
            modal.style.display="none";
            tips.style.display="none";
            /*2.发送网络请求*/

            /*3.根据网络请求的结果,显示一个提示,1秒后消失*/
            let  dialog=document.getElementById("dialog");
            let  dialogTips=document.getElementById("dialogTips");

            dialog.style.display="flex";
            dialogTips.style.display="block";
            document.getElementById("dialogTips").innerText=msg; //提示登录返回的结果

            setTimeout(function(){
                dialog.style.display="none";
                dialogTips.style.display="none";
            },2000);
        },
        getUrlParam:function(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)
                return  unescape(r[2]);
            return null;
        },
        cancelWantUpdate:function (){
            let  modal=document.getElementById("wantUpdate");
            let  tips=document.getElementById("needUpdate");
            modal.style.display="none";
            tips.style.display="none";
        },
        doUpdate:function(){
            /*1.先判断用户是否已经登录*/
            let  loginTag=localStorage.getItem("loginTag");
            if (loginTag!=="logined"){  //如果没登录，就让用户登录
                let  loginEmailDiv=document.getElementById("loginEmail");
                loginEmailDiv.style.display="block";
                return
            }

            //获取要更新的内容
            let content=document.getElementById ("updateTxt").value;
            if (content==null ||content.length>140){
                return;
            }
            //获取更新人的信息(也就是当前登录用户)
            //token  userid  userphoto usernickname
            let  token=localStorage.getItem("userToken");
            let  userid=localStorage.getItem("userid"); //获取评论人的userid
            let  useruuid=localStorage.getItem("userUUID");//获取评论人的uuid
            let  usernickname=localStorage.getItem("usernickname"); //评论人的昵称
            let  userPhoto=localStorage.getItem("userphoto");
            let  cashUUID=this.getUrlParam("cashUUID")

            let  param={
                token:token,
                userid:userid,
                useruuid:useruuid,
                usernickname:usernickname,
                userphoto:userPhoto,
                cashUUID:cashUUID,
                content:content,
            };
            this.$http.post('http://localhost:8080/glove/itemprogress/addupdate.do',param).then(response => {
                //console.log(response.body);
                this.cancelWantUpdate();
                //这里呢，要显示dialog，
                this.showDialog2(response.body.msg); //显示登录结果

            }, err => {
                this.cancelWantUpdate();
                this.showDialog2("登录出现异常");
            });
        }//doUpdate
    }
});