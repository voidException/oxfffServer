

new Vue({
    el: '#addNews',
    data: {
    },
    mounted: function () {

    },
    methods: {

        addNews:function () {
            //获得文章的信息
            let title=document.getElementById("title").value;
            let vicetitle=document.getElementById("vicetitle").value;
            let author=document.getElementById("author").value;
            let source=document.getElementById("source").value;
            let imageone=document.getElementById("imageone").value;
            let newsurl=document.getElementById("newsurl").value;
            let newstype=document.getElementById("newstype").value;

            let param=new  FormData();
            param.append("title",title);
            param.append("vicetitle",vicetitle);
            param.append("author",author);
            param.append("source",source);
            param.append("imageone",imageone);
            param.append("newsurl",newsurl);
            param.append("newstype",newstype);

            axios.post('/glove/grapeAdmin/doAddNews.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {

                //存储或者改变相应的值
                if (response.data.retcode==2000){
                    this.deleteTag=response.data.result;
                    alert("发布成功")
                }else {

                }
            }, err => {

            });

        },


    },

});




