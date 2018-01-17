<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>公司检索</title>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/axios.min.js"></script>
    <style>
        .search{
            display: flex;
            flex-direction: row;
            width: 100%;
            justify-content: flex-start;
            align-items: center;
            height: 50px;
        }
        .searchInput{
            display: flex;
            width: 300px;
            height: 40px;
            justify-content: center;
            align-items: center;
        }
        .searchSubmit{
            display: flex;
            justify-content: center;
            align-items: center;
            height: 40px;
            width: 80px;
            background-color: #00BB3B;
            color: #ffffff;
        }
        .item{
            display: flex;
            flex-direction: row;
            width: 300px;
            justify-content: space-between;
            padding-right: 20px;
            padding-left: 10px;
            height: 30px;
        }
        .itemWrapper{
            border-bottom: #4c4c4c 1px  solid;
            width: 330px;
        }
        .itemWrapperRight{
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            padding-top: 10px;
            width: 60px;
            height: 80px;
        }
        .contain{
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
        }
        .staff{
            cursor: pointer;
            color: #0070FF;
        }
        .wrap{
            display: flex;
            flex-direction: row;
        }
    </style>
</head>
<body>
<div id="companySearch">
    <div class="search">
        <input   id="searchInput"  class="searchInput"  placeholder="根据手机号搜索">
        <div   v-on:click="searchCompanyByPhone"  class="searchSubmit">确定</div>
    </div>
    <div class="wrap">
        <!--左边-->
        <div>
            <template  class="temp" v-for="item in data">
             <div class="contain">
                 <div class="itemWrapper">
                    <div class="item">
                        <div>类别：</div>
                        <div style="margin-right: 10px ;color: red">{{item.helptype}}</div>
                    </div>
                    <div class="item">
                        <div>该计划员工总数：</div>
                        <div style="margin-right: 10px ;color: red">{{item.staffall}}</div>
                    </div>
                     <div class="item">
                         <div>总司总余额：</div>
                         <div style="margin-right: 10px ;color: red">{{item.totalmoenystr}}</div>
                     </div>
                     <div class="item">
                         <div>平均余额：</div>
                         <div style="margin-right: 10px ;color: red">{{item.average}}</div>
                     </div>
                     <div class="item">
                         <div>是否需要提醒：</div>
                         <div style="margin-right: 10px ;color: red">{{item.needtips}}</div>
                     </div>

                 </div>

                <div class="itemWrapperRight">
                    <div  class="staff"  v-on:click="getStaffList"  v-bind:data-helptype="item.helptype"    v-bind:data-uuid="item.useruuid">员工>></div>
                </div>
             </div>
            </template>
        </div>
        <!--右边-->
        <div class="stafflist" id="stafflist">
            <%--<div class="page">--%>
            <%--<div v-on:click="goUpPage">上一页</div>--%>
            <%--<div v-on:click="goNextPage">下一页</div>--%>
            <%--</div>--%>
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
<script src="<%=contextPath%>/resources/putaohelp/js/companySearch.js"></script>
</body>
</html>
