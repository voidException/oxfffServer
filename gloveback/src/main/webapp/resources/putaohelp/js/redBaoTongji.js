
new Vue({
    el: '#redBaoTongji',
    data: {
        redBaoTongji:{}
    },
    mounted: function () {
        this.getSumInfo();
    },
    methods: {

        getSumInfo:function () {
            let param=new FormData();
            axios.post('/glove/ship/getSumInfo.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {

                //console.log(response.data);
                if (response.data.retcode==2000){

                }else {
                    //没有数据或出现错误
                }

            }, err => {

            });
        }

    }
});


