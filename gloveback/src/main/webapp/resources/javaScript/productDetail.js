/**
 * Created by aihaitao on 29/3/2017.
 */
new Vue({
    el: '#productDetail',
    data: {
        "productDetail": {
        },
        "backupList":[
            {
                "photoUrl": "http://7xihgc.com1.z0.glb.clouddn.com/10.jpg",
                "nickname": "东北人家",
                "money": 30,
                "timeStamp":"2017-01-03"
            },
            {
                "photoUrl": "http://7xihgc.com1.z0.glb.clouddn.com/11.jpg",
                "nickname": "幸福江南人",
                "money": 15,
                "timeStamp":"2017-01-04"
            },
            {
                "photoUrl": "http://7xihgc.com1.z0.glb.clouddn.com/12.jpg",
                "nickname": "北大1109实验室",
                "money": 21,
                "timeStamp":"2017-01-05"
            },
            {
                "photoUrl": "http://7xihgc.com1.z0.glb.clouddn.com/13.jpg",
                "nickname": "胡玉",
                "money": 52,
                "timeStamp":"2017-01-06"
            },
            {
                "photoUrl": "http://7xihgc.com1.z0.glb.clouddn.com/14.jpg",
                "nickname": "黄健",
                "money": 59,
                "timeStamp":"2017-01-08"
            },
            {
                "photoUrl": "http://7xihgc.com1.z0.glb.clouddn.com/15.jpg",
                "nickname": "长白山下人",
                "money": 8,
                "timeStamp":"2017-02-09"
            },
            {
                "photoUrl": "http://7xihgc.com1.z0.glb.clouddn.com/18.jpg",
                "nickname": "三毛爱荷西",
                "money": 90,
                "timeStamp":"2017-02-13"
            },
            {
                "photoUrl": "http://7xihgc.com1.z0.glb.clouddn.com/27.jpg",
                "nickname": "春暖花开",
                "money": 36,
                "timeStamp":"2017-03-03"
            },
        ]
    },
    methods:{
        //根据给定的param参数，直接获取value值
        getUrlParam:function(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)
                return  unescape(r[2]);
            return null;
        }
    },
    mounted: function () {

        let  paramID=this.getUrlParam("id"); //url地址栏的参数，就是productInfo的UUID
        //这个是获取众筹产品的信息
        let  url='http://www.geilove.org/productInfo/info/'+paramID;
        this.$http.get(url).then(response => {
            //console.log(response.body);
            this.productDetail=response.body.productInfo;
        }, err => {
            console.log(err);
        });
        //这个是获取支持者信息，根据UUID获取

    },
});

