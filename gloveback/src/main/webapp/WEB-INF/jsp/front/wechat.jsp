
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
//    String path = request.getContextPath();
//    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>分享到微信圈</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/index.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/nickname.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/zhengming.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/modalCommonInput.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/modalReport.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/modalLoading.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/modalLogin.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>

<body style="font-size: 14px;overflow: scroll;flex:1; font-family:  'Microsoft YaHei' ">
<div id="uppart">
    <!--这个是头部-->
    <div class="header">
        <div class="headerChild">
            <div  style=" display:table-cell;vertical-align: middle;">
                <img style="float: left;height: 40px;width: 32px" src="http://onejf30n8.bkt.clouddn.com/gongzhongback.png">
                <a class="headerChilda" href="#">首页</a>
            </div>
        </div>
        <div class="headerChild">
            <div style="display:table-cell;text-align:center; vertical-align:middle;">
                <div style="display: flex;justify-content: center;align-items: center;color: #ffffff">
                    <div>互助</div>
                    <img src="http://onejf30n8.bkt.clouddn.com/gongzhonglogo.png" style="width: 30px;height: 30px;border-radius: 15px">
                    <div>管家</div>
                </div>
            </div>
        </div>
        <div class="headerChild" style="display:table;float:left;background:#2FAC4C;height:46px;width: 34%;">
            <div  onclick="ReportMe()" style="display:table-cell;float:right;vertical-align: middle">
                <a class="headerChildReport" href="#">举报</a>
            </div>
        </div>
    </div>
    <!--头像,昵称等部分-->
    <div style="height: 50px"></div>
    <div class="photo">
        <div class="photoLeft">
            <img :src="tweet.backupeleven" style="float:left;width: 34px;height: 34px;border-radius: 17px;margin-top: 10px;margin-left: 10px;">
            <div style="float: left;font-size: 14px; height:34px;line-height: 34px;padding-top: 10px;padding-left: 10px;">{{tweet.backupnine}}</div>
        </div>
        <div class="photoRight">
            <div style="float: right;font-size:12px;color:#AAA;height:34px;line-height: 34px;padding-top: 10px;padding-right: 10px;">
                共计<span style="font-size:12px;color:red">30</span>天
            </div>
        </div>
    </div>

    <!--标题进度结束时间资金流向说明-->
    <div style="margin-left: 10px;margin-right: 10px;">
        <div class="title"  style="margin-top: 20px;margin-bottom: 20px;text-align: center;color:#555D5F;font-size: 18px;font-weight:bold">
            {{tweet.backuptwelve}}
        </div>
        <div style="height: 3px;background: red; width: 100%;border-radius: 1.5px">
            <div style="height:3px;background: #2FAC4C; width: 100px"></div>
        </div>
        <div>
            <div style="margin-top: 10px;color: #AAA;float: left ">
                结束日期<span style="font-size: 13px;font-weight: bold;color:black">{{cash.closetime |time}}</span>
            </div>
            <a style="color: #222;float: right;margin-top: 10px">资金流向说明>></a>
        </div>
    </div>
    <div style="clear: both;height: 10px"></div>
    <%--以下以后要删除--%>
    <%--<div  class="clickWo" onclick="clickMe()">点击不到我</div>--%>
    <%--<div onclick="alertComment()">显示评论框</div>--%>
    <%--<div onclick="showDialog()">显示dialog</div>--%>
    <%--<div onclick="loginByEmail()">邮箱登录</div>--%>
    <!--目标金额,已筹金额,次数-->
    <div  class="target" >
        <div class="targetMoneyWrapper">
            <div class="targetMoney"><strong>{{cash.targetcash}}</strong><span class="targetMoneyAttr">元</span></div>
            <div class="targetDesp"> 目标金额</div>
        </div>
        <div style="height: 24px;border-right: #AAA solid 0.5px;"></div>
        <div class="targetMoneyWrapper" >
            <div class="targetMoney"><strong>{{cash.realcash}}</strong><span class="targetMoneyAttr">元</span></div>
            <div class="targetDesp"> 已筹钱数</div>
        </div>
        <div style="height: 24px;border-right: #AAA solid 0.5px;"></div>
        <div class="targetMoneyWrapper">
            <div class="targetMoney"><strong>{{cash.sumbackup}}</strong><span class="targetMoneyAttr">次</span></div>
            <div class="targetDesp"> 捐助次数</div>
        </div>
        <!--奇怪,不这用,黑线会跑到上面-->
    </div>
    <div style="height: 10px;background: #F5F6F9"><!--一段灰暗区--></div>
    <div style="color:#aaa;font-size: 15px;font-weight: 800; height: 50px; line-height: 50px;border-bottom:1px solid rgba(0,0,0,.15);padding-left: 10px">
        项目详情
    </div>
    <div style="padding-left: 10px;padding-right:10px;margin-top: 10px;overflow: visible; max-height: none;font-size: 16px;word-wrap:break-word;line-height:1.7">
        {{tweet.msgcontent}}
    </div>
    <div style="display:none;margin-top:-20px;height:20px;line-height: 20px;text-align: center;color: #43AC43;font-size: 15px;font-family:  'Microsoft YaHei'">
        展示全文
    </div>
    <!--这里是图片展示,css3布局-->
    <div style="display: flex;flex-direction: row;justify-content: flex-start;flex-wrap: wrap">
        <img v-if="tweet.videoaddress"  style="height: 96px;width: 23%;padding: 2px" :src="tweet.videoaddress" >
        <img style="height: 96px;width: 23%;padding: 2px" :src="tweet.tweetbackupone">
        <img style="height: 96px;width: 23%;padding: 2px" :src="tweet.tweetbackuptwo">
        <img style="height: 96px;width: 23%;padding: 2px" :src="tweet.tweetbackupthree">
        <img v-if="tweet.tweetbackupseven"  style="height: 96px;width: 23%;padding: 2px" :src="tweet.tweetbackupseven">
        <img v-if="tweet.backupeight" style="height: 96px;width: 23%;padding: 2px" :src="tweet.backupeight">
    </div>
    <div style="padding-left: 10px;padding-right: 10px; border-bottom:1px solid rgba(0,0,0,.15);border-top:1px solid rgba(0,0,0,.15);">
        <div class="zhengmingHeader">
            <div style="font-weight: bold;font-size: 15px;color: #666">资料证明</div>
            <div style="display: flex;flex-direction: row;justify-content: flex-end;align-items: center">
                <div style="color: #43AC43;font-size: 15px">责任声明</div>
                <img style="width: 26px;height: 26px" src="http://onejf30n8.bkt.clouddn.com/gongzhongmore.png" />
            </div>
        </div>
        <div style="margin-top: 14px">
            <div style="font-size: 15px">认证机构</div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">{{cash.circleusernickname}}</div>
            </div>
        </div>
        <div style="margin-top: 14px">
            <div style="font-size: 15px">监督小组</div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">{{cash.superusernickname}}</div>
            </div>
        </div>
        <div style="margin-top: 14px;">
            <div style="font-size: 15px;">具体负责人</div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">{{cash.circleusernickname}}</div>
            </div>
        </div>
        <div style="margin-top: 14px;">
            <div style="font-size: 15px;">收款人</div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;">
                <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                    <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                    <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">{{cash.acceptmoneyman}}</div>
                </div>
                <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px;margin-left: 20px">
                    <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                    <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">关系证明已提交</div>
                </div>
            </div>
        </div>
        <div style="margin-top: 14px;">
            <div style="font-size: 15px;">受助人</div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">{{cash.behelpusername}}</div>
            </div>
            <div  v-if="cash.proveone=='11'"  style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">身份证明已提交</div>
            </div>

            <div  v-if="cash.provetwo=='22'" style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">居委会证明已提交</div>
            </div>
            <div   v-if="cash.provethree=='33'" style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">医院证明已提交</div>
            </div>
            <div   v-if="cash.provefour=='44'" style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">贫困证明已提交</div>
            </div>
            <div   v-if="cash.provefive=='55'" style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">收款人关系证明已提交</div>
            </div>
             <!--审核情况-->
            <div style="font-size: 15px; margin-top: 10px">审核状态</div>
            <div   v-if="cash.progressstate=='10'" style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">尚未审核</div>
            </div>
            <div   v-if="cash.progressstate=='100'" style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">线上审核通过</div>
            </div>
            <div   v-if="cash.progressstate=='200'" style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="http://onejf30n8.bkt.clouddn.com/gongzhongselected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">实地调查通过</div>
            </div>

        </div>
        <div style="height: 10px;background: #F5F6F9"></div>
        <!--实名证实-->
        <div style="display: flex;justify-content: space-between;align-items: center;height: 46px;border-bottom:1px solid rgba(0,0,0,.15);">
            <div>有<span>{{cash.sumbackup}}</span>人实名为Ta证实</div>
            <div v-on:click="confirmIt" style="cursor: pointer;color: #43AC43;">我要证实</div>
        </div>
        <div style="display: flex;align-items:center;justify-content: space-between;height: 60px">
            <div style="display: flex;justify-content: flex-start">
                <template v-for="url in affirmImgUrls">
                    <img :src="url"  style=" width: 38px;height: 38px;border-radius: 19px; ">
                </template>
            </div>
            <div>
                <img src="http://onejf30n8.bkt.clouddn.com/gongzhongmore.png">
            </div>
        </div>
        <div v-if="cash.sumbackup>0"  style="display:flex;align-items:center;height: 30px;margin-top: 0px;margin-bottom: 10px;background: #F5F5F5">
            情况属实。
        </div>
        <div v-else  style="display:flex;align-items:center;height: 30px;margin-top: 0px;margin-bottom: 10px;background: #F5F5F5">
            还没有人证实。
        </div>
        <%--这个是受助人承诺--%>
        <div>
            <div style="display: flex;height: 30px;width: 100%;border-top: rgba(0,0,0,.15) solid 1px;color: #43AC43;justify-content: flex-start;align-items:center;">
                受助人承诺
            </div>
            <div>承诺诺类型（<span>{{cash.promisetype}}</span>）</div>
            <div>{{cash.promisemiaoshu}}</div>
        </div>

    </div>
