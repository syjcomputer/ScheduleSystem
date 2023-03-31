<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="desc" uri="/descTag" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <%
        String path = (String) request.getAttribute("path");
        String message = (String) request.getAttribute("message");
        String path2 = request.getContextPath();
    %>
    <c:set var="message" value="<%= message%>"></c:set>
    <c:set var="path" value="<%=path%>"></c:set>
    <c:set var="path2" value="<%=path2%>"></c:set>

</head>

<body>
<desc:banner/>
<script type="text/javascript">

    function tiao()
    {
        <%--console.log(${message})--%>
        <c:if test="${message!=''}">
        console.log("message")
        document.write("<h1 align='center'>${message}</h1>")
        </c:if>
        <c:if test="${path!=null}">
        console.log("path")
        document.location.href = "<%=path%>"; // 跳转后可以回到原页面
        </c:if>
        <c:if test="${path==null}">
        window.history.back();
        </c:if>
    }

    setTimeout(tiao,1300)
</script>
<br> <br> <br> <br> <br> <br> <br> <br> <br>
<h1 align="center">${message}</h1>
<center><img src="<%=path2 %>/img/loading32.gif">页面跳转中</center>
<desc:footer/>
</body>
</html>
