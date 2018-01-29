


new Vue({
    el: '#companylist',
    data: {
        data:[],
        detailList:[],
        staffList:[], //公司某一互助计划下的所有员工
        page:1,
        pageStaff:1,
        nowuseruuid:""
    },
    mounted: function () {

        var param = new FormData();
        param.append('page',1);
        this.getCompanyList(param);
    },
    filters:{
        formatDate(time){
            function  padLeftZero(str){
                return ('00'+str).substr(str.length);
            }

            function formatDateAction(date,fmt){

                if(/(y+)/.test(fmt)){
                    fmt = fmt.replace(RegExp.$1, (date.getFullYear()+'').substr(4-RegExp.$1.length));
                }
                let o = {
                    'M+': date.getMonth()+1,
                    'd+': date.getDate(),
                    'h+': date.getHours(),
                    'm+': date.getMinutes(),
                    's+': date.getSeconds()
                }
                for(let k in o){
                    let str = o[k]+'';
                    if(new RegExp(`(${k})`).test(fmt)){
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length===1)?str:padLeftZero(str));
                    }
                }
                return fmt;
            }

            let date = new Date(time);
            return formatDateAction(date,'yyyy-MM-dd hh:mm');
            //此处formatDate是一个函数，将其封装在common/js/date.js里面，便于全局使用
        },
        formHelpType(str){
            if (str=="little"){
                return "少儿互助大病互助计划"
            }
            if(str=="young"){
                return "中青年大病互助计划"
            }
            if (str=="old"){
                return "中老年抗癌互助计划"
            }
            if (str=="zonghe"){
                return "综合意外互助计划"
            }
            if (str=="staff"){
                return "企业员工大病互助计划"
            }
            if (str=="employee"){
                return "企业员工意外伤害互助计划"
            }
        }


    },
    methods: {

        getCompanyList:function (param) {

            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求

            axios.post('/glove/grapeAdmin/getCompanyList2.do',param,{
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
        }, //

        getStafflist:function(event){

            document.getElementById('default').style.display='none';
            document.getElementById('detail').style.display='block';
            let  datauuid=event.target.getAttribute("data-useruuid");
            let  helptype=event.target.getAttribute("data-helptype");
            var param=new FormData();
            param.append("useruuid",datauuid) ;
            param.append("helptype",helptype);
            param.append("page",this.pageStaff); //页码
            param.append("pageSize",10);

            axios.post('/glove/grapeAdmin/getUserStaffListHelpType.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.staffList=response.data.result;
                }

            }, err => {

            });
        },
        showDefault:function () {
            document.getElementById("detail").style.display='none'; //隐藏详情
            document.getElementById("default").style.display='block'; //显示默认的

        },
        showDetailTab:function () {
            document.getElementById("default").style.display='none'; //隐藏默认的
            document.getElementById("detail").style.display='block'; //显示详情
        },

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


