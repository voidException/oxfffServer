

new Vue({
    el: '#userlist',
    data: {
        data:[],
        page:1,
        dataAccount:[],
        detail:{},
        pageAccount:1,
        useruuid:'',
        current:1,
        showItem:5,
        allpage:5 ,//默认页数是5页
        count:0,// 总记录条数

    },
    mounted: function () {
        let param=new FormData();
        param.append("page",this.page);
        param.append("pageSize",20); //统一20条
        this.getUserList(param); //
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
        }
    },
    filters:{
        formatDate(time){
            //console.log(typeof(time))

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

            if (typeof (time)=='string'){
                time=parseInt(time)
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
        },
        formCishu(cishu){
            console.log(cishu)
        }
    },
    methods: {

        dosearch:function(event){
            if(event.keyCode == 13){
                //console.log(event.target.value)
                let phone=event.target.value; //
                let param=new FormData();
                param.append("phone",phone);
                axios.post('/glove/grapeAdmin/searchUserAndUserAccountsbyPhone.do',param,{
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }}).then(response => {
                    //存储或者改变相应的值
                    //console.log(response.data)
                    if (response.data.retcode==2000){
                        this.data=response.data.result;
                    }
                }, err => {

                });

            }
        },
        getUserList:function (param) {
            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/getUseList2.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                // console.log(response.data)
                if (response.data.retcode==2000){
                    this.data=response.data.result;
                    this.count=response.data.msg; //总记录数
                    this.allpage=Math.ceil(this.count/10);
                    // console.log(this.count);
                    // console.log(this.allpage)
                }
            }, err => {

            });
        }, //
        showDefault:function () {
            document.getElementById("detail").style.display='none'; //隐藏详情
            document.getElementById("default").style.display='block'; //显示默认的

        },
        showDetailTab:function () {
            document.getElementById("default").style.display='none'; //隐藏默认的
            document.getElementById("detail").style.display='flex'; //显示详情
        },
        showDetail:function (event) {

            document.getElementById("default").style.display='none'; //隐藏默认的
            document.getElementById("detail").style.display='flex'; //显示详

            let  useruuid=event.target.getAttribute("data-useruuid");
            let  accountuuid=event.target.getAttribute("data-accountuuid");
            let  breakif=event.target.getAttribute("data-breakif");
            let  paytotalmoney=event.target.getAttribute("data-paytotalmoney");
            let  username=event.target.getAttribute("data-username");
            let  categorytype=event.target.getAttribute("data-categorytype");
            let  joindate=event.target.getAttribute("data-joindate");
            let  remainfee=event.target.getAttribute("data-remainfee");
            this.detail={
                useruuid:useruuid,
                accountuuid:accountuuid,
                breakif:breakif,
                paytotalmoney:paytotalmoney,
                username:username,
                categorytype:categorytype,
                joindate:joindate,
                remainfee:remainfee
            }
        },
        goto:function(index){
            if(index == this.current)
                return;
            this.current = index;
            //这里可以发送ajax请求
        },
        getPageIndex:function (event) {

            let  index=event.target.getAttribute("data-index");
            let param=new FormData();
            param.append("page",index);
            param.append("pageSize",10); //统一20条
            this.getUserList(param); //
        },
    },

});

