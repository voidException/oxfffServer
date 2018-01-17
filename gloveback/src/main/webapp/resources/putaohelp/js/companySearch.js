
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
        page:1,
        pageStaff:1
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
                console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.data=response.data.result;
                }

            }, err => {

            });
        }, //

        goNextPage:function () {
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
                console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.detailList=response.data.result;
                }

            }, err => {

            });
        },
        goStaffNextPage:function () {
            this.pageStaff++;

            var param = new FormData();
            param.append('page',this.pageStaff);
            this.getCompanyList(param);
        },
        goStaffUpPage:function () {
            if (this.page >1){
                this.page--;
            }
            var param = new FormData();
            param.append('page',this.page);
            this.getCompanyList(param);
        },
        getStaffList:function (event) {

            document.getElementById('stafflist').style.display='block';
            //根据uuid获得员工列表detail
            let  datauuid=event.target.getAttribute("data-uuid");
            let helptype=event.target.getAttribute("data-helptype")
            console.log(helptype)
            var param = new FormData();
            param.append('page',this.page);
            param.append('useruuid',datauuid);
            param.append('helptype',helptype);

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



