
new Vue({
    el: '#redBaoTongji',
    data: {
        redBaoTongji:{}
    },
    mounted: function () {
        this.getSumInfo();
    },
    methods: {

        getSumInfo:function () {
            let param=new FormData();
            axios.post('/glove/grapeAdmin/getRedBaoInfo.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {

                if (response.data.retcode==2000){
                    //this.redBaoTongji=response.data.result;

                    let redMoneys=[];
                    let unactived=response.data.result.unactive;
                    let itemUnactived={value:unactived,name:"未激活"};

                    let active=response.data.result.active;
                    let itemActive={value:active,name:"已激活"};

                    let used=response.data.result.used;
                    let itemsed={value:used,name:"已使用"};

                    redMoneys.push(itemUnactived);
                    redMoneys.push(itemActive);
                    redMoneys.push(itemsed);
                    console.log(redMoneys)

                    let bingTu = echarts.init(document.getElementById('contain'));
                    let  option = {
                        title : {
                            text: '红包统计',
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
                            data:['未激活','已激活','已使用']
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
                                name:'红包统计',
                                type:'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data:redMoneys
                            }
                        ]
                    };
                    bingTu.setOption(option);
                }
            }, err => {

            });
        }

    }
});


