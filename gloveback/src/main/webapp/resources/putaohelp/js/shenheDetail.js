
/**
  审核详情页，提交审核结果
 */

new Vue({
    el: '#shenheDetail',
    data: {
        data:[],
    },
    mounted: function () {
    },
    methods: {

        getShenheListWait:function () {
            var param = new FormData();
            param.append('confirmIf',"refused");
            param.append('page',0);
            param.append("pageSize",1000)

            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/getPutaoauths.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                console.log(response.data);
                this.data=response.data.result;

                //存储或者改变相应的值
                if (response.data.retcode==2000){

                }

            }, err => {
                return alert(err);
            });
        }, //getShenheListWait

    },

});

