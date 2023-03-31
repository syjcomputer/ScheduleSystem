<%@ page import="com.pers.ScheduleSystem.dao.Po.SchedulePo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.BufferedReader" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="schedules" uri="/scheduleListTag" %>
<%@taglib prefix="desc" uri="/descTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>具体日程安排</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        int identity = (int)request.getSession().getAttribute("identity");

        System.out.println(identity);
    %>
    <c:set var="path" value="<%=basePath %>"></c:set>
    <c:set var="identity" value="<%=identity %>"></c:set>
    <style type="text/css">
        body
        {
            margin-left: 0px;
            background-image: url(<%=path %>/images/bhj.jpg);
        }
        .submit
        {
            border: 3px double #416C9C;
            height: 22px;
            width: 100px;
            background-color: #F2F2F2;
            font-size: 12px;
            padding-top: 1px;
            cursor: hand;
        }
        .schedules{
            background-color: #FFFFFF;
        }
        .body{
            text-align: center;
        }
        table {
            border-collapse: collapse;
            width: 100%;

        }

        td, th {
            border: none;
            border-top: 1px solid black;
            border-bottom: 1px solid black;
            text-align: center;
            /*border: none;*/

        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<%--    <script language="javascript">--%>
<%--        function checkAdd()--%>
<%--        {--%>
<%--            <c:if test=""--%>
<%--            if(${identity}==1){--%>

<%--        }--%>
<%--            console.log(${identity})--%>
<%--            location.href='admin/addSchedule.jsp?date='+${date}--%>
<%--        }--%>
<%--    </script>--%>

</head>
<body>
<desc:banner/>
<h1 align="center">具体日程安排</h1>
<form action="../reviewSchedule" class="body">
    <div class="schedules">
        <schedules:calendarSchedules/>
    </div>
    <c:if test="${identity==1}">
        <input name="addSchedule" type="button" class="submit" onclick="javascript:location.href='admin/addSchedule.jsp?date='+${date}" value="添加新日程">
    </c:if>
    <input type="reset" value="返回" class="submit" onclick="javascript:history.back()"/>&nbsp;
</form>
<desc:footer/>
</body>
</html>