</div>

<!--进度更新-->
<div id="progressUpdate">
    <div style="display: flex;align-items: center;color: #43AC43;border-bottom: rgba(0,0,0,.15) solid 1px">
        <div style="display: flex;height: 40px;padding-left:10px;width: 50%;justify-content: flex-start;align-items:center;">
            进度更新
        </div>
        <div   v-on:click="wantUpdate" style=" cursor: pointer;display:flex;height: 40px;padding-right:10px;width: 50%;justify-content: flex-end;align-items:center;">
            我要更新
        </div>
    </div>
    <template v-for="item in lp">
        <div  :key="item.itemProgress.itemprogressid"  style="border-bottom: 1px solid rgba(0,0,0,.15);margin-top: 2px">
            <div style="display: flex;justify-content: space-between;align-items: flex-start;padding: 10px;">
                <div style="display: flex">
                    <img :src="item.itemProgress.userphoto" style="height: 36px;width: 36px;border-radius: 18px">
                    <div style="margin-left: 10px">
                        <div style="display: flex;align-items: flex-start">
                            <div>{{item.itemProgress.usernickname}}</div>
                            <div style="margin-left: 10px">{{item.itemProgress.backupone}}</div>
                        </div>
                        <div style="margin-top: 5px">{{item.itemProgress.updatetime|time}}</div>
                    </div>
                </div>
                <img
                    v-on:click="commentToUpdate"
                    id="commentToUpdate" src="<%=contextPath%>/resources/image/pinglun2.png"
                    v-bind:data-itemprogressid="item.itemProgress.itemprogressid"
                    v-bind:data-useridreciver="item.itemProgress.userid"
                    v-bind:data-useruuidreciver="item.itemProgress.uuid"
                    v-bind:data-recivernickname="item.itemProgress.usernickname"
                    v-bind:data-reciverphoto="item.itemProgress.userphoto" />
            </div>
            <div style="padding-left: 30px;padding-right: 30px">{{item.itemProgress.content}}</div>
            <div style="padding-left: 30px">
                <template v-for="comment in item.lmp">
                    <div style="display: flex;background: #F5F5F5;padding-top: 5px;padding-bottom: 5px;margin-right: 10px;margin-left: 0px">
                        <div  style="color: #4284B6;">
                            <span  v-on:click="commentReplyToUpdate" style="cursor: pointer;"
                                   v-bind:data-itemprogressid="comment.itemprogressid"
                                   v-bind:data-useridreciver="comment.useridsender"
                                   v-bind:data-useruuidreciver="comment.useruuidsender"
                                   v-bind:data-recivernickname="comment.sendernickname"
                                   v-bind:data-reciverphoto="comment.senderphoto">
                                {{comment.sendernickname}}
                            </span><span>:</span>
                            <span v-if="comment.refer==1 " >
                                <span style="color: #0f0f0f">回复</span>
                                <span  v-on:click="commentReplyToUpdate"  style="color: #4284B6;cursor: pointer;"
                                       v-bind:data-itemprogressid="comment.itemprogressid"
                                       v-bind:data-useridreciver="comment.useridreciver"
                                       v-bind:data-useruuidreciver="comment.useruuidreciver"
                                       v-bind:data-recivernickname="comment.recivernickname"
                                       v-bind:data-reciverphoto="comment.reciverphoto">
                                    {{comment.recivernickname}}
                                </span><span>:</span>
                            </span>
                            <span style="color: #666">{{comment.content}}</span>
                        </div>

                    </div>
                </template>
            </div>
        </div>
    </template>
