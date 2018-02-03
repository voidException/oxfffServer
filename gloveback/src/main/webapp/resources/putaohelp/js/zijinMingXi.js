

new Vue({
    el: '#chongzhilist',
    data: {
        data:[],
        dataDeduction:[],
        page:1,
        pageKoufei:1,
        accountSearchTag:'no',
        account:'',

        koufeiAccountSearchTag:'no',
        koufeiAccount:'',

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
        let param=new FormData();
        param.append("page",this.page);
        param.append("pageSize",10);
        this.chongzhiList(param); //
        this.getDeduction(param)

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
        chongzhiList:function (param) {
            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/chongzhiList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.data=response.data.result;
                    this.count=response.data.msg; //总记录数
                    this.allpage=Math.ceil(this.count/10);
                }
            }, err => {

            });
        },
        getDeduction:function (param) {
            axios.post('/glove/grapeAdmin/koufeiList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.dataDeduction=response.data.result;

                    this.countDetail=response.data.msg; //总记录数
                    this.allpageDetail=Math.ceil(this.countDetail/10);
                }
            }, err => {

            });
        },
        doKoufeiSearch:function () {

            let accountInput=document.getElementById("koufeiInput").value;
            //console.log(accountInput)
            let param=new FormData();
            param.append("account",accountInput);
            param.append("page",1);
            param.append("pageSize",10);

            axios.post('/glove/grapeAdmin/searchKoufeiList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.dataDeduction=response.data.result;
                    this.countDetail=response.data.msg; //总记录数
                    this.allpageDetail=Math.ceil(this.countDetail/10);
                    this.koufeiAccountSearchTag='yes' ;
                    this.koufeiAccount=accountInput;
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
            param.append("pageSize",10);
            if (this.accountSearchTag=='no'){
                this.chongzhiList(param);
            }
            else {
                param.append('account',this.account);

                axios.post('/glove/grapeAdmin/searchChongzhiList.do',param,{
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }}).then(response => {
                    //存储或者改变相应的值
                    if (response.data.retcode==2000){
                        this.data=response.data.result;
                        this.count=response.data.msg; //总记录数
                        this.allpage=Math.ceil(this.count/10);
                    }
                }, err => {

                });
            }

        },
        gotoDetail:function(index){
            if(index == this.currentDetail)
                return;
            this.currentDetail = index;
            //这里可以发送ajax请求
        },
        getPageIndexDetail:function (event) {
            let  index=event.target.getAttribute("data-index");
            let param=new FormData();
            param.append("page",index);
            param.append("pageSize",10);
            if (this.koufeiAccountSearchTag=='no'){
                this.getDeduction(param)
            }else {
                param.append('account',this.koufeiAccount);
                axios.post('/glove/grapeAdmin/searchKoufeiList.do',param,{
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }}).then(response => {
                    //存储或者改变相应的值
                    if (response.data.retcode==2000){
                        this.dataDeduction=response.data.result;
                        this.countDetail=response.data.msg; //总记录数
                        this.allpageDetail=Math.ceil(this.countDetail/10);
                    }
                }, err => {

                });
            }
        },
        doSearch(){
            let accountInput=document.getElementById("accountInput").value;
            //console.log(accountInput)
            let param=new FormData();
            param.append("account",accountInput);
            param.append("page",1);
            param.append("pageSize",10);

            axios.post('/glove/grapeAdmin/searchChongzhiList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                //console.log(response.data)
                if (response.data.retcode==2000){
                    this.data=response.data.result;
                    this.count=response.data.msg; //总记录数
                    this.allpage=Math.ceil(this.count/10);
                    this.accountSearchTag='yes' ;
                    this.account=accountInput;
                }
            }, err => {

            });

        }

    },

});

