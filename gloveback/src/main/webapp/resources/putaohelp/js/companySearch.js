
/**
 *
 * 这个适用于公众号内登录
 * 登录成功，要将数据存储在本地，然后显示相应内容
 */

new Vue({
    el: '#companySearch',
    data: {
        data:[], //2个互助计划
        staffList:[],
        pageStaff:1,
        useruuid:"", //
        helptype:"", //每次点击员工都更新此字段
    },
    mounted: function () {

    },
    methods: {
        searchCompanyByPhone:function () {
            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            let phone=document.getElementById("searchInput").value;
            let param=new FormData();
            param.append("phone",phone); //根据手机号进行搜索

            axios.post('/glove/grapeAdmin/doCompanySearch.do',param,{
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


        goStaffNextPage:function () {

            if(this.staffList.length<20){
                alert("没有更多数据了")
                return
            }
            this.pageStaff++;
            var param = new FormData();
            param.append('page',this.pageStaff);
            param.append('useruuid',this.useruuid);
            param.append('helptype',this.helptype);

            axios.post('/glove/grapeAdmin/getUserStaffListHelpType.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.staffList=response.data.result;
                }else {
                    this.staffList=[]
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
            param.append('useruuid',this.useruuid);
            param.append('helptype',this.helptype);

            axios.post('/glove/grapeAdmin/getUserStaffListHelpType.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.staffList=response.data.result;
                }else {
                    this.staffList=[]
                }

            }, err => {

            });

        },
        getStaffList:function (event) {

            document.getElementById('stafflist').style.display='block';
            //根据uuid获得员工列表detail
            this.useruuid=event.target.getAttribute("data-uuid");
            this.helptype=event.target.getAttribute("data-helptype") //互助的类别

            this.pageStaff=1; //每次点击的时候都得归1。
            var param = new FormData();
            param.append('page',this.pageStaff);
            param.append('useruuid',this.useruuid);
            param.append('helptype',this.helptype);

            axios.post('/glove/grapeAdmin/getUserStaffListHelpType.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.staffList=response.data.result;
                }else {
                    this.staffList=[]
                }

            }, err => {

            });

        }

    },

});



