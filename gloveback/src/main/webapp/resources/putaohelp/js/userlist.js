

new Vue({
    el: '#userlist',
    data: {
        data:[],
        page:1,
        dataAccount:[],
        pageAccount:1,
        useruuid:''
    },
    mounted: function () {
        let param=new FormData();
        param.append("page",this.page);
        param.append("pageSize",20); //统一20条
        this.getUserList(param); //

    },
    methods: {
        getUserList:function (param) {
            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/getUseList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.data=response.data.result;
                }
            }, err => {

            });
        }, //
        goNextPage:function () {
            if (this.data.length<20){
                return
            }
            this.page++;
            let param = new FormData();
            param.append("page",this.page);
            param.append("pageSize",20); //统一20条
            this.getUserList(param);
        },
        goUpPage:function () {
            if (this.page >1){
                this.page--;
            }
            let param = new FormData();
            param.append("page",this.page);
            param.append("pageSize",20); //统一20条
            this.getUserList(param);
        },
        ////////////////////////////////
        getAccounts:function (event) {
            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            let  datauuid=event.target.getAttribute("data-uuid");
            this.useruuid=datauuid;
            let param=new FormData();
            param.append("useruuid",datauuid);
            param.append("page",this.pageAccount);
            param.append("pageSize",20);

            axios.post('/glove/grapeAdmin/accountlist.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.dataAccount=response.data.result;
                }
            }, err => {

            });
        }, //
        goUpPageAccount:function () {
            //
            if (this.pageAccount>1){
                this.pageAccount--
            }else {
                alert("已经是第一页了")
                return
            }
            let param=new FormData();
            param.append("useruuid",this.useruuid);
            param.append("page",this.pageAccount);
            param.append("pageSize",20);

            axios.post('/glove/grapeAdmin/accountlist.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.dataAccount=response.data.result;
                }
            }, err => {

            });

        },
        goNextPageAccount:function () {

            if (this.dataAccount.length<20){
                alert("没有更多数据了")
                return
            }
            this.pageAccount++;

            let param=new FormData();
            param.append("useruuid",this.useruuid);
            param.append("page",this.pageAccount);
            param.append("pageSize",20);

            axios.post('/glove/grapeAdmin/accountlist.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.dataAccount=response.data.result;
                }
            }, err => {

            });

        }

    },

});

