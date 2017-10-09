
<%--<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>

    <title>发布项目</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/publishHelpInfo.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>

</head>
<body id="root" style="margin: 0px">
<!--发布求助信息-->
<form  id="form"   action="wapmultiUpload.do"  method="post"   enctype="multipart/form-data" class="form-horizontal" role="form" target="hidden_frame">
    <div id="publishInfo" >
        <!--以下要用js从本地获取具体值后，然后改变默认值-->
        <input  id="token"  name="token" type="text" style="display: none" value="">
        <input  id="useruuid" name="useruuid" type="text" style="display: none" value="">
        <input  id="userName" name="userName" type="text" style="display: none" value="">
        <input  id="selfintroduce"  name="selfintroduce" type="text" style="display: none" value="">
        <input  id="photoUrl" name="photoUrl" type="text" style="display: none" value="">


        <div class="ZhengMing">
            <div class="proveHeader">证明信息</div>
            <div class="checkTeam">
                <div class="checkTeamTxt">所在城市</div>
                <input  id="cityName"  onblur="checkCityName()" class="checkTeamInput"   name="cityName" type="text" value="" placeholder="8字以内" maxlength="8"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">受助人姓名</div>
                <input id="shouZhurenName"   onblur="checkShouZhurenName()" class="checkTeamInput"    name="shouZhurenName" type="text" value="" placeholder="8字以内" maxlength="8"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">受助人ID</div>
                <input  id="shouZhureniDentityNo"  onblur="checkShouZhureniDentityNo()" class="checkTeamInput" name="shouZhureniDentityNo"  type="text" value="" placeholder="填写身份证号" maxlength="18"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">收款人姓名</div>
                <input  id="acceptMoneyName"  onblur="checkAcceptMoneyName()" class="checkTeamInput"  name="acceptMoneyName"  type="text" value="" placeholder="填写收款人姓名" maxlength="8"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">收款人电话</div>
                <input  id="acceptMoneyPhone" onblur="checkAcceptMoneyPhone()" class="checkTeamInput"   name="acceptMoneyPhone"  type="text" value="" placeholder="填写手机号" maxlength="11"/>
            </div>

            <div class="ShiFo">
                <div class="ShiFoTxt">是否有身份证明</div>
                <input  class="ShiFoInput"   name="prove"  type="checkbox" value="11" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有居委会证明</div>
                <input class="ShiFoInput"  name="prove"  type="checkbox" value="22" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有医院证明</div>
                <input class="ShiFoInput"   name="prove"  type="checkbox" value="33" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有贫困证明</div>
                <input class="ShiFoInput" name="prove" type="checkbox" value="44" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有收款人关系证明</div>
                <input class="ShiFoInput"  name="prove"  type="checkbox" value="55" />
            </div>

        </div>
        <%--以下是承诺--%>
        <div class="proveHeader">受助人承诺</div>
        <div style="padding-left: 10px">承诺类型</div>
        <div class="promiseBody" style="display:flex;flex-direction: row;align-items: center;justify-content: space-around;">
            <div class="ChengNuoItem">
                <div>A</div>
                <input  type="checkbox"  name="chengnuoType" value="1"   />
            </div>
            <div class="ChengNuoItem">
                <div>B</div>
                <input  type="checkbox"  name="chengnuoType" value="2" />
            </div>
            <div class="ChengNuoItem">
                <div>C</div>
                <input  type="checkbox"  name="chengnuoType" value="3" />
            </div>
        </div>
        <div class="PromiseTxT">
            <div class="checkTeamTxt">我的承诺</div>
            <textarea   id="chengnuoContent"  onblur="checkChengnuoContent()" class="promiseTextarea"  name="chengnuoContent"  rows="10" cols="10" value="" placeholder="写下承诺" maxlength="200"></textarea>
        </div>
        <%--捐款信息--%>
        <div class="proveHeader">捐款信息</div>
        <div class="donateDate">
            <div>开始日期:</div>
            <div style="margin-left: 20px">今天</div>
        </div>
        <div class="checkTeam">
            <div class="checkTeamTxt">截止日期</div>
            <input  id="endDate"  onblur="checkendDate()" class="checkTeamInput"  name="endDate"  type="date" value="" />
        </div>
        <div class="checkTeam">
            <div class="checkTeamTxt">募捐金额</div>
            <input  id="targetMoney"  onblur="checkTargetMoney()" class="checkTeamInput"  name="targetMoney"  type="number" value="" placeholder="务必输入整数，小于20万"/>
            <div>元</div>
        </div>
        <div class="checkTeam">
            <div class="checkTeamTxt">筹款标题</div>
            <input  id="moneyTitle"   onblur="checkMoneyTitle()" class="checkTeamInput"  name="moneyTitle"  type="text" value="" placeholder="16字以内" maxlength="16"/>
        </div>
        <div class="PromiseTxT">
            <div class="checkTemTxt">描述详情</div>
            <textarea id="content" onblur="checkContent()" class="promiseTextarea" name="content"  rows="30" cols="10" value="" placeholder="800字以内" maxlength="800" style="font-size: 15px"></textarea>
        </div>
    </div>
    <div>
        <div class="proveHeader">上传相应证明图片</div>
        <div class="selectImg">
            <span>1.</span> <input  name="fileone"  type="file" value="浏览" filename=" " />
        </div>
        <div class="selectImg">
            <span>2.</span> <input  name="filetwo"  type="file" value="浏览" />
        </div>
        <div  class="selectImg">
            <span>3.</span> <input  name="filethree"  type="file" value="浏览" />
        </div>
        <div class="selectImg">
            <span>4.</span> <input  name="filefour"  type="file" value="浏览" />
        </div>
        <div  class="selectImg">
            <span>5.</span> <input   name="filefive"  type="file"  value="浏览" />
        </div>
        <div  class="selectImg">
            <span>6.</span> <input   name="filesix"  type="file" value="浏览" />
        </div>
        <div  class="selectImg" style="margin-bottom: 0px">
            <span>7.</span> <input   name="fileseven"  type="file" value="浏览" />
        </div>
        <div style="display: flex;justify-content: center;align-content: center;height: 40px">
            <button type="submit"  class="button">提交</button>
        </div>
    </div>
</form>
<div style="height: 50px"></div>

<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/addHelpMan.js"></script>
</body>
</html>



























