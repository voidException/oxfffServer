<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>公司列表</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>
        #companylist{
            display: flex;
            flex-direction: row;
        }
        .itemWrapper{
            border-bottom: dashed 1px red;
        }
        .itemWrapperRight{
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            height: 80px;
        }
        .item{
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-items: center;
            height: 40px;
        }
        .left{
           width: 300px;
            border-right:dashed 1px red;
        }
        .contain{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
        }
        .page{
            display: flex;
            flex-direction: row;
            width: 300px;
            justify-content: space-around;
            border-bottom: #2FAC4C 1px  solid;
        }
        .right{
            margin-left: 10px;
        }
        .stafflist{
            display: none;
        }
        .pageUp{
            cursor: pointer;
            color: #0070FF;
        }
    </style>
</head>
<body style="height: 3000px">
<div id="companylist">
    <div class="left">
        <div class="page">
            <div v-on:click="goUpPage" class="pageUp"  >上一页</div>
            <div v-on:click="goNextPage" class="PageUp">下一页</div>
        </div>
        <template  class="temp" v-for="item in data">
            <div class="contain">
                <div class="itemWrapper">
                    <div class="item">
                        <div>公司名：</div>
                        <div>{{item.usernickname}}</div>
                    </div>
                    <div class="item">
                        <div>手机号：</div>
                        <div>{{item.userphone}}</div>
                    </div>
                </div>
                <div class="itemWrapperRight">
                    <div v-on:click="aadetail"  v-bind:data-uuid="item.useruuid" class="pageUp"> 详情>></div>
                    <div v-on:click="getStaffList" v-bind:data-uuid="item.useruuid"  class="pageUp"> 员工>></div>
                </div>
            </div>
        </template>
    </div>
    <div class="right">
        <!--点击详情，隐藏列表，点击列表，隐藏详情-->
        <div class="detail" id="detail">
            <template  class="temp" v-for="item in detailList">
                <div class="contain">
                    <div class="itemWrapper">
                        <div class="item">
                            <div>互助计划类型：</div>
                            <div>{{item.helptype}}</div>
                        </div>
                        <div class="item">
                            <div>员工总数：</div>
                            <div>{{item.staffall}}</div>
                        </div>
                        <div class="item">
                            <div>公司总金额：</div>
                            <div>{{item.totalmoenystr}}</div>
                        </div>
                        <div class="item">
                            <div>互助计划类型：</div>
                            <div>{{item.average}}</div>
                        </div>
                    </div>
                </div>
            </template>
        </div>
        <div class="stafflist" id="stafflist">
            <div class="page">
                <div v-on:click="goStaffUpPage"  class="pageUp">上一页</div>
                <div v-on:click="goStaffNextPage"  class="pageUp">下一页</div>
            </div>
            <template  class="temp" v-for="item in staffList">
                <div class="contain">
                    <div class="itemWrapper">
                        <div class="item">
                            <div>员工姓名：</div>
                            <div>{{item.staffname}}</div>
                        </div>
                        <div class="item">
                            <div>员工身份证号：</div>
                            <div>{{item.account}}</div>
                        </div>
                        <div class="item">
                            <div>员工的手机号：</div>
                            <div>{{item.staffphone}}</div>
                        </div>
                        <div class="item">
                            <div>互助的类型：</div>
                            <div>{{item.helptype}}</div>
                        </div>
                        <div class="item">
                            <div>加入的时间：</div>
                            <div>{{item.joindate}}</div>
                        </div>
                    </div>
                </div>
            </template>
        </div>
    </div>


</div>

<script type="text/javascript" src="<%=contextPath%>/resources/putaohelp/js/companylist.js"></script>
</body>
</html>
