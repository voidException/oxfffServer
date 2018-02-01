

new Vue({
    el: '#newsList',
    data: {
        newsList:[],
        page:1,
        deleteTag:0
    },
    mounted: function () {
        var param = new FormData();
        param.append('page',this.page);
        this.getNewsList(param);
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
                    alert("删除成功")
                }else {
                    this.deleteTag=0
                }
            }, err => {

            });

        },
        addNews:function () {
            //增加一篇news

        }

    },

});




