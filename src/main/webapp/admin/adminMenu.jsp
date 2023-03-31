<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="desc" uri="/descTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>管理员功能页</title>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	%>
	<c:set var="path" value="<%=basePath %>"></c:set>

	<script type="text/css">
		iframe{
			z-index: 1;
		}
		.right{
			z-index: 1
		}
		.left{
			z-index: 1;
		}

	</script>

	<%--    <base target="_parent" />--%>

	<!-- js引入 -->
	<script src="${path }/js/jquery.js"></script>
	<script src="${path }/js/bootstrap/bootstrap.min.js"></script>

</head>
<body style="background:url(<%=path %>/images/bhj.jpg); background-size:100% 100% ; background-attachment: fixed">
<desc:banner/>
<div id="left" style="float: left" class="left">
	<iframe src="${path }/admin/leftAdmin.jsp" width="200px" height="800px" scrolling="yes" frameborder="0">
		<p>Your browser does not support iframes.</p>
	</iframe>
</div>
<div id="right" style="float: right" class="right">
	<iframe src="${path }/admin/calendar.jsp" width="1100px" height="800px" scrolling="yes" frameborder="0">
		<p>Your browser does not support iframes.</p>
	</iframe>
</div>

<desc:footer/>
</body>
</html>