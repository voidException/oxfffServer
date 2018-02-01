

new Vue({
    el: '#chongzhilist',
    data: {
        data:[],
        dataDeduction:[],
        page:1,
        pageKoufei:1,
    },
    mounted: function () {
        let param=new FormData();
        param.append("page",this.page);
        param.append("pageSize",10); //统一20条
        this.getUserList(param); //
        this.getDeduction(param)

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
        getUserList:function (param) {
            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/chongzhiList.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.data=response.data.result;
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
    },

});

