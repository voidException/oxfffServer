
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
        confirmIf:"all"
    },
    mounted: function () {
        this.confirmIf="all";
        let param = new FormData();
        param.append('confirmIf',this.confirmIf);
        param.append('page',this.pageAll);
        this.getShenheListWait(param);
    },
    methods: {
        selectByType:function (event) {
            let confirmIf=event.target.getAttribute("data-type");
            this.confirmIf=confirmIf;
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

            }
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
                    console.log(response.data);
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

        pass:function () {

        },
        refused:function(){

        }

    },

});

