

new Vue({
    el: '#newsList',
    data: {
        newsList:[],
        page:1,
        deleteTag:0,

        current:1,
        showItem:5,
        allpage:5 ,//默认页数是5页
        count:0,// 总记录条数
    },
    mounted: function () {
        var param = new FormData();
        param.append('page',this.page);
        param.append('pageSize',10)
        this.getNewsList(param);
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
                return "少儿大病互助计划"
            }
            if(str=="young"){
                return "中青年抗癌互助"
            }
            if (str=="old"){
                return "中老年抗癌互助"
            }
            if (str=="zonghe"){
                return "综合意外互助"
            }
            if (str=="staff"){
                return "企业员工大病互助"
            }
            if (str=="employee"){
                return "企业员工综合意外互助"
            }
        }
    },
    methods: {

        getNewsList:function (param) {
            axios.post('/glove/grapeAdmin/getNewsList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                console.log(response.data);
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.newsList=response.data.result;
                    this.count=response.data.msg; //总记录数
                    this.allpage=Math.ceil(this.count/10);
                }else {
                    this.newsList=[]
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

        deleteNews:function (event) {
            //删除掉一篇news
            let  datauuid=event.target.getAttribute("data-uuid"); //
            let param=new  FormData();
            param.append("datauuid",datauuid);
            axios.post('/glove/grapeAdmin/deleteNews.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {

                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.deleteTag=response.data.result;
                    alert("删除成功");
                    let param = new FormData();
                    param.append('page',this.page);
                    param.append('pageSize',10)
                    this.getNewsList(param);
                }else {
                    this.deleteTag=0
                }
            }, err => {

            });

        },
        addNews:function () {
            //增加一篇news

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
            param.append("pageSize",10);
            this.getNewsList(param);
        },

    },

});




