

new Vue({
    el: '#contain',
    data: {
        dataRole:[],
        dataResource:[],
    },
    mounted: function () {
        let param=new FormData();
        this.getRoleList(param);
        this.getResourceList()
    },
    methods: {

        getRoleList:function (param) {
            axios.post('/glove/grapeAdmin/getRoles.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                // console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.dataRole=response.data.result;
                }
            }, err => {

            });
        },
        getResource:function (param) {
            axios.post('/glove/grapeAdmin/getResource.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.dataResource=response.data.result;
                }
            }, err => {

            });
        },

        getResourceList:function () {
            this.showDefault();
            let param=new FormData();
            param.append("roleid",1);
            this.getResource(param);
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





