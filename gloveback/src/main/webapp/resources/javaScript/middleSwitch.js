

//let publicurl="http://localhost:8080"
//线上
let publicurl="http://geilove.org";

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

    },

    mounted: function () {
        //console.log('mounted 钩子执行...');
        //1.这里监听滚动条事件
        // var d = document.getElementById("part1").offsetHeight;
        var that=this;
        window.addEventListener("scroll", function(event) {
            var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
            //console.log(scrollTop);
            if(document.documentElement.scrollHeight == document.documentElement.clientHeight + scrollTop ) {

                var requestParam={
                    "userIDBehelped":1,
                    "cashiD":1,
                    "page":2,
                    "pageSize":4,
                    "timeStamp":"2017-09-04 00:00:00"
                };

                that.$http.post(publicurl+'/glove/moneysource/backuplist.do',requestParam).then(response => {
                    // console.log(response.body);
                    that.$nextTick(function(){
                        this.lp=response.body.lp.concat(this.lp);
                    });
                    // console.log("hahha");
                    // console.log(this.lp);
                }, err => {
                    console.log(err);
                });

            }
        });

        //2.这里应该从页面获取参数，
        var param={
            "userIDBehelped":1,
            "cashiD":1,
            "page":0,
            "pageSize":2,
            "timeStamp":"2017-09-04 00:00:00"
        };

        this.$http.post(publicurl+'/glove/moneysource/backuplist.do',param).then(response => {
            console.log(response.body);
            this.lp=response.body.lp;
        }, err => {
            console.log(err);
        });
    },

})
