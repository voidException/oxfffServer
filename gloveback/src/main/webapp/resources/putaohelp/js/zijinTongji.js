
new Vue({
    el: '#zijinTongji',
    data: {
        staffDabing:{}, //企业员工大病
        staffYiwai:{},  //企业员工意外
        littleDabing:{}, //少儿大病
        young:{},       //中青年抗癌
        old:{},         //中老年抗癌
        zongheYiwai:{}  //综合意外
    },
    mounted: function () {
        this.getSumInfo('staff');
        this.getSumInfo('employee');
        this.getSumInfo('little');
        this.getSumInfo('young');
        this.getSumInfo('old');
        this.getSumInfo('yiwai');
    },
    methods: {

        getSumInfo:function (helptype) {
            let param=new FormData();
            param.append('helptype',helptype);

            axios.post('/glove/ship/getSumInfo.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //存储或者改变相应的值
                //console.log(response.data);
                if (response.data.retcode==2000){
                    if (helptype=='staff'){
                        this.staffDabing=response.data.result;
                    }else if (helptype=='employee'){
                        this.staffYiwai=response.data.result;
                    }else if (helptype=='little'){
                        this.littleDabing=response.data.result;
                    }else if (helptype=='young'){
                        this.young=response.data.result;
                    }else if (helptype=='old'){
                        this.old=response.data.result;
                    }else {
                        this.zongheYiwai=response.data.result;
                    }
                }else {
                    //没有数据或出现错误
                }

            }, err => {

            });
        }

    }
});

