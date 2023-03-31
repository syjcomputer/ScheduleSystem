<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>功能菜单栏</title>
	<%
    	String path = request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
	<c:set var="path" value="<%=basePath %>"></c:set>
	<script type="text/javascript" src="${path}/js/jquery.js" ></script>
 	<link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link rel="stylesheet" type="text/css" href="${path }/js/zeroModal/zeroModal.css" />
 	<style type="text/css">
		body{margin:0;padding:0;overflow-x:hidden;}
		html, body{height:100%;}
		img{border:none;}
		*{font-family:'微软雅黑';font-size:12px;color:#626262;}
		dl,dt,dd{display:block;margin:0;}
		a{text-decoration:none;}
		a:HOVER{text-decoration: none;}
		a:link{text-decoration:none; }
		a:visited{text-decoration:none; }
		a:active{text-decoration:none;}
		
		.container{width:100%;height:100%;margin:auto;}
		
		/*left*/
		.leftsidebar_box{width:205px;height:auto !important;overflow:visible !important;position:fixed;height:100% !important;background-color:#F9F9F9;margin-left: -15px;border-right: 2px solid #E5E5E5;}
		.line{height:2px;width:100%;background-image:url(../images/left/line_bg.png);background-repeat:repeat-x;}
		.leftsidebar_box dl{border-bottom: 1px solid #E5E5E5; border-color: #FCFCFC -moz-use-text-color #E5E5E5;}
		.leftsidebar_box dt{padding-left:40px;padding-right:10px;background-repeat:no-repeat;background-position:10px center;color:#585858;font-size:14px;position:relative;line-height:48px;cursor:pointer;}
		.leftsidebar_box dd{background-color:#FFFFFF;padding-left:40px;}
		.leftsidebar_box dd a{color:#585858;line-height:20px;font-size: 14px;}
		.leftsidebar_box dt img{position:absolute;right:10px;top:20px;}
		.home dt{background-image:url(../images/left/home.png)}
		.system_log dt{background-image:url(../images/left/system.png)}
		.custom dt{background-image:url(../images/left/p1.png)}
		.channel dt{background-image:url(../images/left/p2.png)}
		.app dt{background-image:url(../images/left/paper.png)}
		.cloud dt{background-image:url(../images/left/subject.png)}
		.syetem_management dt{background-image:url(../images/left/plan.png)}
		.source dt{background-image:url(../images/left/clock.png)}
		.statistics dt{background-image:url(../images/left/statistics.png)}
		.leftsidebar_box dl dd:last-child{padding-bottom:10px;}
	</style>

	<script type="text/javascript">
		function relogin(){
			$.post("../exit",function (data){
				event.preventDefault(); // 阻止默认的链接行为
				if(data=="1"){
					window.location.href = event.target.href; // 跳转到链接
				}else {
					alert("注销登录失败！")
				}
			})
		}

	</script>

	<base target="_parent" />
</head>
<body id="bg">
	
	<div class="container">
		<div class="leftsidebar_box">
			<div class="line"></div>
			<dl class="system_log">
				<dt onClick="changeImage()">查询日程<img src="${path }/images/left/select_xl01.png" /></dt>
				<dd class="first_dd"><a href="${path}/admin/queryByMonth.jsp" id="1" class="handle-view">&emsp;按月查询&emsp;&emsp;&emsp;</a></dd>
				<dd><a href="${path}/admin/queryByDate.jsp" id="2" class="handle-view">&emsp;按日查询&emsp;&emsp;&emsp;</a></dd>
				<dd><a href="${path}/admin/queryByState.jsp" id="3" class="handle-view">&emsp;按状态查询&emsp;&emsp;&emsp;</a></dd>
			</dl>
		
			<dl class="custom">
				<dt onClick="changeImage()">用户管理<img src="${path }/images/left/select_xl01.png" /></dt>
				<dd><a href="${path}/admin/updateUserInfo.jsp" id="4" class="handle-view">&emsp;修改用户密码&emsp;&emsp;&emsp;</a></dd>
				<dd><a href="${path}/index.jsp" id="5" class="handle-view" onclick="relogin()">&emsp;用户注销登录&emsp;&emsp;&emsp;</a></dd>
			</dl>
		
		</div>
	</div>
    
    
	<!-- js引入 -->
    <script src="${path }/js/jquery.js"></script>
    <script src="${path }/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${path }/js/bootstrap/dropdown.js"></script>
	<script src="${path }/js/zeroModal/zeroModal.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.dropdown-toggle').dropdown();
        });
        function changeImage(){}
   </script>
   <script type="text/javascript">
	   $(".leftsidebar_box dt").css({"background-color":"#F2F2F2"});
	   $(".leftsidebar_box dt img").attr("src","../images/left/select_xl01.png");
   		$(function() {
   			
   			$(".leftsidebar_box dd").hide();	// 隐藏下拉框
   			$(".leftsidebar_box dt").click(function(){
   				$(".leftsidebar_box dt").css({"background-color":"#F9F9F9"})
   				$(this).css({"background-color": "#E5E5E5"});
   				$(this).parent().find('dd').removeClass("menu_chioce");
   				$(".leftsidebar_box dt img").attr("src","../images/left/select_xl01.png");
   				$(this).parent().find('img').attr("src","../images/left/select_xl.png");
   				$(".menu_chioce").slideUp(); 
   				$(this).parent().find('dd').slideToggle();
   				$(this).parent().find('dd').addClass("menu_chioce");
   			});
   		});
   </script>
</body>
</html>