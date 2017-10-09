/**
 * Created by aihaitao on 19/4/2017.
 */

/**
 *
 *分享到朋友圈的登录模态弹出框
 * 2017-04-10
 */
new Vue({
    el: '#payView',
    data: {
          orderId:null,
          openId:null,
          prepay_id:null,
          money:null,
          payAppId:null,        //公众号id
          paytimestamp:null,    //时间戳
          paypackage:null,      //订单详情扩展字符串
          paynonceStr:null,     //随机字符串
          paysignType:null,     //MD5
          paySign:null          //签名
    },
    methods: {
        getPrepayiD: function () { //获取
            this.showLoading("flex");
            let orderIdValue=document.getElementById("orderId").value;
            let openIdValue=document.getElementById("openId").value;

            // let unifiedPartParam={
            //     orderId:orderIdValue,
            //     openId: openIdValue,
            //     money:10
            // };
            let unifiedPartParam={
                orderId:"alooge126com",
                openId:"otQXfwdHziiO1pkZptC7l1NKdRT0",
                money:10
            };
            //alert(openIdValue);
            //alert(orderIdValue);
            //显示loading动画，避免被多次操作
            this.$http.post('http://geilove.org/glove/authorize/weixinJSBridge/invoke.do',unifiedPartParam).then(response => {

                //alert(response.body.prepay_id)
                document.getElementById("resdata").innerHTML=response.body.prepay_id;
                //存储或者改变相应的值
                this.showLoading("none");
                if (response.body.retcode==2000){
                    //组装参数，调用WeixinJSBridge.invoke
                    //this.pay(); //发起支付
                    function onBridgeReady(){
                        WeixinJSBridge.invoke(
                            'getBrandWCPayRequest',{
                                "appId":response.body.payAppId,          //公众号名称，由商户传入
                                "timeStamp":response.body.paytimestamp,  //时间戳，自1970年以来的秒数
                                "nonceStr":response.body.paynonceStr,     //随机串
                                "package":response.body.paypackage,       //订单详情扩展字符串
                                "signType":response.body.paysignType,     //微信签名方式:
                                "paySign":response.body.paySign          //微信签名
                            },
                            function(res){
                                if(res.err_msg=='get_brand_wcpay_request:ok'){
                                    alert("支付成功！");
                                    //window.location.href = "/home/gk/decoration/toOwner";
                                }else if(res.err_msg=='get_brand_wcpay_request:cancel'){
                                    alert("支付已取消");
                                }else{
                                    alert('支付失败');
                                }
                            }
                        );
                    };
                    function pay() {
                        if (typeof WeixinJSBridge == "undefined") {
                            if (document.addEventListener) {
                                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                            } else if (document.attachEvent) {
                                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                            }
                        } else {
                            onBridgeReady();
                        }
                    };
                    pay(); //调起支付

                }else {
                    alert("出错了");
                }
            }, err => {
                this.showLoading("none");
                console.log(err)
                alert(err);
            });
        },//getPrepayiD
        isInteger:function(obj){
            return Math.floor(obj) === obj;
         }, //判断是否为整数
        showLoading:function(value) {
            let  modal=document.getElementById("modal");
            modal.style.display=value;
        },//展示loading动画，避免多次重复点击
        losePoint:function () { //input失去焦点

            var moneyStr=document.getElementById("moneyInput").value; //支付的金额
            if (moneyStr==null || moneyStr==""){
                return;
            }
            let money=null;
            try{
                money=parseInt(moneyStr);
                if (money<=0){
                    alert("需要大于1");
                }
            }catch (e){
                alert("额度需要是整数");
            }
            if (this.isInteger(money)){
                this.money=money; //
                //更新合计
                document.getElementById("moneyNum").innerText=money;
                return ;
            }else {//清空啊
                document.getElementById("moneyInput").value=null;
                alert("额度需要是整数");
                return;
            }

        }
    },//methods

});



