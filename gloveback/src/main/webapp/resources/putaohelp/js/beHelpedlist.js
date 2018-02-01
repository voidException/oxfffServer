/**
 * Created by aihaitao on 1/2/2018.
 */


new Vue({
    el: '#behelped',
    data: {
        publicData:[]
    },
    mounted: function () {
        var param = new FormData();
        param.append('page',this.page);
        this.getNewsList(param);
    },
    methods: {

        getNewsList:function (param) {
            axios.post('/glove/grapeAdmin/getPublicList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.publicData=response.data.result;
                }
            }, err => {

            });
        },
        showDefault:function () {
            document.getElementById("detail").style.display='none'; //隐藏详情
            document.getElementById("default").style.display='block'; //显示默认的

        },
        showDetailTab:function () {
            document.getElementById("default").style.display='none'; //隐藏默认的
            document.getElementById("detail").style.display='block'; //显示详情
        },



    },

});




