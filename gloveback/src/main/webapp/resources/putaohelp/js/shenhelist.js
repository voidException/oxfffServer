
/**
 *
 * 这个适用于公众号内登录
 * 登录成功，要将数据存储在本地，然后显示相应内容
 */

new Vue({
    el: '#shenhelist',
    data: {
        data:[],
        detail:{},
        pageAll:1,
        pageUnhandle:1,
        pagePass:1,
        pageRefused:1,
        page:1,
        confirmIf:"all",

        current:1,
        showItem:5,
        allpage:5 ,//默认页数是5页
        count:0,// 总记录条数
    },
    mounted: function () {
        this.confirmIf="all";
        let param = new FormData();
        param.append('confirmIf',this.confirmIf);
        param.append('page',this.page);
        param.append('pageSize',10);

        this.getShenheListWait(param);
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
        formCheckType(str){
            if (str=="pass"){
                return "审核通过"
            }
            if(str=="refused"){
                return "被拒绝"
            }
            if (str=="unhandle"){
                return "待审核"
            }
        },
    },

    methods: {

        goto:function(index){
            if(index == this.current)
                return;
            this.current = index;
            //这里可以发送ajax请求
        },
        getPageIndex:function (event) { //分页实现

            let  index=event.target.getAttribute("data-index");
            let param=new FormData();
            param.append('confirmIf', this.confirmIf);
            param.append("page",index);
            param.append("pageSize",10); //统一20条
            this.getShenheListWait(param); //
        },

        getPutaoauthSearch:function () {
            let name=document.getElementById("shenheSearch").value
            var param = new FormData();
            param.append('name',name);

            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/doshenheSearch.do',param,{
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

        selectByType:function (event) {
            let confirmIf=event.target.getAttribute("data-type");
            this.confirmIf=confirmIf; //这个很重要，当点击分页按钮时，通过此知道当前处于什么状态下
            if (this.confirmIf=="all"){
                this.page=this.pageAll;
            }
            if (this.confirmIf=="unhandle"){
                this.page=this.pageUnhandle;
            }
            if (this.confirmIf=="pass"){
                this.page=this.pagePass;
            }
            if (this.confirmIf =="refused"){
                this.page=this.pageRefused;
            }


            let param = new FormData();
            param.append('confirmIf',this.confirmIf);
            param.append('page',this.page);
            param.append('pageSize',10);

            this.getShenheListWait(param);
            /** 这个方法也可以
                let chkObjs=null;
                let obj=document.getElementsByName("radio3")
                for (let i=0;i<obj.length;i++){ //遍历Radio
                    if(obj[i].checked){
                        chkObjs=obj[i].value;
                        console.log(chkObjs)
                    }
                }
             **/
        },
        getShenheListWait:function (param) {
            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/shenhelistdata.do',param,{
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
        }, //
        getDetail:function () {
            //发送网络请求
            document.getElementById("default").style.display='none'; //隐藏默认的
            document.getElementById("detail").style.display='block'; //显示详情
            let  useruuid=event.target.getAttribute("data-useruuid");
            let param = new FormData();
            param.append('useruuid',useruuid);
            axios.post('/glove/grapeAdmin/detail.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                   // console.log(response.data);
                    this.detail=response.data.result;
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

        shenhe:function (passRefused) {
            let param=new FormData();
            let comment=document.getElementById("comment").value;
            let useruuid=document.getElementById("useruuid").innerText;
            console.log(useruuid)
            param.append("comment",comment);
            param.append("confirmif",passRefused);
            param.append('useruuid',useruuid);

            axios.post('/glove/grapeAdmin/shenhe.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    alert("提交成功")
                }
            }, err => {
                alert("提交失败")
            });

        },
        refused:function(){

        }

    },

});

