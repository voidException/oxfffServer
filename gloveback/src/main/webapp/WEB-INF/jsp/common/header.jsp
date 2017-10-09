<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String contextPath = request.getContextPath();
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";

%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<title>分享到微信圈</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no" />
	<link rel="stylesheet" href="<%=contextPath%>/resources/css/index.css">
	<link rel="stylesheet" href="<%=contextPath%>/resources/css/nickname.css">
	<link rel="stylesheet" href="<%=contextPath%>/resources/css/zhengming.css">
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
