/**
 * Created by aihaitao on 11/4/2017.
 * 这里是举报或者证实的逻辑处理的地方，通过data-RCTag 来区分是举报还是证实
 */

new Vue({
    el: '#modalReport',
    data: {
    },
    methods: {

        //根据给定的url中的name值参数，直接获取value值
        getUrlParam:function(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)
                return  unescape(r[2]);
            return null;
        },
        cancelReport:function (event) {
            let modalReportDiv=document.getElementById ("modalReport");
            modalReportDiv.style.display="none";
        },
        showDialog:function showDialog(msg){
            /*1.先隐藏模态框*/
            let  modal=document.getElementById("modal");
            let  tips=document.getElementById("tips");

            if (modal.style.display==""){
                modal.style.display="none";
                tips.style.display="none";
            }
            /*2.发送网络请求*/

            /*3.根据网络请求的结果,显示一个提示,1秒后消失*/
            let  dialog=document.getElementById("dialog");
            let  dialogTips=document.getElementById("dialogTips");

            if (dialog.style.display=="" || dialog.style.display=="none"){
                dialog.style.display="flex";
                dialogTips.style.display="block";
                document.getElementById("dialogTips").innerText=msg; //提示登录返回的结果
            }
            setTimeout(function(){
                dialog.style.display="none";
                dialogTips.style.display="none";
            },2000);

        },
        applyReport: function (event) {
            //console.log(event.target.attributes[0])
             //console.log(event.target.getAttribute("data-RCTag"))  //
            let dataRCTag=event.target.getAttribute("data-RCTag"); //获取tag


            let relationValue=document.getElementById ("reportInput").value;
            if (relationValue==null ||relationValue.length>15){
                return
            }
            let reportContent=document.getElementById ("reportTextarea").value;
            if (reportContent==null || reportContent.length>140){
                return
            }
            //从浏览器url中获取weibo的ID，从localStorage中获取token
            let  loginTag=localStorage.getItem("loginTag");
            if (loginTag!=="logined"){  //如果没登录，就让用户登录
                let  loginEmailDiv=document.getElementById("loginEmail");
                loginEmailDiv.style.display="block";
                return
            }
            let userToken=localStorage.getItem("userToken"); //token的值
            let tweetiD=this.getUrlParam("tweetiD");  //获取weibo的iD


            let paramReport={
                token:userToken,
                content:reportContent,
                relation:relationValue,
                tag:dataRCTag,
                tuiwenid:tweetiD
            };

            this.$http.post('http://localhost:8080/glove/confirm/report2.do',paramReport).then(response => {
                 console.log(response.body);
                //这里呢，要显示dialog，
                this.showDialog(response.body.msg); //显示登录结果
                this.cancelReport();

            }, err => {
                console.log(err);
                this.showDialog(err);
                this.cancelReport();

            });



        },
    },
})