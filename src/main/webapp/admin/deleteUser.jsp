<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="desc" uri="/descTag" %>
<%@taglib prefix="schedules" uri="/scheduleListTag" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>删除用户</title>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <script type="text/javascript" src="<%=path %>/js/WdatePicker/WdatePicker.js"></script>
</head>

<body leftmargin="2" topmargin="2" background='<%=path %>/images/bhj.jpg'>
<desc:banner/>

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
    <tr bgcolor="#E7E7E7">
        <td height="14" colspan="4" background="<%=path %>/images/admin/tbg.jpg">&nbsp;所有用户&nbsp;</td>
    </tr>
    <tr align="center" bgcolor="#FAFAF1" height="22">
        <td width="10%">id</td>
        <td width="10%">用户名</td>
        <td width="30%">电话</td>
        <td width="30%">操作</td>

    </tr>

    <c:forEach items="${allUsers}" var="user">
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='blue';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
            <td bgcolor="#FFFFFF" align="center">
                    ${user.id}
            </td>
            <td bgcolor="#FFFFFF" align="center">
                    ${user.name}
            </td>
            <td bgcolor="#FFFFFF" align="center">
                    ${user.telephone}
            </td>
            <td bgcolor="#FFFFFF" align="center">
                <a href="<%=path%>/delOneUser?id=${user.id}">删除</a>

            </td>
        </tr>
    </c:forEach>
</table>
<div style="text-align: center">
    <input type="button" value="返回" onclick="javascript:window.location.href='<%=path%>/admin/adminMenu.jsp'"/>
</div>
<desc:footer/>
</body>
</html>