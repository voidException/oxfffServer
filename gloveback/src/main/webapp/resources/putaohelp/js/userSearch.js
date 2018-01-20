

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
        userAccount:[],
        lookDetail:{},
        page:1
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

                     document.getElementById("userBodyContain").style.display='flex'
                     this.dataUser=response.data.result;
                    this.useruuid=this.dataUser.useruuid;
                }

            }, err => {

            });
        },
        getAccountList:function(){

            var param = new FormData();
            param.append('token',"token");
            param.append('useruuid',this.useruuid);
            param.append("page",1);
            param.append("pageSize",20)

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
        getlookDetail:function (event) {

            let accountuuid=event.target.getAttribute("data-accountuuid");
            let categorytype=event.target.getAttribute("data-categorytype");
            document.getElementById("modal").style.display='flex'
            // 在accountList 列表中查找
            for (var i=0;i<this.accountList.length;i++){
                if (accountuuid==this.accountList[i].accountuuid && categorytype==this.accountList[i].categorytype){
                    this.lookDetail=this.accountList[i];
                }
            }
        },
        doClose:function () {
            document.getElementById("modal").style.display='none'
        },
        pageNext:function () {
            //下一页
            if (this.accountList.length<20){
                return
            }
            this.page++ ;
            var param = new FormData();
            param.append('token',"token");
            param.append('useruuid',this.useruuid);
            param.append("page",this.page);
            param.append("pageSize",20)

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
        pageUp:function () {
            //上一页
            if (this.page<=1){
                return
            }
            this.page-- ;
            var param = new FormData();
            param.append('token',"token");
            param.append('useruuid',this.useruuid);
            param.append("page",this.page);
            param.append("pageSize",20)

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

        }

    },

});

