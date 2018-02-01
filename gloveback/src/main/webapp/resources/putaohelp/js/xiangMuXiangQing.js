
new Vue({
    el: '#zijinTongji',
    data: {
        xiangMuList:[],
    },
    mounted: function () {
        this.getSumInfo();
    },
    methods: {

        getSumInfo:function () {
            let param=new FormData();

            axios.post('/glove/grapeAdmin/getSumInfo2.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                //console.log(response.data);
                if (response.data.retcode==2000){
                    this.xiangMuList=response.data.result
                     console.log(this.xiangMuList)
                }

            }, err => {

            });
        }

    }
});

