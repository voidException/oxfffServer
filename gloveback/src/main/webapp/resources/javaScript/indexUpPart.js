/**
 * Created by mfhj-dz-001-424 on 17/2/15.
 */
/**
 * Created by mfhj-dz-001-424 on 17/1/18.
 */
var uppart = new Vue({
    el: '#uppart',
    data: {
        tweet:{},
        cash:{},
        affirmImgUrls:['http://localhost:8080/glove/resources/image/1.jpg','http://localhost:8080/glove/resources/image/5.jpg','http://localhost:8080/glove/resources/image/25.jpg','http://localhost:8080/glove/resources/image/4.jpg'],
    },

    methods: {
        confirmIt: function (event) { //仅仅是设置弹出框出现，和设置confirm标志
            //console.log(event.target.getAttribute("data-reciver"))
            let modalReportDiv=document.getElementById ("modalReport");
            modalReportDiv.style.display="block";
            //这里设置modalReport的举报标志"report" ,证实标志就是confirm
            let  applyDiv = document.getElementById ("applyReport");
            // let  dataRCTag = applyDiv.getAttribute ("data-RCTag");
            applyDiv.setAttribute("data-RCTag","1"); //1代表证实，2代表举报

        },
        handleClick: function() {
        },

        getQueryString: function (name) {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)return  unescape(r[2]); return null;
        }
    },
    created: function () { //应该从浏览器url地址中获取参数userUUID,tweetUUID,cashUUID，组成url地址访问

        let userUUID=this.getQueryString("userUUID");
        let tweetUUID= this.getQueryString("tweetUUID");   //"5eedb509-f9d5-41eb-acc5-e51d25275607";
        let cashUUID= this.getQueryString("cashUUID"); //"5fedb509-f9d5-41eb-acc5-e51d25275607";

        let  url="http://localhost:8080/glove/wechatShare/"+userUUID+'/'+tweetUUID+'/'+cashUUID;

        this.$http.get(url).then(response => {
             console.log(response.body.lp);
            // this.bodyMsg=response.body;
            this.$nextTick(function() {

                this.tweet=response.body.lp.tweet;
                this.cash=response.body.lp.cash;
                //当点击查看正式人列表的时候，再真正获取数据
                //this.affirmImgUrls=response.body.lp.confirmList;
            });
        }, err => {
            console.log(err);
        });
    },
})