</div>

<!--筹款动态-->
<div id="dynamic">
    <div style="display: flex;align-items: center;color: #43AC43;border-bottom: rgba(0,0,0,.15) solid 1px">
        <div style="display: flex;height: 40px;margin-left:10px;width: 50%;justify-content: flex-start;align-items:center;">
            筹款动态(100)
        </div>
        <div style="display: flex;height: 40px;width: 50%;justify-content: center;align-items:center;">
            <span></span>
        </div>
    </div>

    <template v-for="item in lp">
        <div v-show="updateORprogress" :key="item.moneySource.moneysourceid"  style="border-bottom: 1px solid rgba(0,0,0,.15);margin-top: 2px">
            <div style="display: flex;justify-content: space-between;align-items: flex-end;padding: 10px;">
                <div style="display: flex">
                    <img :src="item.moneySource.helpmanphotourl" style="height: 36px;width: 36px;border-radius: 18px">
                    <div style="margin-left: 10px">
                        <div style="display: flex;align-items: flex-start">
                            <div>{{item.moneySource.helpmanusername}}</div>
                            <div style="margin-left: 10px">支持了</div>
                            <div style="margin-left: 10px">{{item.moneySource.moneynum}}</div>
                        </div>
                        <div style="margin-top: 5px">{{item.moneySource.helptime |time}}</div>
                    </div>
                </div>
                <img src="http://onejf30n8.bkt.clouddn.com/gongzhongpinglun2.png" style="height: 18px;width: 18px;cursor: pointer;"
                     id="commentToDynamic"
                     v-on:click="commentToDynamic"
                     v-bind:data-moneysourceid="item.moneySource.moneysourceid"
                     v-bind:data-useridreciver="item.moneySource.useridgoodguy"
                     v-bind:data-useruuidreciver="item.moneySource.backupthree"
                     v-bind:data-recivernickname="item.moneySource.backupone"
                     v-bind:data-reciverphoto="item.moneySource.backuptwo"/>
            </div>
            <div style="padding-left: 10px">
                <template v-for="comment in item.lmp">
                    <div style="display: flex;background: #F5F5F5;padding-top: 5px;padding-bottom: 5px;margin-right: 10px;margin-left: 20px">
                        <div  style="color: #4284B6;">
                            <span  v-on:click="commentReplyToDynamic" style="cursor: pointer;"
                                   v-bind:data-moneysourceid="comment.moneysourceid"
                                   v-bind:data-useridreciver="comment.useridsender"
                                   v-bind:data-useruuidreciver="comment.useruuidsender"
                                   v-bind:data-recivernickname="comment.sendernickname"
                                   v-bind:data-reciverphoto="comment.senderphoto">
                                   {{comment.sendernickname}}
                            </span><span>:</span>
                            <span v-if="comment.refer==1 " >
                                <span style="color: #0f0f0f">回复</span>
                                <span  v-on:click="commentReplyToDynamic"  style="color: #4284B6;cursor: pointer;"
                                       v-bind:data-moneysourceid="comment.moneysourceid"
                                       v-bind:data-useridreciver="comment.useridreciver"
                                       v-bind:data-useruuidreciver="comment.useruuidreciver"
                                       v-bind:data-recivernickname="comment.recivernickname"
                                       v-bind:data-reciverphoto="comment.reciverphoto">
                                    {{comment.recivernickname}}
                                </span><span>:</span>
                            </span>
                            <span style="color: #666">{{comment.pingluntext}}</span>
                        </div>

                    </div>
                </template>
            </div>
        </div>
    </template>
