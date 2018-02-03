


new Vue({
    el: '#defaultMain',
    data: {
        defaultMain:{}
    },
    mounted: function () {
        this.getDefaultMain();
    },
    methods: {
        getDefaultMain:function () {
            let param=new  FormData();

            axios.post('/glove/grapeAdmin/getDefaultMain.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                   this.defaultMain=response.data.result;
                }

            }, err => {

            });

        },
    },

});




