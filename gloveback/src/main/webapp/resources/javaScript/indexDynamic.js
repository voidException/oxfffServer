
//let publicurl="http://localhost:8080"
//线上
let publicurl="http://geilove.org";

//动态更新
new Vue({
    el: '#dynamic',
    data: {
        msg: '',
        retcode:'',
        lp:[],
        updateORprogress:true,
        updateORprogress2:false,
    },
    methods: {
        commentToDynamic:function(event){//这里是对直接对更新的评论
            /* 1. 让弹出框先弹出来*/
            let  modal=document.getElementById("modal");
            let  tips=document.getElementById("tips");
            modal.style.display="block";
            tips.style.display="block";

            /* 2. 告诉弹出框这个是进度更新的还是支持了列表的*/
            let  replyDiv = document.getElementById ("reply");
            replyDiv.setAttribute("data-updateORdynamic","dynamic"); //progressUpdate代表是进度更新，dynamic代表的是支持动态
            replyDiv.setAttribute("data-refer","0");  //0代表回复进度更新人，或者是回复直接回复捐钱人

            /* 3.把 弹出框需要的reciverNickname，userUUIDReciver等信息放置到弹出框，供modalPingLun.js 使用 */
            let  moneysourceid=event.target.getAttribute("data-moneysourceid");
            let  useridreciver=event.target.getAttribute("data-useridreciver");
            let  useruuidreciver=event.target.getAttribute("data-useruuidreciver");
            let  recivernickname=event.target.getAttribute("data-recivernickname");
            let  senderphoto=event.target.getAttribute("data-senderphoto");
            // 相比于"支持了"列表，这个是超集
            replyDiv.setAttribute("data-moneysourceid",moneysourceid);
            replyDiv.setAttribute("data-useridreciver",useridreciver);
            replyDiv.setAttribute("data-useruuidreciver",useruuidreciver);
            replyDiv.setAttribute("data-recivernickname",recivernickname);
            replyDiv.setAttribute("data-senderphoto",senderphoto);

        },
        commentReplyToDynamic:function(event){//这里是对更新下面评论的回复
            /* 1. 让弹出框先弹出来*/
            let  modal=document.getElementById("modal");
            let  tips=document.getElementById("tips");
            modal.style.display="block";
            tips.style.display="block";
            /* 2. 告诉弹出框这个是进度更新的还是支持了列表的*/
            let  replyDiv = document.getElementById ("reply");
            replyDiv.setAttribute("data-updateORdynamic","dynamic"); //progressUpdate代表是进度更新，dynamic代表的是支持动态
            replyDiv.setAttribute("data-refer","1");  //1代表对评论的回复

            /* 3.把 弹出框需要的reciverNickname，userUUIDReciver等信息放置到弹出框，供modalPingLun.js 使用 */
            let  moneysourceid=event.target.getAttribute("data-moneysourceid");
            let  useridreciver=event.target.getAttribute("data-useridreciver");
            let  useruuidreciver=event.target.getAttribute("data-useruuidreciver");
            let  recivernickname=event.target.getAttribute("data-recivernickname");
            let  senderphoto=event.target.getAttribute("data-senderphoto");
            // 相比于"支持了"列表，这个是超集
            replyDiv.setAttribute("data-moneysourceid",moneysourceid);
            replyDiv.setAttribute("data-useridreciver",useridreciver);
            replyDiv.setAttribute("data-useruuidreciver",useruuidreciver);
            replyDiv.setAttribute("data-recivernickname",recivernickname);
            replyDiv.setAttribute("data-senderphoto",senderphoto);

            // let  dataUpdateORdynamic = replyDiv.getAttribute ("data-updateORdynamic");
            // console.log(dataUpdateORdynamic);
            // let  dataRefer = replyDiv.getAttribute ("data-refer");
            // console.log(dataRefer);
        },
        test: function (event) {
            //console.log(event.target.attributes[0])
            console.log(event.target.getAttribute("data-reciver"))
        },
        handleClick: function() {
            this.updateORprogress=true;
            this.updateORprogress2=false;
        },
        handleClick2: function() {
            this.updateORprogress=false;
            this.updateORprogress2=true;
        },
        getQueryString: function (name) {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)return  unescape(r[2]); return null;
        }

    },

    mounted: function () {
        /* 当用户捐助时，从后台能获取到openid、受助人uuid、还有cashUUID，这里根据cashUUID获取*/
        //let userUUID=this.getQueryString("userUUID");
        //let tweetUUID= this.getQueryString("tweetUUID");
        let cashUUID= this.getQueryString("cashUUID");
        var that=this;
        // window.addEventListener("scroll", function(event) {
        //     var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
        //     //console.log(scrollTop);
        //     if(document.documentElement.scrollHeight == document.documentElement.clientHeight + scrollTop ) {
        //
        //         var requestParam={
        //             "cashUUID":cashUUID,
        //             "page":0,
        //             "pageSize":8,
        //             "timeStamp":"2027-09-04 00:00:00"
        //         };
        //
        //         that.$http.post(publicurl+'/glove/moneysource/backuplist',requestParam).then(response => {
        //             // console.log(response.body);
        //             that.$nextTick(function(){
        //                 this.lp=response.body.lp.concat(this.lp);
        //             });
        //             // console.log("hahha");
        //             // console.log(this.lp);
        //         }, err => {
        //             console.log(err);
        //         });
        //
        //     }
        // });

        //2.这里应该从页面获取参数，
        var param={
            "cashUUID":cashUUID,
            "page":0,
            "pageSize":8,
            "timeStamp":"2027-09-04 00:00:00"
        };

        setTimeout(function(){
            that.$http.post(publicurl+'/glove/moneysource/backuplist.do',param).then(response => {
                console.log(response.body);
                that.$nextTick(function(){
                    that.lp=response.body.lp;
                });
            }, err => {
                console.log(err);
            });
        },1000)

    },

})
