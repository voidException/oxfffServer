

//let publicurl="http://localhost:8080"
//线上
let publicurl="http://geilove.org";

new Vue({
    el: '#userSelfHelp',
    data: {
        lp:[],
    },
    mounted: function () {

       // let url='http://localhost:8080/glove/weibos/helpTweetlist.do?lastUpdate="2018-09-02"'
        let baseurl= publicurl+'/glove/weibos/helpTweetlist.do'
        let update="2028-09-02";
        let helpType=this.getQueryString("helpType");
        let urlfinal=baseurl+'?'+"lastUpdate="+update+"&&helpType="+helpType;

        var that=this;
        this.$http.get(urlfinal).then(response => {

            //存储或者改变相应的值
            if (response.body.retcode==2000){
                that.$nextTick(function(){
                    this.lp=response.body.lp.concat(this.lp);
                });
            }

        }, err => {
            //this.showDialog("登录出现异常");
            return alert(err);
        });
    },
    methods:{
        getQueryString:function(name) {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)
                return  unescape(r[2]);
            return null;
        },
        goTimeline:function (event) {//取得div中的cashUUID 和userUUID ，然后拼装
            let userUUID=event.target.getAttribute("data-useruuidtweet");
            let cashuuid=event.target.getAttribute("data-cashuuid");
            let tweetUUID=event.target.getAttribute("data-tweetUUID");
            let url= publicurl+"/glove/path/pages/shareTotimeline.do?userUUID="+userUUID+"&tweetUUID="+tweetUUID+"&cashUUID="+cashuuid
            window.location.href=url;
        }
    }

})


