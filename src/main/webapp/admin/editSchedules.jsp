<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="desc" uri="/descTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>编辑日程</title>

    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
//        int state = request.getAttribute("state");
    %>
    <c:set var="path" value="<%=basePath %>"></c:set>
<%--    <c:set var="state" value="<%=state %>"></c:set>--%>
    <script type="text/javascript" src="<%=path %>/js/WdatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${path}/js/jquery.js" ></script>
    <script type="text/javascript">
        function del()
        {
            var id = $("#id").val()
            console.log(id)
            $.post("./deleteSchedule","id="+id,function (data){

                alert(data["message2"])
                if(data["result2"]=="1"){
                    window.location.href = "${path}/schedules?date="+data["ddl"]
                }else {
                    window.location.href = "${path}/schedules?date="+data["ddl"]
                }
            })
        }

    </script>
</head>
<body leftmargin="2" topmargin="9" background='<%=path %>/images/bhj.jpg'>
<desc:banner/>
<form action="<%=path%>/reviewSchedule" name="formEdit" method="post">
    <input type="hidden" value="<%=request.getAttribute("id")%>" name="id" id="id">
    <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
        <tr bgcolor="#EEF4EA">
            <td colspan="3" background="<%=path %>/images/wbg.gif" class='title'><span>人个日程编辑</span></td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                标题：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="text" value="<%=request.getAttribute("title")%>" name="title" size="20"/>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                内容：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="text" value="<%=request.getAttribute("content")%>" name="content" size="20"/>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                截止时间：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input name="ddl" value="${ddl}" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                提醒时间：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input name="remind" value="<%=request.getAttribute("remind")%>" class="Wdate"  type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                原状态：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <c:if test="${state==0}">
                    </br><p>未到期</p>
                </c:if>
                <c:if test="${state==1}">
                    </br><p>提醒事项</p>
                </c:if>
                <c:if test="${state==2}">
                    </br><p>已执行事项</p>
                </c:if>
                <c:if test="${state==3}">
                    </br><p>过期事项</p>
                </c:if>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                状态修改：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <c:if test="${state==0}">
                    </br><p>不可修改</p>
                </c:if>
                <c:if test="${state==1}">
                    </br><input type="radio" name="review">确认可以执行
                </c:if>
                <c:if test="${state==2}">
                    </br><p>不可修改</p>
                </c:if>
                <c:if test="${state==3}">
                    </br><input type="radio" name="review">已执行
                </c:if>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                &nbsp;
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="hidden" name="user_id" value="${sessionScope.user.id }"/>
                <input type="submit" value="修改"/>
                <input type="button" value="删除" onclick="del()"/>
                <input type="reset" value="返回" onclick="javascript:history.back()"/>&nbsp;
            </td>
        </tr>
    </table>
</form>
<desc:footer/>
</body>
</html>