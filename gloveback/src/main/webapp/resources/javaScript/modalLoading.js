/**
 *
 * Created by mfhj-dz-001-424 on 17/3/14.
 * 这里是显示，Loading动画的控制函数
 */
function showDialog(){
    /*1.先隐藏模态框*/
    let  modal=document.getElementById("modal");
    let  tips=document.getElementById("tips");

    if (modal.style.display==""){
        modal.style.display="none";
        tips.style.display="none";
    }
    /*2.发送网络请求*/

    /*3.根据网络请求的结果,显示一个提示,1秒后消失*/
    let  dialog=document.getElementById("dialog");
    let  dialogTips=document.getElementById("dialogTips");

    if (dialog.style.display=="" || dialog.style.display=="none"){
        dialog.style.display="flex";
        dialogTips.style.display="block";
    }
    setTimeout(function(){
        dialog.style.display="none";
        dialogTips.style.display="none";
    },2000);

}