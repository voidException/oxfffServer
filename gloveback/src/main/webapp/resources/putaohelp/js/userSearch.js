

/**
 *
 * 这个适用于公众号内登录
 * 登录成功，要将数据存储在本地，然后显示相应内容
 */

new Vue({
    el: '#userSearch',
    data: {
        data:[],
    },
    mounted: function () {
    },
    methods: {

        doSearchUser:function () {

            let phone=document.getElementById("phone").value;

            var param = new FormData();
            param.append('info',"phone");
            param.append('typeTag','phone');

            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/grapeAdmin/douserSearch.do',param,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                console.log(response.data);
                this.data=response.data.result;

                //存储或者改变相应的值
                if (response.data.retcode==2000){

                }

            }, err => {
                return alert(err);
            });
        }, //getShenheListWait

    },

});

