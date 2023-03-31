<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="desc" uri="/descTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>更新用户信息</title>

	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	%>
	<c:set var="path" value="<%=basePath %>"></c:set>

	<script type="text/javascript">
		function closeOpen()
		{
			history.back();
			// window.returnValue=false;
			// window.close();
		}

		function check1()
		{
			if(document.form1.loginpw.value=="")
			{
				alert("请输入密码");
				return false;
			}
			if(document.form1.loginpw.value!=document.form1.loginpw1.value)
			{
				alert("两次密码不一致");
				return false;
			}
			document.form1.submit();
		}
	</script>
</head>
<body background="${path}/images/bhj.jpg">
<desc:banner/>
<form action="../updateUser" name="form1" method="post">
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr>
			<th height="40" colspan="2" bgcolor="#FFFFFF" class="f12b-red" style="font-size: 11px;">
				更新密码
			</th>
		</tr>
		<tr>
			<td height="30" align="right" bgcolor="#F9F9F9" style="font-size: 11px;">
				新密码：
			</td>
			<td bgcolor="#FFFFFF">
				&nbsp;
				<input type="password" name="loginpw"/>
			</td>
		</tr>
		<tr>
			<td height="30" align="right" bgcolor="#F9F9F9" style="font-size: 11px;">
				密码确认：
			</td>
			<td bgcolor="#FFFFFF">
				&nbsp;
				<input type="password" name="loginpw1"/>
			</td>
		</tr>
		<tr>
			<td height="30" align="right" bgcolor="#F9F9F9">
				&nbsp;
			</td>
			<td bgcolor="#FFFFFF">
				&nbsp;
				<input type="button" value="确定" onclick="check1();"/>
				<input type="button" value="取消" onclick="closeOpen()"/>
			</td>
		</tr>
	</table>
</form>
<desc:footer/>
</body>
</html>