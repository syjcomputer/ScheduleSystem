<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="desc" uri="/descTag" %>
<%@taglib prefix="schedules" uri="/scheduleListTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>calender UI</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
    <c:set var="path" value="<%=basePath %>"></c:set>
    <script type="text/javascript" src="${path}/js/jquery.js" ></script>
    <link rel="stylesheet" href="${path}/css/bootstrap/bootstrap.css" />
    <link  rel="stylesheet" href="${path}/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/css/bootstrap/bootstrapValidator.css" />
    <link rel="stylesheet" href="${path}/css/calendar.css" />
    <script type="text/javascript" src="${path}/js/bootstrap/bootstrap.js" ></script>
    <script type="text/javascript" src="${path}/js/bootstrap/bootstrapValidator.js" ></script>

    <style type="text/css">
        body{
            background-color: transparent;
        }
    </style>

<%--    <base target="_parent" />--%>

</head>
<body>

    <div class="col-xs-10 col-xs-offset-1" align="center">
        <h1 align="center" style="color: #0f0f0f">日程表</h1>
        <form class="form-inline">
            <div class='calendar' id='calendar' align="center"></div>
            <div align="center" style="color: #d9edf7; font-weight: bold">说明：红色是提醒事项；黑色是未到期事项；灰色是已完成事项；黄色是过期事项</div>

        </form>
    </div>

    <script src="${path}/js/calendar.js" ></script>

<%--<desc:footer/>--%>
</body>
</html>