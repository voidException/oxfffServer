/**
 * Created by aihaitao on 12/4/2017.
 * 对评论或者回复进行发送用的
 */
new Vue({
    el: '#modal',
    data: {},
    methods:{
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
        cancelPinglun:function (){
            let  modal=document.getElementById("modal");
            let  tips=document.getElementById("tips");
            modal.style.display="none";
            tips.style.display="none";
        },
        replyPinglun:function(){
            /*1.先判断用户是否已经登录*/
            let  loginTag=localStorage.getItem("loginTag");
            if (loginTag!=="logined"){  //如果没登录，就让用户登录
                let  loginEmailDiv=document.getElementById("loginEmail");
                loginEmailDiv.style.display="block";
                return
            }

            //获取评论的内容
            let content=document.getElementById ("commentText").value;
            if (content==null ||content.length>140){
                return;
            }
            //获取评论人sender的信息(也就是当前登录用户)
            let useridsender=localStorage.getItem("userid"); //获取评论人的userid
            let useruuidsender=localStorage.getItem("useruuid");//获取评论人的uuid
            let sendernickname=localStorage.getItem("usernickname"); //评论人的昵称
            let senderPhoto=localStorage.getItem("userphoto");
            //获取公共请求参数
            let  replyDiv = document.getElementById ("reply");
            let  userToken=localStorage.getItem("userToken"); //token的值
            let  useridreciver=replyDiv.getAttribute("data-useridreciver"); //获取"被评论"用户的userid
            let  useruuidreciver=replyDiv.getAttribute("data-useruuidreciver");
            let  recivernickname=replyDiv.getAttribute("data-recivernickname");
            //let  reciverphoto=replyDiv.getAttribute("data-reciverphoto"); //后台不需要

            let  dataUpdateORdynamic = replyDiv.getAttribute ("data-updateORdynamic");
            let  dataRefer = replyDiv.getAttribute ("data-refer");
            let  dataReferInteger=parseInt(dataRefer); //转换为数字0或者1
            /*2.判断是属于进度更新还是支持了列表*/
            if (dataUpdateORdynamic=="progressUpdate"){ //这个是对进度评论
                //获取更新进度的主键
                let itemprogressid=replyDiv.getAttribute("data-itemprogressid");
                let  param={
                    token:userToken,
                    itemprogressid:itemprogressid,
                    useridsender:useridsender,
                    useruuidsender:useruuidsender ,
                    sendernickname:sendernickname,
                    senderphoto:senderPhoto,
                    useridreciver:useridreciver,
                    useruuidreciver:useruuidreciver,
                    recivernickname:recivernickname,
                    refer:dataReferInteger,
                    content:content
                };
                this.$http.post('http://localhost:8080/glove/itemprogress/addupdatecomment.do',param).then(response => {
                    console.log(response.body);
                    this.cancelPinglun();
                    //这里呢，要显示dialog，
                    this.showDialog("评论成功"); //显示登录结果

                }, err => {
                    this.cancelPinglun();
                    this.showDialog("登录出现异常");
                });
            };
            if (dataUpdateORdynamic=="dynamic"){ //对支持了进行评论
                let moneysourceid= replyDiv.getAttribute("data-moneysourceid");
                let  param={
                    token:userToken,
                    moneysourceid:moneysourceid,
                    useridsender:useridsender,
                    //useruuidsender:useruuidsender ,
                    sendernickname:sendernickname,
                    //senderphoto:senderPhoto,
                    useridreciver:useridreciver,
                    //useruuidreciver:useruuidreciver,
                    recivernickname:recivernickname,
                    refer:dataReferInteger,
                    pingluntext:content
                };
                this.$http.post('http://localhost:8080/glove/moneysource/addmoneysourcecomment.do',param).then(response => {
                    //console.log(response.body);
                    this.cancelPinglun();
                    //这里呢，要显示dialog，
                    this.showDialog(response.body.msg); //显示登录结果

                }, err => {
                    this.cancelPinglun();
                    this.showDialog("登录出现异常");
                });

            };
        }
    }
});