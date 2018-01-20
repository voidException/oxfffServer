


new Vue({
    el: '#companylist',
    data: {
        data:[],
        detailList:[],
        staffList:[],
        page:1,
        pageStaff:1,
        nowuseruuid:""
    },
    mounted: function () {

        var param = new FormData();
        param.append('page',1);
        this.getCompanyList(param);
    },
    methods: {

        getCompanyList:function (param) {

            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求

            axios.post('/glove/grapeAdmin/getCompanyList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.data=response.data.result;
                }

            }, err => {

            });
        }, //
        goNextPage:function () {
            if(this.data.length<20){
                return
            }
            this.page++;
            var param = new FormData();
            param.append('page',this.page);
            this.getCompanyList(param);
        },
        goUpPage:function () {
            if (this.page >1){
                this.page--;
            }
            var param = new FormData();
            param.append('page',this.page);
            this.getCompanyList(param);
        },
        aadetail:function(event){

            document.getElementById('stafflist').style.display='none';
            document.getElementById('detail').style.display='block';
            let  datauuid=event.target.getAttribute("data-uuid");
            //console.log(datauuid)
            var param=new FormData();
            param.append("useruuid",datauuid) ;
            axios.post('/glove/grapeAdmin/aadetail.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.detailList=response.data.result;
                }

            }, err => {

            });
        },
        goStaffNextPage:function () {
            //console.log(this.staffList)

            if (this.staffList.length<20){
                alert("没有更多数据了")
                return;
            }
            this.pageStaff++;
            var param = new FormData();
            param.append('page',this.pageStaff);
            param.append('useruuid',this.nowuseruuid);

            axios.post('/glove/grapeAdmin/getUserStaffList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.staffList=response.data.result;
                }
            }, err => {

            });
        },
        goStaffUpPage:function () {
            if (this.pageStaff >1){
                this.pageStaff--;
            }
            var param = new FormData();
            param.append('page',this.pageStaff);
            param.append('useruuid',this.nowuseruuid);

            axios.post('/glove/grapeAdmin/getUserStaffList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.staffList=response.data.result;
                }
            }, err => {

            });
        },
        getStaffList:function (event) {
            document.getElementById('detail').style.display='none';
            document.getElementById('stafflist').style.display='block';
            //根据uuid获得员工列表detail
            let  datauuid=event.target.getAttribute("data-uuid");

            //**设置当前的useruuid。设置pageStaff
            this.nowuseruuid=datauuid; //右侧员工列表上一页，下一页需要的useruuid
            this.pageStaff=1;
            this.staffList=[];
            //***********

            var param = new FormData();
            param.append('page',this.pageStaff);
            param.append('useruuid',datauuid);

            axios.post('/glove/grapeAdmin/getUserStaffList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                   //console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.staffList=response.data.result;
                }

            }, err => {

            });

        }

    },

});


