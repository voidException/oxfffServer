/**
 * Created by aihaitao on 16/5/2017.
 */

window.onload = function () {
    //设置input的初始值
    let  token=localStorage.getItem("backupfour");
    let  useruuid=localStorage.getItem("backupten");
    let  userName=localStorage.getItem("usernickname");
    let  selfintroduce=localStorage.getItem("selfintroduce") || "用户还没有简介";
    let  userphoto=localStorage.getItem("userphoto");
    if(token==null || useruuid==null || userName==null || userphoto==null){
        alert("请登录");
        setTimeout(function(){
            window.location.href="http://localhost:8080/glove/path/pages/mobileWo.do"; //公众号首页
        },500)

        return;
    }
    let tokenInput=document.getElementById("token");
    let useruuidInput=document.getElementById("useruuid") ;
    let userNameInput=document.getElementById("userName");
    let selfintroduceInput=document.getElementById("selfintroduce");
    let photoUrlInput=document.getElementById("photoUrl");

    tokenInput.value=token;
    useruuidInput.value=useruuid;
    userNameInput.value=userName;
    selfintroduceInput.value=selfintroduce;
    photoUrlInput.value=userphoto;
    //console.log(document.getElementById("token").value)

};

function checkCityName() {
    let cityName=document.getElementById("cityName").value;
    //console.log(cityName);
    if (cityName==null || cityName==""){
        //alert("城市名字不能为空");
        return;

    }else {
        if (cityName.length>8){
            //alert("城市名字太长");
            return;
        }
    }
}

function checkShouZhurenName() {
    let shouZhurenName=document.getElementById("shouZhurenName").value;
    //console.log(cityName);
    if (shouZhurenName==null || shouZhurenName==""){
        //alert("名字不能为空");
         return;
    }else {
        if (shouZhurenName.length>8){
            // alert("名字太长");
            return;
        }
    }
}

function checkAcceptMoneyName() {
    let acceptMoneyName=document.getElementById("acceptMoneyName").value;
    //console.log(cityName);
    if (acceptMoneyName==null || acceptMoneyName==""){
        //alert("名字不能为空")
    }else {
        if (acceptMoneyName.length>8){
            //alert("名字太长");
            return;
        }
    }
}



function  checkShouZhureniDentityNo() {

    let shouZhureniDentityNo=document.getElementById("shouZhureniDentityNo").value;
    //console.log(cityName);
    if (shouZhureniDentityNo==null || shouZhureniDentityNo==""){
        //alert("受助人身份证不能为空");
        return;
    }else {
        if (shouZhureniDentityNo.length!=18 ){
            //alert("身份证长度不对")
            return;
        }
    }
}

function  checkAcceptMoneyPhone(){
    let acceptMoneyPhone=document.getElementById("acceptMoneyPhone").value;
    //console.log(cityName);
    if (acceptMoneyPhone==null || acceptMoneyPhone==""){
        // alert("收款人手机号不能为空");
        return;
    }else {
        if (acceptMoneyPhone.length!=11 ){
            console.log(acceptMoneyPhone.length);
            // alert("手机号码有误");
            return;

        }
    }

}

function  checkChengnuoContent(){
    let chengnuoContent=document.getElementById("chengnuoContent").value;
    //console.log(cityName);
    if (chengnuoContent==null || chengnuoContent==""){
        // alert("承诺不能为空，至少30字");
        return;
    }else {
        if (chengnuoContent.length<30 ){
            // alert("承诺太短");
            return;
        }
    }
}

function  checkendDate(){
    let endDate=document.getElementById("endDate").value;
    //console.log(endDate)
}

function  checkTargetMoney(){

    function isInteger(obj) {
        return typeof obj === 'number' && obj%1 === 0
    }

    let targetMoney=document.getElementById("targetMoney").value;

    if (targetMoney==null || targetMoney==""){
        // alert("募捐金额不能为空");
        return;
    }else {

        let  targetMoneyInt=parseInt(targetMoney);
        //console.log(targetMoneyInt);
        if( !isInteger(targetMoneyInt)){
             alert("必须为整数");
            return;
        }
    }
}
function  checkMoneyTitle(){
    let moneyTitle=document.getElementById("moneyTitle").value;
    //console.log(moneyTitle);
    if (moneyTitle==null || moneyTitle==""){
        // alert("筹款标题不能为空");
        return;
    }else {
        if(moneyTitle.length<5){
            //alert("标题太短");
            return;
        }
    }

}

function checkContent(){
    let content=document.getElementById("content").value;
    //console.log(moneyTitle);
    if (content==null || content==""){
        //alert("情况描述不能为空");
        return;
    }else {
        if(content.length<200){
            // alert("不能低于200字");
            return;
        }
    }
}





