
<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>公司统计</title>
</head>
<body>

  <div>${companyTotal}</div>
  <div>${staff}</div>
  <div>${employee}</div>
</body>
</html>
