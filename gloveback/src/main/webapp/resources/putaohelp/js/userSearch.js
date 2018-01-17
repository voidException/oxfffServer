

/**
 *
 * 这个适用于公众号内登录
 * 登录成功，要将数据存储在本地，然后显示相应内容
 */

new Vue({
    el: '#userSearch',
    data: {
        dataUser:{},
        accountList:[],
        userAccount:[]
    },
    mounted: function () {
    },
    methods: {

        doAccountSearch:function () {
            //根据身份证检索这个人的互助信息account
            let account=document.getElementById("account").value;
            var param=new FormData();
            param.append("account",account); //
            axios.post('/glove/grapeAdmin/doAccountSearch.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    console.log(response.data);
                    this.userAccount=response.data.result;
                }

            }, err => {

            });
        },

        doSearchUser:function () {

            let phone=document.getElementById("phone").value;
            var param = new FormData();
            param.append('token',"token");
            param.append('phone',phone);

            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/douserSearch.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    //console.log(response.data);
                    this.dataUser=response.data.result;
                }

            }, err => {

            });
        },
        getAccountList:function(){
            let useruuid=document.getElementById("useruuid").innerText;

            var param = new FormData();
            param.append('token',"token");
            param.append('useruuid',useruuid);

            axios.post('/glove/grapeAdmin/doAccountListSearch.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.accountList=response.data.result;
                }

            }, err => {

            });
        },

    },

});

