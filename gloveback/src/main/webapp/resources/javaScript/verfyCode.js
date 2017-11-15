/**
 获取验证码服务
 */

var maxTime = 60;

function timeDown() {

    var nowTime;
    var timer = setInterval(() => {
        nowTime=--maxTime;
        document.getElementById('timeId').innerHTML=nowTime+'秒再次获取';
        if (nowTime === 0) {
            clearInterval(timer);
            maxTime=60;
            document.getElementById('timeId').innerHTML='获取验证码'
            document.getElementById('timeId').disabled=false
            document.getElementById('timeId').style.backgroundColor='#00BB3B'

        }
    }, 1000)
}
function getCode() {
    timeDown();//首先调用定时器，开始倒计时
    document.getElementById('timeId').disabled=true
    document.getElementById('timeId').style.backgroundColor='#CCCCCC'
    let userAccount = new FormData();
    let phone= document.getElementById('phone').value;

    userAccount.append('phone',phone);
    //接下来发送注册请求,后台接口需要写
    axios.post('/glove/phone/getCode.do',userAccount,{
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }}).then(response => {

        if(response.data.retcode==2000){

        }else {
            alert(response.data.msg);
        }

    }, err => {
        console.log(err);
        alert("服务出现异常");
    });

}


