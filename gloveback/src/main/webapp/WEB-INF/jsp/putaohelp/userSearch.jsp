<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>检索用户</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>
        .searchUserWrap{
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: flex-start;
            width: 100%;
            height: 56px;
            background-color: #F2F2F2;
        }
        .seachUser{
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-items: center;
            width: 361px;
            height: 36px;
            margin-left: 10px;
        }
        #phone{
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            background-color: #fff;
            border-radius: 4px;
            border: 1px solid #bfcbd9;
            box-sizing: border-box;
            color: #1f2d3d;
            display: block;
            font-size: inherit;
            height: 36px;
            line-height: 1;
            outline: 0;
            padding: 3px 10px;
            transition: border-color .2s cubic-bezier(.645,.045,.355,1);
            width: 300px;
        }
        .el-button{
            display: inline-block;
            line-height: 1;
            white-space: nowrap;
            cursor: pointer;
            background: #fff;
            border: 1px solid #bfcbd9;
            color: #1f2d3d;
            -webkit-appearance: none;
            text-align: center;
            box-sizing: border-box;
            outline: 0;
            margin: 0;
            padding: 10px 15px;
            font-size: 14px;
            border-radius: 4px;
            width: 60px;

        }
        .el-button--primary{
            color: #fff;
            background-color: #20a0ff;
            border-color: #20a0ff;
            margin-left: 5px;
        }
        input::-webkit-input-placeholder {
            color: #6f7a7e;
            font-size:12px;

        }
        .userHeaderContain{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            height: 40px;
            width: 100%;
            background-color: #EEF1F6;
            margin-top: 5px;
            margin-bottom: 1px;
        }
        .userBodyContain{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            height: 40px;
            width: 100%;
            background-color: #ffffff;
            border-bottom: 1px solid #dfe6ec;
        }
        .userBodyContain:hover{
            background-color:#EEF1F6;
        }
        .userHeaderLeft{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            width: 500px;
        }
        .userBodyrLeft{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            width: 500px;
        }
        .userHeaderRight{
              width: 120px;
              line-height: 40px;
        }
        .headerItem{
            width: 80px;
            line-height: 40px;
            text-align: center;
        }
        .userHeaderLeft2{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            width: 90%;
        }
        .headerItem2{
            width: 100px;
            line-height: 40px;
            text-align: center;
        }
        .userHeaderRight2{
            width: 60px;
            line-height: 40px;
        }
        .modal{
            //display: flex;
            display: none;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            width: 100%;
            height: 700px;
            position: fixed;
            left: 0px;
            top: 0px;
            background-color:rgba(0,0,0,0.5);
            /*opacity: 0.3;*/
        }
        .modalIn{
            display: flex;
            flex-direction: column;
            padding: 10px;
            width: 700px;
            height: 500px;
            background-color: #ffffff;
            border-radius: 5px;
        }

        .modalItem{
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-items: center;
            padding-left: 20px;
            color: #48576a;
            height: 50px;
        }
        .modalTop{
            display: flex;
            justify-content: space-between;
            border-bottom: #2FAC4C 1px solid;
        }
        .modalTopItem{
            width: 60px;
            height: 60px;
            line-height: 60px;
            cursor: pointer;
            color: #0070FF;
        }
        .pageNext{
            //display: flex;
            display: none; //家人很少超过20，隐藏
            justify-content: center;
            align-items: center;
            width: 100%;
            height: 60px;
        }
        .pageItem{
            width: 100px;
            height: 30px;
            line-height: 30px;
            text-align: center;
            color: #ffffff;
            background-color: #20a0ff;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div id="userSearch">
    <div class="searchUserWrap">
        <div class="seachUser">
            <input id="phone" name="phone" type="text" placeholder="请输入手机号" autocomplete="off"/>
            <button v-on:click="doSearchUser"  class="el-button el-button--primary">检索</button>
        </div>
    </div>
    <div class="userHeaderContain">
        <div class="userHeaderLeft">
            <div class="headerItem">ID</div>
            <div class="headerItem">昵称</div>
            <div class="headerItem">手机号</div>
        </div>
        <div class="userHeaderRight">操作</div>
    </div>
    <div  id="userBodyContain" class="userBodyContain" style="display: none">
        <div class="userBodyrLeft">
            <div class="headerItem">{{dataUser.userid}}</div>
            <div class="headerItem">{{dataUser.usernickname}}</div>
            <div class="headerItem">{{dataUser.userphone}}</div>
        </div>
        <div  v-on:click="getAccountList"  class="userHeaderRight" style="cursor: pointer;color: #0070FF" >详情</div>
    </div>

    <div id="userAccountList"> 
        <div class="userHeaderContain">
            <div class="userHeaderLeft2">
                <div class="headerItem2">姓名</div>
                <div class="headerItem2">身份证号</div>
                <div class="headerItem2">互助类别</div>
                <div class="headerItem2">当前状态</div>
                <div class="headerItem2">账户余额</div>
            </div>
            <div class="userHeaderRight2">操作</div>
        </div>

        <template v-for="item in accountList"> 
            <div class="userBodyContain">
                <div class="userHeaderLeft2">
                    <div class="headerItem2">{{item.username}}</div> 
                    <div class="headerItem2">{{item.accountuuid}}</div> 
                    <div class="headerItem2">{{item.categorytype}}</div> 
                    <div class="headerItem2">{{item.nowstate}}</div> 
                    <div class="headerItem2">{{item.paytotalmoney}}</div> 
                </div>
                <div   v-on:click="getlookDetail"
                       v-bind:data-accountuuid="item.accountuuid"
                       v-bind:data-categorytype="item.categorytype"
                       class="userHeaderRight2"
                       style="cursor: pointer;color: #0070FF">
                    查看
                </div>
            </div>
        </template> 
        <div class="pageNext">
            <div class="pageItem">上一页</div>
            <div class="pageItem">下一页</div>
        </div>
        <!---模态框-->
        <div  id="modal" class="modal">
            <div class="modalIn">
                <div class="modalTop">
                    <div class="modalTopItem"></div>
                    <div class="modalTopItem">详情</div>
                    <div  v-on:click="doClose"  class="modalTopItem">关</div>
                </div>

                <div class="modalItem">
                    <div>姓名：</div>
                    <div>{{lookDetail.username}}</div>
                </div>
                <div class="modalItem">
                    <div>身份证：</div>
                    <div>{{lookDetail.accountuuid}}</div>
                </div>
                <div class="modalItem">
                    <div>是否解除：</div>
                    <div>{{lookDetail.breakif}}</div>
                </div>
                <div class="modalItem">
                    <div>互助类别：</div>
                    <div>{{lookDetail.categorytype}}</div>
                </div>
                <div class="modalItem">
                    <div>加入时间：</div>
                    <div>{{lookDetail.joindate}}</div>
                </div>
                <div class="modalItem">
                    <div>当前状态：</div>
                    <div>{{lookDetail.nowstate}}</div>
                </div>
                <div class="modalItem">
                    <div>账户余额：</div>
                    <div>{{lookDetail.paytotalmoney}}</div>
                </div>

            </div>
        </div>
    </div>


</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/userSearch.js"></script>
</html>
