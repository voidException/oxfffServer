
/**
 *
 * 这个适用于公众号内登录
 * 登录成功，要将数据存储在本地，然后显示相应内容
 */

let publicurl="http://localhost:8080"
//线上
//let publicurl="http://geilove.org";

new Vue({
    el: '#wechatLogin',
    data: {

    },
    methods: {
        verify(){ //此方法用于校验邮箱和密码是否合法，与移动端等同
            //console.log("verify")
            let email= document.getElementById("emailInput").value;
            let password =document.getElementById("passwordInput").value;

            let regx=/^1(3|4|5|7|8)\d{9}$/;
            let regP=/^[0-9|a-z|A-Z]\w{5,17}$/; //6-18w位数字和字母组成的密码
            //let testEm='@567890qwertyui';
            //console.log(regP.test(testEm));

            if(email===null ||password===null ||email.length!=11 || password.length<6 ||password.length>18|| !regx.test(email) || !regP.test(password)){
                //控制'您输入的邮箱或密码有误'  errorTips
                return false;
            }
            return true;

        },
        loginIn:function () { //执行登录的方法，先获取邮箱和密码，如果验证不通过，在模态框提示错误；登录后的结果通过dialog显示
            //先校验
            if(!this.verify()){
                return   alert("您的手机号或密码有误") ;
            }

            let email= document.getElementById("emailInput").value;
            let password =document.getElementById("passwordInput").value;
            // let userAccount={
            //     phone:email,
            //     userPass:password
            // };
            var userAccount = new FormData();
            userAccount.append('phone',email);
            userAccount.append('userPass',password);

            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            axios.post('/glove/phone/login.do',userAccount,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }}).then(response => {
                //console.log(response.data);

                //存储或者改变相应的值
                if (response.data.retcode==2000){

                    localStorage.setItem("userToken",response.data.data.backupfour);
                    localStorage.setItem("loginTag","logined");  //登录标志

                    localStorage.setItem("userid",response.data.data.userid);
                    localStorage.setItem("usernickname",response.data.data.usernickname);
                    localStorage.setItem("useremail",response.data.data.useremail);
                    localStorage.setItem("usertype",response.data.data.usertype);
                    localStorage.setItem("certificatetype",response.data.data.certificatetype);
                    localStorage.setItem("realname",response.data.data.realname);
                    localStorage.setItem("selfintroduce",response.data.data.selfintroduce);
                    localStorage.setItem("usertag",response.data.data.usertag);
                    localStorage.setItem("address",response.data.data.address);
                    localStorage.setItem("msgpubcount",response.data.data.msgpubcount);
                    localStorage.setItem("fanscount",response.data.data.fanscount);
                    localStorage.setItem("followcount",response.data.data.followcount);
                    localStorage.setItem("notsay",response.data.data.notsay);
                    localStorage.setItem("userphoto",response.data.data.userphoto);
                    localStorage.setItem("backupone",response.data.data.backupone);
                    localStorage.setItem("backuptwo",response.data.data.backuptwo);
                    localStorage.setItem("backupthree",response.data.data.backupthree);
                    localStorage.setItem("backupfour",response.data.data.backupfour);
                    localStorage.setItem("backupfive",response.data.data.backupfive);
                    localStorage.setItem("backupsix",response.data.data.backupsix);
                    localStorage.setItem("userhelpsman",response.data.data.userhelpsman);
                    localStorage.setItem("userdonate",response.data.data.userdonate);
                    localStorage.setItem("amountaccept",response.data.data.amountaccept);
                    localStorage.setItem("acceptmoney",response.data.data.acceptmoney);
                    localStorage.setItem("backupten",response.data.data.backupten);
                    localStorage.setItem("userhelpsman",response.data.data.userhelpsman);
                    localStorage.setItem("userUUID",response.data.data.useruuid)
                    window.location.href=publicurl+"/glove/path/pages/mobileWo.do"
                }
                else{
                    alert(response.data.msg)
                }

            }, err => {
                //this.showDialog("登录出现异常");
                return alert(err);
            });


        }

    },

});


