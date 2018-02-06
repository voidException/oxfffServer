

new Vue({
    el: '#permissionUser',
    data: {
        data:[],
        dataRole:[],
    },
    mounted: function () {
        let param=new FormData();
        //param.append("tuserid",1)
        this.getUserList(param);
        this.getRoles()
    },
    methods: {

        getUserList:function (param) {
            axios.post('/glove/grapeAdmin/getPermissionUsers.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                // console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.data=response.data.result;
                }
            }, err => {

            });
        },
        getRolesList:function (param) {
            axios.post('/glove/grapeAdmin/getPermissionRoles.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.dataRole=response.data.result;
                }
            }, err => {

            });
        },

        getRoles:function () {
            this.showDetailTab();
            let param=new FormData();
            param.append("tuserid",1)
            this.getRolesList(param);
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





