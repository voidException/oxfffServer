
new Vue({
    el: '#zijinTongji',
    data: {
        xiangMuList:[],
    },
    mounted: function () {
        this.getSumInfo();
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
        },
    },
    methods: {

        getSumInfo:function () {
            let param=new FormData();

            axios.post('/glove/grapeAdmin/getSumInfo2.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                //console.log(response.data);
                if (response.data.retcode==2000){
                    this.xiangMuList=response.data.result
                     console.log(this.xiangMuList)
                }

            }, err => {

            });
        }

    }
});

