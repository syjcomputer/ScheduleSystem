<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="desc" uri="/descTag" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>按月份查询</title>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <script type="text/javascript" src="<%=path %>/js/WdatePicker/WdatePicker.js"></script>
</head>

<body leftmargin="2" topmargin="2" background='<%=path %>/images/bhj.jpg'>
<desc:banner/>
<form action="<%=path%>/querySchedule" method="get">
    <input name="type" type="hidden" value="month">
    <table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" style="margin-top:8px">
        <tr>
            <td>
                查询月份<input name="month" value="${month}" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"/>
                <input type="submit" value="查询"/>
            </td>
        </tr>
    </table>
</form>
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
    <tr bgcolor="#E7E7E7">
        <td height="14" colspan="4" background="<%=path %>/images/admin/tbg.jpg">&nbsp;个人日程查询&nbsp;</td>
    </tr>
    <tr align="center" bgcolor="#FAFAF1" height="22">
        <td width="10%">截止时间</td>
        <td width="10%">提醒时间</td>
        <td width="30%">标题</td>
        <td width="30%">内容</td>

    </tr>
    <c:forEach items="${schedulePoList}" var="schedule">
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='blue';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
            <td bgcolor="#FFFFFF" align="center">
                    ${schedule.ddl}
            </td>
            <td bgcolor="#FFFFFF" align="center">
                    ${schedule.reminderTime}
            </td>
            <td bgcolor="#FFFFFF" align="center">
                    ${schedule.title}
            </td>
            <td bgcolor="#FFFFFF" align="center">
                    ${schedule.context}
            </td>
        </tr>
    </c:forEach>
</table>
<div style="text-align: center">
    <input type="button" value="返回" onclick="javascript:window.location.href='<%=path%>/admin/menu.jsp'"/>
</div>
<desc:footer/>
</body>
</html>