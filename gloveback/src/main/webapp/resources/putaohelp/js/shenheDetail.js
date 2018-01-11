
/**
  审核详情页，提交审核结果
 */

new Vue({
    el: '#shenheDetail',
    data: {
        data:[],
    },
    mounted: function () {
        // 判断是否已通过，如果是，就隐藏id=passrefused的div


    },
    methods: {

        doPassRefused:function (param) {
            axios.post('/glove/grapeAdmin/shenhe.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                    //console.log(response.data);
                    this.data=response.data.result;
                    //存储或者改变相应的值
                    if (response.data.retcode==2000){

                    }

            }, err => {
                return alert(err);
            });
        }, //
        pass:function(){

            let comment=document.getElementById("comment").value;
            let useruuid=document.getElementById("useruuid").innerText;

            var param = new FormData();
            param.append('confirmif',"pass");
            param.append('comment',comment);
            param.append('useruuid',useruuid);

            this.doPassRefused(param)
        },
        refused:function(){

            let comment=document.getElementById("comment").innerText;
            let useruuid=document.getElementById("useruuid").innerText;

            var param = new FormData();
            param.append('confirmif',"refused");
            param.append('comment',comment);
            param.append('useruuid',useruuid);

            this.doPassRefused(param)
        }

    },

});

