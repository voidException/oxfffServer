


new Vue({
    el: '#companylist',
    data: {
        data:[],
        detailList:[],
        staffList:[], //公司某一互助计划下的所有员工
        page:1,
        pageStaff:1,
        nowuseruuid:"",
        nowhelpType:"",

        current:1,
        showItem:5,
        allpage:5 ,//默认页数是5页
        count:0,// 总记录条数

        currentDetail:1,
        showItemDetail:5,
        allpageDetail:5 ,//默认页数是5页
        countDetail:0,// 总记录条数
    },
    mounted: function () {
        var param = new FormData();
        param.append('page',1);
        this.getCompanyList(param);
    },
    computed:{
        pages:function(){
            var pag = [];
            if( this.current < this.showItem ){ //如果当前的激活的项 小于要显示的条数
                //总页数和要显示的条数那个大就显示多少条
                var i = Math.min(this.showItem,this.allpage);
                while(i){
                    pag.unshift(i--);
                }
            }else{ //当前页数大于显示页数了
                var middle = this.current - Math.floor(this.showItem / 2 ),//从哪里开始
                    i = this.showItem;
                if( middle >  (this.allpage - this.showItem)  ){
                    middle = (this.allpage - this.showItem) + 1
                }
                while(i--){
                    pag.push( middle++ );
                }
            }
            return pag
        },
        pagesDetail:function(){
            var pag = [];
            if( this.currentDetail < this.showItemDetail ){ //如果当前的激活的项 小于要显示的条数
                //总页数和要显示的条数那个大就显示多少条
                var i = Math.min(this.showItemDetail,this.allpageDetail);
                while(i){
                    pag.unshift(i--);
                }
            }else{ //当前页数大于显示页数了
                var middle = this.currentDetail - Math.floor(this.showItemDetail / 2 ),//从哪里开始
                    i = this.showItemDetail;
                if( middle >  (this.allpageDetail - this.showItemDetail)  ){
                    middle = (this.allpageDetail - this.showItemDetail) + 1
                }
                while(i--){
                    pag.push( middle++ );
                }
            }
            return pag
        },



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
                    this.count=response.data.msg; //总记录数
                    this.allpage=Math.ceil(this.count/10);
                    //console.log(this.count);
                    //console.log(this.allpage)
                }
            }, err => {

            });
        }, //
        searchCompanyByPhone:function () { //根据手机号搜索公司的2个互助计划
            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            let phone=document.getElementById("searchInput").value;
            let param=new FormData();
            param.append("phone",phone); //根据手机号进行搜索

            axios.post('/glove/grapeAdmin/doCompanySearch2.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.data=[];
                    this.data=(response.data.result)
                    //console.log(this.data)
                }

            }, err => {

            });
        }, //
        searchUserStaffByPhone:function () {
            let phone=document.getElementById("staffPhone").value;
            let param=new FormData();
            param.append("phone",phone); //根据手机号进行搜索searchUserStaffByPhone
            axios.post('/glove/grapeAdmin/searchUserStaffByPhone.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //  console.log(response.data);
                //  存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.staffList=response.data.result;
                }

            }, err => {

            });
        },
        getStafflist:function(event){

            document.getElementById('default').style.display='none';
            document.getElementById('detail').style.display='block';
            let  useruuid=event.target.getAttribute("data-useruuid");
            let  helptype=event.target.getAttribute("data-helptype");

            this.nowuseruuid=useruuid;
            this.nowhelpType=helptype;

            var param=new FormData();
            param.append("useruuid",useruuid) ;
            param.append("helptype",helptype);
            param.append("page",this.pageStaff); //页码
            param.append("pageSize",10);

            axios.post('/glove/grapeAdmin/getUserStaffListHelpType.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.staffList=response.data.result;
                    this.countDetail=response.data.msg; //总记录数
                    this.allpageDetail=Math.ceil(this.countDetail/10);
                }else{ //当没有数据的时候就隐藏这个
                    document.getElementById('detail').style.display='none';
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

        goto:function(index){
            if(index == this.current)
                return;
            this.current = index;
            //这里可以发送ajax请求
        },
        getPageIndex:function (event) {
            let  index=event.target.getAttribute("data-index");
            var param = new FormData();
            param.append("page",index);
            this.getCompanyList(param);
        },
        gotoDetail:function(index){
            if(index == this.currentDetail)
                return;
            this.currentDetail = index;
            //这里可以发送ajax请求
        },
        getPageIndexDetail:function (event) {
            let  index=event.target.getAttribute("data-index");
            let param = new FormData();
            param.append("useruuid",this.nowuseruuid) ;
            param.append("helptype",this.nowhelpType);
            param.append("page",index);
            param.append("pageSize",10);
            axios.post('/glove/grapeAdmin/getUserStaffListHelpType.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
               // console.log(response.data);
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

        },

    },

});