</div>

<%--这里是各种弹出框--%>
<div class="contain">
    <!--这个"modal modalReport.js 管辖"-->
    <div id="modalReport" class="modalReport">
        <div id="tipsReport" class="tipsReport">
            <div class="doCommentReport">与被举报或证实人的关系</div>
            <div style="margin-right: 15px;margin-left: 15px">
                <input  id="reportInput"  style="width: 100%;height: 20px;margin-bottom: 6px" placeholder="15字以内" />
            </div>
            <div class="doCommentReport">举报或证实内容</div>
            <div style="margin-right: 15px;margin-left: 15px">
                <textarea  id="reportTextarea" class="commentTextReport"  rows="6" cols="3"  placeholder="140字以内"></textarea>
            </div>
            <div class="cancelReplayReport">
                <div class="cancelReport"  v-on:click="cancelReport"> 取消</div>
                <div  id="applyReport" class="cancelReport"  v-on:click="applyReport" data-RCTag="">提交</div>
            </div>
        </div>
    </div>

    <!--这个"我要更新-wangUpdate"-->
    <div id="wantUpdate" class="modal">
        <div id="needUpdate" class="tips">
            <div class="doComment">进度更新</div>
            <div style="margin-right: 15px;margin-left: 15px">
                <textarea id="updateTxt"   class="commentText"  rows="6" cols="3"  placeholder="140字以内"></textarea>
            </div>
            <div class="cancelReplay">
                <div class="cancel"  v-on:click="cancelWantUpdate">取消</div>
                <!--data-updateORdynamic 区分是对进度更新还是支持动态的，data-refer区分参见refer-->
                <div  id="reply2"  class="cancel"
                      v-on:click="doUpdate"
                      data-updateORdynamic=""
                      data-refer="">
                    更新
                </div>
            </div>
        </div>
    </div>

    <!--这个"modal 初始时是要隐藏的"-->
    <div id="modal" class="modal">
        <div id="tips" class="tips">
            <div class="doComment">发表评论</div>
            <div style="margin-right: 15px;margin-left: 15px">
                <textarea   id="commentText" class="commentText"  rows="6" cols="3"  placeholder="140字以内"></textarea>
            </div>
            <div class="cancelReplay">
                <div class="cancel"  v-on:click="cancelPinglun">取消</div>
                <!--data-updateORdynamic 区分是对进度更新还是支持动态的，data-refer区分参见refer-->
                <div  id="reply"  class="cancel"
                      v-on:click="replyPinglun"
                      data-updateORdynamic=""
                      data-refer="">
                    回复
                </div>
            </div>
        </div>
    </div>
    <!--  以下这个是发送请求结束后的提示-->
    <div id="dialog" class="dialog">
        <div id="dialogTips" class="dialogTips">回复成功</div>
    </div>
    <!--这个是邮箱登录界面-->
    <div id="loginEmail" class="loginEmail">
        <div class="loginMain">
            <div class="closeWrapper">
                <img class="clickImg"    v-on:click="closeLoginModal"    src="http://onejf30n8.bkt.clouddn.com/gongzhongclose2.png">
            </div>

            <div class="logoWrapper">
                <img src="http://onejf30n8.bkt.clouddn.com/gongzhong512.png" style="width: 50px;height: 50px;border-radius: 25px" />
            </div>
            <div  id="errorTips" class="errorTips">错误提示</div>
            <!--邮箱,密码输入域-->
            <div class="emailWrapper">
                <div class="emailpassTxt">邮箱</div>
                <input  id="emailInput"  class="input" placeholder="输入你的邮箱"/>
            </div>

            <div class="passwdWrapper">
                <div class="emailpassTxt">密码</div>
                <input  id="passwordInput" class="input" placeholder="输入你的密码"/>
            </div>
            <div class="loginInWrapper">
                <div  class="loginIn"   v-on:click="loginIn"   style="color: #ffffff;font-size: 18px">登录</div>
            </div>
            <!--微信登录-->
            <div class="wechatQQweibo">
                <div><img  class="wechatImg"   src="http://onejf30n8.bkt.clouddn.com/gongzhongwechat.png"></div>
                <div><img  class="wechatImg"   src="http://onejf30n8.bkt.clouddn.com/gongzhongweibo.png"></div>
                <div><img  class="wechatImg"   src="http://onejf30n8.bkt.clouddn.com/gongzhongQQ.png"></div>
            </div>
        </div>
    </div>
</div>
<%--弹出框结束--%>


<%--底部--%>
<div id="foo" style="height: 50px"></div>
<div class="footer">
    <div class="zhuafa" style="width: 46%;display: flex;align-items: center;justify-content: center">
        <span>转发</span>
        <img  style="height: 30px;width: 30px" src="http://onejf30n8.bkt.clouddn.com/gongzhongzhuafa.png">
    </div>
    <div class="donate"  style="width: 46%;height: 40px;border-radius:20px;display: flex;align-items: center;justify-content: center;background-color: #2FAC4C">
        <span style="color: #ffffff">捐助Ta</span>
    </div>
</div>

<script>
    Vue.filter('time', function (value) {//value为13位的时间戳
        function add0(m) {
            return m < 10 ? '0' + m : m
        }
        var time = new Date(parseInt(value));
        var y = time.getFullYear();
        var m = time.getMonth() + 1;
        var d = time.getDate();

        return y + '.' + add0(m) + '.' + add0(d);
    });
</script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/indexDynamic.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/indexUpPart.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/indexProgressUpdate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/modalLogin.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/modalLoading.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/modalCommonInput.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/modalReport.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/modalPingLun.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/modalWantUpdate.js"></script>
</body>
</html>

