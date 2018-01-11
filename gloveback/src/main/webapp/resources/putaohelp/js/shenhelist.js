
/**
 *
 * 这个适用于公众号内登录
 * 登录成功，要将数据存储在本地，然后显示相应内容
 */

new Vue({
    el: '#shenhelist',
    data: {
        data:[],
        page:1,
        confirmIf:null
    },
    mounted: function () {
        let confirmIf=document.getElementById("confirmIf").innerText;
        this.confirmIf=confirmIf;
        var param = new FormData();
        param.append('confirmIf',confirmIf);
        param.append('page',1);
        this.getShenheListWait(param);
    },
    methods: {

        getShenheListWait:function (param) {

            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/shenhelistdata.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                    console.log(response.data);
                    this.data=response.data.result;

                //存储或者改变相应的值
                if (response.data.retcode==2000){

                }

            }, err => {

            });
        }, //
        goNextPage:function () {
            this.page++;
            var param = new FormData();
            param.append('useruuid',this.confirmIf);
            param.append('page',this.page);
            this.getShenheListWait(param);
        },
        goUpPage:function () {
            if (this.page >1){
                this.page--;
            }
            var param = new FormData();
            param.append('confirmIf',this.confirmIf);
            param.append('page',this.page);
            this.getShenheListWait(param);
        }

    },

});

