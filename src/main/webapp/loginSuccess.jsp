<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

      <script type="text/javascript">
          $(function()
          {
              <c:if test="${sessionScope.loginUserNums==0}">
              <c:set var="status" value="注册成功！"></c:set>
              //document.write("<h1 align='center'> 注册成功！</h1>")
              </c:if>
              <c:if test="${sessionScope.loginUserNums>=1}">
              //document.write("<h1 align='center'> 登录成功！</h1>")
              <c:set var="status" value="登录成功！"></c:set>
              </c:if>
          })
      </script>
      <%@taglib prefix="desc" uri="/descTag" %>

  </head>
  
  <body>
  <desc:banner/>
       <script type="text/javascript">
           function tiao()
           {
               <c:if test="${sessionScope.loginUserNums==0}">
               document.write("<h1 align='center'> 注册成功！</h1>")

                   window.location.href="index.jsp";
               </c:if>
               <c:if test="${sessionScope.loginUserNums>=1}">
               // document.write("<h1 align='center'> 登录成功！</h1>")
                   window.location.href="admin/menu.jsp";
                    //window.location.href="<%=path %>/calendar";
               </c:if>
           }
           
           setTimeout(tiao,130)
       </script>
       <br> <br> <br> <br> <br> <br> <br> <br> <br>
       <h1 align="center">${status}</h1>
       <center><img src="<%=path %>/img/loading32.gif">页面跳转中</center>
  <desc:footer/>
  </body>
</html>
