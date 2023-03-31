<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
    <%
        String date = request.getParameter("date");

    %>

    <c:set var="path" value="<%=basePath %>"></c:set>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
<%--    <link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />--%>
    <script type="text/javascript" src="${path}/js/WdatePicker/WdatePicker.js"></script>
    <%@taglib prefix="desc" uri="/descTag" %>

    <script language="javascript">
        function check()
        {
            if(document.formAdd.title.value == "")
            {
                alert("日程标题不能为空!");
                return false;
            }
            if(document.formAdd.context.value == "")
            {
                alert("日程内容不能为空!");
                return false;
            }
            if(document.formAdd.ddl.value == "")
            {
                alert("截止时间不能为空!");
                return false;
            }
            if(document.formAdd.remindTime.value == "")
            {
                alert("提醒时间不能为空!");
                return false;
            }
            return true;
        }
    </script>
</head>

<body leftmargin="2" topmargin="9" background='<%=path %>/images/bhj.jpg'>
<desc:banner/>
<form action="../addOneSchedule?date="+${date} name="formAdd" method="post">
    <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
        <tr bgcolor="#EEF4EA">
            <td colspan="3" background="<%=path %>/images/wbg.gif" class='title'><span>人个日程添加</span></td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                标题：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="text" name="title" size="20"/>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                内容：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="text" name="context" size="20"/>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                截止日期：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input name="today" value="<%=request.getParameter("date")%>" readonly="readonly" type="text"/>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                截止时间：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input name="ddl" readonly="readonly" class="Wdate"  type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})"/>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                提醒时间：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input name="remindTime" readonly="readonly" class="Wdate"  type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                &nbsp;
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="hidden" name="user_id" value="${sessionScope.user.id }"/>
                <input type="submit" value="提交" onclick="return check();"/>
                <input type="reset" value="返回" onclick="javascript:history.back()"/>&nbsp;
            </td>
        </tr>
    </table>
</form>
<desc:footer/>
</body>
</html>