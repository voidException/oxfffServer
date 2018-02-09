new Vue({
    el: '#zijinZouShi',
    data: {
        dataX:["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"],
        dataY:[5, 20, 36, 10, 10],
        page:1,

    },
    mounted: function () {
        //获取数据
        let param=new FormData();
        param.append("page",this.page);
        param.append("pageSize",10);
        param.append("helpType","all");
        this.getTongjiList(param);
        this.getBingTu();

    },
    methods: {
        getTongjiList:function (param) {
            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/zijinzoushi.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值

                if (response.data.retcode==2000){
                    //this.data=response.data.result;
                   // console.log(response.data.result)
                    let Ydata=[];
                    let Xdata=[];
                    for(let i=0;i<response.data.result.length;i++){
                        Ydata.push(response.data.result[i].allmoney);
                        let formDatetime=this.formatDate(response.data.result[i].tongjidate)
                        Xdata.push(formDatetime);
                    }
                    this.dataY=Ydata;
                   // console.log(this.dataY);
                    this.dataX=Xdata;
                    // 基于准备好的dom，初始化echarts实例
                    let myChart = echarts.init(document.getElementById('main'));
                    // 指定图表的配置项和数据
                    let option = {
                        title: {
                            text: '葡萄互助账户余额统计(元)'
                        },
                        tooltip: {},
                        legend: {
                            data:['余额']
                        },
                        xAxis: {
                            data: this.dataX
                        },
                        yAxis: {
                            // data: this.dataY //这个控制纵坐标显示的
                        },
                        series: [{
                            name: '资金',
                            type: 'bar',
                            data: this.dataY
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);

                }
            }, err => {

            });
        },
        getBingTu:function () {
            axios.post('/glove/grapeAdmin/bingTu.do',{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值

                if (response.data.retcode==2000){
                    let dataDaBing2=[];
                    for (let i=0;i<6;i++){
                         let name=response.data.result[i].helpName;
                         let value=response.data.result[i].money;
                         let item={value:value,name:name};
                         dataDaBing2.push(item);
                    }
                    console.log(dataDaBing2);
                    //饼状图
                    let bingTu = echarts.init(document.getElementById('detail'));
                    let  option = {
                        title : {
                            text: '6种互助计划余额统计',
                            subtext: '葡萄互助',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient : 'vertical',
                            x : 'left',
                            data:['少儿互助计划','中青年抗癌互助','中老年抗癌互助','综合意外互助','企业员工大病互助','企业员工综合意外互助']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: true},
                                magicType : {
                                    show: true,
                                    type: ['pie', 'funnel'],
                                    option: {
                                        funnel: {
                                            x: '25%',
                                            width: '50%',
                                            funnelAlign: 'left',
                                            max: 1548
                                        }
                                    }
                                },
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        series : [
                            {
                                name:'资金统计',
                                type:'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data:dataDaBing2
                            }
                        ]
                    };
                    bingTu.setOption(option);
                }
            })
        },
        showDefault:function () {
            document.getElementById("detail").style.display='none'; //隐藏详情
            document.getElementById("default").style.display='block'; //显示默认的

        },
        showDetailTab:function () {
            document.getElementById("default").style.display='none'; //隐藏默认的
            document.getElementById("detail").style.display='flex'; //显示详情
        },
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
            return formatDateAction(date,'yyyy-MM-dd');
            //此处formatDate是一个函数，将其封装在common/js/date.js里面，便于全局使用
        },
    },

});
